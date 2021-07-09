package com.epam.homelibrary.DAO;

import com.epam.homelibrary.models.User;

public interface UserDAO {
    void createUser(User user);

    void blockUser(String username);

    void getUserLogHistory();

    User authenticate(String login, String password);
}
