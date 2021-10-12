package com.epam.homelibrary.endpoint;

import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.LibraryAPI;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/UserDataBaseDAO", new UserDataBaseDAO());
        Endpoint.publish("http://localhost:9999/ws/LibraryDataBaseDAO", new LibraryDataBaseDAO());
    }
}
