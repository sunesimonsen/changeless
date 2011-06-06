package com.jayway.changeless.records;

public class RecordValidationException extends RuntimeException {
	private static final long serialVersionUID = 3601535581873860535L;

	public RecordValidationException() {
		super();
	}

	public RecordValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordValidationException(String message) {
		super(message);
	}

	public RecordValidationException(Throwable cause) {
		super(cause);
	}

}
