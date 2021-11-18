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

public class RESTConnectionService {
    private static final String REST_URI = "http://localhost:9999/library/";
    private String jwtToken;

    private Client client = ClientBuilder.newClient();

    public User authenticate(String login, String password) {
        Response response = client.target(REST_URI)
                .path("users/authorization")
                .request(MediaType.APPLICATION_JSON)
                .header("login", login)
                .header("password", password)
                .get();
        System.out.println("authenticate response status: " + response.getStatus());
        response.getCookies().get("token").getValue();
        System.out.println("jwtToken: " + jwtToken);
        return response.readEntity(User.class);
    }

    public void createUser(User user) {

    }


    public void blockUser(String username) {

    }


    public void addBook(Book book) {
        Response response = client.target(REST_URI)
                .path("books/add")
                .request(MediaType.APPLICATION_JSON)
                .cookie("token", jwtToken)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
        System.out.println("addBook response status: " + response.getStatus());
    }


    public void removeBook(String nameOfBook) {
        Response response = client.target(REST_URI)
                .path("books/remove" + nameOfBook)
                .request(MediaType.TEXT_PLAIN)
                .delete();
        System.out.println("removeBook response status: " + response.getStatus());
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


    public String getListOfBooksFromDB() {
        Response response = client.target(REST_URI)
                .path("books/get-books")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response.readEntity(String.class);
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
