package com.spark.uac.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��鼤�����Ƿ����</p>
 *


 *
 
 * @version 2012-4-12
 */
public class CheckActivationCodeKey{
	
	private GUID userId;
	
	private String pwd;
	
	public CheckActivationCodeKey(GUID userId,String pwd){
	    this.userId = userId;
	    this.pwd = pwd;
    }

	public GUID getUserId(){
    	return userId;
    }

	public String getPwd(){
    	return pwd;
    }
		
}
