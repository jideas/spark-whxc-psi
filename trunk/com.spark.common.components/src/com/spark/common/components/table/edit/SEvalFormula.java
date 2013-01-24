package com.spark.common.components.table.edit;

/**
 * 执行脚本：主要用于对某些编列的可用性进行控制<br>
 * 公式格式：{1}.setEnabled(true) 其中1为列序号
 * 
 */
public class SEvalFormula extends SFormula {

	/**
	 * 
	 * @param expressScript
	 */
	public SEvalFormula(String expressScript) {
		super(expressScript);
	}

}
