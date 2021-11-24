package com.epam.homelibrary.common.models;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "Bookmark")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bookmark", propOrder = {
        "id",
        "page",
        "visitor_id",
        "book"
})
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id", required = true)
    private int id;
    @Column(name = "page")
    @XmlElement(name = "page", required = true)
    private int page;
    @JoinColumn(name = "visitor_id")
    @OneToOne
    @XmlElement()
    private User visitor;
    @JoinColumn(name = "book_id")
    @OneToOne
    @XmlElement()
    private Book book;

    public Bookmark() {
    }

    public int getId() {
        return id;
    }

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
