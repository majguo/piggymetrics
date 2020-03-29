package com.mp.piggymetrics.statistics.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("rates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRates() {
        return Response.ok(ratesService.getCurrentRates()).build();
    }
    
    @GET
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsByAccountName(@PathParam("accountName") String accountName) {
        return Response.ok(statisticsService.findByAccountName(accountName)).build();
    }
    
    @PUT
    @Path("{accountName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAccountStatistics(@PathParam("accountName") String accountName, Account account) {
    	statisticsService.save(accountName, account);
        
        return Response.ok().build();
    }
}
