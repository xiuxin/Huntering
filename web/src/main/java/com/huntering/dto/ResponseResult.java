/**
 * 
 */
package com.huntering.dto;


/**
 * @author Bell
 *
 */
public class ResponseResult<T> {
	private boolean success = true;
	private T result;
	
	/**
	 * 
	 */
	public ResponseResult() {
	}
	
	
	public ResponseResult(boolean success, T result) {
		super();
		this.success = success;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	
}
