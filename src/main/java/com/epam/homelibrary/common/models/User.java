package com.epam.homelibrary.common.models;

import com.epam.homelibrary.client.Main;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "Visitor")
@XmlRootElement //?

//@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
        "id",
        "name",
        "login",
        "password",
        "isAdmin",
        "blocked",
        "listOfBookmarks",
        "listOfBooks"
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id", required = true)
    private int id;
    @Column(name = "name")
    @XmlElement(name = "name", required = true)
    protected String name;
    @Column(name = "login")
    @XmlElement(name = "login", required = true)
    protected String login;
    @Column(name = "password")
    @XmlElement(name = "password", required = true)
    protected String password;
    @Column(name = "isAdmin")
    @XmlElement(name = "isAdmin", required = true)
    protected boolean isAdmin;
    @Column(name = "blocked", nullable = false)
    @XmlElement(name = "blocked", required = true)
    protected boolean blocked;
    @Transient
    protected ArrayList<Bookmark> listOfBookmarks = new ArrayList<>();
    @Transient
    protected ArrayList<Book> listOfBooks = new ArrayList<>();

    public User() {
    }

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
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
}

