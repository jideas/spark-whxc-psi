package com.spark.psi.publish;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>���̿��Ʋ���</p>
 *


 *
 
 * @version 2012-3-8
 */
public enum OrderAction{
	/**
	 * ɾ��
	 */
	Delete(Action.Delete),
	/**
	 * �ύ
	 */
	Submit(Action.Submit),
	/**
	 * ����
	 */
	Approval(Action.Approval),
	/**
	 * ��ֹ
	 */
	Stop(Action.Stop),
	/**
	 * ����ִ��
	 */
	Execut(Action.ReExecute),
	/**
	 * ȷ�Ϸ���
	 */
	consignment(Action.Consignment),
	/**
	 * ����
	 */
	Deny(Action.Deny),
	
	/**
	 * ����
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
	 * ��ҵ��������鷭��ɽ�������
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
