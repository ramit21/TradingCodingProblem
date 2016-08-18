package main.com.trade.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.com.trade.model.DataRow;
import main.com.trade.service.ServiceIntf;
import main.com.trade.service.impl.FeeCalcService;
import main.com.trade.util.ColumnComparator;

public class FeesCalcController extends ControllerTemplate { // Template DP

	private final String[] sortCols;

	public FeesCalcController(String inputFileName, String outputFileName, String[] sortCols) {
		super(inputFileName, outputFileName);
		this.sortCols = sortCols;
	}

	@Override
	public void computeFees(List<DataRow> dataList) {
		ServiceIntf<List<DataRow>, List<DataRow>> service = FeeCalcService.getService(); // stragy
																							// DP
		service.execute(dataList);
	}

	@Override
	public List<DataRow> reOrder(List<DataRow> dataList, int curIndex) {

		if (dataList.size() < 2 || curIndex >= sortCols.length) {
			return dataList;
		}
		String curSortKey = sortCols[curIndex];
		ColumnComparator comp = new ColumnComparator(curSortKey);
		Collections.sort(dataList, comp);

		List<DataRow> returnList = new ArrayList<>();
		List<DataRow> tmpList = new ArrayList<>();
		String prevValue = null;
		String curVal = null;
		for (DataRow data : dataList) {
			curVal = data.getDataValue(curSortKey);
			if (prevValue != null && !prevValue.equalsIgnoreCase(curVal)) {
				tmpList = reOrder(tmpList, ++curIndex);
				if (tmpList != null) {
					returnList.addAll(tmpList);
				}
				tmpList.clear();
			}
			tmpList.add(data);
			prevValue = curVal;
		}

		if (tmpList != null) {
			returnList.addAll(tmpList);
		}
		return returnList;
	}

}
