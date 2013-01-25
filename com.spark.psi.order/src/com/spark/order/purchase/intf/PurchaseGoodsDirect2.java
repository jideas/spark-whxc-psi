package com.spark.order.purchase.intf;

import com.jiuqi.dna.core.type.GUID;


/**
 * <p>采购商品(直供)</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
public class PurchaseGoodsDirect2 extends PurchaseGoods2{
	protected GUID sourceSaleItemId;//	来源销售明细ID	guid
	protected GUID sourceSaleId;//来源销售订单ID
	protected boolean deleteFlag;//删除标志
	
	public PurchaseGoodsDirect2(){
		this.purchaseCause = PurchaseConstant2.directCause;
	}
	
	public GUID getSourceSaleItemId() {
		return sourceSaleItemId;
	}
	public void setSourceSaleItemId(GUID sourceSaleItemId) {
		this.sourceSaleItemId = sourceSaleItemId;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public GUID getSourceSaleId() {
		return sourceSaleId;
	}

	public void setSourceSaleId(GUID sourceSaleId) {
		this.sourceSaleId = sourceSaleId;
	}
	
}
