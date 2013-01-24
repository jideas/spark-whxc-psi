/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.instorage.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ص���������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class RelationCheckInSheet {

	/**
	 * ��ض���ID
	 */
	private GUID relationOrderId;
	/**
	 * ��ض���������
	 */
	private double orderGoodsTotalCount;
	/**
	 * ��ض����ܽ��
	 */
	private double orderTotalAmount;
	/**
	 * ���������
	 */
	private double checkedInCount;
	/**
	 * �Ѹ�����
	 */
	private double checkedInAmount;
	/**
	 * �ѳ�����
	 */
	private double inStoreAmount;
	
	public double getInStoreAmount() {
		return inStoreAmount;
	}
	public void setInStoreAmount(double inStoreAmount) {
		this.inStoreAmount = inStoreAmount;
	}
	public double getOrderGoodsTotalCount() {
		return orderGoodsTotalCount;
	}
	public void setOrderGoodsTotalCount(double orderGoodsTotalCount) {
		this.orderGoodsTotalCount = orderGoodsTotalCount;
	}
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public double getCheckedInCount() {
		return checkedInCount;
	}
	public void setCheckedInCount(double checkedInCount) {
		this.checkedInCount = checkedInCount;
	}
	public double getCheckedInAmount() {
		return checkedInAmount;
	}
	public void setCheckedInAmount(double checkedInAmount) {
		this.checkedInAmount = checkedInAmount;
	}
	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}
	public GUID getRelationOrderId() {
		return relationOrderId;
	}
	
}
