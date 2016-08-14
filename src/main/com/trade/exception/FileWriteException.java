package main.com.trade.exception;

public class FileWriteException extends RuntimeException {

	private static final long serialVersionUID = 9040782968230129943L;
	private final String errorMessage;
	private final String fileName;

	private FileWriteException(Builder builder) {
		this.errorMessage = builder.errorMessage;
		this.fileName = builder.fileName;
	}

	public static class Builder {
		private String errorMessage;
		private String fileName;

		public Builder withErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public Builder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public FileWriteException build() {
			return new FileWriteException(this);
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getFileName() {
		return fileName;
	}

}
