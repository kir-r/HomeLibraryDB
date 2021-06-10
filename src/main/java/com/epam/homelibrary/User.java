package com.epam.homelibrary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User extends AbstractUser {
    private String name;
    private String login;
    private String password;
    private boolean isBlocked;
    private Book book;
    private String nameOfBook;
    private ArrayList<Bookmark> listOfBookmarks = new ArrayList<Bookmark>();
    private ArrayList<Book> listOfBooks = new ArrayList<Book>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean login(String login, String password) {
        if (login.equalsIgnoreCase(this.login) && password.equals(this.password)) {
            return true;
        } else {
            Main.logger.info("Oops, login or password is incorrect.\nMake sure that CapsLock is not on by mistake, and try again.");

            return false;
        }
    }

    @Override
    public boolean isAdmin(String name) {
        return false;
    }

    @Override
    public void addBook(String addressJSON) throws IOException {
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
        listOfBooks.add(book);
        JSONLibraryRefresher.refresh(listOfBooks);
        Main.logger.info("List of books size: " + listOfBooks.size());
        Main.logger.info("New book " + book.toString() + "is created.");
    }

    @Override
    public void removeBook(String addressJSON) throws IOException {
        Main.logger.info("Type name of book you want to remove");
        nameOfBook = Main.reader.readLine();
        Main.logger.info("List of books size: " + listOfBooks.size());
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(book -> book.getName().equals(nameOfBook))
                .findFirst();
        optionalBook.ifPresent(book -> listOfBooks.remove(book));
        if (optionalBook.isPresent()) {
            listOfBooks.remove(optionalBook.get());
            Main.logger.info("Book " + nameOfBook + " is removed.");
        } else {
            Main.logger.info("There is not such a book");
        }
        JSONLibraryRefresher.refresh(listOfBooks);
        Main.logger.info("List of books size: " + listOfBooks.size());
    }

    @Override
    public void removeBookByAuthor(String addressJSON) throws IOException {
        Main.logger.info("List of books before " + listOfBooks);
        Main.logger.info("Type name of author whose books you want to remove");
        String nameOfAuthor = Main.reader.readLine();
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(book -> book.getAuthor().equals(nameOfAuthor))
                .findFirst();
        if (optionalBook.isPresent()) {
            listOfBooks.remove(optionalBook.get());
            Main.logger.info("Books of " + nameOfAuthor + " are removed.");
        } else {
            Main.logger.info("There is no book of this author");
        }
        JSONLibraryRefresher.refresh(listOfBooks);
        Main.logger.info("List of books after " + listOfBooks);
    }

    @Override
    public void addListOfBooks() {
        Main.logger.info("Type address of books catalog");
        try (BufferedReader reader = new BufferedReader(new FileReader(Main.reader.readLine()))) {
            Gson gson = new Gson();
            Collection<Book> listOfBooksToAdd = gson.fromJson(reader, new TypeToken<List<Book>>() {
            }.getType());

            JSONLibraryRefresher.refresh(new ArrayList<>(listOfBooksToAdd));
            Main.logger.info("Books from are added to Library.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBookmark() throws IOException, NoSuchBookException {
        Main.logger.info("listOfBookmarks " + listOfBookmarks);
        Bookmark bookmark = new Bookmark();
        book = searchBookByName();
        Main.logger.info("Type number of page");
        bookmark.setVisitor(this);
        bookmark.setBook(book);
        bookmark.setPage(Integer.parseInt(Main.reader.readLine()));
        book.setBookmark(bookmark);
        listOfBookmarks.add(bookmark);
        JSONLibraryRefresher.refresh(listOfBooks);
        Main.logger.info("listOfBookmarks " + listOfBookmarks);
        Main.logger.info(book.getBookmark());
    }

    @Override
    public void removeBookmark() throws IOException, NoSuchBookException {
        Main.logger.info("Before - listOfBookmarks " + listOfBookmarks);
        book = searchBookByName();
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(b -> b.getName().equals(book.getName()))
                .findFirst();
        if (optionalBook.isPresent()) {
            listOfBookmarks.remove(optionalBook.get().getBookmark());
            optionalBook.get().setBookmark(null);
            optionalBook.get().setHasBookmark(false);
            Main.logger.info("Bookmark from " + optionalBook.get().getName() + " is removed");
            JSONLibraryRefresher.refresh(listOfBooks);
        } else {
            throw new NoSuchBookException("There is no such a book");
        }
        Main.logger.info("After - listOfBookmarks " + listOfBookmarks);
    }

    @Override
    public Book searchBookByName() throws IOException, NoSuchBookException {
        Main.logger.info("Type a name of a book");
        String bookName = Main.reader.readLine();
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(book -> book.getName().equals(bookName))
                .findFirst();
        if (optionalBook.isPresent()) {
            Main.logger.info(optionalBook.get());
            return optionalBook.get();
        } else {
            throw new NoSuchBookException("There is no such a book");
        }
    }

    @Override
    public ArrayList<Book> searchBookByAuthor() throws IOException, NoSuchBookException {
        ArrayList<Book> listBooksByAuthor = new ArrayList<>();
        Main.logger.info("Type a name of an author");
        String authorName = Main.reader.readLine();
        listOfBooks.stream()
                .filter(book -> book.getAuthor().equals(authorName))
                .forEach(listBooksByAuthor::add);
        if (!listBooksByAuthor.isEmpty()) {
            Main.logger.info(listBooksByAuthor);
            return listBooksByAuthor;
        } else {
            throw new NoSuchBookException("There is no such a book");
        }
    }

    @Override
    public Book searchBookByISBN() throws IOException, NoSuchBookException {
        Main.logger.info("Type an ISBN");
        long ISBN = Long.parseLong(Main.reader.readLine());
        Optional<Book> optionalBook = listOfBooks.stream()
                .filter(book -> book.getISBN() == ISBN)
                .findFirst();
        if (optionalBook.isPresent()) {
            Main.logger.info(optionalBook.get());
            return optionalBook.get();
        } else {
            throw new NoSuchBookException("There is no such a book");
        }
    }

    @Override
    public ArrayList<Book> searchBookInRangeOfYears() throws IOException, NoSuchBookException {
        Main.logger.info("Type a year from");
        int yearFrom = Integer.parseInt(Main.reader.readLine());
        Main.logger.info("Type a year to");
        int yearTo = Integer.parseInt(Main.reader.readLine());
        List<Book> list = listOfBooks.stream()
                .filter(book -> yearFrom <= book.getYear() && yearTo >= book.getYear())
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            ArrayList<Book> listBooksByAuthor = new ArrayList<>(list);
            Main.logger.info(listBooksByAuthor);
            return listBooksByAuthor;
        } else {
            throw new NoSuchBookException("There is no such a book");
        }
    }

    @Override
    public Book searchBookByYearPagesName() throws NoSuchBookException, IOException {
        Main.logger.info("Type name of a book");
        String name = Main.reader.readLine();
        Main.logger.info("Type a year");
        int year = Integer.parseInt(Main.reader.readLine());
        Main.logger.info("Type amount of pages");
        int pages = Integer.parseInt(Main.reader.readLine());
        Optional<Book> optionalBook = listOfBooks.stream()
                .filter(book -> book.getName().equals(name))
                .filter(book -> book.getYear() == year)
                .filter(book -> book.getPages() == pages)
                .findFirst();
        if (optionalBook.isPresent()) {
            Main.logger.info(optionalBook.get());
            return optionalBook.get();
        }
        throw new NoSuchBookException("There is no such a book");
    }

    @Override
    public ArrayList<Book> searchBookWithBookmarks() throws NoSuchBookException {
        List<Book> list = listOfBooks.stream()
                .filter(book -> book.hasBookmark)
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            ArrayList<Book> booksWithBookmark = new ArrayList<>(list);
            Main.logger.info(booksWithBookmark);
            return booksWithBookmark;
        }
        throw new NoSuchBookException("There is no such a book");
    }

    @Override
    public String toString() {
        return "user " + name;
    }
}
