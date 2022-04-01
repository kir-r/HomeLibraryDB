package com.epam.homelibrary.client.config;

import com.epam.homelibrary.client.LibraryAPI;
import com.epam.homelibrary.client.RESTConnectionService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.epam.homelibrary")
//@PropertySource()
public class MainConfig {

    @Bean
    @DependsOn("restConnectionService")
//    @DependsOn({"restConnectionService"})
    public LibraryAPI libraryAPI(RESTConnectionService restConnectionService) {
        return new LibraryAPI(restConnectionService);
    }

    @Bean
    @DependsOn("restTemplate")
    public RESTConnectionService restConnectionService(RestTemplate restTemplate) {
        return new RESTConnectionService(restTemplate);
    }

    @Bean
    @DependsOn("restTemplateBuilder")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }
}
