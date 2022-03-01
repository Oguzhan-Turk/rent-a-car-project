package com.oguzhanturk.rentacar.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T> {

	public ErrorDataResult(T data, String messages) {
		super(data, false, messages);
	}

	public ErrorDataResult(T data) {
		super(data, false);
	}

	public ErrorDataResult() {
		super(null, false);
	}

}
