package com.jjara.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;

@Controller
public class TestController {

    @Value("${spring.profiles.active}") private String profile;

    @PostConstruct
    public void postConstruct() {
        System.out.println(profile);
    }
}
