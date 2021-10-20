package com.epam.homelibrary.client;

import com.epam.homelibrary.server.DAO.LibraryDAO;
import com.epam.homelibrary.server.DAO.UserDAO;
import com.epam.homelibrary.server.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.server.DAO.impl.UserJsonDAO;
import com.epam.homelibrary.server.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Admin;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.util.List;

public class LibraryAPI {
    User user;
    private LibraryDAO libraryDAO;
    private UserDAO userDAO;
    private UserJsonDAO userJsonDAO;
    private BufferedReader reader;
    private Facade facade;

    public LibraryAPI() throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
//        libraryDAO = new LibraryDataBaseDAO();
//        userDAO = new UserDataBaseDAO();
//        UserJsonDAO userJsonDAO = new UserJsonDAO();
        facade = new Facade();
//        !!connect to publisher
    }

    public void operate() {
        try {
            while (user == null) {
                Main.logger.info("Welcome to Home Library!\nPlease login\nLogin: ");
                String login = reader.readLine();
                Main.logger.info("Password: ");
                String password = reader.readLine();
                user = facade.authenticate(login, password);
//              user = userDAO.authenticate(login, password);
//              user = userJsonDAO.authenticate(login, password);
                if (user == null) {
                    System.out.println("Oops, login or password is incorrect.\nMake sure that CapsLock is not on by mistake, and try again.\n");
                } else if (!user.blocked()) {
                    userOperates();
                } else {
                    Main.logger.info("Sorry, you have been banned");
                    libraryDAO.closeConnection();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void userOperates() {
        Admin.getListOfUsers().add(user);
        StringBuilder commands = new StringBuilder("Please type some of these commands:\n" +
                "1 to add a new book\n" +
                "2 to remove a book\n" +
                "3 to remove a book by author\n" +
                "4 to add a bookmark\n" +
                "5 to remove a bookmark\n" +
                "6 to search a book by name\n" +
                "7 to search a book by author\n" +
                "8 to search a book by ISBN\n" +
                "9 to search a book in range of years\n" +
                "10 to search a book by years pages name\n" +
                "11 to search a book with bookmarks\n");
        if (user.isAdmin()) {
            commands.append("12 to create a user\n" +
                    "13 to block a user\n" +
                    "14 to get user log history\n");
        }
        commands.append("type \"print books\" to see books in data base,\n" +
                "type \"print bookmarks\" to see books in data base,\n" +
                "or type \"exit\" to leave app");
        Main.logger.info(commands.toString());

        try {
            while (true) {
                String command = reader.readLine();
                switch (command) {
                    case ("1"):
                        addBook();
                        break;
                    case ("2"):
                        removeBook();
                        break;
                    case ("3"):
                        removeBookByAuthor();
                        break;
                    case ("4"):
                        addBookmark();
                        break;
                    case ("5"):
                        removeBookmark();
                        break;
                    case ("6"):
                        searchBookByName();
                        break;
                    case ("7"):
                        searchBookByAuthor();
                        break;
                    case ("8"):
                        searchBookByISBN();
                        break;
                    case ("9"):
                        searchBookInRangeOfYears();
                        break;
                    case ("10"):
                        searchBookByYearsPagesName();
                        break;
                    case ("11"):
                        searchBookWithBookmarks();
                        break;
                    case ("12"):
                        createUser();
                        break;
                    case ("13"):
                        blockUser();
                        break;
                    case ("14"):
                        getUserLogHistory();
                        break;
                    case ("print books"):
                        printBooks();
                        break;
                    case ("print bookmarks"):
                        printBookmarks();
                        break;
                    case ("print users"):
                        printUsers();
                        break;
                    case ("exit"):
                        reader.close();
                        facade.closeConnection();
//                        libraryDAO.closeConnection();
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        try {
            Book book;
            book = new Book();
            Main.logger.info("Set name of a book");
            book.setName(reader.readLine());
            Main.logger.info("Set author");
            book.setAuthor(reader.readLine());
            Main.logger.info("Set year");
            book.setYear(Integer.parseInt(reader.readLine()));
            Main.logger.info("Set ISBN");
            book.setISBN(Long.parseLong(reader.readLine()));
            Main.logger.info("Set number of pages");
            book.setPages(Integer.parseInt(reader.readLine()));
            Main.logger.info("New book " + book.toString() + "is created.");
//            libraryDAO.addBook(book);
            facade.addBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBook() {
        try {
            Main.logger.info("Type name of book you want to remove");
            String bookName = reader.readLine();
//            libraryDAO.removeBook(bookName);
            facade.removeBook(bookName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBookByAuthor() {
        try {
            Main.logger.info("Type name of author whose books you want to remove");
            String nameOfAuthor = reader.readLine();
//            libraryDAO.removeBookByAuthor(nameOfAuthor);
            facade.removeBookByAuthor(nameOfAuthor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBookmark() {
        try {
            List<Book> listOfBooksFromDB;
            Bookmark bookmark = new Bookmark();
            bookmark.setVisitor(user);
            Main.logger.info("Type a number of a page");
            bookmark.setPage(Integer.parseInt(reader.readLine()));
            Main.logger.info("Type a name of a book to add a bookmark");
            String bookName = reader.readLine();
//            listOfBooksFromDB = libraryDAO.searchBookByName(bookName);
            listOfBooksFromDB = facade.searchBookByName(bookName);
            bookmark.setBook(listOfBooksFromDB.get(0));
//            libraryDAO.addBookmark(bookmark);
            facade.addBookmark(bookmark);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBookmark() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of a book to remove a bookmark");
            String bookName = reader.readLine();
//            listOfBooksFromDB = libraryDAO.searchBookByName(bookName);
            listOfBooksFromDB = facade.searchBookByName(bookName);
            if (!listOfBooksFromDB.isEmpty()) {
//                libraryDAO.removeBookmark(listOfBooksFromDB.get(0));
                facade.removeBookmark(listOfBooksFromDB.get(0));
            } else {
                Main.logger.info("We don't have this book");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByName() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of a book");
            String bookName = reader.readLine();
//            listOfBooksFromDB = libraryDAO.searchBookByName(bookName);
            listOfBooksFromDB = facade.searchBookByName(bookName);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have this book");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByAuthor() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of an author");
            String authorName = reader.readLine();
//            listOfBooksFromDB = libraryDAO.searchBookByAuthor(authorName);
            listOfBooksFromDB = facade.searchBookByAuthor(authorName);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books by this author");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByISBN() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type an ISBN");
            long ISBN = Long.parseLong(reader.readLine());
//            listOfBooksFromDB = libraryDAO.searchBookByISBN(ISBN);
            listOfBooksFromDB = facade.searchBookByISBN(ISBN);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books by this author");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookInRangeOfYears() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a year from");
            int yearFrom = Integer.parseInt(reader.readLine());
            Main.logger.info("Type a year to");
            int yearTo = Integer.parseInt(reader.readLine());
            if (yearFrom <= yearTo) {
//                listOfBooksFromDB = libraryDAO.searchBookInRangeOfYears(yearFrom, yearTo);
                listOfBooksFromDB = facade.searchBookInRangeOfYears(yearFrom, yearTo);
                if (!listOfBooksFromDB.isEmpty()) {
                    for (Book book : listOfBooksFromDB) {
                        System.out.println(book);
                    }
                } else {
                    Main.logger.info("We don't have books in this range of years");
                }
            } else {
                Main.logger.info("Range of years seems to be incorrect");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByYearsPagesName() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type name of a book");
            String bookName = reader.readLine();
            Main.logger.info("Type a year");
            int year = Integer.parseInt(reader.readLine());
            Main.logger.info("Type amount of pages");
            int pages = Integer.parseInt(reader.readLine());
//            listOfBooksFromDB = libraryDAO.searchBookByYearPagesName(bookName, year, pages);
            listOfBooksFromDB = facade.searchBookByYearPagesName(bookName, year, pages);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books with this parameters");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBookWithBookmarks() {
//        List<Book> listOfBookWithBookmarks = libraryDAO.searchBookWithBookmarks(user);
        List<Book> listOfBookWithBookmarks = facade.searchBookWithBookmarks(user);
        Main.logger.info(listOfBookWithBookmarks);
    }

    private void createUser() {
        try {
            if (user.isAdmin()) {
                User user = new User();
                Main.logger.info("Set a username: ");
                String username = reader.readLine();
                Main.logger.info("Set a login: ");
                String login = reader.readLine();
                Main.logger.info("Set a password: ");
                String password = reader.readLine();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(username);
                user.setAdmin(false);
                user.setBlocked(false);
                System.out.println(user);
                facade.createUser(user);
//                userDAO.createUser(user);
//              userJsonDAO.createUser(username);
            } else {
                Main.logger.info("Sorry, you don't have admin rights");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void blockUser() {
        try {
            if (user.isAdmin()) {
                Main.logger.info("Type a name of user you want to block: ");
                String username = reader.readLine();
//              userDAO.blockUser(username);
                facade.blockUser(username);
//              userJsonDAO.blockUser();
            } else {
                Main.logger.info("Sorry, you don't have admin rights");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserLogHistory() {
        if (user.isAdmin()) {
            facade.getUserLogHistory();
//          userDAO.getUserLogHistory();
//          userJsonDAO.getUserLogHistory();
        } else {
            Main.logger.info("Sorry, you don't have admin rights");
        }
    }

    private void printBooks() {
        List<Book> listOfBooksFromDB;
        listOfBooksFromDB = facade.getListOfBooksFromDB();
//        listOfBooksFromDB = libraryDAO.getListOfBooksFromDB();
        if (!listOfBooksFromDB.isEmpty()) {
            for (Book book : listOfBooksFromDB) {
                System.out.println(book);
            }
        } else {
            Main.logger.info("Database is empty");
        }
    }

    private void printBookmarks() {
        List<Bookmark> listOfBookmarksFromDB = facade.getListOfBookmarksFromDB();
//        List<Bookmark> listOfBookmarksFromDB = libraryDAO.getListOfBookMarksFromDB();
        if (!listOfBookmarksFromDB.isEmpty()) {
            for (Bookmark bm : listOfBookmarksFromDB) {
                System.out.println(bm);
            }
        } else {
            Main.logger.info("Database is empty");
        }
    }

    private void printUsers() {
        List<User> listOfUsersFromDB = facade.getListOfUserFromDB();
//        List<User> listOfUsersFromDB = libraryDAO.getListOfUserFromDB();
        if (!listOfUsersFromDB.isEmpty()) {
            for (User user : listOfUsersFromDB) {
                System.out.println(user);
            }
        } else {
            Main.logger.info("Database is empty");
        }
    }
}
