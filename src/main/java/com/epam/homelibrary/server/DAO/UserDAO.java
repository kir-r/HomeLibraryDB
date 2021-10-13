package com.epam.homelibrary.server.DAO;

import com.epam.homelibrary.models.User;

public interface UserDAO {
    void createUser(User user);

    void blockUser(String username);

    User authenticate(String login, String password);

    void getUserLogHistory();
}
