package main.com.trade.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import main.com.trade.exception.FileReadException;
import main.com.trade.model.DataRow;
import main.com.trade.service.ServiceIntf;

public class FileReaderService implements ServiceIntf<String, List<DataRow>> {

	private static FileReaderService readerService = new FileReaderService();

	private FileReaderService() {
	}

	public static FileReaderService getService() {
		return readerService;
	}

	@Override
	public List<DataRow> execute(String fileName) {
		List<DataRow> dataList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String[] colNames = reader.readLine().split(",");
			String buf;
			while ((buf = reader.readLine()) != null) {
				String[] values = buf.split(",");
				DataRow dataRow = new DataRow();
				for (int i = 0; i < colNames.length; i++) {
					dataRow.addData(colNames[i], values[i]);
				}
				dataList.add(dataRow);
			}
			reader.close();
		} catch (Exception e) {
			throw new FileReadException.Builder().withFileName(fileName).withErrorMessage(e.getMessage()).build();
		}
		return dataList;
	}

}
