package com.spark.psi.publish;


/**
 * 
 * <p>订单状态</p>
 *


 *
 
 
 */
public enum OrderStatus{
	
	/**
	 * 待提交
	 */
	Submit("待提交"),
	/**
	 * 订单状态为待审核(本人未审核)
	 * 常规待审核页签使用此状态
	 */
	Approval_No("待审批"),
	
	/**
	 * 订单状态为待审核，但已通过本人审核
	 * 销售跟踪页签使用此状态
	 */
	Approval_Yes("待审批"),

	/**
	 * 未入库
	 */
	CheckingIn_NO("未入库"),
	/**
	 * 未出库
	 */
	CheckingOut_No("未出库"),
	/**
	 * 部分入库
	 */
	CheckingIn_Part("部分入库"),
	/**
	 * 部门出库
	 */
	CheckingOut_Part("部分出库"),
	/**
	 * 全部入库
	 */
	CheckedIn("全部入库"),
	/**
	 * 全部出库
	 */
	CheckedOut("全部出库"),
	/**
	 * 未发货
	 */
	CONSIGNMENT_NO("未发货"),
	/**
	 * 已发货
	 */
	CONSIGNMENT_YES("已发货"),
	/**
	 * 已驳回
	 */
	Denied("已退回"),
	/**
	 * 已中止（仅仅用于前台向后台要数据）
	 */
	Stop("已中止"),
	/**
	 * 已完成
	 */
	finish("已完成");
	
	
	final String name;

	public String getName(){
    	return name;
    }
	
	private OrderStatus(String name){
		this.name = name;
	}
	
	/**
	 * 判断当前枚举是否在指定枚举集合中，在里面的话返回true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isIn(OrderStatus...statuss){
		for(OrderStatus os : statuss){
			if(this == os){
				return true;
			}
		}
		return false;
	}
	
}	
