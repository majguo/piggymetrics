package com.mp.piggymetrics.statistics.domain.timeseries;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.math.BigDecimal;

@Entity
public class Statistics {
	
	@Column
	private BigDecimal incomesAmout;
	
	@Column
	private BigDecimal expensesAmout;
	
	@Column
	private BigDecimal savingAmout;

	public Statistics() {
	}

	public Statistics(BigDecimal incomesAmout, BigDecimal expensesAmout, BigDecimal savingAmount) {
		this.incomesAmout = incomesAmout;
		this.expensesAmout = expensesAmout;
		this.savingAmout = savingAmount;
	}

	public BigDecimal getIncomesAmout() {
		return incomesAmout;
	}

	public void setIncomesAmout(BigDecimal incomesAmout) {
		this.incomesAmout = incomesAmout;
	}
	
	public BigDecimal getExpensesAmout() {
		return expensesAmout;
	}

	public void setExpensesAmout(BigDecimal expensesAmout) {
		this.expensesAmout = expensesAmout;
	}

	public BigDecimal getSavingAmout() {
		return savingAmout;
	}

	public void setSavingAmout(BigDecimal savingAmout) {
		this.savingAmout = savingAmout;
	}
	
}
