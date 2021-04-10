package com.jjara.webflux.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class IndexRouter {

    @Value("${instance.id}")
    private String instanceId;

    @Bean
    public RouterFunction<ServerResponse> htmlRouter() {
        return route(GET("/message"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue("Instance Id " + instanceId));
    }
}