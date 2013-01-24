package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * Ա����ְ����ְ(Ա��ID)
 * @author Administrator
 */
public class EmployeeStatusChangeEvent extends Event {
	
	public enum Action{

		/**
		 * ��ְ
		 */
		Resign,

		/**
		 * ��ְ
		 */
		Reinstatus

	}
	
	private GUID id;
	
	private Action action;
	
	public EmployeeStatusChangeEvent(GUID id,Action action){
		this.action = action;
		this.id = id;
    }

	public GUID getId(){
    	return id;
    }

	public Action getAction(){
    	return action;
    }
	
	
}
