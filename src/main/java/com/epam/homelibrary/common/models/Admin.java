package com.epam.homelibrary.common.models;

import java.util.*;

public class Admin extends User {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();

    public static ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "admin " + name;
    }
}
