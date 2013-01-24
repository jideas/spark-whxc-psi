package com.spark.common.components.table;

import com.jiuqi.dna.ui.wt.graphics.Color;

public class STableTextColor {
	private String rowId;
	private int columnIndex;
	private Color color;
	
	public STableTextColor(String rowId, int columnIndex, Color color) {
		super();
		this.rowId = rowId;
		this.columnIndex = columnIndex;
		this.color = color;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
