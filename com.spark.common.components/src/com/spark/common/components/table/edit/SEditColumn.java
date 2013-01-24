package com.spark.common.components.table.edit;

import com.spark.common.components.table.STableColumn;

/**
 * 编辑列信息
 */
public abstract class SEditColumn extends STableColumn {

	/**
	 * 值发生变化时执行的公式
	 */
	private SFormula[] formulas;

	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public SEditColumn(String name, int width, int align, String[] titles) {
		super(name, width, align, titles);
	}

	/**
	 * 
	 * @return
	 */
	public SFormula[] getFormulas() {
		return formulas;
	}

	/**
	 * 
	 * @param formulas
	 */
	public void setFormulas(SFormula... formulas) {
		this.formulas = formulas;
	}

}
