package main.com.trade.exception;

public class FileReadException extends RuntimeException {

	private static final long serialVersionUID = -718058430456565498L;
	private final String errorMessage;
	private final String fileName;

	private FileReadException(Builder builder) {
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

		public FileReadException build() {
			return new FileReadException(this);
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getFileName() {
		return fileName;
	}

}
