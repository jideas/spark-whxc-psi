package com.spark.psi.publish;


/**
 * 
 * <p>配送单状态</p>
 *
 */
public enum DeliverStatus{
	
	/**
	 * 待配送
	 */
	Deliver("01","待配送"),
	/**
	 * 配送中
	 */
	Delivering("02","配送中"),
	/**
	 * 已到货
	 */
	Arrived("03","已到货"),
	/**
	 * 异常
	 */
	Exception("04","异常待处理"),
	/**
	 * 已处理
	 */
	Handled("05","异常已处理");
		
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
