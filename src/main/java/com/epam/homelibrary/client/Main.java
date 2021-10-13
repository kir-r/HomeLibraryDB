package com.epam.homelibrary.client;

import com.epam.homelibrary.server.DAO.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        LibraryAPI libraryAPI = new LibraryAPI();
        libraryAPI.operate();
    }
}
