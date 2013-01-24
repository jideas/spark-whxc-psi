package com.spark.uac.publish;

/**
 * 
 * <p>用户状态</p>
 * 


 *
 
 * @version 2012-4-10
 */
public enum UserStatus{
	/**未激活 新增的用户*/
	Not_Activated("01"),
	/**激活中 获取密码或找回密码状态*/
	Activation("02"),
	/**已成功激活*/
	Activated("03");
	
	final String code;
	
	public String getCode(){
    	return code;
    }

	UserStatus(String code){
		this.code = code;
	}
	
	public static UserStatus getUserStatusByCode(String code){
		for(UserStatus status : UserStatus.values()){
	        if(status.getCode().equals(code)){
	        	return status;
	        }
        }
		return null;
	}
	
}
