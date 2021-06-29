package com.epam.homelibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserAuthenticator {

    public static User authenticate(String login, String password) {
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
}
