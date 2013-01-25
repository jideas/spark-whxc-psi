package com.spark.psi.publish;


/**
 * 
 * <p>���͵�״̬</p>
 *
 */
public enum DeliverStatus{
	
	/**
	 * ������
	 */
	Deliver("01","������"),
	/**
	 * ������
	 */
	Delivering("02","������"),
	/**
	 * �ѵ���
	 */
	Arrived("03","�ѵ���"),
	/**
	 * �쳣
	 */
	Exception("04","�쳣������"),
	/**
	 * �Ѵ���
	 */
	Handled("05","�쳣�Ѵ���");
		
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private DeliverStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static DeliverStatus getStatus(String code)
	{
		if(code.equals(DeliverStatus.Deliver.getCode()))
		{
			return DeliverStatus.Deliver;
		}
		else if(code.equals(DeliverStatus.Delivering.getCode()))
		{
			return DeliverStatus.Delivering;
		}
		else if(code.equals(DeliverStatus.Arrived.getCode()))
		{
			return DeliverStatus.Arrived;
		}
		else if(code.equals(DeliverStatus.Exception.getCode()))
		{
			return DeliverStatus.Exception;
		}
		else if(code.equals(DeliverStatus.Handled.getCode()))
		{
			return DeliverStatus.Handled;
		}
		else
		{
			return null;
		}
	}
		
}	
