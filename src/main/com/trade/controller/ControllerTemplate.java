package main.com.trade.controller;

import java.util.List;

import main.com.trade.model.DataRow;
import main.com.trade.model.FileWriterDM;
import main.com.trade.service.ServiceIntf;
import main.com.trade.service.impl.FileReaderService;
import main.com.trade.service.impl.FileWriterService;

public abstract class ControllerTemplate {

	private final String inputFileName;
	private final String outputFileName;

	public ControllerTemplate(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	public final boolean produceTransactionFeeFile() {
		List<DataRow> dataList = readFile();
		computeFees(dataList);
		dataList = reOrder(dataList, 0);
		return writeFile(dataList);
	}

	public List<DataRow> readFile() {
		ServiceIntf<String, List<DataRow>> service = FileReaderService.getService();
		return service.execute(inputFileName);
	}

	public abstract void computeFees(List<DataRow> dataList);

	public List<DataRow> reOrder(List<DataRow> dataList, int curIndex) {// hook
																		// method
		return dataList;
	}

	public Boolean writeFile(List<DataRow> dataList) {
		ServiceIntf<FileWriterDM, Boolean> service = FileWriterService.getService();
		FileWriterDM writerDm = new FileWriterDM.Builder().withDataList(dataList).withFileName(outputFileName).build();
		return service.execute(writerDm);
	}
}
