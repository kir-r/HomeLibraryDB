package com.epam.homelibrary.server.DAO.impl;

import com.epam.homelibrary.models.Admin;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.client.Main;
import com.epam.homelibrary.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class UserJsonDAO {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();

    public User authenticate(String login, String password) {
        if (login.equalsIgnoreCase("Admin") && password.equals("qwerty")) {
            Admin admin = null;
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\admin.json"))) {
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    stringBuilder.append(s);
                }

                String jsonText = stringBuilder.toString();

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                admin = gson.fromJson(jsonText, Admin.class);

                Main.logger.info("You have logged as admin\n" + admin);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return admin;

        } else if (login.equalsIgnoreCase("User") && password.equals("11111")) {
            User user = null;
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\users.json"))) {
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    stringBuilder.append(s);
                }

                String jsonText = stringBuilder.toString();

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                user = gson.fromJson(jsonText, User.class);

                Main.logger.info("You have logged as user\n" + user);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        } else {
            System.out.println("Oops, login or password is incorrect.\nMake sure that CapsLock is not on by mistake, and try again.\n");
            return null;
        }
    }

    public static void refresh(ArrayList<Book> listOfBooks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\resources\\Library.json"))) {
            String json = new Gson().toJson(listOfBooks);
            bufferedWriter.write(json + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username) throws IOException { //13
        User user = new User();
        user.setName(username);
        user.setBlocked(false);
        listOfUsers.add(user);
        Main.logger.info("New user " + user.getName() + " is created.");
    }

    public void blockUser() { //14
        String newJson;
        try (FileReader reader = new FileReader("src\\main\\resources\\users.json")) {
            Gson gson = new Gson();
            User user = gson.fromJson(reader, User.class);
            user.setBlocked(true);
            newJson = gson.toJson(user);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\resources\\users.json"))) {
                bufferedWriter.write(newJson);
            } catch (Exception e) {
                Main.logger.error(e.getMessage());
            }
        } catch (IOException e) {
            Main.logger.error(e.getMessage());
        }
    }

    public void getUserLogHistory() { //15
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader("src/main/resources/app.log"))) {
            while (bufferedreader.ready()) {
                Main.logger.info(bufferedreader.readLine());
            }
        } catch (IOException e) {
            Main.logger.error(e.getMessage());
        }
    }
}
