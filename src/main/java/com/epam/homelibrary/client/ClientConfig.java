package com.epam.homelibrary.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@Configuration
//@PropertySource()
public class ClientConfig {

    @Bean
    @DependsOn({"RESTConnectionService"})
    public LibraryAPI libraryAPI(RESTConnectionService RESTConnectionService) {
        return new LibraryAPI(RESTConnectionService);
    }

    @Bean
    @DependsOn({"restTemplate"})
    public RESTConnectionService clientUserService(RestTemplate restTemplate) {
        return new RESTConnectionService(restTemplate);
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }
}
