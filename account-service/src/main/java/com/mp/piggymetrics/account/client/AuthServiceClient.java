package com.mp.piggymetrics.account.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.account.domain.User;

@ApplicationScoped
@RegisterRestClient
public interface AuthServiceClient {

    @POST
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user);
}
