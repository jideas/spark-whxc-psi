/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;



/**
 * <p>�½��͸��¿ͻ���ϢTask</p>
 * �����ͻ�����
 * ���������ͻ�����
 * �༭�ͻ����� 
 *


 *
 
 * @version 2012-3-2
 */

public class UpdateCustomerTask extends UpdatePartnerTask<UpdatePartnerTask.Method>{
	
	private String pricePolicy;

	/**
	 * @return �۸����
	 */
	public String getPricePolicy() {
		return pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}






}
