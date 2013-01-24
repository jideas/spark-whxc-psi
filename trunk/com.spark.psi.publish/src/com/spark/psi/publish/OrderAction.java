package com.spark.psi.publish;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>流程控制操作</p>
 *


 *
 
 * @version 2012-3-8
 */
public enum OrderAction{
	/**
	 * 删除
	 */
	Delete(Action.Delete),
	/**
	 * 提交
	 */
	Submit(Action.Submit),
	/**
	 * 审批
	 */
	Approval(Action.Approval),
	/**
	 * 中止
	 */
	Stop(Action.Stop),
	/**
	 * 重新执行
	 */
	Execut(Action.ReExecute),
	/**
	 * 确认发货
	 */
	consignment(Action.Consignment),
	/**
	 * 驳回
	 */
	Deny(Action.Deny),
	
	/**
	 * 分配
	 */
	Allocate(Action.Allocate);
	
	private Action action;

	/**
	 * @param action
	 */
	private OrderAction(Action action) {
		this.action = action;
	}
	public Action getAction() {
		return action;
	}
	
	/**
	 * 将业务控制数组翻译成界面数组
	 * @param actions
	 * @return String[]
	 */
	public static String[] getItemActions(OrderAction... actions) {
		List<String> list = new ArrayList<String>();
		for(OrderAction action : actions){
			list.add(action.getAction().name());
		}
		return list.toArray(new String[list.size()]);
	}
	
	public boolean isIn(OrderAction... actions){
		for(OrderAction a : actions){
			if(null != a && this == a){
				return true;
			}
		}
		return false;
	}
}
