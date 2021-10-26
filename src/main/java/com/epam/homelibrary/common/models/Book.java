package com.epam.homelibrary.common.models;

import javax.persistence.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table (name = "Book")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = {
        "id",
        "name",
        "author",
        "year",
        "ISBN",
        "pages"
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id", required = true)
    private int id;
    @Column(name = "name")
    @XmlElement(name = "name", required = true)
    private String name;
    @Column(name = "author")
    @XmlElement(name = "author", required = true)
    private String author;
    @Column(name = "year")
    @XmlElement(name = "year", required = true)
    private int year;
    @Column(name = "ISBN")
    @XmlElement(name = "ISBN", required = true)
    private long ISBN;
    @Column(name = "pages")
    @XmlElement(name = "pages", required = true)
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
