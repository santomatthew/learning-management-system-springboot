package com.lawencon.lmssanto.dto;

public class ErrorResDto<T> {

	private T message;

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

}
