package com.ua.sensor.models;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * @author Anton Muzhytskyi
 */

@Entity
@Table(name="Sensor")
public class Sensor implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	@NotEmpty(message="Name should not be empty")
	@Size(min = 3 , max = 30, message = "Name of sensor should be from 3 to 30 characters")
	private String name;

	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}	
	
}
