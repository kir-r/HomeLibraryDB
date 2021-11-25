package com.epam.homelibrary.server.endpoint;

import com.epam.homelibrary.server.controller.LibraryController;
import com.epam.homelibrary.server.filter.AuthenticationFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Publisher {
    public static void main(String[] args) {
        ResourceConfig resourceConfig = new ResourceConfig();
//        resourceConfig.register(MultiPartFeature.class); //?
                resourceConfig.register(AutoScanFeature.class); //?
        resourceConfig.register(AuthenticationFilter.class);
        resourceConfig.register(LibraryController.class);
        HttpServer server = GrizzlyHttpServerFactory
                .createHttpServer(URI
                        .create("http://localhost:9999/library/"), resourceConfig);
    }
}
