package main.com.trade.controller;

import java.util.List;

import main.com.trade.model.DataRow;
import main.com.trade.service.ServiceIntf;
import main.com.trade.service.impl.FeeCalcService;

public class FeesCalcController extends ControllerTemplate{//TemplateDP
	
	public FeesCalcController(String inputFileName, String outputFileName){
		super(inputFileName,outputFileName);
	}

	@Override
	public void computeFees(List<DataRow> dataList) {
		ServiceIntf<List<DataRow>,List<DataRow>> service = FeeCalcService.getService();// strategy DP
		service.execute(dataList);
	}

}
