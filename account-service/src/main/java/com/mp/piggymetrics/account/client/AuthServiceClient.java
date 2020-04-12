package com.mp.piggymetrics.account.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.account.domain.User;

@ApplicationScoped
@RegisterRestClient(configKey = "authServiceClient")
public interface AuthServiceClient {

    @POST
    @Path("auth/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user);
    
    @GET
    @Path("health/ready")
    @Produces(MediaType.APPLICATION_JSON)
	public Response ready();
}
