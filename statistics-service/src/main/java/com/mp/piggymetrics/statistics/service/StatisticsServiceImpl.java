package com.mp.piggymetrics.statistics.service;

import com.mp.piggymetrics.statistics.domain.*;
import com.mp.piggymetrics.statistics.domain.timeseries.DataPoint;
import com.mp.piggymetrics.statistics.domain.timeseries.ItemMetric;
import com.mp.piggymetrics.statistics.domain.timeseries.Rates;
import com.mp.piggymetrics.statistics.domain.timeseries.Statistics;
import com.mp.piggymetrics.statistics.repository.DataPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StatisticsServiceImpl implements StatisticsService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	private DataPointRepository repository;

	@Inject
	private ExchangeRatesService ratesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DataPoint> findByAccountName(String accountName) {
		return repository.findByAccount(accountName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataPoint save(String accountName, Account account) {

		ZonedDateTime zonedDateTime = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault());
		
		Set<ItemMetric> incomes = account.getIncomes().stream()
				.map(this::createItemMetric)
				.collect(Collectors.toSet());

		Set<ItemMetric> expenses = account.getExpenses().stream()
				.map(this::createItemMetric)
				.collect(Collectors.toSet());

		DataPoint dataPoint = new DataPoint();
		String pointId = accountName + zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
		dataPoint.setId(pointId);
		dataPoint.setAccount(accountName);
		dataPoint.setDate(Date.from(zonedDateTime.toInstant()));
		dataPoint.setIncomes(incomes);
		dataPoint.setExpenses(expenses);
		dataPoint.setStatistics(createStatisticMetrics(incomes, expenses, account.getSaving()));
		dataPoint.setRates(createRates());

		log.info("new datapoint has been created: {}", pointId);

		return repository.save(dataPoint);
	}

	private Statistics createStatisticMetrics(Set<ItemMetric> incomes, Set<ItemMetric> expenses, Saving saving) {

		BigDecimal savingAmount = ratesService.convert(saving.getCurrency(), Currency.getBase(), saving.getAmount());

		BigDecimal expensesAmount = expenses.stream()
				.map(ItemMetric::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal incomesAmount = incomes.stream()
				.map(ItemMetric::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return new Statistics(incomesAmount, expensesAmount, savingAmount);
	}

	private Rates createRates() {
		Map<Currency, BigDecimal> rates = ratesService.getCurrentRates();
		
		return new Rates(rates.get(Currency.EUR), rates.get(Currency.RUB), rates.get(Currency.USD));
	}
	
	/**
	 * Normalizes given item amount to {@link Currency#getBase()} currency with
	 * {@link TimePeriod#getBase()} time period
	 */
	private ItemMetric createItemMetric(Item item) {

		BigDecimal amount = ratesService
				.convert(item.getCurrency(), Currency.getBase(), item.getAmount())
				.divide(item.getPeriod().getBaseRatio(), 4, RoundingMode.HALF_UP);

		return new ItemMetric(item.getTitle(), amount);
	}
}
