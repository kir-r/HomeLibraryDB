package com.epam.homelibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class JSONLibraryRefresher {
    public static void refresh(ArrayList<Book> listOfBooks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\resources\\Library.json"))) {
            String json = new Gson().toJson(listOfBooks);
            bufferedWriter.write(json + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
