package com.epam.homelibrary.client;

import com.epam.homelibrary.client.exception.BookNotFoundException;
import com.epam.homelibrary.client.exception.BookmarkNotFoundException;
import com.epam.homelibrary.client.exception.UnauthorizedUserException;
import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.wrappers.BookListWrapper;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.common.models.wrappers.BookmarkListWrapper;
import com.epam.homelibrary.common.models.wrappers.UserListWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import jakarta.ws.rs.client.Client;
//import jakarta.ws.rs.client.ClientBuilder;
//import jakarta.ws.rs.client.Entity;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.springframework.http.ResponseEntity;

import java.util.List;

//@Service
@Component
public class RESTConnectionService {
    private static final String REST_URI = "http://localhost:9999/library/";
    private final static String AUTHENTICATE_URI = REST_URI + "/authorization";
    private String jwtToken;
    private User user;

    private RestTemplate restTemplate;


    @Autowired
    public RESTConnectionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //    private Client client = ClientBuilder.newClient();

    public User authenticate(String login, String password) throws UnauthorizedUserException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("login", login);
        httpHeaders.add("password", password);

            ResponseEntity<User> response = restTemplate
                    .exchange(AUTHENTICATE_URI, HttpMethod.GET,
                            new HttpEntity<String>(httpHeaders), User.class);





//        Response response = client.target(REST_URI)
//                .path("users/authorization")
//                .request(MediaType.APPLICATION_JSON)
//                .header("login", login)
//                .header("password", password)
//                .get();
//        if (response.getStatus() == 401) {
//            throw new UnauthorizedUserException("User is not authorized");
//        }


        User user = response.getBody();
//        if (user != null) {
//            jwtToken = response.getCookies().get("token").getValue();
//        }
//        System.out.println("jwtToken: " + jwtToken);
        return user;
    }

//    public void createUser(User user) {
//        Response response = client.target(REST_URI)
//                .path("users/addUser+")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
//        System.out.println("createUser response status: " + response.getStatus());
//    }
//
//    public void blockUser(String username) {
//        Response response = client.target(REST_URI)
//                .path("books/add")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .post(Entity.entity(username, MediaType.TEXT_PLAIN));
//        System.out.println("blockUser response status: " + response.getStatus());
//    }
//
//    public void addBook(Book book) {
//        Response response = client.target(REST_URI)
//                .path("books/add")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
//        System.out.println("addBook response status: " + response.getStatus());
//    }
//
//    public void removeBook(String nameOfBook) {
//        Response response = client.target(REST_URI)
//                .path("books/remove/" + nameOfBook)
//                .request(MediaType.TEXT_PLAIN)
//                .cookie("token", jwtToken)
//                .delete();
//        System.out.println("removeBook response status: " + response.getStatus());
//    }
//
//    public void removeBookByAuthor(String nameOfAuthor) {
//        Response response = client.target(REST_URI)
//                .path("books/removeBookByAuthor/" + nameOfAuthor)
//                .request(MediaType.TEXT_PLAIN)
//                .cookie("token", jwtToken)
//                .delete();
//        System.out.println("removeBookByAuthor response status: " + response.getStatus());
//    }
//
//
//    public void addBookmark(Bookmark bookmark) {
//        Response response = client.target(REST_URI)
//                .path("books/addBookmark")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .post(Entity.entity(bookmark, MediaType.APPLICATION_JSON));
//        System.out.println("addBookmark response status: " + response.getStatus());
//
//    }
//
//    public void removeBookmark(int bookId) {
//        Response response = client.target(REST_URI)
//                .path("books/removeBookmark/" + bookId)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .delete();
//        System.out.println("removeBookmark response status: " + response.getStatus());
//    }
//
//    public List<Book> searchBookByName(String bookName) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookByName/" + bookName)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//    public List<Book> searchBookByAuthor(String authorName) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookByAuthor/" + authorName)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//    public List<Book> searchBookByISBN(long ISBN) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookByISBN/" + ISBN)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookInRangeOfYears/" + yearFrom + "/" + yearTo)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//    public List<Book> searchBookByYearPagesName(String name, int year, int pages) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookByYearPagesName/" + name + "/" + year + "/" + pages)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//    public List<Book> searchBookWithBookmarks(int visitorId) throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/searchBookWithBookmarks/" + visitorId)
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have this book");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//
//    public List<Book> getListOfBooksFromDB() throws BookNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/get-books")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookNotFoundException("We don't have books");
//        }
//        return response
//                .readEntity(BookListWrapper.class)
//                .getList();
//    }
//
//
//    public List<Bookmark> getListOfBookmarksFromDB() throws BookmarkNotFoundException {
//        Response response = client.target(REST_URI)
//                .path("books/get-bookmarks")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        if (response.getStatus() == 404) {
//            throw new BookmarkNotFoundException("We don't have bookmarks");
//        }
//        return response
//                .readEntity(BookmarkListWrapper.class)
//                .getList();
//    }
//
//    public List<User> getListOfUserFromDB() {
//        Response response = client.target(REST_URI)
//                .path("users/get-users")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        return response.readEntity(UserListWrapper.class).getList();
//    }
//
//    public List<String> getUserLogHistory() {
//        Response response = client.target(REST_URI)
//                .path("users/get-logs")
//                .request(MediaType.APPLICATION_JSON)
//                .cookie("token", jwtToken)
//                .get();
//        return response.readEntity(List.class);
//    }

    public void closeConnection() {
//        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }

}

