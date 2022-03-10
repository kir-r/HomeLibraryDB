package com.epam.homelibrary.server.endpoint;

import com.epam.homelibrary.server.controller.LibraryController;
import com.epam.homelibrary.server.controller.UserController;
import com.epam.homelibrary.server.filter.AuthenticationFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;

@EnableWebMvc
@Configuration
@ComponentScan("com.epam.homelibrary")
@EnableJpaRepositories("com.epam.homelibrary")
public class Publisher {
    public static void main(String[] args) {

        //TODO config add?

        ResourceConfig resourceConfig = new ResourceConfig();
//        resourceConfig.register(MultiPartFeature.class); //?
        resourceConfig.register(AutoScanFeature.class); //?
        resourceConfig.register(AuthenticationFilter.class);
        resourceConfig.register(LibraryController.class);
        resourceConfig.register(UserController.class);
        HttpServer server = GrizzlyHttpServerFactory
                .createHttpServer(URI
                        .create("http://localhost:9999/library/"), resourceConfig);
    }
}
