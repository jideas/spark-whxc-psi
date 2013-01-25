package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>修改用户界面样式</p>
 *


 *
 
 * @version 2012-4-23
 */
public class UpdateEmployeeStyleTask extends SimpleTask{
	
	private GUID id;
	
	private String style;
	
	public UpdateEmployeeStyleTask(GUID id,String style){
	    this.id = id;
	    this.style = style;
    }

	public GUID getId(){
    	return id;
    }

	public String getStyle(){
    	return style;
    } 
	
}