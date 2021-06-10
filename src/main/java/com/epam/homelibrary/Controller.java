package com.epam.homelibrary;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Controller {
    Admin admin;
    User user;
    AbstractUser visitor;
    String command;
    Book book;

    public final void operate(String login, String password) {
        visitor = UserAuthenticator.authenticate(login, password);
        if (visitor instanceof Admin) {
            admin = (Admin) visitor;
            adminOperates();
        } else if (visitor instanceof User) {
            user = (User) visitor;
            if (!user.isBlocked()) {
                userOperates();
            }
            else Main.logger.info("Sorry, you have been banned");
        }
    }

    private void adminOperates() {
        Main.logger.info("Please type some of these commands:\n" + "1 to create a user\n"
                + "2 to block a user\n" + "3 to add a new book\n" + "4 to remove a book\n"
                + "5 to remove a book by author\n" + "6 to add a list of books\n"
                + "7 to add a bookmark\n" + "8 to remove a bookmark\n" + "9 to search a book by name\n"
                + "10 to search a book by author\n" + "11 to search a book by ISBN\n"
                + "12 to search a book in range of years\n" + "13 to search a book by years pages name\n"
                + "14 to search a book with bookmarks\n" + "15 to get user log history\n");
        try {
            while (!(command = Main.reader.readLine()).equalsIgnoreCase("exit")) {
                switch (command) {
                    case ("1"):
                        admin.createUser();
                        break;
                    case ("2"):
                        admin.blockUser();
                        break;
                    case ("3"):
                        admin.addBook("resource\\homeLibrary.json");
                        break;
                    case ("4"):
                        admin.removeBook("resource\\homeLibrary.json");
                        break;
                    case ("5"):
                        admin.removeBookByAuthor("resource\\homeLibrary.json");
                        break;
                    case ("6"):
                        admin.addListOfBooks();
                        break;
                    case ("7"):
                        admin.addBookmark();
                        break;
                    case ("8"):
                        admin.removeBookmark();
                        break;
                    case ("9"):
                        admin.searchBookByName();
                        break;
                    case ("10"):
                        admin.searchBookByAuthor();
                        break;
                    case ("11"):
                        admin.searchBookByISBN();
                        break;
                    case ("12"):
                        admin.searchBookInRangeOfYears();
                        break;
                    case ("13"):
                        admin.searchBookByYearPagesName();
                        break;
                    case ("14"):
                        admin.searchBookWithBookmarks();
                        break;
                    case ("15"):
                        admin.getUserLogHistory();
                        break;
                }
            }
        } catch (IOException | NoSuchBookException e) {
            e.printStackTrace();
        }
    }

    private void userOperates() {
        Admin.getListOfUsers().add(user);
        Main.logger.info("Please type some of these commands:\n" + "1 to add a new book\n" + "2 to remove a book\n"
                + "3 to remove a book by author\n" + "4 to add a list of books\n"
                + "5 to add a bookmark\n" + "6 to remove a bookmark\n" + "7 to search a book by name\n"
                + "8 to search a book by author\n" + "9 to search a book by ISBN\n"
                + "10 to search a book in range of years\n" + "11 to search a book by years pages name\n"
                + "12 to search a book with bookmarks\n");
        try {
            while (!(command = Main.reader.readLine()).equalsIgnoreCase("exit")) {
                switch (command) {
                    case ("1"):
                        user.addBook("resource\\homeLibrary.json");
                        break;
                    case ("2"):
                        user.removeBook("resource\\homeLibrary.json");
                        break;
                    case ("3"):
                        user.removeBookByAuthor("resource\\homeLibrary.json");
                        break;
                    case ("4"):
                        user.addListOfBooks();
                        break;
                    case ("5"):
                        user.addBookmark();
                        break;
                    case ("6"):
                        user.removeBookmark();
                        break;
                    case ("7"):
                        user.searchBookByName();
                        break;
                    case ("8"):
                        user.searchBookByAuthor();
                        break;
                    case ("9"):
                        user.searchBookByISBN();
                        break;
                    case ("10"):
                        user.searchBookInRangeOfYears();
                        break;
                    case ("11"):
                        user.searchBookByYearPagesName();
                        break;
                    case ("12"):
                        user.searchBookWithBookmarks();
                        break;
                }
            }
        } catch (IOException | NoSuchBookException e) {
            e.printStackTrace();
        }
    }
}
