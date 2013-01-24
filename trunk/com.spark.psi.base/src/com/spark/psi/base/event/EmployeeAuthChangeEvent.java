package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>员工角色发生改变事件</p>
 *


 *
 
 * @version 2012-6-25
 */
public class EmployeeAuthChangeEvent extends Event{

	private GUID empId; 
	
	public EmployeeAuthChangeEvent(GUID empId){
	    this.empId = empId;
    }

	public GUID getEmpId(){
    	return empId;
    }
	
	
}
