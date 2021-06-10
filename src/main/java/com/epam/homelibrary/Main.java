package com.epam.homelibrary;

import com.epam.homelibrary.databaseDAO.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Home library
 *
 * Для задания Домашняя библиотека из Internal Java Lab Design and Architecture создать
 * DAO слой и реализовать работу с H2 DataBase с использованием flyway при помощи Hibernate.
 */
public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);
    static BufferedReader reader;

    public static void main(String[] args) {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            logger.info("Welcome to Home Library!\nPlease login\nLogin: ");
            String login = reader.readLine();
            logger.info("Password: ");
            String password = reader.readLine();

            DBConnector.connect();
            DBConnector.createTable();

            Controller controller = new Controller();
            controller.operate(login, password);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        cleanUp();
    }

    private static void cleanUp() {
        try {
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
