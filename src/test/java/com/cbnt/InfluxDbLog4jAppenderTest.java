package com.cbnt;

import org.apache.logging.log4j.Logger;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;

// special thanks to https://logging.apache.org/log4j/log4j-2.3/manual/configuration.html

public class InfluxDbLog4jAppenderTest {

	private static final Logger logger = LogManager.getLogger(InfluxDbLog4jAppenderTest.class);

	
	 public static void main(final String... args) {
		    	 
	        logger.trace("Entering Test.");
	        InfluxDbLog4jAppenderTest test = new InfluxDbLog4jAppenderTest();
	        
	        test.doGood();
	        
	        test.doBad();
	        
	        logger.trace("Exiting Test.");
	        
	        System.exit(0);
	        
	 }
	 
	 public void doGood(){
		 logger.info("Did it.");
	 }
	
	 public void doBad(){
		 logger.error("Didn't do it.");
	 }
	 
}
