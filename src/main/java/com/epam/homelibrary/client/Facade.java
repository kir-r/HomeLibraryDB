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
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
    private static final String WS_URL = "http://localhost:9999/ws/LibraryWebService?wsdl";
    private URL url;
    private QName qname;
    private Service service;
    private LibraryWebService libraryWebService;

    public Facade() throws Exception {
        url = new URL(WS_URL);
        qname = new QName("http://controller.server.homelibrary.epam.com/", "LibraryWebServiceImplService");
        service = Service.create(url, qname);
        libraryWebService = service.getPort(LibraryWebService.class);
    }

    //post request!!!

    User authenticate(String login, String password) throws Exception {
        Map<String, Object> req_ctx = ((BindingProvider) libraryWebService).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList(login));
        headers.put("Password", Collections.singletonList(password));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        return libraryWebService.authenticate();
    }

    void createUser(User user) {
        libraryWebService.createUser(user);
    }

    void blockUser(String username) {
        libraryWebService.blockUser(username);
    }

    void addBook(Book book) {
        libraryWebService.addBook(book);
    }

    void removeBook(String nameOfBook) {
        libraryWebService.removeBook(nameOfBook);
    }

    void removeBookByAuthor(String nameOfAuthor) {
        libraryWebService.removeBookByAuthor(nameOfAuthor);
    }

    void addBookmark(Bookmark bookmark) {
        libraryWebService.addBookmark(bookmark);
    }

    void removeBookmark(Book book) {
        libraryWebService.removeBookmark(book);
    }

    List<Book> searchBookByName(String bookName) {
        return libraryWebService.searchBookByName(bookName);
    }

    List<Book> searchBookByAuthor(String authorName) {
        return libraryWebService.searchBookByAuthor(authorName);
    }

    List<Book> searchBookByISBN(long ISBN) {
        return libraryWebService.searchBookByISBN(ISBN);
    }

    List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return libraryWebService.searchBookInRangeOfYears(yearFrom, yearTo);
    }

    List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return libraryWebService.searchBookByYearPagesName(name, year, pages);
    }

    List<Book> searchBookWithBookmarks(User user) {
        return libraryWebService.searchBookWithBookmarks(user);
    }

    void getUserLogHistory() {
    }

    List<Book> getListOfBooksFromDB() {
        return libraryWebService.getListOfBooksFromDB();
    }

    List<Bookmark> getListOfBookmarksFromDB() {
        return libraryWebService.getListOfBookmarksFromDB();
    }

    List<User> getListOfUserFromDB() {
        return libraryWebService.getListOfUserFromDB();
    }

    void closeConnection() {
        libraryWebService.closeConnection();
    }
}
