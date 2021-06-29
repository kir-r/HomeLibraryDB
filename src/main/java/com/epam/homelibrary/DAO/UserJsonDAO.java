package com.epam.homelibrary.DAO;

import com.epam.homelibrary.Book;
import com.epam.homelibrary.Main;
import com.epam.homelibrary.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class UserJsonDAO {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();

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
