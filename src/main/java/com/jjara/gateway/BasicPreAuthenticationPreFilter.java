package com.jjara.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class BasicPreAuthenticationPreFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(String routeId, Consumer consumer) {
        return (exchange, chain) -> {
            return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
        };
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
        };
    }
}
