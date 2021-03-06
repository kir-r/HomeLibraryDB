package com.epam.homelibrary.client;

import com.epam.homelibrary.client.exception.BookNotFoundException;
import com.epam.homelibrary.client.exception.BookmarkNotFoundException;
import com.epam.homelibrary.client.exception.UnauthorizedUserException;
import com.epam.homelibrary.common.models.Admin;
import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.util.List;

public class LibraryAPI {
    User user;
    private BufferedReader reader;
    //    private SOAPConnectionService SOAPConnectionService;
    private RESTConnectionService RESTConnectionService;

    public LibraryAPI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
//        SOAPConnectionService = new SOAPConnectionService();
        RESTConnectionService = new RESTConnectionService();
    }

    public void operate() {
        try {
            while (user == null) {
                Main.logger.info("Welcome to Home Library!\nPlease login\nLogin: ");
                String login = reader.readLine();
                Main.logger.info("Password: ");
                String password = reader.readLine();
                try {
                    user = RESTConnectionService.authenticate(login, password);
                } catch (UnauthorizedUserException e) {
                    e.printStackTrace();
                }
                if (user == null) {
                    System.out.println("Oops, login or password is incorrect.\nMake sure that CapsLock is not on by mistake, and try again.\n");
                } else if (!user.blocked()) {
                    userOperates();
                } else {
                    Main.logger.info("Sorry, you have been banned");
                    RESTConnectionService.closeConnection();
                }
            }
        } catch (IOException e) {
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
                        RESTConnectionService.closeConnection();
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
            RESTConnectionService.addBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBook() {
        try {
            Main.logger.info("Type name of book you want to remove");
            String bookName = reader.readLine();
            RESTConnectionService.removeBook(bookName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBookByAuthor() {
        try {
            Main.logger.info("Type name of author whose books you want to remove");
            String nameOfAuthor = reader.readLine();
            RESTConnectionService.removeBookByAuthor(nameOfAuthor);
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
            listOfBooksFromDB = RESTConnectionService.searchBookByName(bookName);
            bookmark.setBook(listOfBooksFromDB.get(0));
            RESTConnectionService.addBookmark(bookmark);
        } catch (IOException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Harry Potter Chamber of Secrets
    private void removeBookmark() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of a book to remove a bookmark");
            String bookName = reader.readLine();
            listOfBooksFromDB = RESTConnectionService.searchBookByName(bookName);
            if (!listOfBooksFromDB.isEmpty()) {
                RESTConnectionService.removeBookmark(listOfBooksFromDB.get(0).getId());
            } else {
                Main.logger.info("We don't have this book");
            }
        } catch (IOException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByName() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of a book");
            String bookName = reader.readLine();
            listOfBooksFromDB = RESTConnectionService.searchBookByName(bookName);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have this book");
            }
        } catch (IOException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByAuthor() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type a name of an author");
            String authorName = reader.readLine();
            listOfBooksFromDB = RESTConnectionService.searchBookByAuthor(authorName);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books by this author");
            }
        } catch (IOException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByISBN() {
        try {
            List<Book> listOfBooksFromDB;
            Main.logger.info("Type an ISBN");
            long ISBN = Long.parseLong(reader.readLine());
            listOfBooksFromDB = RESTConnectionService.searchBookByISBN(ISBN);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books with this ISBN");
            }
        } catch (IOException | BookNotFoundException e) {
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
                listOfBooksFromDB = RESTConnectionService.searchBookInRangeOfYears(yearFrom, yearTo);
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
        } catch (IOException | BookNotFoundException e) {
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
            listOfBooksFromDB = RESTConnectionService.searchBookByYearPagesName(bookName, year, pages);
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("We don't have books with this parameters");
            }
        } catch (IOException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchBookWithBookmarks() {
        List<Book> listOfBookWithBookmarks = null;
        try {
            listOfBookWithBookmarks = RESTConnectionService.searchBookWithBookmarks(user.getId());
            if (!listOfBookWithBookmarks.isEmpty()) {
                for (Book book : listOfBookWithBookmarks) {
                    System.out.println(book);
                }
            } else {
                System.out.println("There are no books with bookmarks");
            }
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
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
                RESTConnectionService.createUser(user);
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
                RESTConnectionService.blockUser(username);
            } else {
                Main.logger.info("Sorry, you don't have admin rights");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserLogHistory() {
        if (user.isAdmin()) {
            for (String logString : RESTConnectionService.getUserLogHistory()) {
                System.out.println(logString);
            }
        } else {
            Main.logger.info("Sorry, you don't have admin rights");
        }
    }

    private void printBooks() {
        List<Book> listOfBooksFromDB = null;
        try {
            listOfBooksFromDB = RESTConnectionService.getListOfBooksFromDB();
            if (!listOfBooksFromDB.isEmpty()) {
                for (Book book : listOfBooksFromDB) {
                    System.out.println(book);
                }
            } else {
                Main.logger.info("Database is empty");
            }
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printBookmarks() {
        List<Bookmark> listOfBookmarksFromDB = null;
        try {
            listOfBookmarksFromDB = RESTConnectionService.getListOfBookmarksFromDB();
            if (!listOfBookmarksFromDB.isEmpty()) {
                for (Bookmark bm : listOfBookmarksFromDB) {
                    System.out.println(bm);
                }
            } else {
                Main.logger.info("Database is empty");
            }
        } catch (BookmarkNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printUsers() {
        List<User> listOfUsersFromDB = RESTConnectionService.getListOfUserFromDB();
        if (!listOfUsersFromDB.isEmpty()) {
            for (User user : listOfUsersFromDB) {
                System.out.println(user);
            }
        } else {
            Main.logger.info("Database is empty");
        }
    }
}
