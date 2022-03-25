package com.epam.homelibrary.server.controller;

//import com.epam.homelibrary.common.LibraryWebService;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.server.DAO.HistoryManager;
import com.epam.homelibrary.server.DAO.LibraryDAO;
import com.epam.homelibrary.server.DAO.UserDAO;
import com.epam.homelibrary.server.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.server.DAO.impl.UserDataBaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

//@WebService(endpointInterface = "com.epam.homelibrary.common.LibraryWebService")

//@Contract
@Service
public class LibraryWebServiceImpl {
    private LibraryDAO libraryDAO = new LibraryDataBaseDAO();

//    @Autowired
//    private LibraryDAO libraryDAO;
    private UserDAO userDAO = new UserDataBaseDAO();

//    @Autowired
//    public LibraryWebServiceImpl(LibraryDAO libraryDAO) {
//        this.libraryDAO = libraryDAO;
//    }

    public User authenticate(String login, String password) {
        HistoryManager.authenticate(login);
        return userDAO.authenticate(login, password);
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void blockUser(String username) {
        userDAO.blockUser(username);
    }

    public void addBook(Book book) {
        libraryDAO.addBook(book);
    }

    public void removeBook(String nameOfBook) {
        libraryDAO.removeBook(nameOfBook);
    }

    public void removeBookByAuthor(String nameOfAuthor) {
        libraryDAO.removeBookByAuthor(nameOfAuthor);
    }

    public void addBookmark(Bookmark bookmark) {
        libraryDAO.addBookmark(bookmark);
    }

    public void removeBookmark(int bookId) {
        libraryDAO.removeBookmark(bookId);
    }

    public List<Book> searchBookByName(String bookName) {
        return libraryDAO.searchBookByName(bookName);
    }

    public List<Book> searchBookByAuthor(String authorName) {
        return libraryDAO.searchBookByAuthor(authorName);
    }

    public List<Book> searchBookByISBN(long ISBN) {
        return libraryDAO.searchBookByISBN(ISBN);
    }

    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
        return libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
    }

    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
        return libraryDAO.searchBookByYearPagesName(name, year, pages);
    }

    public List<Book> searchBookWithBookmarks(int id) {
        return libraryDAO.searchBookWithBookmarks(id);
    }

//    @Transactional
    public List<Book> getListOfBooksFromDB() {
        return libraryDAO.getListOfBooksFromDB(); //TODO correct?
//        return libraryDAO.findAll();
    }

    public List<Bookmark> getListOfBookmarksFromDB() {
        return libraryDAO.getListOfBookMarksFromDB();
    }

    public List<User> getListOfUserFromDB() {
        return libraryDAO.getListOfUserFromDB();
    }

    public List<String> getUserLogHistory() {
        return HistoryManager.read();
    }

    public void closeConnection() {
        HistoryManager.cleanUp();
        libraryDAO.closeConnection();
    }
}
