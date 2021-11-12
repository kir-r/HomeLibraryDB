package com.epam.homelibrary.server.filter;

import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

//import javax.annotation.Priority;
import jakarta.annotation.Priority;
import java.io.IOException;

@Provider
@Logged
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        //containerRequestContext get cookie, verify token!
//Из реквеста достаю куки, из куки токен, verify
    }
}
