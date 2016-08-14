package main.com.trade.service.impl;

import java.util.List;

import main.com.trade.model.DataRow;
import main.com.trade.service.ServiceIntf;
import main.com.trade.util.TradeConstants;

public class FeeCalcService implements ServiceIntf<List<DataRow>, List<DataRow>> {

	private static FeeCalcService service = new FeeCalcService();

	private FeeCalcService() {
	}

	public static FeeCalcService getService() {
		return service;
	}

	@Override
	public List<DataRow> execute(List<DataRow> input) {
		markIntraDay(input);
		for (DataRow data : input) {

			double fees = Double.valueOf(data.getDataValue("Quantity")) * Double.valueOf(data.getDataValue("PricePerUnit"));
			if (data.isIntraDay()) {
				fees *= TradeConstants.INTRA_DAY_FEES;
			} else {
				fees *= TradeConstants.REGULAR_FEES;
			}
			data.addData(TradeConstants.FEES_COL_HEADER, String.valueOf(fees));
		}

		return null;
	}

	// inlcude logic to include part orders
	// eg buy 100, sell 50, and again sell 50
	private void markIntraDay(List<DataRow> data) {
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = 0; j < data.size(); j++) {
				DataRow data1 = data.get(i);
				DataRow data2 = data.get(j);
				if (!data1.isIntraDay() && !data2.isIntraDay() && data1.getDataValue("ClientName").equals(data2.getDataValue("ClientName"))
						&& data1.getDataValue("Date").equals(data2.getDataValue("Date"))
						&& !data1.getDataValue("Type").equalsIgnoreCase(data2.getDataValue("Type"))) {
					data1.setIntraDay(true);
					data2.setIntraDay(true);
				}
			}
		}
	}

}
