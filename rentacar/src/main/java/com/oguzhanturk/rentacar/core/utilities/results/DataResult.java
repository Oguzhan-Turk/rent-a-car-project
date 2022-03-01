package com.oguzhanturk.rentacar.core.utilities.results;

public class DataResult<T> extends Result {

	private T data;

	public DataResult(T data, boolean success, String messages) {
		super(success, messages);
		this.data = data;
	}

	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}

	public T getData() {
		return data;
	}

}
