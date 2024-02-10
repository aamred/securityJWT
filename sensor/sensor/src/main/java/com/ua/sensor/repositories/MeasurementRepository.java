package com.ua.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ua.sensor.models.Measurement;

/**
 * @author Anton Muzhytskyi
 */

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer>{

}
