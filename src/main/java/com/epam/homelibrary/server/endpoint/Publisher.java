package com.epam.homelibrary.server.endpoint;

import com.epam.homelibrary.server.controller.LibraryWebServiceImpl;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/LibraryWebService", new LibraryWebServiceImpl());

    }
}
