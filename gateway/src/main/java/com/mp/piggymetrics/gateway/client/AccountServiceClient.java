package com.mp.piggymetrics.gateway.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.gateway.domain.User;
import com.mp.piggymetrics.gateway.domain.Account;

@ApplicationScoped
@RegisterRestClient(configKey = "accountServiceClient")
@RegisterClientHeaders
public interface AccountServiceClient {

	@GET
    @Path("accounts/current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent();
	
    @PUT
    @Path("accounts/current")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurrent(Account update);
    
    @POST
    @Path("accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user);
    
    @GET
    @Path("health/ready")
    @Produces(MediaType.APPLICATION_JSON)
	public Response ready();
}
