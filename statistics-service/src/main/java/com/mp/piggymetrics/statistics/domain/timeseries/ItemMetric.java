package com.mp.piggymetrics.statistics.domain.timeseries;

import com.mp.piggymetrics.statistics.domain.Currency;
import com.mp.piggymetrics.statistics.domain.TimePeriod;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.math.BigDecimal;

/**
 * Represents normalized {@link com.piggymetrics.statistics.domain.Item} object
 * with {@link Currency#getBase()} currency and {@link TimePeriod#getBase()} time period
 */
@Entity
public class ItemMetric {

	@Column
	private String title;

	@Column
	private BigDecimal amount;

	public ItemMetric() {
	}

	public ItemMetric(String title, BigDecimal amount) {
		this.title = title;
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ItemMetric that = (ItemMetric) o;

		return title.equalsIgnoreCase(that.title);

	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}
}
