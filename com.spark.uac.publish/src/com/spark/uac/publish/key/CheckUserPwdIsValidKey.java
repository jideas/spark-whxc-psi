package com.spark.uac.publish.key;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.entity.UserInfo;

/**
 * 
 * <p>验证指定密码是否正确</p>
 *  context.find(Boolean.class,CheckUserPwdIsValidKey)
 *


 *
 
 * @version 2012-4-20
 */
@StructClass
public class CheckUserPwdIsValidKey {

	private UserInfo user;
	
	private String pwd;
	
	private GUID uid;
	
	public CheckUserPwdIsValidKey(GUID uid,String pwd){
	    this.uid = uid;
	    this.pwd = pwd;
    }
	
	public CheckUserPwdIsValidKey(UserInfo user,String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	public UserInfo getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public GUID getUid(){
    	return uid;
    }
	
	
}
