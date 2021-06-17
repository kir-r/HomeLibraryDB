package com.epam.homelibrary.databaseDAO;

import com.epam.homelibrary.Book;
import com.epam.homelibrary.Bookmark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LibraryDAO {
    private static SessionFactory sessionFactory; //hibernate

    static {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Bookmark.class);
        sessionFactory = configuration.configure().buildSessionFactory();

//       DBConnector.getConnection(); ?
    }



    public void addBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
    }

    void deleteBook() {
    }

    void addAuthor() {
    }

    void deleteAuthor() {
    }

    void addBooksFromCSV() {
    }

    void addBooksFromJSON() {
    }

    void searchBookForTitle() {
    }

    void searchBooksForAuthor() {
    }

    void searchBookForISBN() {
    }

    void searchBooksByYearRange() {
    }

    void searchBookByYearPagesNumberAndTitle() {
    }

    void findBookByFullTitle() {
    }

    void closeConnection() {
    }

}
