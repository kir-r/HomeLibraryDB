package com.epam.homelibrary.server.endpoint;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableWebMvc
//@Configuration
@ComponentScan("com.epam.homelibrary")
//@EnableJpaRepositories("com.epam.homelibrary")
@SpringBootApplication
public class Publisher {
    public static final Logger logger = LogManager.getLogger("Publisher");
    public static void main(String[] args) {
        SpringApplication.run(Publisher.class, args);



//spring data - application xml for h2

//https://www.baeldung.com/bootstraping-a-web-application-with-spring-and-java-based-configuration

// параллельная реализация: откатиться к состоянию до введения спринга и начать с
// апдейта контроллера(спринг рест), а потом вернуться к бд.

        /*ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(AutoScanFeature.class); //?
        resourceConfig.register(AuthenticationFilter.class);
        resourceConfig.register(LibraryController.class);
        resourceConfig.register(UserController.class);
        HttpServer server = GrizzlyHttpServerFactory
                .createHttpServer(URI
                        .create("http://localhost:9999/library/"), resourceConfig);*/
    }
}
