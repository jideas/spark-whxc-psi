package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>验证手机号码是否唯一</p>
 *


 *
 
 * @version 2012-6-15
 */
public class ValidationMoblieNumberIsOnlyKey{
	
	private GUID id;
	
	private String mobileNumber;
	
	public ValidationMoblieNumberIsOnlyKey(GUID id,String mobileNumber){
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
