package com.epam.homelibrary.server.DAO;

import com.epam.homelibrary.common.models.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);

    void blockUser(String username);

    User authenticate(String login, String password);

    List<String> getUserLogHistory();
}
