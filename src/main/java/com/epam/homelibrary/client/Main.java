package com.epam.homelibrary.client;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Main {
    public static final Logger logger = LogManager.getLogger("Main");

    public static void main(String[] args) {
        BasicConfigurator.configure();
        LibraryAPI libraryAPI = new LibraryAPI();
        libraryAPI.operate();
    }
}
