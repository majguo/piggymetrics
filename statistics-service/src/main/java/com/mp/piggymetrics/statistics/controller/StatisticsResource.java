package com.mp.piggymetrics.statistics.controller;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.mp.piggymetrics.statistics.domain.Account;
import com.mp.piggymetrics.statistics.service.ExchangeRatesService;
import com.mp.piggymetrics.statistics.service.StatisticsService;

@Path("")
@RequestScoped
public class StatisticsResource {
	
	@Inject
	private ExchangeRatesService ratesService;
	
	@Inject
	private StatisticsService statisticsService;
	
	@Inject
    private JsonWebToken jwtPrincipal;

    @GET
    @Path("rates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRates() {
        return Response.ok(ratesService.getCurrentRates()).build();
    }
    
    @GET
    @Path("current")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentAccountStatistics() {
    	return Response.ok(statisticsService.findByAccountName(this.jwtPrincipal.getName())).build();
    }
    
    @GET
    @Path("{accountName}")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsByAccountName(@PathParam("accountName") String accountName) {
    	if (this.jwtPrincipal.getGroups().contains("admin") || this.jwtPrincipal.getName().equals(accountName)) {
    		return Response.ok(statisticsService.findByAccountName(accountName)).build();
    	}
    	return Response.status(Status.UNAUTHORIZED).entity("No permission granted to view statistics of other account!").build();
    }
    
    @PUT
    @Path("{accountName}")
    @RolesAllowed({ "user", "admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAccountStatistics(@PathParam("accountName") String accountName, Account account) {
    	if (this.jwtPrincipal.getGroups().contains("admin") || this.jwtPrincipal.getName().equals(accountName)) {
    		statisticsService.save(accountName, account);
        	return Response.ok().build();
    	}
    	return Response.status(Status.UNAUTHORIZED).entity("No permission granted to update statistics of other account!").build();
    }
}
