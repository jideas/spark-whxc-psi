package com.spark.common.components.table.edit;

import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;

/**
 * 下拉列表编辑
 * 
 */
public class SListEditColumn extends SEditColumn {

	/**
	 * 
	 */
	private ListSourceAdapter listSource;

	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public SListEditColumn(String name, int width, int align, String... titles) {
		super(name, width, align, titles);
	}

	/**
	 * 
	 * @return
	 */
	public ListSourceAdapter getListSource() {
		return listSource;
	}

	/**
	 * 
	 * @param listSource
	 */
	public void setListSource(ListSourceAdapter listSource) {
		this.listSource = listSource;
	}

}
