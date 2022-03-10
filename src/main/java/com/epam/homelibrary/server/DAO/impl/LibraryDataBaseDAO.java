//package com.epam.homelibrary.server.DAO.impl;
//
//import com.epam.homelibrary.server.DAO.HistoryManager;
//import com.epam.homelibrary.server.DAO.LibraryDAO;
//import com.epam.homelibrary.common.models.Book;
//import com.epam.homelibrary.common.models.Bookmark;
//import com.epam.homelibrary.client.Main;
//import com.epam.homelibrary.common.models.User;
//import org.hibernate.Session;
//
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import javax.persistence.criteria.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LibraryDataBaseDAO implements LibraryDAO {
//    private DBConnector dBConnector;
//
//    public LibraryDataBaseDAO() {
//        dBConnector = DBConnector.getDBConnector();
//    }
//
//    public void addBook(Book book) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(book);
//            transaction.commit();
//            HistoryManager.write("added new book " + book.toString());
//        }
//    }
//
//    public void removeBook(String nameOfBook) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//
//            CriteriaDelete<Book> criteriaDelete = cb.createCriteriaDelete(Book.class);
//            Root<Book> root = criteriaDelete.from(Book.class);
//            criteriaDelete.where(cb.equal(root.get("name"), nameOfBook));
//
//            Transaction transaction = session.beginTransaction();
//            session.createQuery(criteriaDelete).executeUpdate();
//            transaction.commit();
//            HistoryManager.write("Book \"" + nameOfBook + "\" is removed");
//        }
//    }
//
//    public void removeBookByAuthor(String nameOfAuthor) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//
//            CriteriaDelete<Book> criteriaDelete = cb.createCriteriaDelete(Book.class);
//            Root<Book> root = criteriaDelete.from(Book.class);
//            criteriaDelete.where(cb.equal(root.get("author"), nameOfAuthor));
//
//            Transaction transaction = session.beginTransaction();
//            session.createQuery(criteriaDelete).executeUpdate();
//            transaction.commit();
//            HistoryManager.write("Book by author " + nameOfAuthor + " is removed");
//        }
//    }
//
//    public void addBookmark(Bookmark bookmark) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(bookmark);
//            transaction.commit();
//            HistoryManager.write(bookmark + " is added");
//        }
//    }
//
//    public void removeBookmark(int bookId) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaDelete<Bookmark> criteriaDelete = cb.createCriteriaDelete(Bookmark.class);
//            Root<Bookmark> root = criteriaDelete.from(Bookmark.class);
//
//            criteriaDelete.where(cb.equal(root.get("book").get("id"), bookId));
//
//            Transaction transaction = session.beginTransaction();
//            session.createQuery(criteriaDelete).executeUpdate();
//            transaction.commit();
//            HistoryManager.write("Bookmark from book with ID \"" + bookId + "\" is removed");
//
//        }
//    }
//
//    public List<Book> searchBookByName(String bookName) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//            criteriaQuery.select(root).where(cb.equal(root.get("name"), bookName));
//
//            Query<Book> query = session.createQuery(criteriaQuery);
//            return query.getResultList();
//        }
//    }
//
//    public List<Book> searchBookByAuthor(String authorName) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//            criteriaQuery.select(root).where(cb.equal(root.get("author"), authorName));
//            Query<Book> query = session.createQuery(criteriaQuery);
//            return query.getResultList();
//        }
//    }
//
//    public List<Book> searchBookByISBN(long ISBN) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//            criteriaQuery.select(root).where(cb.equal(root.get("ISBN"), ISBN));
//            Query<Book> query = session.createQuery(criteriaQuery);
//            return query.getResultList();
//        }
//    }
//
//    public List<Book> searchBookInRangeOfYears(int yearFrom, int yearTo) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//            criteriaQuery.select(root).where(cb.between(root.get("year"), yearFrom, yearTo));
//            Query<Book> query = session.createQuery(criteriaQuery);
//            return query.getResultList();
//        }
//    }
//
//    public List<Book> searchBookByYearPagesName(String name, int year, int pages) {
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//
//            Predicate[] predicates = new Predicate[3];
//            predicates[0] = cb.equal(root.get("name"), name);
//            predicates[1] = cb.equal(root.get("year"), year);
//            predicates[2] = cb.equal(root.get("pages"), pages);
//            criteriaQuery.select(root).where(predicates);
//
//            Query<Book> query = session.createQuery(criteriaQuery);
//            return query.getResultList();
//        }
//    }
//
//    public List<Book> searchBookWithBookmarks(int visitorId) { //new one
//        List<Book> listOfBooksWithBookmarks = new ArrayList<>();
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Bookmark> criteriaQuery = cb.createQuery(Bookmark.class);
//            Root<Bookmark> root = criteriaQuery.from(Bookmark.class);
//
//            criteriaQuery
//                    .select(root)
//                    .where(cb.equal(root
//                            .get("visitor").get("id"), visitorId));
//
//            Query<Bookmark> query = session.createQuery(criteriaQuery);
//            List<Bookmark> bookmarksWithBooks = query.getResultList();
//            for (Bookmark bookmark : bookmarksWithBooks) {
//                listOfBooksWithBookmarks.add(bookmark.getBook());
//            }
//        }
//        return listOfBooksWithBookmarks;
//    }
//
//    public List<Book> getListOfBooksFromDB() {
//        List<Book> list;
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
//            Root<Book> root = cr.from(Book.class);
//            cr.select(root);
//            Query<Book> query = session.createQuery(cr);
//            list = query.getResultList();
//            return list;
//        }
//    }
//
//    public List<Bookmark> getListOfBookMarksFromDB() {
//        List<Bookmark> list;
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Bookmark> cr = cb.createQuery(Bookmark.class);
//            Root<Bookmark> root = cr.from(Bookmark.class);
//            cr.select(root);
//            Query<Bookmark> query = session.createQuery(cr);
//            list = query.getResultList();
//            return list;
//        }
//    }
//
//    public List<User> getListOfUserFromDB() {
//        List<User> list;
//        try (Session session = dBConnector.sessionFactory.openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<User> cr = cb.createQuery(User.class);
//            Root<User> root = cr.from(User.class);
//            cr.select(root);
//            Query<User> query = session.createQuery(cr);
//            list = query.getResultList();
//            return list;
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            dBConnector.sessionFactory.close();
//        } catch (Exception e) {
//            Main.logger.error(e.getMessage());
//        }
//    }
//}
