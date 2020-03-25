package com.mp.piggymetrics.account.domain;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Saving {

	@NotNull
	@Column
	private BigDecimal amount;

	@NotNull
	@Column
	private Currency currency;

	@NotNull
	@Column
	private BigDecimal interest;

	@NotNull
	@Column
	private Boolean deposit;

	@NotNull
	@Column
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
