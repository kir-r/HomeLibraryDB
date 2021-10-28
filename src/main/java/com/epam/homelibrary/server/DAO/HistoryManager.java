package com.epam.homelibrary.server.DAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryManager {
    static BufferedReader fileReader;
    static BufferedWriter fileWriter;
    static SimpleDateFormat simpleDateFormat;
    private static String login;

    static {
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/userHistory.txt"));
            fileWriter = new BufferedWriter(new FileWriter("src/main/resources/userHistory.txt", true));
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss  ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void authenticate(String userLogin) {
        try {
            login = userLogin;
            fileWriter.write(simpleDateFormat.format(new Date()) + login + " has logged in\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String message) {
        try {
            fileWriter.write(simpleDateFormat.format(new Date()) + login + ": " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> read() {
        List<String> historyList = new ArrayList<>();
        try {
            String line;
            while ((line = fileReader.readLine()) != null) {
                historyList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    public static void cleanUp() {
        try {
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
