package com.mp.piggymetrics.gateway.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mp.piggymetrics.gateway.client.AuthServiceClient;
import com.mp.piggymetrics.gateway.client.AccountServiceClient;
import com.mp.piggymetrics.gateway.domain.Account;
import com.mp.piggymetrics.gateway.domain.User;

@Path("")
@RequestScoped
public class GatewayResource {
    
    @Inject
	@RestClient
	private AuthServiceClient authClient;

    @Inject
	@RestClient
	private AccountServiceClient accountClient;
    
    @POST
    @Path("auth/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
    	return authClient.loginUser(user);
    }

    @GET
    @Path("accounts/current")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent() {
    	return accountClient.getCurrent();
    }
    
    @PUT
    @Path("accounts/current")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCurrent(Account update) {
    	return accountClient.updateCurrent(update);
    }

    @POST
    @Path("accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
    	return accountClient.add(user);
    }

}
