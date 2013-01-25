package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�޸��û��ֻ�����</p>
 *


 *
 
 * @version 2012-4-23
 */
public class UpdateEmployeeMobileTask extends SimpleTask{
	
	private GUID id;
	
	private String mobileNumber;
	
	public UpdateEmployeeMobileTask(GUID id,String mobileNumber){
	    this.id = id;
	    this.mobileNumber = mobileNumber;
    }

	public GUID getId(){
    	return id;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }
	
	
	
}