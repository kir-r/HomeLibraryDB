package com.epam.homelibrary.DAO;

import com.epam.homelibrary.models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface UserDAO {
    @WebMethod
    void createUser(User user);

    @WebMethod
    void blockUser(String username);

    @WebMethod
    void getUserLogHistory();

    @WebMethod
    User authenticate(String login, String password);
}
