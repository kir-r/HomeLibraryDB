package com.epam.homelibrary.client;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class

RESTConnectionService {
    private static final String REST_URI = "http://localhost:9999/library/";

    private Client client = ClientBuilder.newClient();

    public User authenticate(String login, String password) {
        Response response = client.target(REST_URI)
                .path("users/authorization")
                .request(MediaType.APPLICATION_JSON)
                .header("login", login)
                .header("password", password)
                .get();
        System.out.println("response.hasEntity(): " + response.hasEntity());
        return response.readEntity(User.class);
    }

    public void createUser(User user) {

    }


    public void blockUser(String username) {

    }


    public void addBook(Book book) {

    }


    public void removeBook(String nameOfBook) {

    }


    public void removeBookByAuthor(String nameOfAuthor) {

    }


    public void addBookmark(Bookmark bookmark) {

    }


    public void removeBookmark(Book book) {

    }


    public List<Book> searchBookByName(String bookName) {
        return null;
    }


    public List<Book> searchBookByAuthor(String authorName) {
        return null;
    }


    public List<Book> searchBookByISBN(long ISBN) {
        return null;
    }


    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return null;
    }


    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return null;
    }


    public List<Book> searchBookWithBookmarks(User user) {
        return null;
    }


    public List<Book> getListOfBooksFromDB() {
        return null;
    }


    public List<Bookmark> getListOfBookmarksFromDB() {
        return null;
    }


    public List<User> getListOfUserFromDB() {
        return null;
    }


    public List<String> getUserLogHistory() {
        return null;
    }


    public void closeConnection() {
//        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }

}
