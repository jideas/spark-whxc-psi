package com.spark.common.components.table.edit;

/**
 * �й�ʽ
 * 
 */
public abstract class SFormula {

	/**
	 * �ű����ʽ
	 */
	private String expressScript;

	/**
	 * 
	 * @param expressScript
	 */
	public SFormula(String expressScript) {
		super();
		this.expressScript = expressScript;
	}

	/**
	 * 
	 * @return
	 */
	public String getExpressScript() {
		return expressScript;
	}

}
