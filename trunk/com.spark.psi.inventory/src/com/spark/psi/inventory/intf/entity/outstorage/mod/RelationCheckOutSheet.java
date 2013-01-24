/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.outstorage.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ص��ݳ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class RelationCheckOutSheet {

	/**
	 * ��ض���ID
	 */
	private GUID relaOrderGuid;
	/**
	 * ��ض���������
	 */
	private double orderGoodsTotalCount;
	/**
	 * ��ض����ܽ��
	 */
	private double orderTotalAmount;
	/**
	 * �ѳ�������
	 */
	private double checkedOutCount;
	/**
	 * ���տ���
	 */
	private double checkedOutAmount;
	/**
	 * �ѳ�����
	 */
	private double outStoreAmount;
	
	public double getOutStoreAmount() {
		return outStoreAmount;
	}
	public void setOutStoreAmount(double outStoreAmount) {
		this.outStoreAmount = outStoreAmount;
	}
	public GUID getRelaOrderGuid() {
		return relaOrderGuid;
	}
	public void setRelaOrderGuid(GUID relaOrderGuid) {
		this.relaOrderGuid = relaOrderGuid;
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
	public double getCheckedOutCount() {
		return checkedOutCount;
	}
	public void setCheckedOutCount(double checkedOutCount) {
		this.checkedOutCount = checkedOutCount;
	}
	public double getCheckedOutAmount() {
		return checkedOutAmount;
	}
	public void setCheckedOutAmount(double checkedOutAmount) {
		this.checkedOutAmount = checkedOutAmount;
	}
	
}
