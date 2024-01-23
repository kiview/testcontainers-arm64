package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/client")
public class HelloResource {

    @RestClient
    HelloService helloService;


    @GET
    @Path("/hello")
    public String hello() {
        return helloService.hello().message;
    }
}
