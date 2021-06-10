package com.epam.homelibrary;

public class Bookmark {
    private Book book;
    private int page;
    private AbstractUser visitor;

    public AbstractUser getVisitor() {
        return visitor;
    }

    public void setVisitor(AbstractUser visitor) {
        this.visitor = visitor;
    }

    public Book getBook() {
        return book;
    }

    public int getPage() {
        return page;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Bookmark in book " + book.getName() + " on page " + page + " from user " + visitor;
    }
}
