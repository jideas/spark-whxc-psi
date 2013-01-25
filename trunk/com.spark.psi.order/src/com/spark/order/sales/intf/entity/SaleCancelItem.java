/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.sales.intf.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderDet;

/**
 * <p>�����˻���ϸʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-10
 */

public class SaleCancelItem extends OrderDet{

	/**
	 * �ֿ�
	 */
	private GUID storeId;
	private String storeName;
	/**
	 * ����ɹ��۸�
	 */
	private double recentPrice;
	/**
	 * �������
	 */
	private double storeNum;
	 
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public double getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(double recentPrice) {
		this.recentPrice = recentPrice;
	}
	public double getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(double storeNum) {
		this.storeNum = storeNum;
	}
}
