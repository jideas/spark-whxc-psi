package com.spark.order.intf.publish.entity;


/**
 * �����˻�����ά��
 * @author zhoulijun
 *
 */
public final class SalesReturnInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.SalesReturnInfo{
	
	/**
	 * �����˻���Ʒ��ϸ
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
