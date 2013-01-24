package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.QueryScope;

/**
 * 
 * <p>����Ա��ID��ÿͻ��б�</p>
 * 
 *


 *
 
 * @version 2012-3-12
 */
public class GetCustomerListByEmployeeIdKey{
	
	private GUID employeeId;
	
	private QueryScope scope;
	
	private String text = "";
	
	private GUID tenantId ;
	
	/**
	 * ����Ա��ID��ÿ��ÿͻ��б�
	 * @param employeeId
	 */
	public GetCustomerListByEmployeeIdKey(GUID employeeId){
		this.employeeId = employeeId;
	}
	
	public GetCustomerListByEmployeeIdKey(GUID employeeId,GUID tenantId){
	    this.employeeId = employeeId;
	    this.tenantId = tenantId;
    }
	
	public GetCustomerListByEmployeeIdKey(GUID employeeId,QueryScope scope){
		this.employeeId = employeeId;
		this.scope = scope;
	}

	public GUID getEmployeeId(){
    	return employeeId;
    }

	public QueryScope getScope(){
    	return scope;
    }

	public String getText(){
    	return text;
    }

	public void setText(String text){
    	this.text = text ==null ? "" : text;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
	
	
		
	
}
