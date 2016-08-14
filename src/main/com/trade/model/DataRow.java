package main.com.trade.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataRow {

	private final Map<String, String> dataMap;
	private boolean isIntraDay;

	public DataRow() {
		dataMap = new LinkedHashMap<String, String>();
	}

	public void addData(String key, String value) {
		dataMap.put(key, value);
	}

	public String getDataValue(String key) {
		return dataMap.get(key);
	}

	public String getKeysAsString() {
		StringBuffer buf = new StringBuffer();
		for (String key : dataMap.keySet()) {
			buf.append(key);
			buf.append(",");
		}
		if (buf.length() != 0) {
			buf.deleteCharAt(buf.length()-1);
		}
		return buf.toString();
	}

	public String getValuesAsString() {
		StringBuffer buf = new StringBuffer();
		for (String key : dataMap.keySet()) {
			buf.append(dataMap.get(key));
			buf.append(",");
		}
		if (buf.length() != 0) {
			buf.deleteCharAt(buf.length()-1);
		}
		return buf.toString();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		for (Map.Entry<String, String> entry : dataMap.entrySet()) {
			buf.append(entry.getKey());
			buf.append("=");
			buf.append(entry.getValue());
			buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DataRow)) {
			return false;
		}
		boolean isEqual = true;
		DataRow rhsMap = (DataRow) obj;
		for (String key : dataMap.keySet()) {
			if (!dataMap.get(key).equalsIgnoreCase(rhsMap.getDataMap().get(key))) {
				isEqual = false;
				break;
			}
		}
		return super.equals(isEqual);
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public boolean isIntraDay() {
		return isIntraDay;
	}

	public void setIntraDay(boolean isIntraDay) {
		this.isIntraDay = isIntraDay;
	}
	
}
