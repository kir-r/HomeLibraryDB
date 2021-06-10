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
    public static void connect() {
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

            String homeLibrary = "CREATE TABLE Books (\n" +
                    "    Id integer PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    Name varchar(255),\n" +
                    "    Author varchar(255),\n" +
                    "    Year integer,\n" +
                    "    ISBN long,\n" +
                    "    Pages integer\n" +
                    ");";

            statement.execute(homeLibrary);

            /*statement = connection.createStatement();

            String values = "INSERT INTO CATALOGUE VALUES (111, 222, 'catalogue')";

            statement.execute(values);

            statement = connection.createStatement();
            String resultSQL = "SELECT * FROM Books";
            ResultSet resultSet = statement.executeQuery(resultSQL);

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + ", "
                        + resultSet.getInt("Pages")
                        + ", " + resultSet.getString("Name"));
            }*/

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
