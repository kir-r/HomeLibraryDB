package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.wrappers.BookListWrapper;
import com.epam.homelibrary.common.models.wrappers.BookmarkListWrapper;
import com.epam.homelibrary.server.DAO.HistoryManager;
//import com.epam.homelibrary.server.filter.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController { //RESTful Service

    //    @Inject
    private LibraryWebServiceImpl libraryWebServiceImpl;

    @Autowired
    public LibraryController(LibraryWebServiceImpl libraryWebServiceImpl) {
        this.libraryWebServiceImpl = libraryWebServiceImpl;
    }

//    @POST //TODO annotations according to restcontroller
//    @Path("/add")
//    @Logged
//    public Response addBook(Book book) {
//        libraryWebServiceImpl.addBook(book);
//        return Response
//                .status(Response.Status.OK)
//                .entity(book)
//                .build();
//    }
//
//    @DELETE
//    @Path("/remove/{nameOfBook}")
//    @Logged
//    public Response removeBook(@PathParam("nameOfBook") String nameOfBook) {
//        System.out.println("nameOfBook to remove: " + nameOfBook);
//        libraryWebServiceImpl.removeBook(nameOfBook);
//        return Response.status(200).build();
//    }
//
//    @DELETE
//    @Path("/removeBookByAuthor/{nameOfAuthor}")
//    @Logged
//    public Response removeBookByAuthor(String nameOfAuthor) {
//        libraryWebServiceImpl.removeBookByAuthor(nameOfAuthor);
//        return Response.status(200).build();
//    }
//
//    @POST
//    @Path("/addBookmark")
//    @Logged
//    public Response addBookmark(Bookmark bookmark) {
//        libraryWebServiceImpl.addBookmark(bookmark);
//        return Response.status(200).build();
//    }
//
//    @DELETE
//    @Path("/removeBookmark/{bookId}")
//    @Logged
//    public Response removeBookmark(@PathParam("bookId") int bookId) {
//        libraryWebServiceImpl.removeBookmark(bookId);
//        return Response.status(200).build();
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookByName/{bookName}")
//    @Logged
//    public Response searchBookByName(@PathParam("bookName") String bookName) {
//        System.out.println(bookName);
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByName(bookName);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookByAuthor/{authorName}")
//    @Logged
//    public Response searchBookByAuthor(@PathParam("authorName") String authorName) {
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByAuthor(authorName);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookByISBN/{ISBN}")
//    @Logged
//    public Response searchBookByISBN(@PathParam("ISBN") long ISBN) {
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByISBN(ISBN);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookInRangeOfYears/{yearFrom}/{yearTo}")
//    @Logged
//    public Response searchBookInRangeOfYears(@PathParam("yearFrom") int yearFrom, @PathParam("yearTo") int yearTo) {
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookInRangeOfYears(yearFrom, yearTo);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookByYearPagesName/{name}/{year}/{pages}")
//    @Logged
//    public Response searchBookByYearPagesName(@PathParam("name") String name,
//                                              @PathParam("year") int year,
//                                              @PathParam("pages") int pages) {
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookByYearPagesName(name, year, pages);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/searchBookWithBookmarks/{visitorId}")
//    public Response searchBookWithBookmarks(@PathParam("visitorId") int visitorId) {
//        List<Book> listOfBooks = libraryWebServiceImpl.searchBookWithBookmarks(visitorId);
//        if (listOfBooks.size() != 0) {
//            BookListWrapper bookListWrapper = new BookListWrapper();
//            bookListWrapper.setList(listOfBooks);
//            System.out.println("searchBookWithBookmarks listOfBooks " + listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }

    //    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/get-books")
    @GetMapping(value = "/get-books")
//    @Logged
    public ResponseEntity<List<Book>> getListOfBooksFromDB() {
        List<Book> listOfBooks = libraryWebServiceImpl.getListOfBooksFromDB();
        if (listOfBooks.size() != 0) {
            BookListWrapper bookListWrapper = new BookListWrapper();
            bookListWrapper.setList(listOfBooks);
            return ResponseEntity.ok(listOfBooks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookListWrapper)
//                    .build();
        } else {
            System.out.println("getListOfBooksFromDB produces nothing");
            return null;
//            return Response.status(404).build();
        }
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/get-bookmarks")
//    public Response getListOfBookmarksFromDB() {
//        List<Bookmark> listOfBookmarks = libraryWebServiceImpl.getListOfBookmarksFromDB();
//        if (listOfBookmarks.size() != 0) {
//            BookmarkListWrapper bookmarkListWrapper = new BookmarkListWrapper();
//            bookmarkListWrapper.setList(listOfBookmarks);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(bookmarkListWrapper)
//                    .build();
//        } else {
//            return Response.status(404).build();
//        }
//    }

    public void closeConnection() {
        HistoryManager.cleanUp();
//        libraryDAO.closeConnection();
    }
}
