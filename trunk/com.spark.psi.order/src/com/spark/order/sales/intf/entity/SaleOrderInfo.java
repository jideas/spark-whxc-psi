package com.spark.order.sales.intf.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;

/**
 * <p>销售订单实体</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-19
 */
@StructClass
public class SaleOrderInfo extends OrderInfo{
	@StructField
	private GUID consigneeGuid;//	收货人Guid	GUID
	@StructField
	private String consignee;//	收货人	V(10)
	@StructField
	private String consigneeTel;//	收货人电话	V(20)
	@StructField
	private String Address;//	收货地址	V(200)
	@StructField
	private double DisAmount;//	整单折扣	N 
	@StructField
	private boolean isAllot = false;//	是否分配
	@StructField
	private long planOutDate; // 预计出库日期
	
	//--------------------网上订单--------------
	@StructField
	private GUID buyGuid;//在线下单采购订单GUID
	@StructField
	private GUID tenantsGuidB;//	远程租户GUID
	
	
	
	/**
	 * @return the planOutDate
	 */
	public long getPlanOutDate() {
		return planOutDate;
	}
	/**
	 * @param planOutDate the planOutDate to set
	 */
	public void setPlanOutDate(long planOutDate) {
		this.planOutDate = planOutDate;
	} 
	/**
	 * @return the isAllot
	 */
	public boolean isAllot() {
		return isAllot;
	}
	/**
	 * @return the tenantsGuidB
	 */
	public GUID getTenantsGuidB() {
		return tenantsGuidB;
	} 
	/**
	 * @param isAllot the isAllot to set
	 */
	public void setAllot(boolean isAllot) {
		this.isAllot = isAllot;
	}
	/**
	 * @param tenantsGuidB the tenantsGuidB to set
	 */
	public void setTenantsGuidB(GUID tenantsGuidB) {
		this.tenantsGuidB = tenantsGuidB;
	}
	/**
	 * @return the buyGuid
	 */
	public GUID getBuyGuid() {
		return buyGuid;
	}
	/**
	 * @param buyGuid the buyGuid to set
	 */
	public void setBuyGuid(GUID buyGuid) {
		this.buyGuid = buyGuid;
	}
	/**
	 * @return the consigneeGuid
	 */
	public GUID getConsigneeGuid() {
		return consigneeGuid;
	}
	/**
	 * @return the consignee
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * @return the consigneeTel
	 */
	public String getConsigneeTel() {
		return consigneeTel;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @return the disAmount
	 */
	public double getDisAmount() {
		return DisAmount;
	}
	/**
	 * @param consigneeGuid the consigneeGuid to set
	 */
	public void setConsigneeGuid(GUID consigneeGuid) {
		this.consigneeGuid = consigneeGuid;
	}
	/**
	 * @param consignee the consignee to set
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * @param consigneeTel the consigneeTel to set
	 */
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @param disAmount the disAmount to set
	 */
	public void setDisAmount(double disAmount) {
		DisAmount = disAmount;
	}
			
}
