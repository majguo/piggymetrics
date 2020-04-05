package com.mp.piggymetrics.statistics.repository.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bson.types.Decimal128;
import org.jnosql.artemis.AttributeConverter;

public class BigDecimalConverter implements AttributeConverter<BigDecimal, Decimal128>{
    
	@Override
    public Decimal128 convertToDatabaseColumn(BigDecimal attribute) {
        return new Decimal128(attribute.setScale(10, RoundingMode.CEILING));
    }
    
    @Override
    public BigDecimal convertToEntityAttribute(Decimal128 dbData) {
    	return dbData.bigDecimalValue();
    }
}
