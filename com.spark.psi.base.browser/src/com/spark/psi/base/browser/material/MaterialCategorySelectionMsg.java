package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.type.GUID;

public class MaterialCategorySelectionMsg {
	private GUID categoryId;

	public MaterialCategorySelectionMsg(GUID categoryId) {
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
