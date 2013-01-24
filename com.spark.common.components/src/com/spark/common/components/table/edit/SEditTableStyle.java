package com.spark.common.components.table.edit;

import com.spark.common.components.table.STableStyle;

/**
 * 
 */
public class SEditTableStyle extends STableStyle {

	/**
	 * 
	 */
	private boolean autoAddRow;

	/**
	 * @return the autoAddRow
	 */
	public boolean isAutoAddRow() {
		return autoAddRow;
	}

	/**
	 * @param autoAddRow
	 *            the autoAddRow to set
	 */
	public void setAutoAddRow(boolean autoAddRow) {
		this.autoAddRow = autoAddRow;
	}
	
}
