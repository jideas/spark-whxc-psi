package com.spark.uac.publish.key;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;

/**
 * 
 * <p>验证登录验证密钥的正确性</p>
 * 可远程访问


 *
 
 * @version 2012-5-29
 */
@StructClass
public class ValidationCredentialKey{

	@StructField
	private String userId;
	@StructField
	private String credential;
	
	public ValidationCredentialKey(String userid,String credential){
	    this.userId = userid;
	    this.credential = credential;
    }

	public String getUserId(){
    	return userId;
    }

	public String getCredential(){
    	return credential;
    }
	
	
	
}
