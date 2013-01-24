package com.spark.uac.publish;

/**
 * 
 * <p>�û�״̬</p>
 * 


 *
 
 * @version 2012-4-10
 */
public enum UserStatus{
	/**δ���� �������û�*/
	Not_Activated("01"),
	/**������ ��ȡ������һ�����״̬*/
	Activation("02"),
	/**�ѳɹ�����*/
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
