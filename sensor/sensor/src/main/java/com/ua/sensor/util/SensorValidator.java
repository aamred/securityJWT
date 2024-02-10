package com.ua.sensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ua.sensor.models.Sensor;
import com.ua.sensor.services.SensorService;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class SensorValidator implements Validator {
	
	private final SensorService sensorService;
	
	@Autowired
	public SensorValidator(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Sensor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sensor sensor = (Sensor) target;
		
		if(sensorService.findByName(sensor.getName()).isPresent())
			errors.rejectValue("name", "The sensor with this name exist already");
	}	

}
