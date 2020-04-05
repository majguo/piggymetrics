package com.mp.piggymetrics.statistics.domain;

import javax.validation.constraints.NotNull;

import org.jnosql.artemis.Convert;

import com.mp.piggymetrics.statistics.repository.converter.BigDecimalConverter;

import java.math.BigDecimal;

public class Saving {

	@NotNull
	@Convert(BigDecimalConverter.class)
	private BigDecimal amount;

	@NotNull
	private Currency currency;

	@NotNull
	@Convert(BigDecimalConverter.class)
	private BigDecimal interest;

	@NotNull
	private Boolean deposit;

	@NotNull
	private Boolean capitalization;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Boolean getDeposit() {
		return deposit;
	}

	public void setDeposit(Boolean deposit) {
		this.deposit = deposit;
	}

	public Boolean getCapitalization() {
		return capitalization;
	}

	public void setCapitalization(Boolean capitalization) {
		this.capitalization = capitalization;
	}
}
