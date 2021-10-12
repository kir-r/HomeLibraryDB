package com.epam.homelibrary;

import com.epam.homelibrary.DAO.LibraryDAO;
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

        URL url = new URL("http://localhost:9999/ws/UserDataBaseDAO?wsdl");
        QName qname = new QName("http://ws.mkyong.com/", "UserDataBaseDAO");

        Service service = Service.create(url, qname);

        LibraryDAO libraryDAO = service.getPort(LibraryDAO.class);

        System.out.println(libraryDAO.getListOfBooksFromDB());

    }
}
