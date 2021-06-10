package com.epam.homelibrary;

public class Book {
    private String name;
    private String author;
    private int year;
    private long ISBN;
    private int pages;
    private Bookmark bookmark;
    protected boolean hasBookmark = false;

    public void setHasBookmark(boolean hasBookmark) {
        this.hasBookmark = hasBookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
        hasBookmark = true;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public Book() {
    }

    public Book(String name, String author, int year, long ISBN, int pages) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public long getISBN() {
        return ISBN;
    }

    public int getPages() {
        return pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"\n" +
                "by " + author + "\n" +
                "year " + year + "\n"+
                "ISBN - " + ISBN +"\n"+
                "pages - " + pages +"\n";
    }
}
