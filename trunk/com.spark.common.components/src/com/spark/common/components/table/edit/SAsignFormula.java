package com.spark.common.components.table.edit;

/**
 * 赋值公式：执行expressScript，将值赋给指定列<br>
 * 公式格式：{1}*{2}-{3}，其中1/2/3为列序号
 * 
 */
public class SAsignFormula extends SFormula {

	/**
	 * 赋值的目标
	 */
	private String target;

	/**
	 * 
	 * @param expressScript
	 */
	public SAsignFormula(String expressScript, String target) {
		super(expressScript);
		this.target = target;
	}

	/**
	 * 
	 * @return
	 */
	public String getTarget() {
		return target;
	}

}
