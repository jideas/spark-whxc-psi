package com.spark.common.components.table.edit;

/**
 * 下拉列表编辑2
 * 
 */
public class SListEdit2Column extends SEditColumn {

	private int maxItemCount;

	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public SListEdit2Column(String name, int width, int align,
			int maxItemCount, String... titles) {
		super(name, width, align, titles);
		this.maxItemCount = maxItemCount;
	}

	public int getMaxItemCount() {
		return maxItemCount;
	}
}
