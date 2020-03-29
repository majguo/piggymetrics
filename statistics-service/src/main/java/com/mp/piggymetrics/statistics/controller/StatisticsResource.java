package com.mp.piggymetrics.statistics.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mp.piggymetrics.statistics.service.ExchangeRatesService;

@Path("")
@RequestScoped
public class StatisticsResource {
	
	@Inject
	private ExchangeRatesService ratesService;

    @GET
    @Path("rates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRates() {
        return Response.ok(ratesService.getCurrentRates()).build();
    }
}
