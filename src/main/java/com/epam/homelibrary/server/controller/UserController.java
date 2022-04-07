package com.epam.homelibrary.server.controller;

import com.epam.homelibrary.common.models.User;
import com.epam.homelibrary.common.models.wrappers.UserListWrapper;
import com.epam.homelibrary.server.TokenManager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;

import java.util.List;

@RestController
//@RequestMapping("/users")
@RequestMapping("/library/users")
public class UserController {

    private final TokenManager tokenManager = new TokenManager();
//    private final AuthenticationFilter authenticationFilter = new AuthenticationFilter();

    //    @Inject
    @Autowired
    private LibraryWebServiceImpl libraryWebServiceImpl;

    //    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/authorization")

    @PostMapping(value = "/authorization")
    public ResponseEntity<User> authenticate(@RequestHeader("login") String login, @RequestHeader("password") String password) {
        System.out.println(login + " " + password);
        User user = libraryWebServiceImpl.authenticate(login, password);
        System.out.println(user);
        if (user != null) {
            return ResponseEntity.ok(user);
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
    @GetMapping(value = "/get-users")
    public ResponseEntity<List<User>> getListOfUserFromDB() {
        List<User> listOfUsers = libraryWebServiceImpl.getListOfUserFromDB();
        UserListWrapper userListWrapper = new UserListWrapper();
        userListWrapper.setList(listOfUsers);
        return ResponseEntity.ok(listOfUsers);
    }
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
