/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-15       Ī��        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.key.SelectMainKey;

/**
 * <p>���б�����ͳ��Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-15
 */

public class TotalAmountTask extends SimpleTask {

	/**
	 * ���б����ݲ�ѯKEY
	 */
	private SelectMainKey key;
	/**
	 * ��������
	 */
	private int totalCount;
	/**
	 * �ɹ����
	 */
	private double totalOrderAmount;
	/**
	 * �˻����
	 */
	private double totalCancelAmount;
	
	public SelectMainKey getKey() {
		return key;
	}
	public void setKey(SelectMainKey key) {
		this.key = key;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public double getTotalCancelAmount() {
		return totalCancelAmount;
	}
	public void setTotalCancelAmount(double totalCancelAmount) {
		this.totalCancelAmount = totalCancelAmount;
	}
}
