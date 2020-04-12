package com.mp.piggymetrics.statistics.monitoring;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.Readiness;

import com.mp.piggymetrics.statistics.controller.StatisticsResource;
import com.mp.piggymetrics.statistics.service.StatisticsService;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class StatisticsReadinessCheck implements HealthCheck {

	@Inject
	private StatisticsService statisticsManager;

	@Override
	public HealthCheckResponse call() {
		return isReady()
				? HealthCheckResponse.named(StatisticsResource.class.getSimpleName() + "Readiness")
						.withData("default server", "available").up().build()
				: HealthCheckResponse.named(StatisticsResource.class.getSimpleName() + "Readiness")
						.withData("default server", "not available").down().build();
	}

	private boolean isReady() {
		try {
			statisticsManager.count();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}