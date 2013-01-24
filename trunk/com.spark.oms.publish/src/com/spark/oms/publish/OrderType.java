package com.spark.oms.publish;
/**
 * 订单类别：新建订单；变更订单；续期单；恢复单
 * @author mengyongfeng
 *
 */
public enum OrderType {
	NewOrder("01","新建"),
	PreLongOrder("02","续单"),
	ChangeOrder("03","变更"),
	RestoreOrder("04","恢复单"),
	EditOrder("05","修改"),
	ShowOrder("06","详情"),
	//新建订单在免费运行的变更，更多类似于编辑功能
	PreChangeOrder("07","变更");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private OrderType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderType getOrderType(String code){
		if(NewOrder.code.equals(code)){
			return NewOrder;
		}else if(PreLongOrder.code.equals(code)){
			return PreLongOrder;
		}else if(ChangeOrder.code.equals(code)){
			return ChangeOrder;
		}else if(RestoreOrder.code.equals(code)){
			return RestoreOrder;
		}else if(EditOrder.code.equals(code)){
			return EditOrder;
		}else if(ShowOrder.code.equals(code)){
			return ShowOrder;
		}else if(PreChangeOrder.code.equals(code)){
			return PreChangeOrder;
		}else{
			return null;
		}
	}
}
