package com.mp.piggymetrics.statistics.domain.timeseries;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.util.Date;
import java.util.Set;

/**
 * Represents daily time series data point containing
 * current account state
 */
@Entity
public class DataPoint {

	@Id
	private String id;
	
	@Column
	private String account;

	@Column
	private Date date;

	@Column
	private Set<ItemMetric> incomes;

	@Column
	private Set<ItemMetric> expenses;

	@Column
	private Statistics statistics;

	@Column
	private Rates rates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<ItemMetric> getIncomes() {
		return incomes;
	}

	public void setIncomes(Set<ItemMetric> incomes) {
		this.incomes = incomes;
	}

	public Set<ItemMetric> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<ItemMetric> expenses) {
		this.expenses = expenses;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}
}
