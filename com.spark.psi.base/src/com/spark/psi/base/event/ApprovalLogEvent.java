package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.spark.psi.base.ApprovalLog;

/**
 * ÉóºË¼ÇÂ¼ÊÂ¼þ
 * @author Administrator
 */
public class ApprovalLogEvent extends Event {
	
	private ApprovalLog entity;
	
	public ApprovalLogEvent(ApprovalLog entity){
	    this.entity = entity;
    }

	public ApprovalLog getEntity(){
    	return entity;
    }
	
	
	
}
