package com.epam.homelibrary;

import javax.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Visitor")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    protected String name;
    @Column(name = "login")
    protected String login;
    @Column(name = "password")
    protected String password;
    @Column(name = "blocked", nullable = false)
    protected boolean blocked;
    @Transient
    protected ArrayList<Bookmark> listOfBookmarks = new ArrayList<>();
    @Transient
    protected ArrayList<Book> listOfBooks = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean blocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAdmin(String name) {
        return false;
    }

//    public String toString() {
//        return "user " + name;
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                '}';
    }

    public boolean login(String login, String password) {
        if (login.equalsIgnoreCase(this.login) && password.equals(this.password)) {
            return true;
        } else {
            Main.logger.info("Oops, login or password is incorrect.\nMake sure that CapsLock is not on by mistake, and try again.");

            return false;
        }
    }


    /*public void removeBook(String nameOfBook) throws IOException { //2
        Main.logger.info("List of books size: " + listOfBooks.size());
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(book -> book.getName().equals(nameOfBook))
                .findFirst();
        optionalBook.ifPresent(book -> listOfBooks.remove(book));
        if (optionalBook.isPresent()) {
            listOfBooks.remove(optionalBook.get());
            Main.logger.info("Book " + nameOfBook + " is removed from list of books.");

            libraryDataBaseDAO.removeBook(nameOfBook);

        } else {
            Main.logger.info("There is not such a book");
        }
        UserJsonDAO.refresh(listOfBooks);
        Main.logger.info("List of books size: " + listOfBooks.size());
    }

    public void removeBookByAuthor(String nameOfAuthor) throws IOException { //3
        Main.logger.info("List of books before " + listOfBooks);
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
        UserJsonDAO.refresh(listOfBooks);
        Main.logger.info("List of books after " + listOfBooks);
    }

    public void addListOfBooks(String addressOfBookCatalog) { //4
        try (BufferedReader reader = new BufferedReader(new FileReader(addressOfBookCatalog))) {
            Gson gson = new Gson();
            Collection<Book> listOfBooksToAdd = gson.fromJson(reader, new TypeToken<List<Book>>() {
            }.getType());

            UserJsonDAO.refresh(new ArrayList<>(listOfBooksToAdd));
            Main.logger.info("Books from are added to Library.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addBookmark() throws IOException { //5
       *//* Main.logger.info("listOfBookmarks " + listOfBookmarks);
        Bookmark bookmark = new Bookmark();
        book = searchBookByName();
        Main.logger.info("Type number of page");
        bookmark.setVisitor(this);
        bookmark.setBook(book);
        bookmark.setPage(Integer.parseInt(Main.reader.readLine()));
        book.setBookmark(bookmark);
        listOfBookmarks.add(bookmark);
        UserJsonDAO.refresh(listOfBooks);
        Main.logger.info("listOfBookmarks " + listOfBookmarks);
        Main.logger.info(book.getBookmark());*//*
    }
    public void removeBookmark(String bookName) throws IOException { //6
//        Main.logger.info("Before - listOfBookmarks " + listOfBookmarks);
//        book = searchBookByName(bookName);
//        Optional<Book> optionalBook = listOfBooks
//                .stream()
//                .filter(b -> b.getName().equals(book.getName()))
//                .findFirst();
//        if (optionalBook.isPresent()) {
//            listOfBookmarks.remove(optionalBook.get().getBookmark());
//            optionalBook.get().setBookmark(null);
//            optionalBook.get().setHasBookmark(false);
//            Main.logger.info("Bookmark from " + optionalBook.get().getName() + " is removed");
//            UserJsonDAO.refresh(listOfBooks);
//        } else {
//            throw new NoSuchBookException("There is no such a book");
//        }
//        Main.logger.info("After - listOfBookmarks " + listOfBookmarks);
    }

    public Book searchBookByName(String bookName) throws IOException { //7
        Optional<Book> optionalBook = listOfBooks
                .stream()
                .filter(book -> book.getName().equals(bookName))
                .findFirst();
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            Main.logger.info("There is not such a book");
            return null;
        }
    }

    public List<Book> searchBookByAuthor(String authorName) throws IOException { //8
        ArrayList<Book> listBooksByAuthor = new ArrayList<>();
        listOfBooks.stream()
                .filter(book -> book.getAuthor().equals(authorName))
                .forEach(listBooksByAuthor::add);
        return listBooksByAuthor;
    }

    public Book searchBookByISBN(long ISBN) throws IOException { //9
        Optional<Book> optionalBook = listOfBooks.stream()
                .filter(book -> book.getISBN() == ISBN)
                .findFirst();
        if (optionalBook.isPresent()) { //get without check - ?
            Main.logger.info(optionalBook.get());
            return optionalBook.get();
        } else {
            Main.logger.info("There is not such a book");
            return null;
        }
    }

    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) throws IOException { //10
        return listOfBooks.stream()
                .filter(book -> yearFrom <= book.getYear() && yearTo >= book.getYear())
                .collect(Collectors.toList());
    }

    public Book searchBookByYearPagesName(String name, int year, int pages) { //11
        Optional<Book> optionalBook = listOfBooks.stream()
                .filter(book -> book.getName().equals(name))
                .filter(book -> book.getYear() == year)
                .filter(book -> book.getPages() == pages)
                .findFirst();
        if (optionalBook.isPresent()) {
            Main.logger.info(optionalBook.get());
            return optionalBook.get();
        } else {
            Main.logger.info("There is not such a book");
            return null;
        }
    }

    public List<Book> searchBookWithBookmarks() { //12
        // работаем со списком закладок, фильтруем закладки у которых нет книг
        // книги оставшихся закладок выводим

        return listOfBookmarks.stream()
                .filter(bookmark -> bookmark.getBook() != null)
                .map(Bookmark::getBook)
                .collect(Collectors.toList());
    }

    public List<Book> getListFromDB() {
        return libraryDataBaseDAO.getListFromDB();
    }

    public void closeConnection() {
        try {
            libraryDataBaseDAO.closeConnection();
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
        }
    }*/
}

