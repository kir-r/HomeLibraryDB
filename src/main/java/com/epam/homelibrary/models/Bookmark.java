package com.epam.homelibrary.models;

import javax.persistence.*;

@Entity
@Table(name = "Bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "page")
    private int page;
    @Transient                             //---?
    private User visitor;
    @JoinColumn(name = "book_id")
    @OneToOne
    private Book book;

    public User getVisitor() {
        return visitor;
    }

    public void setVisitor(User visitor) {
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
        return "Bookmark in book " + book.getName() + " on page " + page + " from " + getVisitor();
    }
}
