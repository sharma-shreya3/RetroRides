package edu.northeastern.info6250.RetroRides.util;

import java.time.Year;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AttributeConverter implements jakarta.persistence.AttributeConverter<Year, Short> {

	@Override
	public Short convertToDatabaseColumn(Year attribute) {
		// TODO Auto-generated method stub
		  if (attribute != null) {
	            return (short) attribute.getValue();
	        }
		return null;
	}

	@Override
	public Year convertToEntityAttribute(Short dbData) {
		  if (dbData != null) {
	            return Year.of(dbData);
	        }
		return null;
	}

}
