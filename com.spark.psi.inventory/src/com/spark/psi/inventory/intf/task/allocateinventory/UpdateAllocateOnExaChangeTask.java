
package com.spark.psi.inventory.intf.task.allocateinventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>������̱��Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class UpdateAllocateOnExaChangeTask extends SimpleTask{
	public final static String rejectReason = "������̱�����������ύ��"; 
	
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
