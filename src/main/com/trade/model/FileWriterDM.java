package main.com.trade.model;

import java.util.List;

public class FileWriterDM {

	private final List<DataRow> dataList;
	private final String fileName;

	private FileWriterDM(Builder builder) {
		this.dataList = builder.dataList;
		this.fileName = builder.fileName;
	}

	public static class Builder {

		private List<DataRow> dataList;
		private String fileName;

		public Builder withDataList(List<DataRow> dataList) {
			this.dataList = dataList;
			return this;
		}

		public Builder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public FileWriterDM build() {
			return new FileWriterDM(this);
		}
	}

	public List<DataRow> getDataList() {
		return dataList;
	}

	public String getFileName() {
		return fileName;
	}
}
