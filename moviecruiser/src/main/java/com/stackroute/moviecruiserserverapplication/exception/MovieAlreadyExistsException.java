package com.stackroute.moviecruiserserverapplication.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {

	String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public MovieAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MovieAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MovieAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MovieAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
