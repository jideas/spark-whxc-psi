package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * ������÷����仯�¼�
 */
public class ApprovalConfigChangedEvent extends Event{
	
	private Mode mode;
	
	public ApprovalConfigChangedEvent(Mode mode){
		this.mode = mode;
    }

	public Mode getMode(){
    	return mode;
    }
	
	
}
