package com.spark.common.components.table.edit;

/**
 * 列公式
 * 
 */
public abstract class SFormula {

	/**
	 * 脚本表达式
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
