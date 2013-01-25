package com.spark.order.intf.event;



/**
 * <p>执行动作枚举，订单事件用</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-15
 */
public enum ChangedType {
	/**
	 * 保存
	 */
	SAVE("save"),
	/**
	 * 提交
	 */
	Submit("submit"),
	/**
	 * 审批
	 */
	Approval("approval"),
	/**
	 * 生效的
	 */
	Effectual("effectual"),
	/**
	 * 中止
	 */
	Stop("stop"),
	/**
	 * 重新执行
	 */
	Execut("execut"),
	/**
	 * 确认发货
	 */
	consignment("consignment"),
	/**
	 * 驳回
	 */
	Deny("deny"),
	/**
	 * 出库完成
	 */
	StoreFinish("storeFinish"),
	/**
	 * 完成
	 */
	Finish("finish"),
	/**
	 * 配货完成
	 */
	Distribute("distribute"),
	/**
	 * 删除
	 */
	Delete("delete");
	private String code;
	
	private ChangedType(String code){
		this.code = code;
		OrderChangedEvent.changedTypeMap.put(code.toUpperCase(), this);
	}
	/**
	 * 获得字符串标志
	 * @return String
	 */
	public String toString() {
		return code;
	}
	/**
	 * 通过字符串标志获得枚举
	 * @param str
	 * @return ChangedType
	 */
	public static ChangedType getChangedType(String str){
		if(null==str){
			return null;
		}
		return OrderChangedEvent.changedTypeMap.get(str.toUpperCase());
	}
}
