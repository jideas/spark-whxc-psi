package com.spark.psi.base.browser.goods;

public final class GoodsListOnSaleJointProcessor extends GoodsListProcessor {
	
	public static enum ColumnName {
		supplier, name, code, shelfLife, warningDay
	}

	@Override
	protected boolean isViewJointOnly() {
		return true;
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
	
	
}