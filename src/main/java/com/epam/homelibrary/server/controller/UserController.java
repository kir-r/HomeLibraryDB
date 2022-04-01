package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.common.models.wrappers.UserListWrapper;
import com.epam.homelibrary.server.DAO.HistoryManager;
import com.epam.homelibrary.server.TokenManager.TokenManager;
//import com.epam.homelibrary.server.filter.AuthenticationFilter;
//import com.epam.homelibrary.server.filter.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//
//import jakarta.ws.rs.core.NewCookie;
//import jakarta.ws.rs.core.Response;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final TokenManager tokenManager = new TokenManager();
//    private final AuthenticationFilter authenticationFilter = new AuthenticationFilter();

    //    @Inject
    @Autowired
    private LibraryWebServiceImpl libraryWebServiceImpl;

    //    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/authorization")
    @GetMapping(value = "/authorization")
    public ResponseEntity<Void> authenticate(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password) {
        System.out.println(login + " " + password);
        User user = libraryWebServiceImpl.authenticate(login, password);
        System.out.println(user);
        if (user != null) {
            String token = tokenManager.encodeToken(login);
            return ResponseEntity.ok().header(token).build();
//                    .status(Response.Status.OK)
//                    .cookie(new NewCookie("token", token))
//                    .entity(user)
//                    .build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

//    @POST
//    @Path("/addUser")
//    @Logged
//    public Response createUser(User user) {
//        libraryWebServiceImpl.createUser(user);
//        return Response
//                .status(Response.Status.OK)
//                .entity(user)
//                .build();
//    }
//
//    @POST
//    @Path("/blockUser/{username}")
//    @Logged
//    public Response blockUser(@PathParam("username") String username) {
//        libraryWebServiceImpl.blockUser(username);
//        return Response
//                .status(Response.Status.OK)
//                .entity(username)
//                .build();
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/get-users")
//    public Response getListOfUserFromDB() {
//        List<User> listOfUsers = libraryWebServiceImpl.getListOfUserFromDB();
//        UserListWrapper userListWrapper = new UserListWrapper();
//        userListWrapper.setList(listOfUsers);
//        return Response
//                .status(Response.Status.OK)
//                .entity(userListWrapper)
//                .build();
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/get-logs")
//    public Response getUserLogHistory() {
//        return Response
//                .status(Response.Status.OK)
//                .entity(HistoryManager.read())
//                .build();
//    }
}
