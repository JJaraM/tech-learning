package com.jjara.gateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class DiscoveryHandler {

    @Lazy private final EurekaClient eurekaClient;

    public DiscoveryHandler(@Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping
    public Mono<List<String>> instances() {
        return Mono.just(
                eurekaClient.getApplications().getRegisteredApplications()
                .stream().map(application -> application.getName()).collect(Collectors.toList())
        );
    }
}
