package com.cbnt;

import java.util.ArrayList;
import java.util.List;

public class InfluxDbNoSqlObject {

	private List<String> columns;
	private List<Object> values;
	
	public InfluxDbNoSqlObject() {
		this.columns = new ArrayList<String>();
		this.values = new ArrayList<Object>();
	}
	
	public void addItem(String column, Object value) {
		this.columns.add(column);
		this.values.add(value);
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<Object> getValues() {
		return values;
	}
	
}
