package com.ua.sClient.dto;

import java.util.List;



/**
 * @author Anton Muzhytskyi
 */

public class MeasurementsResponse {

	 List<MeasurementDTO> measurements;

	public List<MeasurementDTO> getMeasurements(){
		return measurements;
	}

	public void setMeasurements(List<MeasurementDTO> measurements) {
		this.measurements = measurements;
	}
}
