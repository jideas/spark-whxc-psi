package com.spark.psi.publish;


/**
 * 
 * <p>生产订单状态</p>
 *
 */
public enum ProduceOrderStatus{
	
	/**
	 * 待提交
	 */
	Submiting("01","待提交"),
	/**
	 * 待审批
	 */
	Submited("02","待审批"),
	/**
	 * 退回
	 */
	Reject("03","已退回"),
	/**
	 * 加工中
	 */
	Producing("04","加工中"),
	/**
	 * 已完成
	 */
	Finished("05","已完成");
	
	
	
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private ProduceOrderStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static ProduceOrderStatus getStatus(String code)
	{
		if(code.equals(ProduceOrderStatus.Producing.getCode()))
		{
			return ProduceOrderStatus.Producing;
		}
		else if(code.equals(ProduceOrderStatus.Reject.getCode()))
		{
			return ProduceOrderStatus.Reject;
		}
		else if(code.equals(ProduceOrderStatus.Submited.getCode()))
		{
			return ProduceOrderStatus.Submited;
		}
		else if(code.equals(ProduceOrderStatus.Submiting.getCode()))
		{
			return ProduceOrderStatus.Submiting;
		}
		else if(code.equals(ProduceOrderStatus.Finished.getCode()))
		{
			return ProduceOrderStatus.Finished;
		}
		else
		{
			return null;
		}
	}
		
}	
