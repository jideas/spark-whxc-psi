/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.customer
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.customer
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

package com.spark.customer;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderTaskFather;

/**
 * <p>�������ö��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */

 final class AlreadyUseCreditTask extends OrderTaskFather<AlreadyUseCreditTask.Method>{
	private AlreadyUseCredit entity;//�����ڴ���ʱʹ��
	/**
	 * <p>�������ö��ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 
	 * @version 2012-3-9
	 */

	public enum Method {
		ADD, SUB, Create
	}
	/**
	 * �ͻ�id
	 */
	private GUID customerId;
	/**
	 * �仯���
	 */
	private double changeAmount;
	public AlreadyUseCreditTask(AlreadyUseCredit entity) {
		super();
		this.entity = entity;
	}
	public AlreadyUseCredit getEntity() {
		return entity;
	}
	public GUID getCustomerId() {
		return customerId;
	}
	public double getChangeAmount() {
		return changeAmount;
	}
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}
	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}
	public AlreadyUseCreditTask(GUID customerId, double changeAmount) {
		super();
		this.customerId = customerId;
		this.changeAmount = changeAmount;
	}

	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}
	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}


}
