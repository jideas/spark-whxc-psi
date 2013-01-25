/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �޸Ŀͻ���������Ա
 * 1 ����ͻ�id��������Աid����ָ������Ա����Ϊָ���ͻ�������Ա��
 * 2 ֻ����ͻ�id�����˿ͻ���Ϊ����ͻ�
 
 *
 */
public class UpdateCustomerSalesManTask extends SimpleTask {
	
	private GUID customerId,employeeId;
	
	/**
	 * ���ͻ��趨������Ա
	 * @param customerId
	 * @param employeeId
	 */
	public UpdateCustomerSalesManTask(final GUID customerId,final GUID employeeId){
		this.customerId = customerId;
		this.employeeId = employeeId;
	}
	
	/**
	 * ��ָ���ͻ���Ϊ����ͻ�
	 * @param customerId
	 */
	public UpdateCustomerSalesManTask(final GUID customerId){
		this.customerId = customerId;
	}

	/**
	 * ��ÿͻ�id
	 * 
	 * @return GUID
	 */
	public GUID getCustomerId(){
    	return customerId;
    }

	/**
	 * �������Ա��ID
	 * ��idΪnull���򽫿ͻ���Ϊ����ͻ�
	 * 
	 * @return GUID
	 */
	public GUID getEmployeeId(){
    	return employeeId;
    }	
	
	
}
