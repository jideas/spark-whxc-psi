package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>������Ʒ+�ֿ�(��Ӧ��)��ѯ����Ĳɹ���Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-16
 */
public class SelectPurchaseByStoreAndGoodsKey extends SelectKey{
	private final GUID goodsGuid;
	private GUID storeGuid;
	private GUID cuspGuid;
	
	public SelectPurchaseByStoreAndGoodsKey(GUID goodsGuid, GUID storeGuid) {
		this.goodsGuid = goodsGuid;
		this.storeGuid = storeGuid;
	}
	/**
	 * @param goodsGuid
	 */
	public SelectPurchaseByStoreAndGoodsKey(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
		this.storeGuid = null;
	}
	/**
	 * @return the goodsGuid
	 */
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	/**
	 * @return the storeGuid
	 */
	public GUID getStoreGuid() {
		return storeGuid;
	}
	/**
	 * @return the cuspGuid
	 */
	public GUID getCuspGuid() {
		return cuspGuid;
	}
	/**
	 * @param cuspGuid the cuspGuid to set
	 */
	public void setCuspGuid(GUID cuspGuid) {
		this.storeGuid = null;
		this.cuspGuid = cuspGuid;
	}
	
}
