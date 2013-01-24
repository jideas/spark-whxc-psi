/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.inventory
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.inventory;

/**
 * <p>����ܽ�������</p>
 * key:GoodsInventorySumKey
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GoodsInventorySum {

	private double totalCount;
	private double totalAmount;
	private double totalToDeliverCount;
	/**
	 * ������
	 * 
	 * @return double
	 */
	public double getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * �ܽ��
	 * 
	 * @return double
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setTotalToDeliverCount(double totalToDeliverCount) {
		this.totalToDeliverCount = totalToDeliverCount;
	}
	/**
	 * ������������
	 * 
	 * @return double
	 */
	public double getTotalToDeliverCount() {
		return totalToDeliverCount;
	}
	
}
