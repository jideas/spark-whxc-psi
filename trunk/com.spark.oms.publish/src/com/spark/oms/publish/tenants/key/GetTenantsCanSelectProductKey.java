package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.OrderType;

/**
 * ��ȡ�⻧��ѡ�����Ʒ
 */
public class GetTenantsCanSelectProductKey {
	
	/**
	 * �⻧��ʶ
	 */
	private GUID tenantsRECID;
	
	/**
	 * ��Ʒϵ�б�ʶ
	 */
	private String serial;
	
	/**
	 * ѡ��Χ
	 * @return
	 */
	private OrderType orderType;
	
	/**
	 * ��Ʒ��Ŀ
	 * @return
	 */
	private String productItemCode;
	
	
	
	public String getProductItemCode() {
		return productItemCode;
	}

	public void setProductItemCode(String productItemCode) {
		this.productItemCode = productItemCode;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}
