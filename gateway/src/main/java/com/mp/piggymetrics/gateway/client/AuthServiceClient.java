package com.mp.piggymetrics.gateway.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.gateway.domain.User;

@ApplicationScoped
@RegisterRestClient(configKey = "authServiceClient")
public interface AuthServiceClient {

    @POST
    @Path("auth/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user);
}
