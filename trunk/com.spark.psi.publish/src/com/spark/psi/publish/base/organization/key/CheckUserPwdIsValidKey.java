package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>验证指定密码是否正确</p>
 *  context.find(Boolean.class,CheckUserPwdIsValidKey)
 *


 *
 
 * @version 2012-4-20
 */
public class CheckUserPwdIsValidKey {
	
	private String pwd;
	
	private GUID uid;
	
	public CheckUserPwdIsValidKey(GUID uid,String pwd){
	    this.uid = uid;
	    this.pwd = pwd;
    }

	public String getPwd() {
		return pwd;
	}

	public GUID getUid(){
    	return uid;
    }
	
	
}
