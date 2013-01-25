package com.spark.psi.publish;

/**
 * 联系信息类型
 * 
 */
public enum ContactType {
	/**个人联系人 */
	Personal("01"), // 个人联系人
	/**客户供应联系人 */
	Partner("02"), // 
	/**收获送货联系人 */
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
