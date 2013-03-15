package com.spark.psi.publish;
/**
 * 
 * ����״̬
 *
 */
public enum JointSettlementStatus {


	
	/**
	 * ���ύ
	 */
	Submitting("01","���ύ"),
	/**
	 * �����
	 */
	Submitted("02","�����"),
	/**
	 * ������
	 */
	Paying("03","������"),
	/**
	 * �����
	 */
	Paid("04","�����"),
	/**
	 * �˻�
	 */
	Deny("05","�˻�");
	
	
	
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
