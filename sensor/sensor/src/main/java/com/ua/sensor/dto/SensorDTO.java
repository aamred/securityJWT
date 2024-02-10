package com.ua.sensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

public class SensorDTO {

	@NotEmpty(message= "Name should not be empty")
	@Size(min=3, max=30, message = "Sensor's name shold be from3 to 30 characters")
	private String name;

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
}
