package com.epam.homelibrary.models;

import javax.persistence.*;

@Entity
@Table (name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private int year;
    @Column(name = "ISBN")
    private long ISBN;
    @Column(name = "pages")
    private int pages;

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
