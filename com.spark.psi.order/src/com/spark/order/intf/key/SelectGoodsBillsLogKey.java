package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>��ѯ��Ʒ������¼</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-1
 */
public class SelectGoodsBillsLogKey extends SelectKey{
	private final BillsEnum billsEnum;//��ѯ�������͡��ͻ���BillsEnum.SALE����Ӧ�̴�BillsEnum.BUY
	private GUID cuspGuid; //�ͻ���Ӧ�̱��
	private final GUID goodsProGuid; //��Ʒ����GUID
	private GUID tenantsGuid; //�⻧GUID
//	private long pageSize = 12;
//	private long offset = 0; 
	
	public SelectGoodsBillsLogKey(BillsEnum billsEnum, GUID cuspGuid, GUID goodsProGuid) {
		this.billsEnum = billsEnum;
		this.cuspGuid = cuspGuid;
		this.goodsProGuid = goodsProGuid;
	}
	
	public SelectGoodsBillsLogKey(BillsEnum billsEnum, GUID goodsProGuid) {
		this.billsEnum = billsEnum;
		this.goodsProGuid = goodsProGuid;
	}
	
	
//	/**
//	 * @return the pageSize
//	 */
//	public long getPageSize() {
//		return pageSize;
//	}
//
//	/**
//	 * @return the offset
//	 */
//	public long getOffset() {
//		return offset;
//	}

//	/**
//	 * @param pageSize the pageSize to set
//	 */
//	public void setPageSize(long pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	/**
//	 * @param offset the offset to set
//	 */
//	public void setOffset(long offset) {
//		this.offset = offset;
//	}

	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
	/**
	 * @return the cuspGuid
	 */
	public GUID getCuspGuid() {
		return cuspGuid;
	}
	/**
	 * @return the goodsProGuid
	 */
	public GUID getGoodsProGuid() {
		return goodsProGuid;
	}
	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
}
