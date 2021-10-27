package com.epam.homelibrary.client;

import com.epam.homelibrary.common.LibraryWebService;
import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;

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

public class SOAPConnectionService {
    private static final String WS_URL = "http://localhost:9999/ws/LibraryWebService?wsdl";
    private static final String nameSpaceURI = "http://controller.server.homelibrary.epam.com/";
    private LibraryWebService libraryWebService;

    public SOAPConnectionService() {
        try {
            URL url = new URL(WS_URL);
            QName qname = new QName(nameSpaceURI, "LibraryWebServiceImplService");
            Service proxiesForEndpoint = Service.create(url, qname);
            libraryWebService = proxiesForEndpoint.getPort(LibraryWebService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    User authenticate(String login, String password) {
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

    List<String> getUserLogHistory() {
        return libraryWebService.getUserLogHistory();
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
