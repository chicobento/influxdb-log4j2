/*
* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package com.cbnt;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.nosql.appender.NoSqlProvider;
import org.apache.logging.log4j.status.StatusLogger;

/**
 * The MongoDB implementation of {@link NoSqlProvider}.
 */
@Plugin(name = "InfluxDb", category = "Core", printObject = true)
public final class InfluxDbProvider implements NoSqlProvider<InfluxDbConnection> {
    
	private static final Logger LOGGER = StatusLogger.getLogger();

	private final String url;
	private final String username;
	private final String password;
	private final String databaseName;
	private final String seriesName;
	private final String transport;
	private final String description;

	private Integer udpPort;

    public InfluxDbProvider(final String databaseName, final String seriesName,
    		final String url, final String username, final String password, final String transport, final Integer udpPort) {
    	this.databaseName = databaseName;
		this.seriesName = seriesName;
		this.url = url;
		this.username = username;
		this.password = password;
		this.transport = transport;
		this.udpPort = udpPort;
		this.description = "InfluxDbProvider [" + databaseName + "]"; 
		validateConfiguration();
    }

	private void validateConfiguration() throws IllegalArgumentException {
	}

	@Override
    public InfluxDbConnection getConnection() {
        return new InfluxDbConnection(databaseName, seriesName, url, username, password, transport, udpPort);
    }

    @Override
    public String toString() {
        return this.description;
    }

    @PluginFactory
    public static InfluxDbProvider createNoSqlProvider(
            @PluginAttribute(value = "databaseName") final String databaseName,
            @PluginAttribute(value = "seriesName", defaultString = "applicationLog") final String seriesName,
            @PluginAttribute(value = "url") final String url,
            @PluginAttribute(value = "username") final String username,
            @PluginAttribute(value = "password", sensitive = true) final String password,
            @PluginAttribute(value = "transport", defaultString = "TCP") final String transport,
            @PluginAttribute(value = "udpPort", defaultInt = 4444) final Integer udpPort) {
        return new InfluxDbProvider(databaseName, seriesName, url, username, password, transport, udpPort);
    }

}
