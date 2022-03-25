package com.epam.homelibrary.server.DAO.impl;

import com.epam.homelibrary.server.DAO.UserDAO;
import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.server.endpoint.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataBaseDAO implements UserDAO {
    private DBConnector dBConnector;

    public UserDataBaseDAO() {
        dBConnector = DBConnector.getDBConnector();
    }

    public User authenticate(String login, String password) {
        User user = null;
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = cb.equal(root.get("login"), login);
            predicates[1] = cb.equal(root.get("password"), password);
            criteriaQuery.select(root).where(predicates);

            Query<User> query = session.createQuery(criteriaQuery);
            if (!query.getResultList().isEmpty()) {
                user = query.getResultList().get(0);
            }
            return user;
        }
    }

    public void createUser(User user) {
        try (Session session = dBConnector.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            Publisher.logger.info("New user " + user.getName() + " is created.");
        }
    }

    public void blockUser(String username) {
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);

            criteriaUpdate.set("blocked", true);
            criteriaUpdate.where(cb.equal(root.get("name"), username));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
            Publisher.logger.info("User " + username + " is banned.");
        }
    }

    public List<String> getUserLogHistory() {
        List<String> listOfUserLogs = new ArrayList<>();
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader("src/Publisher/resources/app.log"))) {
            while (bufferedreader.ready()) {
                listOfUserLogs.add(bufferedreader.readLine());
            }
        } catch (IOException e) {
            Publisher.logger.error(e.getMessage());
        }
        return listOfUserLogs;
    }
}
