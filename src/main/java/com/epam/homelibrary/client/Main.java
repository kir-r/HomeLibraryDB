package com.epam.homelibrary.client;

import com.epam.homelibrary.client.config.MainConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static final Logger logger = LogManager.getLogger("Main");
    private LibraryAPI libraryAPI;
    private RESTConnectionService restConnectionService;

    public static void main(String[] args) {
//        BasicConfigurator.configure();

        Main main = new Main();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

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
