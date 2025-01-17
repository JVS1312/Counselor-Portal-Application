package com.jspider.Exception;


public class EnquiryNotFound extends RuntimeException {
	
	private String message;
	
	public EnquiryNotFound(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
