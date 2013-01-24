/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.task.dealing
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��������Ӧ��/Ӧ��Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class DealingAdjustmentTask extends SimpleTask {

	/**
	 * �ͻ�/��Ӧ��ID
	 */
	private GUID partnerId;
	
	/**
	 * ���ͣ��ͻ�/��Ӧ�̣�
	 */
	private String type;
	/**
	 * ������������ֵ��
	 */
	private double amount;
	
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
