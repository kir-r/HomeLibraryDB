package com.epam.homelibrary.databaseDAO;

import com.epam.homelibrary.Main;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnector {
    static Properties properties = new Properties();
    static String URL;
    static String USER;
    static String PASSWORD;
    static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void readProperties() {
        try (FileReader fileReader = new FileReader("src\\main\\resources\\configuration.properties")) {
            properties.load(fileReader);
            URL = properties.getProperty("URL");
            USER = properties.getProperty("USER");
            PASSWORD = properties.getProperty("PASSWORD");
        } catch (IOException e) {
            Main.logger.error(e.getMessage());
        }
    }
    public static void connect() {  //connect with H2 without hibernate?
        readProperties();
        Driver driver = new org.h2.Driver();
        try {
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            Main.logger.error(e.getMessage());
        }
    }

    public static void createTable() {
        Statement statement;
        try {
            statement = connection.createStatement();

            String books = "CREATE TABLE Books (\n" +
                    "    id integer PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name varchar(255),\n" +
                    "    author varchar(255),\n" +
                    "    year integer,\n" +
                    "    ISBN long,\n" +
                    "    pages integer\n" +
                    ");";

            statement.execute(books);

            String bookmarks = "CREATE TABLE Bookmarks (\n" +
                    "    id integer PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    page integer,\n" +
                    "    book_id integer,\n" +
                    "    FOREIGN KEY(book_id) REFERENCES Books(id)\n" +
                    ");";


            statement.execute(bookmarks);

        } catch (SQLException e) {
            Main.logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            Main.logger.error(e.getMessage());
        }
    }


}
