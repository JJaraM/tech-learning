package com.jjara.gateway;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class CustomWebSecurityConfig {

    private RouteLocator routeLocator;
    private Map<Object, Authentication> concurrentMap = new ConcurrentHashMap<>();

    public CustomWebSecurityConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange().pathMatchers("/**").permitAll().and().addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.REACTOR_CONTEXT)
            .build();
    }


    public AuthenticationWebFilter authenticationWebFilter() {
        return new AuthenticationWebFilter(resolver());
    }

    public ReactiveAuthenticationManagerResolver<ServerWebExchange> resolver() {
        return exchange -> routeLocator.getRoutes()
            .concatMap(route -> Mono.just(route).filterWhen(r -> r.getPredicate().apply(exchange))
            .switchIfEmpty(Mono.error(NotFoundException.create(true, "Not Found")))
            .doOnError(e -> route.getId())
            .onErrorResume(e -> Mono.empty()))
            .next().map(route -> {
                var serverHttpRequest = exchange.getRequest();
                if (serverHttpRequest.getPath().subPath(0).value().startsWith("/customer")) {
                    return customersAuthenticationManager(exchange);
                }
                return employeesAuthenticationManager();
            });
    }

    public ReactiveAuthenticationManager customersAuthenticationManager(ServerWebExchange exchange) {
        return authentication -> Mono.just(getAuthentication(authentication, exchange))
            .switchIfEmpty(Mono.error(new UsernameNotFoundException(authentication.getPrincipal().toString())));
    }

    private Authentication getAuthentication(Authentication authentication, ServerWebExchange exchange) {
        if (!concurrentMap.containsKey(authentication.getCredentials())) {
            var auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            concurrentMap.put(auth.getCredentials(), auth);
        }
        throw new AuthenticationCredentialsNotFoundException("");
    }

    public ReactiveAuthenticationManager employeesAuthenticationManager() {
        return authentication -> employee(authentication)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(authentication
                        .getPrincipal()
                        .toString())))
                .doOnError(e -> e.printStackTrace())
                .map(
                        b -> new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                authentication.getCredentials(),
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                        )
                );
    }

    public Mono<String> employee(Authentication authentication) {
        return Mono.justOrEmpty(authentication
                .getPrincipal()
                .toString()
                .startsWith("employee") ? authentication
                .getPrincipal()
                .toString() : null);
    }

}
