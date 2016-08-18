package main.com.trade.util;

import java.util.Comparator;

import main.com.trade.model.DataRow;

public class ColumnComparator implements Comparator<DataRow> {

	private final String key;

	public ColumnComparator(String key) {
		this.key = key;
	}

	@Override
	public int compare(DataRow o1, DataRow o2) {
		return o1.getDataValue(key).compareTo(o2.getDataValue(key));
	}

}
