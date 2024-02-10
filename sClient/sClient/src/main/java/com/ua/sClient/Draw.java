package com.ua.sClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import com.ua.sClient.dto.MeasurementDTO;
import com.ua.sClient.dto.MeasurementsResponse;


/**
 * @author Anton Muzhytskyi
 */

public class Draw {
	public static void main(String[] args) {
		List<Double> temperatures = getTemperaturesFromServer();
		draw(temperatures);
	}

	private static List<Double> getTemperaturesFromServer() {
		final RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:8080/measurements";
		
		MeasurementsResponse jsonResponse = restTemplate.getForObject(url,  MeasurementsResponse.class);
		
		if( jsonResponse == null || jsonResponse.getMeasurements() == null)
			return Collections.emptyList();
		return jsonResponse.getMeasurements().stream().map(MeasurementDTO::getValue)
				.collect(Collectors.toList());
	}
	
	
	// Can not connect QuickChart
	private static void draw(List<Double> temperatures) {
	//	double[] xLine = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
	//	double[] yLine = temperatures.stream().mapToDouble(x ->x).toArray();	
	//	XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature", xLine, yLine);
	//	new SwingWrapper(chart).displayChart();
		
		System.out.println(temperatures);
	}
	
	
}
