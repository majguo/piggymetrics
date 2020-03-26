package com.mp.piggymetrics.account.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import javax.annotation.security.RolesAllowed;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.mp.piggymetrics.account.domain.Account;
import com.mp.piggymetrics.account.domain.User;
import com.mp.piggymetrics.account.service.AccountManager;

@Path("")
public class AccountResource {

    @Inject
    private AccountManager accountManager;

    @Inject
    private JsonWebToken jwtPrincipal;

    @GET
    @Path("current")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent() {
        return Response.ok(accountManager.get(this.jwtPrincipal.getName())).build();
    }
    
    @GET
    @RolesAllowed({ "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(accountManager.getAll()).build();
    }

    @GET
    @Path("{name}")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
		if (this.jwtPrincipal.getGroups().contains("admin") || this.jwtPrincipal.getName().equals(name)) {
			return Response.ok(accountManager.get(name)).build();
		}
		return Response.status(Status.UNAUTHORIZED).entity("No permission granted to view other account!").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
    	Account savedAccount = accountManager.add(user);
        
        return Response.created(
                UriBuilder.fromResource(this.getClass()).path(String.valueOf(savedAccount.getName())).build())
                .build();
    }

}
