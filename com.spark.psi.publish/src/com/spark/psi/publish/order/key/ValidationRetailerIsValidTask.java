package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��֤����Ա���Ƿ���Ч</p>
 * context.hendle(ValidationRetailerIsValidTask)


 *
 
 * @version 2012-5-7
 */
public class ValidationRetailerIsValidTask extends SimpleTask{
	
	private GUID id;
	
	private boolean valid;
	
	private String msg;
	
	public ValidationRetailerIsValidTask(GUID id){
	    this.id = id;
    }
	
	public ValidationRetailerIsValidTask(){
	    // TODO Auto-generated constructor stub
    }

	public GUID getId(){
    	return id;
    }

	public boolean isValid(){
    	return valid;
    }

	public void setValid(boolean valid){
    	this.valid = valid;
    }

	public String getMsg(){
    	return msg;
    }

	public void setMsg(String msg){
    	this.msg = msg;
    }
	
	
}
