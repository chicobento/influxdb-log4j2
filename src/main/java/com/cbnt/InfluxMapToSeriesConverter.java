package com.cbnt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

public class InfluxMapToSeriesConverter {

	private BatchPoints batchPoints;
	private String measurement;
	
	private Map<String, Object> pointFields;
	private TimeUnit pointTimeUnit = TimeUnit.MILLISECONDS;
	private Long pointTime;
	
	public InfluxMapToSeriesConverter(String measure, BatchPoints bp){
		batchPoints = bp;
		measurement = measure;
		pointFields = new HashMap<String, Object>();
		pointTime = System.currentTimeMillis();
	}

	public BatchPoints convertToBatchPoints(Map<String, Object> map) {
		
		convertToFields(null, map);
		addPointToBatch();
		return batchPoints;
				
	}

	@SuppressWarnings("unchecked")
	private void convertToFields(String parent, Map<String, Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (parent != null) {
				key = parent + "." + key;
			}
			Object value = entry.getValue();
			if(value == null || value == ""){
				continue;
			}
			if (value instanceof Map<?, ?>) {
				convertToFields(key, (Map<String, Object>) value);
			} else if (value instanceof String || value instanceof Number) {
				addFieldToPoint(key, value);
			} else {
				addFieldToPoint(key, value.toString());
			}
	
		}
		
		
	}
	
	private void addFieldToPoint(String k, Object v){
		pointFields.put(k, v);
	}
	
	private void addPointToBatch(){
		
		Point point = Point.measurement(measurement)
                .time(pointTime, pointTimeUnit)
                .fields(pointFields)
                .build();
		
		batchPoints.point(point);
	}
	
}
