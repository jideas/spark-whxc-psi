package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * 审核配置发生变化事件
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
