influxdb-log4j2
=============

An InfluxDB Log4j 2 appender.

This implementation intends to add influxdb support to the [Log4j 2 NoSQL appenders](https://logging.apache.org/log4j/2.x/manual/appenders.html#NoSQLAppender) list.  

# Usage
Follows a few sample configurations for the InfluxDb appender: 

### TCP mode:
```XML
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
```XML
    <?xml version="1.0" encoding="UTF-8"?>
    <Configuration status="error">
      <Appenders>
        <NoSql name="databaseAppender">
			<InfluxDb seriesName="applicationLog" url="http://sandbox.influxdb.com:8086/"
			username="loggingUser" password="abc123" transport="UDP" udpPort="4444" />
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
```XML
      <dependency>
        <groupId>com.cbnt</groupId>
        <artifactId>influxdb-log4j2</artifactId>
        <version>0.1-SNAPSHOT</version>
      </dependency>
```

# Sample Data

An example of an exception logged with influxdb-log4j2:

|time|sequence_number|thrown.type|threadName|source.className|source.lineNumber|thrown.message|source.fileName|contextStack|thrown.stackTrace|marker|date|level|thrown.cause.message|source.methodName|message|thrown.cause.type|millis|thrown.cause.stackTrace|loggerName|
----|---------------|-----------|----------|----------------|-----------------|--------------|---------------|------------|-----------------|------|----|-----|--------------------|-----------------|-------|-----------------|------|-----------------------|----------|
|1433015646874|200001|java.lang.RuntimeException|main|com.cbnt.HelloWorld|13|A Random exception|HelloWorld.java|[]|[{fileName=HelloWorld.java, methodName=testC, className=com.cbnt.HelloWorld, lineNumber=28}, {fileName=HelloWorld.java, methodName=testB, className=com.cbnt.HelloWorld, lineNumber=23}, {fileName=HelloWorld.java, methodName=testA, className=com.cbnt.HelloWorld, lineNumber=19}, {fileName=HelloWorld.java, methodName=main, className=com.cbnt.HelloWorld, lineNumber=11}]||May 30, 2015 4:54:06 PM|ERROR|A Random inner exception|main|Ohhh no, a fucking exception|java.lang.Exception|1433015646746|[{fileName=HelloWorld.java, methodName=testC, className=com.cbnt.HelloWorld, lineNumber=28}, {fileName=HelloWorld.java, methodName=testB, className=com.cbnt.HelloWorld, lineNumber=23}, {fileName=HelloWorld.java, methodName=testA, className=com.cbnt.HelloWorld, lineNumber=19}, {fileName=HelloWorld.java, methodName=main, className=com.cbnt.HelloWorld, lineNumber=11}]|com.cbnt.HelloWorld|

### Build Requirements

* Java 1.6+
* Maven 3.0+

This implementation is still on early development days and hasn't been published yet to any public maven repository.
