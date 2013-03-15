package com.spark.psi.publish;
/**
 * 
 * 结算状态
 *
 */
public enum JointSettlementStatus {


	
	/**
	 * 待提交
	 */
	Submitting("01","待提交"),
	/**
	 * 待审核
	 */
	Submitted("02","待审核"),
	/**
	 * 进行中
	 */
	Paying("03","进行中"),
	/**
	 * 已完成
	 */
	Paid("04","已完成"),
	/**
	 * 退回
	 */
	Deny("05","退回");
	
	
	
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private JointSettlementStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static JointSettlementStatus getStatus(String code)
	{
		if(code.equals(JointSettlementStatus.Paying.getCode()))
		{
			return JointSettlementStatus.Paying;
		}
		else if(code.equals(JointSettlementStatus.Deny.getCode()))
		{
			return JointSettlementStatus.Deny;
		}
		else if(code.equals(JointSettlementStatus.Paid.getCode()))
		{
			return JointSettlementStatus.Paid;
		}
		else if(code.equals(JointSettlementStatus.Submitted.getCode()))
		{
			return JointSettlementStatus.Submitted;
		}
		else if(code.equals(JointSettlementStatus.Submitting.getCode()))
		{
			return JointSettlementStatus.Submitting;
		}
		else if(code.equals(JointSettlementStatus.Deny.getCode()))
		{
			return JointSettlementStatus.Deny;
		}
		else
		{
			return null;
		}
	}
		

}
