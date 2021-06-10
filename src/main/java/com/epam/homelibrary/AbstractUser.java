package com.epam.homelibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser {
    public abstract boolean login(String login, String password);
    public abstract boolean isAdmin(String name);
    public abstract void addBook(String addressJSON) throws IOException;
    public abstract void removeBook(String addressJSON) throws IOException;
    public abstract void removeBookByAuthor(String addressJSON) throws IOException;
    public abstract void addListOfBooks() throws IOException;
    public abstract void addBookmark() throws IOException, NoSuchBookException;
    public abstract void removeBookmark() throws IOException, NoSuchBookException;
    public abstract Book searchBookByName() throws IOException, NoSuchBookException;
    public abstract ArrayList<Book> searchBookByAuthor() throws IOException, NoSuchBookException;
    public abstract Book searchBookByISBN() throws IOException, NoSuchBookException;
    public abstract ArrayList<Book> searchBookInRangeOfYears() throws IOException, NoSuchBookException;
    public abstract Book searchBookByYearPagesName() throws NoSuchBookException, IOException;
    public abstract ArrayList<Book> searchBookWithBookmarks() throws NoSuchBookException;
}

