package com.epam.homelibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Home library
 * <p>
 * Для задания Домашняя библиотека из Internal Java Lab Design and Architecture создать
 * DAO слой и реализовать работу с H2 DataBase с использованием flyway при помощи Hibernate.
 */
public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LibraryAPI libraryAPI = new LibraryAPI();
        libraryAPI.operate();
    }
}
