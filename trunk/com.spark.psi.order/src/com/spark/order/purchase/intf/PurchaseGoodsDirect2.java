package com.spark.order.purchase.intf;

import com.jiuqi.dna.core.type.GUID;


/**
 * <p>�ɹ���Ʒ(ֱ��)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
public class PurchaseGoodsDirect2 extends PurchaseGoods2{
	protected GUID sourceSaleItemId;//	��Դ������ϸID	guid
	protected GUID sourceSaleId;//��Դ���۶���ID
	protected boolean deleteFlag;//ɾ����־
	
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
