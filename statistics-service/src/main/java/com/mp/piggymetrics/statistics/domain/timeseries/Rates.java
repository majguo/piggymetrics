package com.mp.piggymetrics.statistics.domain.timeseries;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.math.BigDecimal;

@Entity
public class Rates {
	
	@Column
	private BigDecimal EUR;
	
	@Column
	private BigDecimal RUB;
	
	@Column
	private BigDecimal USD;

	public Rates() {
	}

	public Rates(BigDecimal EUR, BigDecimal RUB, BigDecimal USD) {
		this.EUR = EUR;
		this.RUB = RUB;
		this.USD = USD;
	}

	public BigDecimal getEUR() {
		return EUR;
	}

	public void setEUR(BigDecimal eUR) {
		EUR = eUR;
	}

	public BigDecimal getRUB() {
		return RUB;
	}

	public void setRUB(BigDecimal rUB) {
		RUB = rUB;
	}
	
	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}
	
}
