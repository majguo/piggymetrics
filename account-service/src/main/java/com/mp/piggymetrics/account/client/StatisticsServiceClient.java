package com.mp.piggymetrics.account.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.account.domain.Account;

@ApplicationScoped
@RegisterRestClient(configKey = "statisticsServiceClient")
@RegisterClientHeaders
public interface StatisticsServiceClient {

    @PUT
    @Path("statistics/{accountName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAccountStatistics(@PathParam("accountName") String accountName, Account account);
    
    @GET
    @Path("health/ready")
    @Produces(MediaType.APPLICATION_JSON)
	public Response ready();
}
