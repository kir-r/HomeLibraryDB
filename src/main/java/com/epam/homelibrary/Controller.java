package com.epam.homelibrary;

import com.epam.homelibrary.DAO.impl.UserDataBaseDAO;
import com.epam.homelibrary.DAO.impl.UserJsonDAO;
import com.epam.homelibrary.DAO.impl.LibraryDataBaseDAO;
import com.epam.homelibrary.models.Admin;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.models.User;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.List;

public class Controller {
    User user;
    String command;
    Book book;
    private String bookName;
    private String login;
    private String password;
    private List<Book> listOfBooksFromDB;
    private List<Bookmark> listOfBookmarksFromDB;
    private List<User> listOfUsersFromDB;
    static BufferedReader reader;
    protected LibraryDataBaseDAO libraryDataBaseDAO;
    protected UserDataBaseDAO userDataBaseDAO;
    protected UserJsonDAO userJsonDAO;

    public Controller() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        libraryDataBaseDAO = new LibraryDataBaseDAO();
        userDataBaseDAO = new UserDataBaseDAO();
        userJsonDAO = new UserJsonDAO();
    }

    public void operate() {
        while (user == null) {
            try {
                Main.logger.info("Welcome to Home Library!\nPlease login\nLogin: ");
                login = reader.readLine();
                Main.logger.info("Password: ");
                password = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            user = UserAuthenticator.authenticate(login, password);
        }
        if (!user.blocked()) {
            userOperates();
        } else {
            Main.logger.info("Sorry, you have been banned");
            System.exit(0);
        }
    }

    private void userOperates() {
        Admin.getListOfUsers().add(user);
        Main.logger.info("Please type some of these commands:\n" +
                "1 to add a new book\n" +
                "2 to remove a book\n" +
                "3 to remove a book by author\n" +
                "4 to add a list of books\n" +
                "5 to add a bookmark\n" +
                "6 to remove a bookmark\n" +
                "7 to search a book by name\n" +
                "8 to search a book by author\n" +
                "9 to search a book by ISBN\n" +
                "10 to search a book in range of years\n" +
                "11 to search a book by years pages name\n" +
                "12 to search a book with bookmarks\n" +
                "13 to to create a user\n" +
                "14 to block a user\n" +
                "15 to get user log history\n" +
                "type \"print books\" to see books in data base,\n" +
                "type \"print bookmarks\" to see books in data base,\n" +
                "or type \"exit\" to leave app");

        try {
            while (true) {
                command = reader.readLine();
                switch (command) {
                    case ("1"):
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
                        libraryDataBaseDAO.addBook(book);
                        break;
                    case ("2"):
                        Main.logger.info("Type name of book you want to remove");
                        bookName = reader.readLine();
                        libraryDataBaseDAO.removeBook(bookName);
                        break;
                    case ("3"):
                        Main.logger.info("Type name of author whose books you want to remove");
                        String nameOfAuthor = reader.readLine();
                        libraryDataBaseDAO.removeBookByAuthor(nameOfAuthor);
                        break;

                        //TODO numbers, 4

                    case ("5"):
                        Bookmark bookmark = new Bookmark();
                        bookmark.setVisitor(user);
                        Main.logger.info("Type a name of a page");
                        bookmark.setPage(Integer.parseInt(reader.readLine()));
                        Main.logger.info("Type a name of a book to add a bookmark");
                        bookName = reader.readLine();
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByName(bookName);
                        bookmark.setBook(listOfBooksFromDB.get(0));

                        libraryDataBaseDAO.addBookmark(bookmark);
                        break;
                    case ("6"):
                        Main.logger.info("Type a name of a book to remove a bookmark");
                        bookName = reader.readLine();
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByName(bookName);
                        if (!listOfBooksFromDB.isEmpty()) {
                            libraryDataBaseDAO.removeBookmark(listOfBooksFromDB.get(0));
                        } else {
                            Main.logger.info("We don't have this book");
                        }
                        break;
                    case ("7"):
                        Main.logger.info("Type a name of a book");
                        bookName = reader.readLine();
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByName(bookName);
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("We don't have this book");
                        }
                        break;
                    case ("8"):
                        Main.logger.info("Type a name of an author");
                        String authorName = reader.readLine();
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByAuthor(authorName);
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("We don't have books by this author");
                        }
                        break;
                    case ("9"):
                        Main.logger.info("Type an ISBN");
                        long ISBN = Long.parseLong(reader.readLine());
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByISBN(ISBN);
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("We don't have books by this author");
                        }
                        break;
                    case ("10"):
                        Main.logger.info("Type a year from");
                        int yearFrom = Integer.parseInt(reader.readLine());
                        Main.logger.info("Type a year to");
                        int yearTo = Integer.parseInt(reader.readLine());
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookInRangeOfYears(yearFrom, yearTo);
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("We don't have books in this range of years");
                        }
                        break;
                    case ("11"):
                        Main.logger.info("Type name of a book");
                        bookName = reader.readLine();
                        Main.logger.info("Type a year");
                        int year = Integer.parseInt(reader.readLine());
                        Main.logger.info("Type amount of pages");
                        int pages = Integer.parseInt(reader.readLine());
                        listOfBooksFromDB = libraryDataBaseDAO.searchBookByYearPagesName(bookName, year, pages);
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("We don't have books with this parameters");
                        }
                        break;
                    case ("12"):
                        List<Book> listOfBookWithBookmarks = libraryDataBaseDAO.searchBookWithBookmarks();
                        Main.logger.info(listOfBookWithBookmarks);
                        break;
                    case ("13"):
                        if (user instanceof Admin) {
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

                            user.setBlocked(false);
                            System.out.println(user);
                            userDataBaseDAO.createUser(user);
//                          userJsonDAO.createUser(username);
                        } else {
                            Main.logger.info("Sorry, you don't have admin rights");
                        }
                        break;
                    case ("14"):
                        if (user instanceof Admin) {
                            Main.logger.info("Type a name of user you want to block: ");
                            String username = reader.readLine();
                            userDataBaseDAO.blockUser(username);
//                            userJsonDAO.blockUser();
                        } else {
                            Main.logger.info("Sorry, you don't have admin rights");
                        }
                        break;
                    case ("15"):
                        if (user instanceof Admin) {
                            userDataBaseDAO.getUserLogHistory();
//                            userJsonDAO.getUserLogHistory();
                        } else {
                            Main.logger.info("Sorry, you don't have admin rights");
                        }
                        break;
                        case ("print books"):
                        listOfBooksFromDB = libraryDataBaseDAO.getListOfBooksFromDB();
                        if (!listOfBooksFromDB.isEmpty()) {
                            for (Book book : listOfBooksFromDB) {
                                System.out.println(book);
                            }
                        } else {
                            Main.logger.info("Database is empty");
                        }
                        break;
                    case ("print bookmarks"):
                        listOfBookmarksFromDB = libraryDataBaseDAO.getListOfBookMarksFromDB();
                        if (!listOfBookmarksFromDB.isEmpty()) {
                            for (Bookmark bm : listOfBookmarksFromDB ) {
                                System.out.println(bm);
                            }
                        } else {
                            Main.logger.info("Database is empty");
                        }
                        break;
                    case ("print users"):
                        listOfUsersFromDB = libraryDataBaseDAO.getListOfUserFromDB();
                        if (!listOfUsersFromDB.isEmpty()) {
                            for (User user : listOfUsersFromDB) {
                                System.out.println(user);
                            }
                        } else {
                            Main.logger.info("Database is empty");
                        }
                        break;
                    case ("exit"):
                        reader.close();
                        libraryDataBaseDAO.closeConnection();
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
