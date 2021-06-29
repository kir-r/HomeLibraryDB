package com.epam.homelibrary.DAO;

import com.epam.homelibrary.Book;
import com.epam.homelibrary.Bookmark;
import com.epam.homelibrary.User;
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
