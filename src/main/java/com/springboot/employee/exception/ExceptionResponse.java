package com.springboot.employee.exception;


/**
 * Custom exception response class
 *
 */
public class ExceptionResponse {
	
	private String errorCode;
	private String errorMessage;
	private String status;
	
	public ExceptionResponse()
	{
		
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
