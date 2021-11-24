package com.epam.homelibrary.client;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.wrappers.BookListWrapper;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.common.models.wrappers.BookmarkListWrapper;
import com.epam.homelibrary.common.models.wrappers.UserListWrapper;
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
        User user = response.readEntity(User.class);
        if (user != null) {
            jwtToken = response.getCookies().get("token").getValue();
        }
        System.out.println("jwtToken: " + jwtToken);
        return user;
    }

    public void createUser(User user) {
        Response response = client.target(REST_URI)
                .path("users/addUser")
                .request(MediaType.APPLICATION_JSON)
                .cookie("token", jwtToken)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        System.out.println("createUser response status: " + response.getStatus());
    }

    public void blockUser(String username) {
        Response response = client.target(REST_URI)
                .path("books/add")
                .request(MediaType.APPLICATION_JSON)
                .cookie("token", jwtToken)
                .post(Entity.entity(username, MediaType.TEXT_PLAIN));
        System.out.println("blockUser response status: " + response.getStatus());
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
                .cookie("token", jwtToken)
                .delete();
        System.out.println("removeBook response status: " + response.getStatus());
    }

    public void removeBookByAuthor(String nameOfAuthor) {
        Response response = client.target(REST_URI)
                .path("books/removeBookByAuthor" + nameOfAuthor)
                .request(MediaType.TEXT_PLAIN)
                .cookie("token", jwtToken)
                .delete();
        System.out.println("removeBookByAuthor response status: " + response.getStatus());
    }


    public void addBookmark(Bookmark bookmark) {
        Response response = client.target(REST_URI)
                .path("books/addBookmark")
                .request(MediaType.APPLICATION_JSON)
                .cookie("token", jwtToken)
                .post(Entity.entity(bookmark, MediaType.APPLICATION_JSON));
        System.out.println("addBookmark response status: " + response.getStatus());

    }

    public void removeBookmark(int bookId) {
        Response response = client.target(REST_URI)
                .path("books/removeBookmark" + bookId)
                .request(MediaType.APPLICATION_JSON)
                .cookie("token", jwtToken)
                .delete();
        System.out.println("removeBookmark response status: " + response.getStatus());
    }

    public List<Book> searchBookByName(String bookName) {
        Response response = client.target(REST_URI)
                .path("books/searchBookByName" + bookName)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }

    public List<Book> searchBookByAuthor(String authorName) {
        Response response = client.target(REST_URI)
                .path("books/searchBookByAuthor" + authorName)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }

    public List<Book> searchBookByISBN(long ISBN) {
        Response response = client.target(REST_URI)
                .path("books/searchBookByISBN" + ISBN)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }

    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        Response response = client.target(REST_URI)
                .path("books/searchBookInRangeOfYears" + yearFrom + "/" + yearTo)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }

    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        Response response = client.target(REST_URI)
                .path("books/searchBookByYearPagesName" + name + "/" + year + "/" + pages)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }

    public List<Book> searchBookWithBookmarks(int visitorId) {
        Response response = client.target(REST_URI)
                .path("books/searchBookWithBookmarks" + visitorId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }


    public List<Book> getListOfBooksFromDB() {
        Response response = client.target(REST_URI)
                .path("books/get-books")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookListWrapper.class)
                .getList();
    }


    public List<Bookmark> getListOfBookmarksFromDB() {
        Response response = client.target(REST_URI)
                .path("books/get-bookmarks")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response
                .readEntity(BookmarkListWrapper.class)
                .getList();
    }

    public List<User> getListOfUserFromDB() {
        Response response = client.target(REST_URI)
                .path("users/get-users")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response.readEntity(UserListWrapper.class).getList();
    }

    public List<String> getUserLogHistory() {
        Response response = client.target(REST_URI)
                .path("users/get-logs")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response.readEntity(List.class);
    }

    public void closeConnection() {
//        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }

}
