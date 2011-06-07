package com.jayway.changeless.records;

/**
 * An exception indicating that a class can not be used for building 
 * records from. 
 */
public class RecordValidationException extends RuntimeException {
	private static final long serialVersionUID = 3601535581873860535L;

	/**
	 * Constructs a new record validation exception with null as its detail message. 
	 */
	public RecordValidationException() {
		super();
	}

	
	/**
	 * <p>
	 * Constructs a new record validation exception with the specified detail message and cause.
	 * </p>
	 * <p>
	 * Note that the detail message associated with cause is not automatically incorporated in this runtime exception's detail message.
	 * </p> 
	 * @param message the detail message
	 * @param cause the cause (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public RecordValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * <p>
	 * Constructs a new record validation exception with the specified detail message. 
	 * The cause is not initialized, and may subsequently be initialized by a call to 
	 * {@link Throwable#initCause(Throwable)}. 
	 * </p>
	 * @param message the detail message
	 */
	public RecordValidationException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * Constructs a new  record validation exception with the specified cause and a detail message of 
	 * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause). 
	 * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables. 
	 * </p>
	 * @param cause the cause (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public RecordValidationException(Throwable cause) {
		super(cause);
	}

}
