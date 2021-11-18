package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/")
public class LibraryController { //RESTful Service

    //Priority annotation

    @Inject
    private LibraryWebServiceImpl libraryWebServiceImpl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("users/authorization")
    public Response authenticate(@HeaderParam("login") String login, @HeaderParam("password") String password) {
        User user = libraryWebServiceImpl.authenticate(login, password);
        if (user != null) {
            return Response
                    .status(Response.Status.OK)
                    //jwt token add to cookie
                    .entity(user)
                    .build();
        } else return Response.status(401).build();

        //all except auth - annotate @Logged
    }

    public Response createUser(User user) {
        return null;
    }

    public Response blockUser(String username) {
        return null;
    }

    @POST
    @Path("books/add")
    public Response addBook(Book book) {
        libraryWebServiceImpl.addBook(book);
        return Response
                .status(Response.Status.OK)
                .entity(book)
                .build();
    }

    @DELETE
    @Path("books/remove{nameOfBook}")
    public Response removeBook(@PathParam("nameOfBook") String nameOfBook) {
        System.out.println("nameOfBook to remove: " + nameOfBook);
        libraryWebServiceImpl.removeBook(nameOfBook);
        return Response.status(200).build();
    }

    public Response removeBookByAuthor(String nameOfAuthor) {
        return null;
    }

    public Response addBookmark(Bookmark bookmark) {
        return null;
    }


    public Response removeBookmark(Book book) {
        return null;
    }

    public Response searchBookByName(String bookName) {
        libraryWebServiceImpl.searchBookByName(bookName);
        return null;
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
