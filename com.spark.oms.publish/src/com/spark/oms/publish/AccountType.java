package com.spark.oms.publish;
/**
 *账户类型：计量账户，租赁账户
 */
public enum AccountType {
	LeaseAccount("01","租赁账户"),
	PieceAccount("02","计量账户");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private AccountType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static AccountType getAccountType(String code){
		if(LeaseAccount.code.equals(code)){
			return LeaseAccount;
		}else if(PieceAccount.code.equals(code)){
			return PieceAccount;
		}else{
			return null;
		}
	}

}
