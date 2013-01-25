package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>查询商品订单记录</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-1
 */
public class SelectGoodsBillsLogKey extends SelectKey{
	private final BillsEnum billsEnum;//查询单据类型。客户传BillsEnum.SALE，供应商传BillsEnum.BUY
	private GUID cuspGuid; //客户供应商编号
	private final GUID goodsProGuid; //商品属性GUID
	private GUID tenantsGuid; //租户GUID
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
