package com.cbnt;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.nosql.appender.DefaultNoSqlObject;
import org.apache.logging.log4j.nosql.appender.NoSqlConnection;
import org.apache.logging.log4j.nosql.appender.NoSqlObject;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Serie;

/**
 * The MongoDB implementation of {@link NoSqlConnection}.
 */
public final class InfluxDbConnection implements NoSqlConnection<Map<String, Object>, DefaultNoSqlObject> {

	
	private InfluxDB influxDB;
	private String seriesName;
	private String database;
	private boolean useUdp;
	private Integer udpPort;
	
	public InfluxDbConnection(String databaseName, String serieName, String url, String username, String password, String transport, Integer udpPort) {
		this.database = databaseName;
		this.seriesName = serieName;
		this.influxDB = InfluxDBFactory.connect(url, username, password);
		this.useUdp = "UDP".equalsIgnoreCase(transport);
		this.udpPort = udpPort;
	}
	
	@Override
	public DefaultNoSqlObject createObject() {
		return new DefaultNoSqlObject();
	}

	@Override
	public DefaultNoSqlObject[] createList(int length) {
		return new DefaultNoSqlObject[length];
	}
	
	@Override
	public void insertObject(NoSqlObject<Map<String, Object>> object) {
		InfluxMapToSeriesConverter converter = new InfluxMapToSeriesConverter();
		Serie serie = converter.convertToSerie(seriesName, object.unwrap());
		
		if (!useUdp) {
			influxDB.write(database, TimeUnit.MILLISECONDS, serie);
		} else {
			influxDB.writeUdp(udpPort, serie);
		}
	}

	@Override
	public void close() {
		// Do nothing...
	}

	@Override
	public boolean isClosed() {
		return false;
	}

}
