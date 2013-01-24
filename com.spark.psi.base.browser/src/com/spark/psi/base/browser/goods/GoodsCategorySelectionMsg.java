package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.type.GUID;

public class GoodsCategorySelectionMsg {

	private GUID categoryId;

	public GoodsCategorySelectionMsg(GUID categoryId) {
		super();
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryId
	 */
	public GUID getCategoryId() {
		return categoryId;
	}

}
