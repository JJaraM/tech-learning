<p align="center">
    <a href="https://jonathanjaramorales.herokuapp.com">
        <img src="https://github.com/JJaraM/blog-microservice-ui/blob/master/src/main/resources/public/logo-210x.png" height="210">
    </a>
</p>

<p align="center">
    <h2 align="center">
        <a href="https://jonathanjaramorales.herokuapp.com">Tech Learning</a>
    </h2>
    <p align="center">
        <a href="https://jonathanjaramorales.herokuapp.com/category/184"><img src="https://img.shields.io/badge/-spring-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/178"><img src="https://img.shields.io/badge/-spring%5Fboot-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/214"><img src="https://img.shields.io/badge/-spring%5Fcloud-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/215"><img src="https://img.shields.io/badge/-spring%5Fconfiguration%5Fserver-fd6d75.svg"/></a>
    </p>
</p>

# Table of Contents
1. [Getting Started](#getting-started)
2. [Spring Projects](#spring-projects)
    1. [Gateway](#gateway)
4. [Third Example](#third-example)
5. [Fourth Example](#fourth-examplehttpwwwfourthexamplecom)

# Getting Started
The following repository shows multiple application types and each application is a specific branch, if you want to check the solution please go to the corresponding branch

# Spring Projects
The following list of projects are spring implementations that resolved specific issues.

## Gateway
The spring gateway solution will help us to redirect the request that we received from the clients, so in this way we can provide only one single entry point to multiple solutions.

This approach has some advantages:
* Allow to have one entry point for multiple web services.
* Gives the possibility to run the load balance logic into this service.
* Gives the possibility to stablish the security part in one single place.

### [Gateway + Eureka](https://github.com/JJaraM/tech-learning/tree/spring/cloud/gateway/gateway-eureka-server)
The following implementation use the spring cloud gateway with a eureka integration to get the cluster members from eureka.
