package com.epam.homelibrary.DAO;

import com.epam.homelibrary.Book;
import com.epam.homelibrary.Main;
import com.epam.homelibrary.User;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.io.*;

public class UserDataBaseDAO {
    private DBConnector dBConnector;

    public UserDataBaseDAO() {
        dBConnector = DBConnector.getDBConnector();
    }

    public void createUser(User user) { //13
        try (Session session = dBConnector.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            Main.logger.info("New user " + user.getName() + " is created.");
        }
    }

    public void blockUser(String username) { //14
        try (Session session = dBConnector.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);

            criteriaUpdate.set("blocked", true);
            criteriaUpdate.where(cb.equal(root.get("name"), username));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        }
    }

    public void getUserLogHistory() { //15
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader("src/main/resources/app.log"))) {
            while (bufferedreader.ready()) {
                Main.logger.info(bufferedreader.readLine());
            }
        } catch (IOException e) {
            Main.logger.error(e.getMessage());
        }
    }
}
