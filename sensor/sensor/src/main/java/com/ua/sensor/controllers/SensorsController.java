package com.ua.sensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ua.sensor.dto.SensorDTO;
import com.ua.sensor.models.Sensor;
import com.ua.sensor.services.SensorService;
import com.ua.sensor.util.MeasurementErrorResponse;
import com.ua.sensor.util.MeasurementException;
import com.ua.sensor.util.SensorValidator;

import jakarta.validation.Valid;

/**
 * @author Anton Muzhytskyi
 */

@RestController
@RequestMapping("/sensors")
public class SensorsController {
	
	private final SensorService sensorService;
	private final ModelMapper modelMapper;
	private final SensorValidator sensorValidator;
	
	@Autowired
	public SensorsController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
		super();
		this.sensorService = sensorService;
		this.modelMapper = modelMapper;
		this.sensorValidator = sensorValidator;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, 
			BindingResult bindingResult){
		
		Sensor sensorToAdd = convertToSensor(sensorDTO);
		sensorValidator.validate(sensorToAdd, bindingResult);
		if(bindingResult.hasErrors())
			//returnErrorsToClient(bindingResult);
			return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		sensorService.register(sensorToAdd);
		return ResponseEntity.ok(HttpStatus.OK);
		}

	@ExceptionHandler
	private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
		MeasurementErrorResponse response = new MeasurementErrorResponse(
				e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
	private Sensor convertToSensor(SensorDTO sensorDTO) {
		return modelMapper.map(sensorDTO, Sensor.class);
	}

}
