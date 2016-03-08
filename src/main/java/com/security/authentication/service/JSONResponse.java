package com.security.authentication.service;

/**
 * Used in spring security authentication services for generate JSON response
 * 
 * @author Pranav
 *
 * @param <T>
 */
public class JSONResponse<T> {

	private String status;
	private String message;
	T data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
