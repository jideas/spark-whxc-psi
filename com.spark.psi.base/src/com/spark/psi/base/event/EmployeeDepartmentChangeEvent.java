package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * Ա�����ű仯�¼�
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
	 * ԭ����
	 * 
	 * @return GUID
	 */
	public GUID getOldDept(){
    	return oldDept;
    }
	
	
}
