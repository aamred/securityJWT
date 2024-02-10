package com.ua.sensor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ua.sensor.models.Sensor;

/**
 * @author Anton Muzhytskyi
 */

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {
	Optional<Sensor> findByName(String name);

}
