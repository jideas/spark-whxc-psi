
package com.spark.psi.inventory.intf.task.allocateinventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>审核流程变更Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class UpdateAllocateOnExaChangeTask extends SimpleTask{
	public final static String rejectReason = "审核流程变更，请重新提交！"; 
	
	private String cause = rejectReason;
	private boolean isOpenExamine = false;
	private GUID userGuid;
	public UpdateAllocateOnExaChangeTask(GUID userGuid, boolean isOpenExamine) {
		this.isOpenExamine = isOpenExamine;
		this.userGuid = userGuid;
	}
	public boolean isOpenExamine() {
		return isOpenExamine;
	}
	public GUID getUserGuid() {
		return userGuid;
	}
	public String getCause(){
    	return cause;
    }
	public void setCause(String cause){
    	this.cause = cause;
    }
}
