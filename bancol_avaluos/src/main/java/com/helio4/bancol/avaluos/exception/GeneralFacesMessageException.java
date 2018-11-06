package com.helio4.bancol.avaluos.exception;

public class GeneralFacesMessageException extends Exception {
	private static final long serialVersionUID = 1L;

	private String title;
	private String message;

	public GeneralFacesMessageException(String title, String message) {
		super(title + " " + message);
		this.title = title;
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
