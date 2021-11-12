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

//import org.glassfish.jersey.spi.Contract; //?
import org.jvnet.hk2.annotations.Contract; //?
import org.jvnet.hk2.annotations.Service;

//import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

//@WebService(endpointInterface = "com.epam.homelibrary.common.LibraryWebService")

//@Contract
@Service
public class LibraryWebServiceImpl {
    private LibraryDAO libraryDAO = new LibraryDataBaseDAO();
    private UserDAO userDAO = new UserDataBaseDAO();

//    @Resource
    WebServiceContext webServiceContext;


    public User authenticate(String login, String password) {
//        MessageContext messageContext = webServiceContext.getMessageContext();
//
//        Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
//        List userList = (List) http_headers.get("Username");
//        List passList = (List) http_headers.get("Password");
//
//        String login;
//        String password;
//
//        if (userList != null) {
//            login = userList.get(0).toString();
//        } else return null;
//
//        if (passList != null) {
//            password = passList.get(0).toString();
//        } else return null;

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


    public void removeBookmark(Book book) {
        libraryDAO.removeBookmark(book);
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


    public List<Book> searchBookWithBookmarks(User user) {
        return libraryDAO.searchBookWithBookmarks(user);
    }


    public List<Book> getListOfBooksFromDB() {
        return libraryDAO.getListOfBooksFromDB();
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
