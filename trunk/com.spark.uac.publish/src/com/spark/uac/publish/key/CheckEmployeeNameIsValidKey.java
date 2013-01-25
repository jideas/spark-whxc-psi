package com.spark.uac.publish.key;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����⻧���û������Ƿ���Ч</p>
 *


 *
 
 * @version 2012-4-11
 */
@StructClass
public class CheckEmployeeNameIsValidKey{
	@StructField
	private GUID tenantID;
	@StructField
	private String name;
	@StructField
	private String mobilePhone;

	public CheckEmployeeNameIsValidKey(GUID tenantId,String name,String mobilePhone){
	    this.tenantID = tenantId;
	    this.name = name;
	    this.mobilePhone = mobilePhone;
    }

	public GUID getTenantID(){
    	return tenantID;
    }

	public String getName(){
    	return name;
    }

	public String getMobilePhone(){
    	return mobilePhone;
    }
	
	
}
