package com.spark.psi.publish;

public abstract class MultItemRowObject {
	private String id;
	private int rowSpan;
	private boolean isFirstItem = false;
	
	public MultItemRowObject(String id, int rowSpan, boolean isFirstItem) {
		this.id = id;
		this.rowSpan = rowSpan;
		this.isFirstItem = isFirstItem;
	}

	public final String getId() {
		return id;
	}

	public final int getRowSpan() {
		return rowSpan;
	}

	public final boolean isFirstItem() {
		return isFirstItem;
	}
}


