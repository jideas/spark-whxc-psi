/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.purchase.intf.entity;

import com.spark.order.intf.entity.OrderInfo;

/** 
 * �ɹ��˻�ʵ�� 
 */

public class PurchaseCancel extends OrderInfo {

	private String consignee; 
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	} 
}
