package com.mp.piggymetrics.account.monitoring;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mp.piggymetrics.account.controller.AccountResource;
import com.mp.piggymetrics.account.service.AccountService;
import com.mp.piggymetrics.account.client.AuthServiceClient;
import com.mp.piggymetrics.account.client.StatisticsServiceClient;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class AccountReadinessCheck implements HealthCheck {

	@Inject
	private AccountService accountManager;
	
	@Inject
	@RestClient
	private AuthServiceClient authClient;
	
	@Inject
	@RestClient
	private StatisticsServiceClient statisticsClient;

	@Override
	public HealthCheckResponse call() {
		return isReady()
				? HealthCheckResponse.named(AccountResource.class.getSimpleName() + "Readiness")
						.withData("default server", "available").up().build()
				: HealthCheckResponse.named(AccountResource.class.getSimpleName() + "Readiness")
						.withData("default server", "not available").down().build();
	}

	private boolean isReady() {
		try {
			accountManager.count();
			return "UP".equals(authClient.ready().readEntity(JsonObject.class).getString("status"))
					&& "UP".equals(statisticsClient.ready().readEntity(JsonObject.class).getString("status"));
		} catch (Exception e) {
			return false;
		}
	}
}