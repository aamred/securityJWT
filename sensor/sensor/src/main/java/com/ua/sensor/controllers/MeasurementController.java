package com.ua.sensor.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ua.sensor.dto.MeasurementDTO;
import com.ua.sensor.dto.MeasurementResponse;
import com.ua.sensor.models.Measurement;
import com.ua.sensor.services.MeasurementService;
import com.ua.sensor.util.MeasurementErrorResponse;
import com.ua.sensor.util.MeasurementException;
import com.ua.sensor.util.MeasurementValidator;

import jakarta.validation.Valid;

/**
 * @author Anton Muzhytskyi
 */

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
	
	private final MeasurementService measurementService;
	private final MeasurementValidator measurementValidator;
	private final ModelMapper modelMapper;
	
	@Autowired
	public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator,
			ModelMapper modelMapper) {
		this.measurementService = measurementService;
		this.measurementValidator = measurementValidator;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO
			,BindingResult bindingResult){
		Measurement measurementToAdd = convertToMeasurement(measurementDTO);
		
		measurementValidator.validate(measurementToAdd, bindingResult);
		if(bindingResult.hasErrors())
			//returnErrorsToClient(bindingResult);
			return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		measurementService.addMeasurement(measurementToAdd);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping()
	public MeasurementResponse getMeasurements() {
		return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/rainyDays")
	public Long getRainyDays() {
		return measurementService.findAll().stream().filter(Measurement::getRaining).count();
	}
	
	private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
		return modelMapper.map(measurementDTO, Measurement.class );
	}
	
	private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
		return modelMapper.map(measurement, MeasurementDTO.class);
	}
	
	@ExceptionHandler
	private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
		MeasurementErrorResponse response = new MeasurementErrorResponse(
		e.getMessage(),
		System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	

}
