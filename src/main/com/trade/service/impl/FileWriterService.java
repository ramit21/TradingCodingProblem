package main.com.trade.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import main.com.trade.exception.FileWriteException;
import main.com.trade.model.DataRow;
import main.com.trade.model.FileWriterDM;
import main.com.trade.service.ServiceIntf;

public class FileWriterService implements ServiceIntf<FileWriterDM, Boolean> {

	public static FileWriterService service = new FileWriterService();

	public static FileWriterService getService() {
		return service;
	}

	@Override
	public Boolean execute(FileWriterDM inputDM) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(inputDM.getFileName()));
			List<DataRow> dataList = inputDM.getDataList();
			if (dataList.isEmpty()) {
				writer.close();
				return false;
			}
			writer.write(inputDM.getDataList().get(0).getKeysAsString() + "\n");
			for (DataRow dataRow : dataList) {
				writer.write(dataRow.getValuesAsString() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			throw new FileWriteException.Builder().withFileName(inputDM.getFileName()).withErrorMessage(e.getMessage()).build();
		}
		return true;
	}

}
