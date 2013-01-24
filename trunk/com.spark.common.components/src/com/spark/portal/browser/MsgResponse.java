package com.spark.portal.browser;

public class MsgResponse {

	/**
	 * 
	 */
	private Object returnValue;

	/**
	 * 
	 */
	private Object returnValue2;

	/**
	 * 
	 */
	private Object returnValue3;

	/**
	 * 
	 */
	private Object returnValue4;

	/**
	 * 
	 */
	private boolean finished;

	/**
	 * 
	 * @param finished
	 */
	public MsgResponse(boolean finished) {
		this(finished, null);
	}

	/**
	 * 
	 * @param finished
	 * @param returnValue
	 */
	public MsgResponse(boolean finished, Object returnValue) {
		this(finished, returnValue, null);
	}

	/**
	 * 
	 * @param finished
	 * @param returnValue
	 * @param returnValue2
	 */
	public MsgResponse(boolean finished, Object returnValue, Object returnValue2) {
		this(finished, returnValue, returnValue2, null);
	}

	/**
	 * 
	 * @param finished
	 * @param returnValue
	 * @param returnValue2
	 * @param returnValue3
	 */
	public MsgResponse(boolean finished, Object returnValue,
			Object returnValue2, Object returnValue3) {
		this(finished, returnValue, returnValue2, returnValue3, null);
	}

	/**
	 * 
	 * @param finished
	 * @param returnValue
	 * @param returnValue2
	 * @param returnValue3
	 * @param returnValue4
	 */
	public MsgResponse(boolean finished, Object returnValue,
			Object returnValue2, Object returnValue3, Object returnValue4) {
		this.finished = finished;
		this.returnValue = returnValue;
		this.returnValue2 = returnValue2;
		this.returnValue3 = returnValue3;
		this.returnValue4 = returnValue4;
	}

	/**
	 * @return the returnValue
	 */
	public Object getReturnValue() {
		return returnValue;
	}

	/**
	 * @return the returnValue2
	 */
	public Object getReturnValue2() {
		return returnValue2;
	}

	/**
	 * @return the returnValue3
	 */
	public Object getReturnValue3() {
		return returnValue3;
	}

	/**
	 * @return the returnValue4
	 */
	public Object getReturnValue4() {
		return returnValue4;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

}
