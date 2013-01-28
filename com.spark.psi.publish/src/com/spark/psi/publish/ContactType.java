package com.spark.psi.publish;

/**
 * ��ϵ��Ϣ����
 * 
 */
public enum ContactType {
	/**������ϵ�� */
	Personal("01"), // ������ϵ��
	/**�ͻ���Ӧ��ϵ�� */
	Partner("02"), // 
	/**�ջ��ͻ���ϵ�� */
	Delivery("03"); // 

	final String code;
	
	private ContactType(String code){
	    this.code = code;
    }

	public String getCode(){
    	return code;
    }
	
	public static ContactType getContactTypeByCode(String code){
		for(ContactType type : ContactType.values()){
	        if(type.code.equals(code)){
	        	return type;
	        }
        }
		return null;
	}
	
}