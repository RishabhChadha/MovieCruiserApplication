package com.stackroute.moviecruiserserverapplication.exception;

public class MovieNotFoundException extends Exception {

	@SuppressWarnings("serial")
	
	String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public MovieNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MovieNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MovieNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public MovieNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
