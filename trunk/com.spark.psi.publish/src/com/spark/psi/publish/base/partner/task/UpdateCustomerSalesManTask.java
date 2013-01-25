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
 * 修改客户的销售人员
 * 1 传入客户id和销售人员id，将指定销售员工设为指定客户的销售员工
 * 2 只传入客户id，将此客户设为共享客户
 
 *
 */
public class UpdateCustomerSalesManTask extends SimpleTask {
	
	private GUID customerId,employeeId;
	
	/**
	 * 将客户设定销售人员
	 * @param customerId
	 * @param employeeId
	 */
	public UpdateCustomerSalesManTask(final GUID customerId,final GUID employeeId){
		this.customerId = customerId;
		this.employeeId = employeeId;
	}
	
	/**
	 * 将指定客户设为共享客户
	 * @param customerId
	 */
	public UpdateCustomerSalesManTask(final GUID customerId){
		this.customerId = customerId;
	}

	/**
	 * 获得客户id
	 * 
	 * @return GUID
	 */
	public GUID getCustomerId(){
    	return customerId;
    }

	/**
	 * 获得销售员工ID
	 * 若id为null，则将客户设为共享客户
	 * 
	 * @return GUID
	 */
	public GUID getEmployeeId(){
    	return employeeId;
    }	
	
	
}
