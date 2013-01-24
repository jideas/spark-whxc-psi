package com.spark.common.components.table.edit;

import com.spark.common.components.table.STableColumn;

/**
 * �༭����Ϣ
 */
public abstract class SEditColumn extends STableColumn {

	/**
	 * ֵ�����仯ʱִ�еĹ�ʽ
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
