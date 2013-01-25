package com.spark.order.intf.publish.entity;


/**
 * 销售退货详情维护
 * @author zhoulijun
 *
 */
public final class SalesReturnInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.SalesReturnInfo{
	
	/**
	 * 销售退货商品明细
	 */
	private SalesReturnGoodsItemImpl[] returnGoodsItems;

	public SalesReturnGoodsItemImpl[] getReturnGoodsItems(){
    	return returnGoodsItems;
    }

	/**
	 * @param returnGoodsItems the returnGoodsItems to set
	 */
	public void setReturnGoodsItems(SalesReturnGoodsItemImpl[] returnGoodsItems) {
		this.returnGoodsItems = returnGoodsItems;
	}
	
	
	
}
