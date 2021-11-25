package com.epam.homelibrary.server.filter;

import com.epam.homelibrary.server.TokenManager.TokenManager;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

//import javax.annotation.Priority;
import jakarta.annotation.Priority;

import java.io.IOException;

@Provider
@Logged
//@PreMatching //?
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private final TokenManager tokenManager = new TokenManager();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String jwtToken = containerRequestContext.getCookies().get("token").getValue();

        if (!tokenManager.decodeToken(jwtToken)) {
            Response response = Response
                    .status(Response.Status.FORBIDDEN)
                    .build();
            containerRequestContext.abortWith(response);
        }


        //containerRequestContext get cookie, verify token!
//Из реквеста достаю куки, из куки токен, verify
    }
}
