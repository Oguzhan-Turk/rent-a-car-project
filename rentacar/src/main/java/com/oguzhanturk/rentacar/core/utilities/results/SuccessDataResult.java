package com.oguzhanturk.rentacar.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(T data, String messages) {
		super(data, true, messages);
	}
	public SuccessDataResult(T data) {
		super(data, true);
	}
	public SuccessDataResult() {
		super(null, true);
	}
}
