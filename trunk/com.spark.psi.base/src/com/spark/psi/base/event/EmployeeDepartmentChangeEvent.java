package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 员工部门变化事件
 * @author Administrator
 */
public class EmployeeDepartmentChangeEvent extends Event {
	
	private GUID id,oldDept;
	
	public EmployeeDepartmentChangeEvent(GUID id,GUID oldDept){
	    this.id = id;
	    this.oldDept = oldDept;
    }

	public GUID getId(){
    	return id;
    }
	
	/**
	 * 原部门
	 * 
	 * @return GUID
	 */
	public GUID getOldDept(){
    	return oldDept;
    }
	
	
}
