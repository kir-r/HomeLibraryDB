package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.BookListWrapper;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.server.TokenManager.TokenManager;
import com.epam.homelibrary.server.filter.AuthenticationFilter;
import com.epam.homelibrary.server.filter.Logged;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
public class LibraryController { //RESTful Service

    private final TokenManager tokenManager = new TokenManager();
    private final AuthenticationFilter authenticationFilter = new AuthenticationFilter();

    //Priority annotation

    @Inject
    private LibraryWebServiceImpl libraryWebServiceImpl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("users/authorization")
    public Response authenticate(@HeaderParam("login") String login, @HeaderParam("password") String password) {
        User user = libraryWebServiceImpl.authenticate(login, password);
        if (user != null) {
            String token = tokenManager.encodeToken(login);
            return Response
                    .status(Response.Status.OK)
                    //jwt token add to cookie
                    .cookie(new NewCookie("token", token))
                    .entity(user)
                    .build();
        } else return Response.status(401).build();
    }

    public Response createUser(User user) {
        return null;
    }

    public Response blockUser(String username) {
        return null;
    }


    @POST
    @Path("books/add")
    @Logged
    public Response addBook(Book book) {
        libraryWebServiceImpl.addBook(book);
        return Response
                .status(Response.Status.OK)
                .entity(book)
                .build();
    }

    @DELETE
    @Path("books/remove{nameOfBook}")
    @Logged
    public Response removeBook(@PathParam("nameOfBook") String nameOfBook) {
        System.out.println("nameOfBook to remove: " + nameOfBook);
        libraryWebServiceImpl.removeBook(nameOfBook);
        return Response.status(200).build();
    }

    @DELETE
    @Path("books/remove{nameOfAuthor}")
    @Logged
    public Response removeBookByAuthor(String nameOfAuthor) {
        libraryWebServiceImpl.removeBookByAuthor(nameOfAuthor);
        return Response.status(200).build();
    }

    @POST
    @Path("books/addBookmark")
    @Logged
    public Response addBookmark(Bookmark bookmark) {
        libraryWebServiceImpl.addBookmark(bookmark);
        return Response.status(200).build();
    }

    @DELETE
    @Path("books/remove")
    @Logged
    public Response removeBookmark(Book book) {
        libraryWebServiceImpl.removeBookmark(book);
        return Response.status(200).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/search{bookName}")
    @Logged
    public Response searchBookByName(@PathParam("bookName") String bookName) {
        System.out.println(bookName);
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByName(bookName);
        System.out.println(listOfBooks);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
            return Response
                    .status(Response.Status.OK)
                    .entity(bookListWrapper)
                    .build();
    }

    public Response searchBookByAuthor(String authorName) {
        libraryWebServiceImpl.searchBookByAuthor(authorName);
        return null;
    }

    public Response searchBookByISBN(long ISBN) {
        libraryWebServiceImpl.searchBookByISBN(ISBN);
        return null;
    }

    public Response searchBookInRangeOfYears(int yearFrom, int yearTo) {
        libraryWebServiceImpl.searchBookInRangeOfYears(yearFrom, yearTo);
        return null;
    }

    public Response searchBookByYearPagesName(String name, int year, int pages) {
        libraryWebServiceImpl.searchBookByYearPagesName(name, year, pages);
        return null;
    }

    public Response searchBookWithBookmarks(User user) {
        libraryWebServiceImpl.searchBookWithBookmarks(user);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/get-books")
    public Response getListOfBooksFromDB() {
        List<Book> listOfBooks = libraryWebServiceImpl.getListOfBooksFromDB();
        if (listOfBooks != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(listOfBooks)
                    .build();
        } else return Response.status(401).build();
    }

    public Response getListOfBookmarksFromDB() {
        libraryWebServiceImpl.getListOfBookmarksFromDB();
        return null;
    }

    public Response getListOfUserFromDB() {
        libraryWebServiceImpl.getListOfUserFromDB();
        return null;
    }

    public Response getUserLogHistory() {
//        return HistoryManager.read();
        return null;
    }

    public void closeConnection() {
//        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }
}
