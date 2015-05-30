influxdb-log4j2
=============

An InfluxDB Log4j 2 appender.

This implementation intends to add influxdb support to the [Log4j 2 NoSQL appenders](https://logging.apache.org/log4j/2.x/manual/appenders.html#NoSQLAppender) list.  

Follows a few sample configurations for the InfluxDb appender: 

### TCP mode:
```
    <?xml version="1.0" encoding="UTF-8"?>
    <Configuration status="error">
      <Appenders>
        <NoSql name="databaseAppender">
			<InfluxDb databaseName="applicationDb" seriesName="applicationLog"
				url="http://sandbox.influxdb.com:8086/" username="loggingUser"
				password="abc123" />
        </NoSql>
      </Appenders>
      <Loggers>
        <Root level="warn">
          <AppenderRef ref="databaseAppender"/>
        </Root>
      </Loggers>
    </Configuration>
```

### UDP mode
```
    <?xml version="1.0" encoding="UTF-8"?>
    <Configuration status="error">
      <Appenders>
        <NoSql name="databaseAppender">
			<InfluxDb databaseName="applicationDb" seriesName="applicationLog"
				url="http://sandbox.influxdb.com:8086/" username="loggingUser"
				password="abc123" transport="UDP" udpPort="4444" />
        </NoSql>
      </Appenders>
      <Loggers>
        <Root level="warn">
          <AppenderRef ref="databaseAppender"/>
        </Root>
      </Loggers>
    </Configuration>
```

### Maven
```
      <dependency>
        <groupId>com.cbnt</groupId>
        <artifactId>influxdb-log4j2</artifactId>
        <version>0.1-SNAPSHOT</version>
      </dependency>
```

### Build Requirements

* Java 1.6+
* Maven 3.0+

This implementation is still on early development days and hasn't been published yet to any public maven repository.
