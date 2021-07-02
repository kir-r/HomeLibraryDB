package com.epam.homelibrary.DAO.impl;

import com.epam.homelibrary.DAO.LibraryDAO;
import com.epam.homelibrary.models.Book;
import com.epam.homelibrary.models.Bookmark;
import com.epam.homelibrary.Main;
import com.epam.homelibrary.models.User;
import org.hibernate.Session;


import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class LibraryDataBaseDAO implements LibraryDAO {
    private DBConnector dBConnector;

    public LibraryDataBaseDAO() {
        dBConnector = DBConnector.getDBConnector();
    }

    public void addBook(Book book) { //1
        try (Session session = dBConnector.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
    }

    public void removeBook(String nameOfBook) {     //2
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaDelete<Book> criteriaDelete = cb.createCriteriaDelete(Book.class);
            Root<Book> root = criteriaDelete.from(Book.class);
            criteriaDelete.where(cb.equal(root.get("name"), nameOfBook));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
            Main.logger.info("Book \"" + nameOfBook + "\" is removed");
        }
    }

    public void removeBookByAuthor(String nameOfAuthor)  { //3
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaDelete<Book> criteriaDelete = cb.createCriteriaDelete(Book.class);
            Root<Book> root = criteriaDelete.from(Book.class);
            criteriaDelete.where(cb.equal(root.get("author"), nameOfAuthor));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
            Main.logger.info("Book by author " + nameOfAuthor + " is removed");
        }
    }

    public void addBookmark(Bookmark bookmark) { //5
        try (Session session = dBConnector.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(bookmark);
            transaction.commit();
            Main.logger.info(bookmark + " is added");
        }
    }

    public void removeBookmark(Book book) { //6
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Bookmark> criteriaDelete = cb.createCriteriaDelete(Bookmark.class);
            Root<Bookmark> root = criteriaDelete.from(Bookmark.class);
            criteriaDelete.where(cb.equal(root.get("book"), book));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
            Main.logger.info("Bookmark from \"" + book.getName() + "\" is removed");

        }
    }

    public List<Book> searchBookByName(String bookName) { //7
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(cb.equal(root.get("name"), bookName));

            Query<Book> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    public List<Book> searchBookByAuthor(String authorName) { //8
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(cb.equal(root.get("author"), authorName));
            Query<Book> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    public List<Book> searchBookByISBN(long ISBN) { //9
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(cb.equal(root.get("ISBN"), ISBN));
            Query<Book> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) { //10
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(cb.between(root.get("year"), yearFrom, yearTo));
            Query<Book> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    public List<Book> searchBookByYearPagesName(String name, int year, int pages) { //11
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);

            Predicate[] predicates = new Predicate[3];
            predicates[0] = cb.equal(root.get("name"), name);
            predicates[1] = cb.equal(root.get("year"), year);
            predicates[2] = cb.equal(root.get("pages"), pages);
            criteriaQuery.select(root).where(predicates);

            Query<Book> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    public List<Book> searchBookWithBookmarks() { //12
        // работаем со списком закладок, фильтруем закладки у которых нет книг
        // книги оставшихся закладок выводим

        return null;
    }


    public List<Book> getListOfBooksFromDB() {
        List<Book> list;
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root);
            Query<Book> query = session.createQuery(cr);
            list = query.getResultList();
            return list;
        }
    }

    public List<Bookmark> getListOfBookMarksFromDB() {
        List<Bookmark> list;
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Bookmark> cr = cb.createQuery(Bookmark.class);
            Root<Bookmark> root = cr.from(Bookmark.class);
            cr.select(root);
            Query<Bookmark> query = session.createQuery(cr);
            list = query.getResultList();
            return list;
        }
    }

    public List<User> getListOfUserFromDB() {
        List<User> list;
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root);
            Query<User> query = session.createQuery(cr);
            list = query.getResultList();
            return list;
        }
    }

    public void closeConnection() {
        try {
            dBConnector.sessionFactory.close();
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
        }
    }
}
