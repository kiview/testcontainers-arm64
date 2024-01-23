package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
@RegisterRestClient
public interface HelloService {

    @GET
    Hello hello();
}