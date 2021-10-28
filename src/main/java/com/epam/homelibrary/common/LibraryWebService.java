package com.epam.homelibrary.common;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface LibraryWebService {
    @WebMethod
    User authenticate();

    @WebMethod
    void createUser(User user);

    @WebMethod
    void blockUser(String username);

    @WebMethod
    void addBook(Book book);

    @WebMethod
    void removeBook(String nameOfBook);

    @WebMethod
    void removeBookByAuthor(String nameOfAuthor);

    @WebMethod
    void addBookmark(Bookmark bookmark);

    @WebMethod
    void removeBookmark(Book book);

    @WebMethod
    List<Book> searchBookByName(String bookName);

    @WebMethod
    List<Book> searchBookByAuthor(String authorName);

    @WebMethod
    List<Book> searchBookByISBN(long ISBN);

    @WebMethod
    List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo);

    @WebMethod
    List<Book> searchBookByYearPagesName(String name, int year, int pages);

    @WebMethod
    List<Book> searchBookWithBookmarks(User user);

    @WebMethod
    List<Book> getListOfBooksFromDB();

    @WebMethod
    List<Bookmark> getListOfBookmarksFromDB();

    @WebMethod
    List<User> getListOfUserFromDB();

    @WebMethod
    List<String> getUserLogHistory();

    void closeConnection();
}
