package com.mp.piggymetrics.account.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
    @RolesAllowed({ "user" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent() {
        return Response.ok(accountManager.get(this.jwtPrincipal.getName())).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
    	Account account = accountManager.get(name);
        return Response.ok(account).build();
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
