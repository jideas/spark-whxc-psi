package com.spark.psi.publish;


/**
 * 
 * <p>网上订单状态</p>
 *
 */
public enum OnlineOrderStatus{
	
	/**
	 * 待付款
	 */
	Paying("01","待付款"),
	/**
	 * 已生效
	 */
	Effected("02","已生效"),
	/**
	 * 拣货中
	 */
	Picking("03","拣货中"),
	/**
	 * 配送中
	 */
	Delivering("04","配送中"),
	/**
	 * 已到货
	 */
	Arrivaled("05","已到货"),
	/**
	 * 完成
	 */
	Finished("06","已完成");
	
	
	
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private OnlineOrderStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static OnlineOrderStatus getStatus(String code)
	{
		if(code.equals(OnlineOrderStatus.Paying.getCode()))
		{
			return OnlineOrderStatus.Paying;
		}
		else if(code.equals(OnlineOrderStatus.Arrivaled.getCode()))
		{
			return OnlineOrderStatus.Arrivaled;
		}
		else if(code.equals(OnlineOrderStatus.Delivering.getCode()))
		{
			return OnlineOrderStatus.Delivering;
		}
		else if(code.equals(OnlineOrderStatus.Effected.getCode()))
		{
			return OnlineOrderStatus.Effected;
		}
		else if(code.equals(OnlineOrderStatus.Finished.getCode()))
		{
			return OnlineOrderStatus.Finished;
		}
		else if(code.equals(OnlineOrderStatus.Picking.getCode()))
		{
			return OnlineOrderStatus.Picking;
		}
		else
		{
			return null;
		}
	}
		
}	
