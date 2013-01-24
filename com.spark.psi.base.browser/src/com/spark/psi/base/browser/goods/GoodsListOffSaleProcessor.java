package com.spark.psi.base.browser.goods;

public final class GoodsListOffSaleProcessor extends GoodsListProcessor {
	
	public static enum ColumnName {
		name, code, shelfLife, warningDay
	}

	@Override
	protected boolean isViewOnSale() {
		return false;
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
