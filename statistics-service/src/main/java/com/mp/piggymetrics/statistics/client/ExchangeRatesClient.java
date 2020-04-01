package com.mp.piggymetrics.statistics.client;

import java.math.BigDecimal;
import com.google.common.collect.ImmutableMap;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.mp.piggymetrics.statistics.domain.Currency;
import com.mp.piggymetrics.statistics.domain.ExchangeRatesContainer;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://api.exchangeratesapi.io")
public interface ExchangeRatesClient {

    @GET
    @Path("latest")
    @Produces(MediaType.APPLICATION_JSON)
    @Fallback(fallbackMethod = "getRatesFallback")
    public ExchangeRatesContainer getRates(@QueryParam("base") Currency base);
    
    default ExchangeRatesContainer getRatesFallback(Currency base) {
        ExchangeRatesContainer container = new ExchangeRatesContainer();
        container.setBase(Currency.getBase());
        container.setRates(ImmutableMap.of(
            Currency.EUR.name(), new BigDecimal(0.9127418766),
            Currency.RUB.name(), new BigDecimal(78.4488864549),
            Currency.USD.name(), BigDecimal.ONE
		));
        return container;
    }
}
