package com.spark.oms.publish;
/**
 *�˻����ͣ������˻��������˻�
 */
public enum AccountType {
	LeaseAccount("01","�����˻�"),
	PieceAccount("02","�����˻�");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
