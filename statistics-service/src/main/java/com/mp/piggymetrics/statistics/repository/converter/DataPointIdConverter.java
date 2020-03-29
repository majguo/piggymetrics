package com.mp.piggymetrics.statistics.repository.converter;

import java.util.Date;

import org.jnosql.artemis.AttributeConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mp.piggymetrics.statistics.domain.timeseries.DataPointId;

public class DataPointIdConverter implements AttributeConverter<DataPointId, DBObject>{
    
	@Override
    public DBObject convertToDatabaseColumn(DataPointId attribute) {
    	
    	DBObject object = new BasicDBObject(2);

		object.put("date", attribute.getDate());
		object.put("account", attribute.getAccount());
		
        return object;
    }
    
    @Override
    public DataPointId convertToEntityAttribute(DBObject dbData) {
    	Date date = (Date) dbData.get("date");
		String account = (String) dbData.get("account");

		return new DataPointId(account, date);
    }
}
