package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.LibraryWebService;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;
import com.epam.homelibrary.server.DAO.LibraryDAO;
import com.epam.homelibrary.server.DAO.UserDAO;
import com.epam.homelibrary.server.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.server.DAO.impl.UserDataBaseDAO;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.epam.homelibrary.common.LibraryWebService")
public class LibraryWebServiceImpl implements LibraryWebService {
    private LibraryDAO libraryDAO = new LibraryDataBaseDAO();
    private UserDAO userDAO = new UserDataBaseDAO();

    @Override
    public User authenticate(String login, String password) {
        return userDAO.authenticate(login, password);
    }

    @Override
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    @Override
    public void blockUser(String username) {
        userDAO.blockUser(username);
    }

    @Override
    public void addBook(Book book) {
        libraryDAO.addBook(book);
    }

    @Override
    public void removeBook(String nameOfBook) {
        libraryDAO.removeBook(nameOfBook);
    }

    @Override
    public void removeBookByAuthor(String nameOfAuthor) {
        libraryDAO.removeBookByAuthor(nameOfAuthor);
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
        libraryDAO.addBookmark(bookmark);
    }

    @Override
    public void removeBookmark(Book book) {
        libraryDAO.removeBookmark(book);
    }

    @Override
    public List<Book> searchBookByName(String bookName) {
        return libraryDAO.searchBookByName(bookName);
    }

    @Override
    public List<Book> searchBookByAuthor(String authorName) {
        return libraryDAO.searchBookByAuthor(authorName);
    }

    @Override
    public List<Book> searchBookByISBN(long ISBN) {
        return libraryDAO.searchBookByISBN(ISBN);
    }

    @Override
    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
    }

    @Override
    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return libraryDAO.searchBookByYearPagesName(name, year, pages);
    }

    @Override
    public List<Book> searchBookWithBookmarks(User user) {
        return libraryDAO.searchBookWithBookmarks(user);
    }

    @Override
    public List<Book> getListOfBooksFromDB() {
        return libraryDAO.getListOfBooksFromDB();
    }

    @Override
    public List<Bookmark> getListOfBookmarksFromDB() {
        return libraryDAO.getListOfBookMarksFromDB();
    }

    @Override
    public List<User> getListOfUserFromDB() {
        return libraryDAO.getListOfUserFromDB();
    }

    @Override
    public void closeConnection() {
        libraryDAO.closeConnection();
    }
}
