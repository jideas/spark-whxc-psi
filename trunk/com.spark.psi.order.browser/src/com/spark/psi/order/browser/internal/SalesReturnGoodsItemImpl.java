package com.spark.psi.order.browser.internal;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�����˻�����Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public class SalesReturnGoodsItemImpl extends OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.SalesReturnGoodsItem{

	private String storeName; //�˻��ֿ�����
	
	private GUID storeId; //�˻��ֿ�id	

	public String getStoreName(){
    	return storeName;
    }
	public GUID getStoreId(){
    	return storeId;
    }
	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	
}
