package com.mp.piggymetrics.statistics.repository;

import org.jnosql.artemis.Repository;

import com.mp.piggymetrics.statistics.domain.timeseries.DataPoint;

import java.util.List;

public interface DataPointRepository extends Repository<DataPoint, String> {
    List<DataPoint> findByAccount(String account);
}
