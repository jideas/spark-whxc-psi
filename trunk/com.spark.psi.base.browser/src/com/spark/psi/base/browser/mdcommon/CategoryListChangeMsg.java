package com.spark.psi.base.browser.mdcommon;

import com.jiuqi.dna.core.type.GUID;

public class CategoryListChangeMsg {
	private GUID categoryId;
	
	public CategoryListChangeMsg(GUID categoryId) {
		this.categoryId = categoryId;
	}

	public GUID getCategoryId() {
		return categoryId;
	}

	
}
