/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns;
import com.spark.psi.order.browser.util.OrderDetailRender;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;

/**
 * 销售退货单明细视图
 * 
 */
public abstract class AbstractSalesReturnSheetDetailRender extends OrderDetailRender {
	@Override
	protected final Cloumns[] getColumnsEnumList() {
		return new Cloumns[] { Cloumns.ReturnStore, Cloumns.GoodsItemCode,Cloumns.GoodsNo,
				Cloumns.GoodsName, Cloumns.GoodsProperties, Cloumns.GoodsUnit,
				Cloumns.ReturnCount, Cloumns.ReturnPrice, Cloumns.ReturnAmount };
	}
	
	/**
	 * 
	 */
	public final int getDecimal(Object element, int columnIndex) {
		switch(columnIndex) {
		case 6:
			SalesReturnGoodsItem item = (SalesReturnGoodsItem) element;
			return item.getScale(); 
		case 7:
			return 2;
		case 8:
			return 2;
		}
		return -1;
	}

	@Override
	protected final String getText(Object element, Cloumns columnEnum) {
		if(null == columnEnum){
			return "";
		}
		SalesReturnGoodsItem item = (SalesReturnGoodsItem) element;
		switch (columnEnum) {
		case ReturnStore:
			return item.getStoreName();
		default:
			return super.getText(element, columnEnum);
		}
	}
	
	@Override
	protected String getOrderTotalAmountTitle() {
		return "退货总额";
	}
}
