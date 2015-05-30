package com.cbnt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.influxdb.dto.Serie;

public class InfluxMapToSeriesConverter {

	private Map<String, Object> flatMap;

	public Serie convertToSerie(String seriesName, Map<String, Object> map) {
		this.flatMap = new HashMap<String, Object>();

		convertToFlat(null, map);
		
		String[] columns = flatMap.keySet().toArray(new String[flatMap.keySet().size()]);
		
		Object[] values = flatMap.values().toArray(new Object[flatMap.values().size()]);
		System.out.println(flatMap);
		return new Serie.Builder(seriesName).columns(columns).values(values).build();
	}

	private void convertToFlat(String parent, Map<String, Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (parent != null) {
				key = parent + "." + key;
			}
			Object value = entry.getValue();
			if (value instanceof Map<?, ?>) {
				convertToFlat(key, (Map<String, Object>) value);
			} else if (value == null || value instanceof String || value instanceof Number || value instanceof Date) {
				flatMap.put(key, value);
			} else {
				flatMap.put(key, value.toString());
			}
		}
	}
}
