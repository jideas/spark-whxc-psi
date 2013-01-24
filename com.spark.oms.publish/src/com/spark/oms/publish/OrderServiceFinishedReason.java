package com.spark.oms.publish;

/**
 * 服务结束原因：期满；变更；续期；中止；未付款
 * @author mengyongfeng
 *
 */
public enum OrderServiceFinishedReason {
	/**
	 * 使用
	 */
	UnFinish("00","未结束"),
	/**
	 * 使用
	 */
	Expired("01","结束"),
	Changed("02","变更"),
	Extend("03","完成"),
	Restore("06","恢复"),
	/**
	 * 使用
	 */
	Suspend("04","中止"),
	/**
	 * 子服务被终止
	 */
	SuspendChidren("07",""),
	PreChangeOrder("08","变更"),
	NonPayment("05","");
//	NonPayment("05","未付款");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private OrderServiceFinishedReason(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceFinishedReason getOrderServiceFinishedReason(String code){
		if(Expired.code.equals(code)){
			return Expired;
		}else if(Changed.code.equals(code)){
			return Changed;
		}else if(Suspend.code.equals(code)){
			return Suspend;
		}else if(Extend.code.equals(code)){
			return Extend;
		}else if(Restore.code.equals(code)){
			return Restore;
		}else if(NonPayment.code.equals(code)){
			return NonPayment;
		}else if(UnFinish.code.equals(code)){
			return UnFinish;
		}else if(PreChangeOrder.code.equals(code)){
			return PreChangeOrder;
		}else{
			return null;
		}
	}
}
