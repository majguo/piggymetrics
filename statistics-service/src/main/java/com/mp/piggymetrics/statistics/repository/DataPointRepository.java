package com.mp.piggymetrics.statistics.repository;

import org.jnosql.artemis.Param;
import org.jnosql.artemis.Query;
import org.jnosql.artemis.Repository;

import com.mp.piggymetrics.statistics.domain.timeseries.DataPoint;
import com.mp.piggymetrics.statistics.domain.timeseries.DataPointId;

import java.util.List;

public interface DataPointRepository extends Repository<DataPoint, DataPointId> {
	@Query("select * from DataPoint where id.account = @account")
    List<DataPoint> findByIdAccount(@Param("account") String account);
}
