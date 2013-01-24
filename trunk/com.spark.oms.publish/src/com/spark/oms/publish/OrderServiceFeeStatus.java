package com.spark.oms.publish;
/**
 * 服务费用状态: 续费宽限、等待交款（续单，变更单）、临近续费、免费试用（新订单）、正常
 * 
 * 说明： 新建订单：免费使用》临近续费》续费宽限》正常交款》临近续费》续费宽限》正常交款
 *       变更或续期单： 等待交款》正常交款》临近续费》续费宽限》。
 *       
 *       
 */
public enum OrderServiceFeeStatus {
	Free(3,"免费试用"),
	Wait(5,"等待交款"),
	Pro(4,"临近续费"),
	Extend(6,"续费宽限"),
//	NoPay(7,"无缴款"),
	NoPay(7,""),
	Normal(2,"正常"),
	Finish(1,"正常"),//已经创建了下次订单
	
	//added all,use query where
	All(0,"全部");
	
	
	/**
	 * 代码
	 */
	private int code;

	/**
	 * 名称
	 */
	private String name;
	
	private OrderServiceFeeStatus(int code,String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceFeeStatus getOrderServiceFeeStatus(int code){
		if(Free.code==code){
			return Free;
		}else if(Wait.code==code){
			return Wait;
		}else if(Pro.code==code){
			return Pro;
		}else if(Extend.code==code){
			return Extend;
		}else if(Normal.code==code){
			return Normal;
		}else if(Finish.code==code){
			return Finish;
		}else if(NoPay.code==code){
			return NoPay;
		}else{
			return null;
		}
	}
}
