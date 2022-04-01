package com.epam.homelibrary.client;

import com.epam.homelibrary.server.endpoint.Publisher;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public class Main {
    public static final Logger logger = LogManager.getLogger("Main");
    private LibraryAPI libraryAPI;
    private RESTConnectionService restConnectionService;

    public static void main(String[] args) {
//        BasicConfigurator.configure();

        Main main = new Main();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        main.libraryAPI = applicationContext.getBean(LibraryAPI.class);
        main.restConnectionService = applicationContext.getBean(RESTConnectionService.class);
        main.run();


//        LibraryAPI libraryAPI = new LibraryAPI();
//        libraryAPI.operate();
    }

    private void run() {
        libraryAPI.operate();
    }


}
