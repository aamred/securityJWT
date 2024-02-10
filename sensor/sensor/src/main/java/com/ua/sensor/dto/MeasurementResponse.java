package com.ua.sensor.dto;

import java.util.List;

/**
 * @author Anton Muzhytskyi
 */

public class MeasurementResponse {

	private List<MeasurementDTO> measurements;

	public MeasurementResponse(List<MeasurementDTO> measurements) {
		this.measurements = measurements;
	}
	
	public List<MeasurementDTO> getMeasurements(){
		return measurements;
	}

	public void setMeasurements(List<MeasurementDTO> measurements) {
		this.measurements = measurements;
	}
	
}
