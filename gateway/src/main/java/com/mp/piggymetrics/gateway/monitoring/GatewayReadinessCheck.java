package com.mp.piggymetrics.gateway.monitoring;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mp.piggymetrics.gateway.client.AccountServiceClient;
import com.mp.piggymetrics.gateway.client.AuthServiceClient;
import com.mp.piggymetrics.gateway.controller.GatewayResource;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class GatewayReadinessCheck implements HealthCheck {

    @Inject
	@RestClient
	private AuthServiceClient authClient;

    @Inject
	@RestClient
	private AccountServiceClient accountClient;

	@Override
	public HealthCheckResponse call() {
		return isReady()
				? HealthCheckResponse.named(GatewayResource.class.getSimpleName() + "Readiness")
						.withData("default server", "available").up().build()
				: HealthCheckResponse.named(GatewayResource.class.getSimpleName() + "Readiness")
						.withData("default server", "not available").down().build();
	}

	private boolean isReady() {
		return "UP".equals(authClient.ready().readEntity(JsonObject.class).getString("status"))
				&& "UP".equals(accountClient.ready().readEntity(JsonObject.class).getString("status"));
	}
}