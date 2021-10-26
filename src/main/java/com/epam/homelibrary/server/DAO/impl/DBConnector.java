package com.epam.homelibrary.server.DAO.impl;

import com.epam.homelibrary.common.models.Book;
import com.epam.homelibrary.common.models.Bookmark;
import com.epam.homelibrary.common.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnector {
    SessionFactory sessionFactory;
    private static DBConnector dBConnector;

    private DBConnector() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Bookmark.class);
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static DBConnector getDBConnector() {
        if (dBConnector == null) {
            dBConnector = new DBConnector();
        }
        return dBConnector;
    }
}
