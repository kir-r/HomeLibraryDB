package com.epam.homelibrary.DAO;

import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;

import java.util.List;

public interface LibraryDAO {

    void addBook(Book book);

    void removeBook(String nameOfBook);

    void removeBookByAuthor(String nameOfAuthor);

    void addBookmark(Bookmark bookmark);

    void removeBookmark(Book book);

    List<Book> searchBookByName(String bookName);

    List<Book> searchBookByAuthor(String authorName);

    List<Book> searchBookByISBN(long ISBN);

    List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo);

    List<Book> searchBookByYearPagesName(String name, int year, int pages);

    List<Book> searchBookWithBookmarks(User user);

    List<Book> getListOfBooksFromDB();

    List<Bookmark> getListOfBookMarksFromDB();

    List<User> getListOfUserFromDB();

    void closeConnection();
}
