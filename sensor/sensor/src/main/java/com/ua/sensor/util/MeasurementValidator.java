package com.ua.sensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.ua.sensor.models.Measurement;
import com.ua.sensor.services.SensorService;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class MeasurementValidator implements Validator {
	
	private final SensorService sensorService;
	
	
	@Autowired
	public MeasurementValidator(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Measurement.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Measurement measurement = (Measurement)target;
		if(measurement.getSensor() == null) {
			return;
		}
		if(sensorService.findByName(measurement.getSensor().getName()).isEmpty())
			errors.rejectValue("sensor", "The sensor with this name is not exist");
	}

}
