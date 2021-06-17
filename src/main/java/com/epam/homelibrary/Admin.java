package com.epam.homelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Admin extends User {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();


    public static ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public void createUser(String username) throws IOException {
        User user = new User();
        user.setName(username);
        user.setBlocked(false);
        listOfUsers.add(user);
        Main.logger.info("New user " + user.getName() + " is created.");
    }

    public void blockUser() {
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

    @Override
    public boolean isAdmin(String name) {
        return true;
    }

    public void getUserLogHistory() {
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader("src/main/resources/app.log"))) {
            while (bufferedreader.ready()) {
                Main.logger.info(bufferedreader.readLine());
            }
        } catch (IOException e) {
            Main.logger.error(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "admin " + name;
    }
}
