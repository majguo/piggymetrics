package com.mp.piggymetrics.gateway.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
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
    
	@Timed(name = "gatewayLoginTime", absolute = true)
    @Counted(name = "gatewayLoginCount", absolute = true)
    @POST
    @Path("auth/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
    	return authClient.loginUser(user);
    }

	@Timed(name = "gatewayGetCurrentAccountTime", absolute = true)
    @Counted(name = "gatewayGetCurrentAccountCount", absolute = true)
    @GET
    @Path("accounts/current")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent() {
    	return accountClient.getCurrent();
    }
    
	@Timed(name = "gatewayUpdateCurrentAccountTime", absolute = true)
    @Counted(name = "gatewayUpdateCurrentAccountCount", absolute = true)
    @PUT
    @Path("accounts/current")
    @RolesAllowed({ "user", "admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurrent(Account update) {
    	return accountClient.updateCurrent(update);
    }

	@Timed(name = "gatewayCreateAccountTime", absolute = true)
    @Counted(name = "gatewayCreateAccountCount", absolute = true)
    @POST
    @Path("accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
    	return accountClient.add(user);
    }

}
