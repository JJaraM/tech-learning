# 

<p align="center">
    <a href="https://jonathanjaramorales.herokuapp.com">
        <img src="https://github.com/JJaraM/blog-microservice-ui/blob/master/src/main/resources/public/logo-210x.png" height="210">
    </a>
</p>

<p align="center">
    <h2 align="center">
        <a href="https://jonathanjaramorales.herokuapp.com/post/254">Configuration Server FileSystem</a>
    </h2>
    <p align="center">
        <a href="https://jonathanjaramorales.herokuapp.com/category/184"><img src="https://img.shields.io/badge/-spring-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/178"><img src="https://img.shields.io/badge/-spring%5Fboot-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/214"><img src="https://img.shields.io/badge/-spring%5Fcloud-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/215"><img src="https://img.shields.io/badge/-spring%5Fconfiguration%5Fserver-fd6d75.svg"/></a>
    </p>
</p>


This guide will show you how to centralized your configuration and consume it througth a `spring cloud configuration server`, but this implementation is focus on save all files in the server, instead of git as repository, you may want to do this if you want to prevent that any user update the git repository and this could cause issues in the different environments... well this depends of how your company implements the deployments.

# Prerequisites:

* Have an IDE in your computer.
*  JDK 1.8 or later.
* At least 20 minutes to enjoy.

## Jump to code:
If you want to jump the whole explanation and just check the code please use the following command, please remind  that you will need to have installed svn to run the command or if you prefer download the entire code from https://github.com/JJaraM/spring-cloud/  and go to the folder **configuration-server-filesystem** to check the code and run it.

```comment
svn checkout https://github.com/JJaraM/spring-cloud/trunk/configuration-server/configuration-server-filesystem
```

# Getting Start:
First you will need to define if you want to use `gradle` or `maven` to build your project.

## Gradle
Before to start we are going to configure our gradle project, so in that way we can use this to run our application.
```groovy
plugins {
    id 'org.springframework.boot' version '2.4.3'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
    mavenCentral()
    maven { url 'https://repo.spring.io/milestone' }
}

ext {
    set('springCloudVersion', "2020.0.0")
}

dependencies {
    implementation 'org.springframework.cloud:spring-cloud-config-server'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

test {
    useJUnitPlatform()
}
```

```comment
The dependency that we are going to need to download the jars.
The place where we are going to find the jars
```

## Main Class:
 All `java` application starts for the main class,  and all spring boot applications needs to be annotated with `@SpringBootApplication`, and to enable the configuration server needs to be annotated with `@EnableConfigServer`

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigurationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServiceApplication.class, args);
    }
}
```

## Yaml Configuration:
The magic part of the configuration file lives in the `application.yml` or `bootstrap` depends of your application.

```yml
server:
  port: 8080
spring:
  application: configuration-server-filesystem
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          searchLocations:
          <<2>> - /${USER_HOME}/configuration-server-filesystem/server-files

```

```comment
This is the profile that is going to use the application for this type of configuration server you need to use **native** type as required, otherwise you are going to get an error.
In this section you will need to specify the different folders where are located your files, as recommendation is better to keep everything in a single directory so in that way you don't need to add multiple entries there.
```

```gitclone

If you have any question please go to:

https://github.com/JJaraM/spring-cloud/tree/master/configuration-server
```

## Creating a yml file

Inside of the project repository there is a file  called ``application-ws-dev.yml`` in the folder ``server-files`` this will be the configuration file that is going to return the configuration server when some application send a requests. If you want to check the result then execute this request on your browser http://localhost:8080/application-ws/dev. 

If you want to create a new file you can store a new file in this folder (i.e server-files), and needs to follow the next pattern:  application-${name}-#{environment}, and replace the variables:

```comment
${name} = Corresponds with the name of the application, if an external application is going to use this file, this name needs to be consistent with the value that is in the property **spring.application**

${environment} = Corresponds with the environment that is going to request the data, this is important if you are going to keep in the same server and folder multiple properties for multiple environments.
```
