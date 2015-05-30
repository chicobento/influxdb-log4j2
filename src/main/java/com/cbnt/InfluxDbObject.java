package com.cbnt;

import org.apache.logging.log4j.nosql.appender.NoSqlObject;

public final class InfluxDbObject implements NoSqlObject<InfluxDbNoSqlObject> {

	protected InfluxDbNoSqlObject data;
	
    public InfluxDbObject() {
    	data = new InfluxDbNoSqlObject();
    }

    @Override
    public void set(final String field, final Object value) {
        this.data.addItem(field, value);
    }

    @Override
    public void set(final String field, final NoSqlObject<InfluxDbNoSqlObject> value) {
    	this.data.addItem(field, value);
    }

    @Override
    public void set(final String field, final Object[] values) {
    	this.data.addItem(field, values);
    }

    @Override
    public InfluxDbNoSqlObject unwrap() {
	    return data;
    }

	@Override
	public void set(String field, NoSqlObject<InfluxDbNoSqlObject>[] values) {
		this.data.addItem(field, values);
	}
}
