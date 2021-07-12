package com.epam.homelibrary.models;

import com.epam.homelibrary.Main;

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
    @Column(name = "isAdmin")
    protected boolean isAdmin;
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

