package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 员工离职、复职(员工ID)
 * @author Administrator
 */
public class EmployeeStatusChangeEvent extends Event {
	
	public enum Action{

		/**
		 * 离职
		 */
		Resign,

		/**
		 * 复职
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
