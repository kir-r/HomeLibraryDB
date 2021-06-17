package com.epam.homelibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Admin admin;
    User user;
    String command;
    Book book;

    public final void operate(String login, String password) {
        user = UserAuthenticator.authenticate(login, password);
        if (user instanceof Admin) {
            admin = (Admin) user;
            adminOperates();
        } else if (user != null) {
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
                        Main.logger.info("Type username: ");
                        String username = Main.reader.readLine();
                        admin.createUser(username);
                        break;
                    case ("2"):
                        admin.blockUser();
                        break;
                    case ("3"):
                        book = new Book();
                        Main.logger.info("Set name of a book");
                        book.setName(Main.reader.readLine());
                        Main.logger.info("Set author");
                        book.setAuthor(Main.reader.readLine());
                        Main.logger.info("Set year");
                        book.setYear(Integer.parseInt(Main.reader.readLine()));
                        Main.logger.info("Set ISBN");
                        book.setISBN(Long.parseLong(Main.reader.readLine()));
                        Main.logger.info("Set number of pages");
                        book.setPages(Integer.parseInt(Main.reader.readLine()));
                        Main.logger.info("New book " + book.toString() + "is created.");
                        admin.addBook("resource\\homeLibrary.json", book);
                        break;
                    case ("4"):
                        Main.logger.info("Type name of book you want to remove");
                        String nameOfBook = Main.reader.readLine();
                        admin.removeBook(nameOfBook);
                        break;
                    case ("5"):
                        Main.logger.info("Type name of author whose books you want to remove");
                        String nameOfAuthor = Main.reader.readLine();
                        admin.removeBookByAuthor(nameOfAuthor);
                        break;
                    case ("6"):
                        Main.logger.info("Type address of books catalog");
                        String addressOfBookCatalog = Main.reader.readLine();
                        admin.addListOfBooks(addressOfBookCatalog);
                        break;
                    case ("7"):
                        admin.addBookmark();
                        break;
                    case ("8"):
//                        admin.removeBookmark();
                        break;
                    case ("9"):
                        Main.logger.info("Type a name of a book");
                        String bookName = Main.reader.readLine();
                        book = admin.searchBookByName(bookName);
                        Main.logger.info(book);
                        break;
                    case ("10"):
                        Main.logger.info("Type a name of an author");
                        String authorName = Main.reader.readLine();
                        List<Book> listBooksByAuthor = admin.searchBookByAuthor(authorName);
                        Main.logger.info(listBooksByAuthor);
                        break;
                    case ("11"):
                        Main.logger.info("Type an ISBN");
                        long ISBN = Long.parseLong(Main.reader.readLine());
                        book = admin.searchBookByISBN(ISBN);
                        Main.logger.info(book);
                        break;
                    case ("12"):
                        Main.logger.info("Type a year from");
                        int yearFrom = Integer.parseInt(Main.reader.readLine());
                        Main.logger.info("Type a year to");
                        int yearTo = Integer.parseInt(Main.reader.readLine());
                        List<Book> listOfBookInRangeOfYears = admin.searchBookInRangeOfYears(yearFrom, yearTo);
                        Main.logger.info(listOfBookInRangeOfYears);
                        break;
                    case ("13"):
                        Main.logger.info("Type name of a book");
                        String name = Main.reader.readLine();
                        Main.logger.info("Type a year");
                        int year = Integer.parseInt(Main.reader.readLine());
                        Main.logger.info("Type amount of pages");
                        int pages = Integer.parseInt(Main.reader.readLine());
                        book = admin.searchBookByYearPagesName(name, year, pages);
                        Main.logger.info(book);
                        break;
                    case ("14"):
                        List<Book> listOfBookWithBookmarks = admin.searchBookWithBookmarks();
                        Main.logger.info(listOfBookWithBookmarks);
                        break;
                    case ("15"):
                        admin.getUserLogHistory();
                        break;
                }
            }
        } catch (IOException e) {
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
                        book = new Book();
                        Main.logger.info("Set name of a book");
                        book.setName(Main.reader.readLine());
                        Main.logger.info("Set author");
                        book.setAuthor(Main.reader.readLine());
                        Main.logger.info("Set year");
                        book.setYear(Integer.parseInt(Main.reader.readLine()));
                        Main.logger.info("Set ISBN");
                        book.setISBN(Long.parseLong(Main.reader.readLine()));
                        Main.logger.info("Set number of pages");
                        book.setPages(Integer.parseInt(Main.reader.readLine()));
                        Main.logger.info("New book " + book.toString() + "is created.");
                        user.addBook("resource\\homeLibrary.json", book);
                        break;
                    case ("2"):
                        Main.logger.info("Type name of book you want to remove");
                        String nameOfBook = Main.reader.readLine();
                        user.removeBook(nameOfBook);
                        break;
                    case ("3"):
                        Main.logger.info("Type name of author whose books you want to remove");
                        String nameOfAuthor = Main.reader.readLine();
                        user.removeBookByAuthor(nameOfAuthor);
                        break;
                    case ("4"):
                        Main.logger.info("Type address of books catalog");
                        String addressOfBookCatalog = Main.reader.readLine();
                        user.addListOfBooks(addressOfBookCatalog);
                        break;
                    case ("5"):
                        user.addBookmark();
                        break;
                    case ("6"):
//                        user.removeBookmark();
                        break;
                    case ("7"):
                        Main.logger.info("Type a name of a book");
                        String bookName = Main.reader.readLine();
                        book = user.searchBookByName(bookName);
                        Main.logger.info(book);
                        break;
                    case ("8"):
                        Main.logger.info("Type a name of an author");
                        String authorName = Main.reader.readLine();
                        List<Book> listBooksByAuthor = user.searchBookByAuthor(authorName);
                        Main.logger.info(listBooksByAuthor);
                        break;
                    case ("9"):
                        Main.logger.info("Type an ISBN");
                        long ISBN = Long.parseLong(Main.reader.readLine());
                        book = user.searchBookByISBN(ISBN);
                        Main.logger.info(book);
                        break;
                    case ("10"):
                        Main.logger.info("Type a year from");
                        int yearFrom = Integer.parseInt(Main.reader.readLine());
                        Main.logger.info("Type a year to");
                        int yearTo = Integer.parseInt(Main.reader.readLine());
                        List<Book> listOfBookInRangeOfYears = user.searchBookInRangeOfYears(yearFrom, yearTo);
                        Main.logger.info(listOfBookInRangeOfYears);
                        break;
                    case ("11"):
                        Main.logger.info("Type name of a book");
                        String name = Main.reader.readLine();
                        Main.logger.info("Type a year");
                        int year = Integer.parseInt(Main.reader.readLine());
                        Main.logger.info("Type amount of pages");
                        int pages = Integer.parseInt(Main.reader.readLine());
                        book = user.searchBookByYearPagesName(name, year, pages);
                        Main.logger.info(book);
                        break;
                    case ("12"):
                        List<Book> listOfBookWithBookmarks = user.searchBookWithBookmarks();
                        Main.logger.info(listOfBookWithBookmarks);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
