package com.study.response;

public class ResponseWrapper<T> {

	private boolean success;
	private String message;
	private T response;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public ResponseWrapper(T response) {
		init(true, "ok", response);
	}

	public ResponseWrapper(boolean success, String message, T response) {
		super();
		init(success, message, response);
	}

	private void init(boolean success, String message, T response) {
		this.success = success;
		this.message = message;
		this.response = response;
	}
	
	public static ResponseWrapper ERROR_RESPONSE(String error_msg) {
		return new ResponseWrapper<>(false, error_msg, null);
	}
	
	public static ResponseWrapper ERROR_RESPONSE() {
		return new ResponseWrapper<>(false, ErrorMsg.DEFAULT_DB_ERROR, null);
	}
	
	public static ResponseWrapper OK_RESPONSE() {
		return new ResponseWrapper<>(true, "ok", null);
	}
}
