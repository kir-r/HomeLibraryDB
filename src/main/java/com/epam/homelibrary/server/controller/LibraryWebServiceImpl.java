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

    }

    @Override
    public void blockUser(String username) {

    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void removeBook(String nameOfBook) {

    }

    @Override
    public void removeBookByAuthor(String nameOfAuthor) {

    }

    @Override
    public void addBookmark(Bookmark bookmark) {

    }

    @Override
    public void removeBookmark(Book book) {

    }

    @Override
    public List<Book> searchBookByName(String bookName) {
        return null;
    }

    @Override
    public List<Book> searchBookByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Book> searchBookByISBN(long ISBN) {
        return null;
    }

    @Override
    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return null;
    }

    @Override
    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return null;
    }

    @Override
    public List<Book> searchBookWithBookmarks(User user) {
        return null;
    }

    @Override
    public List<Book> getListOfBooksFromDB() {
        return null;
    }

    @Override
    public List<Bookmark> getListOfBookMarksFromDB() {
        return null;
    }

    @Override
    public List<User> getListOfUserFromDB() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
//    methods UserDAO + LibraryDAO
}
