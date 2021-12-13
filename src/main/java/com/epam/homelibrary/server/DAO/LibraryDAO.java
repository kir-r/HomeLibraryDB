package com.epam.homelibrary.server.DAO;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;


import java.util.List;


public interface LibraryDAO {

    void addBook(Book book);

    void removeBook(String nameOfBook);

    void removeBookByAuthor(String nameOfAuthor);

    void addBookmark(Bookmark bookmark);

    void removeBookmark(int bookId);

    List<Book> searchBookByName(String bookName);

    List<Book> searchBookByAuthor(String authorName);

    List<Book> searchBookByISBN(long ISBN);

    List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo);

    List<Book> searchBookByYearPagesName(String name, int year, int pages);

    List<Book> searchBookWithBookmarks(int id);

    List<Book> getListOfBooksFromDB();

    List<Bookmark> getListOfBookMarksFromDB();

    List<User> getListOfUserFromDB();

    void closeConnection();
}
