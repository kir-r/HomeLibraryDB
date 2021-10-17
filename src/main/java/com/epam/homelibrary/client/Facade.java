package com.epam.homelibrary.client;

import com.epam.homelibrary.common.LibraryWebService;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;
import com.epam.homelibrary.server.DAO.LibraryDAO;
import com.epam.homelibrary.server.DAO.UserDAO;
import com.epam.homelibrary.server.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.server.DAO.impl.UserDataBaseDAO;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class Facade {
    private URL url;
    private QName qname;
    private Service service;
    private LibraryWebService libraryWebService;

    public Facade() throws Exception {
        url = new URL("http://localhost:9999/ws/LibraryWebService?wsdl");
        qname = new QName("http://controller.server.homelibrary.epam.com/", "LibraryWebServiceImplService");
        service = Service.create(url, qname);
        libraryWebService = service.getPort(LibraryWebService.class);
    }

//    methods UserDAO + LibraryDAO
    //post request

    User authenticate(String login, String password) {
        return libraryWebService.authenticate(login, password);
    }

    void createUser(User user) {
    }

    void blockUser(String username) {
    }

    void addBook(Book book) {
        libraryWebService.addBook(book);
    }

    void removeBook(String nameOfBook) {
    }

    void removeBookByAuthor(String nameOfAuthor) {
    }

    void addBookmark(Bookmark bookmark) {
    }

    void removeBookmark(Book book) {
    }

    List<Book> searchBookByName(String bookName) {
        return null;
    }

    List<Book> searchBookByAuthor(String authorName) {
        return null;
    }

    List<Book> searchBookByISBN(long ISBN) {
        return null;
    }

    List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return null;
    }

    List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return null;
    }

    List<Book> searchBookWithBookmarks(User user) {
        return null;
    }

    List<Book> getListOfBooksFromDB() {
        System.out.println("facadelist:    " + libraryWebService.getListOfBooksFromDB());
        return libraryWebService.getListOfBooksFromDB();
    }

    List<Bookmark> getListOfBookMarksFromDB() {
        return libraryWebService.getListOfBookMarksFromDB();
    }

    List<User> getListOfUserFromDB() {
        return libraryWebService.getListOfUserFromDB();
    }

    void closeConnection() {
        libraryWebService.closeConnection();
    }
}
