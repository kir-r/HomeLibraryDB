package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.wrappers.BookListWrapper;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.common.models.wrappers.BookmarkListWrapper;
import com.epam.homelibrary.common.models.wrappers.UserListWrapper;
import com.epam.homelibrary.server.DAO.HistoryManager;
import com.epam.homelibrary.server.DAO.LibraryDAO;
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
//    @Inject
//    private LibraryDAO libraryDAO;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("users/authorization")
    public Response authenticate(@HeaderParam("login") String login, @HeaderParam("password") String password) {
        System.out.println(login + " " + password);
        User user = libraryWebServiceImpl.authenticate(login, password);
        System.out.println(user);
        if (user != null) {
            String token = tokenManager.encodeToken(login);
            return Response
                    .status(Response.Status.OK)
                    .cookie(new NewCookie("token", token))
                    .entity(user)
                    .build();
        } else return Response.status(401).build();
    }

    @POST
    @Path("users/addUser")
    @Logged
    public Response createUser(User user) {
        libraryWebServiceImpl.createUser(user);
        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @POST
    @Path("users/blockUser{username}")
    @Logged
    public Response blockUser(@PathParam("username") String username) {
        libraryWebServiceImpl.blockUser(username);
        return Response
                .status(Response.Status.OK)
                .entity(username)
                .build();
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
    @Path("books/removeBookByAuthor{nameOfAuthor}")
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
    @Path("books/removeBookmark")
    @Logged
    public Response removeBookmark(Book book) {
        libraryWebServiceImpl.removeBookmark(book);
        return Response.status(200).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookByName{bookName}")
    @Logged
    public Response searchBookByName(@PathParam("bookName") String bookName) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByName(bookName);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(bookListWrapper)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookByAuthor{authorName}")
    @Logged
    public Response searchBookByAuthor(@PathParam("authorName") String authorName) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByAuthor(authorName);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(bookListWrapper)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookByISBN{ISBN}")
    @Logged
    public Response searchBookByISBN(@PathParam("ISBN") long ISBN) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByISBN(ISBN);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(bookListWrapper)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookInRangeOfYears{yearFrom}/{yearTo}")
    @Logged
    public Response searchBookInRangeOfYears(@PathParam("yearFrom") int yearFrom, @PathParam("yearTo") int yearTo) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookInRangeOfYears(yearFrom, yearTo);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(bookListWrapper)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookByYearPagesName{name}/{year}/{pages}")
    @Logged
    public Response searchBookByYearPagesName(@PathParam("name") String name,
                                              @PathParam("year") int year,
                                              @PathParam("pages") int pages) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByYearPagesName(name, year, pages);
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(bookListWrapper)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/searchBookWithBookmarks{visitorId}")
    public Response searchBookWithBookmarks(@PathParam ("visitorId") int visitorId) {
        List<Book> listOfBooks = libraryWebServiceImpl.searchBookWithBookmarks(visitorId);;
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
        return Response
                .status(Response.Status.OK)
                .entity(listOfBooks)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/get-books")
    public Response getListOfBooksFromDB() {
        List<Book> listOfBooks = libraryWebServiceImpl.getListOfBooksFromDB();
        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setList(listOfBooks);
            return Response
                    .status(Response.Status.OK)
                    .entity(listOfBooks)
                    .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("books/get-bookmarks")
    public Response getListOfBookmarksFromDB() {
        List<Bookmark> listOfBookmarks = libraryWebServiceImpl.getListOfBookmarksFromDB();
        BookmarkListWrapper bookmarkListWrapper = new BookmarkListWrapper();
        bookmarkListWrapper.setList(listOfBookmarks);
        return Response
                .status(Response.Status.OK)
                .entity(listOfBookmarks)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("users/get-users")
    public Response getListOfUserFromDB() {
        List<User> listOfUsers = libraryWebServiceImpl.getListOfUserFromDB();
        UserListWrapper userListWrapper = new UserListWrapper();
        userListWrapper.setList(listOfUsers);
        return Response
                .status(Response.Status.OK)
                .entity(listOfUsers)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("users/get-logs")
    public Response getUserLogHistory() {
        return Response
                .status(Response.Status.OK)
                .entity(HistoryManager.read())
                .build();
    }

    public void closeConnection() {
        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }
}
