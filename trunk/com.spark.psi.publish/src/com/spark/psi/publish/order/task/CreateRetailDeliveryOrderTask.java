/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;


/**
 * <p>�����ͻ����ŵ����۵�</p>
 *


 *
 
 * @version 2012-3-6
 */

public class CreateRetailDeliveryOrderTask extends CreateRetailOrderTask{
	
	/**
	 * �ջ���
	 */
	private String consignee;
	
	/**
	 * �ջ��ַ
	 */
	private String deliveryAddress;
	
	/**
	 * �绰����
	 */
	private String mobileNumber;
	
	/**
	 * ����ʱ��
	 */
	private long deliveryTime;

	
	public String getConsignee(){
    	return consignee;
    }

	public void setConsignee(String consignee){
    	this.consignee = consignee;
    }

	public String getDeliveryAddress(){
    	return deliveryAddress;
    }

	public void setDeliveryAddress(String deliveryAddress){
    	this.deliveryAddress = deliveryAddress;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public void setMobileNo(String mobileNumber){
    	this.mobileNumber = mobileNumber;
    }

	public long getDeliveryTime(){
    	return deliveryTime;
    }

	public void setDeliveryTime(long deliveryTime){
    	this.deliveryTime = deliveryTime;
    }
	
	
	
}
