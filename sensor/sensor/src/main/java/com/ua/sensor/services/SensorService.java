package com.ua.sensor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ua.sensor.models.Sensor;
import com.ua.sensor.repositories.SensorRepository;

/**
 * @author Anton Muzhytskyi
 */

@Service
@Transactional(readOnly = true)
public class SensorService {

	private final SensorRepository sensorRepository;
	
	@Autowired
	public SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}
	
	public Optional<Sensor> findByName(String b){
		return sensorRepository.findByName(b);
	}
	
	@Transactional
	public void register(Sensor sensor) {
		sensorRepository.save(sensor);
	}
}
