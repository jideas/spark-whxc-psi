package com.spark.psi.publish.base.config.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ���۾���ʣ������õ����ö�Ƚ��</p>
 *


 *
 
 * @version 2012-5-18
 */
public class GetSalesManResidualCreditAmountKey{

	private GUID id;
	
	private GUID[] customers;
	
	public GetSalesManResidualCreditAmountKey(GUID id,GUID[] customers){
		this.id = id;
		this.customers = customers;
    }
	
	public GetSalesManResidualCreditAmountKey(GUID[] customers){
		this.customers = customers;
    }
	
	public GetSalesManResidualCreditAmountKey(){
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID[] getCustomers(){
    	return customers;
    }

	public void setCustomers(GUID[] customers){
    	this.customers = customers;
    }
	
	
}
