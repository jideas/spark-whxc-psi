/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-14       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.instorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ֹ������ִ����ⵥʱ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-14
 */

public class StopInTask extends SimpleTask{

	public StopInTask(GUID relaOrdGuid, String stopReason, boolean isStop){
		this.relaOrdGuid = relaOrdGuid;
		this.stopReason = stopReason;
		this.isStop = isStop;
	}

	/**
	 * ��ص���recid
	 */
	private GUID relaOrdGuid;

	/**
	 * ��ֹԭ��
	 */
	private String stopReason;

	/**
	 * �Ƿ���ֹ
	 */
	private boolean isStop;

	public int count;

	/**
	 * @return the relaOrdGuid
	 */
	public GUID getRelaOrdGuid(){
		return relaOrdGuid;
	}

	/**
	 * @param relaOrdGuid the relaOrdGuid to set
	 */
	public void setRelaOrdGuid(GUID relaOrdGuid){
		this.relaOrdGuid = relaOrdGuid;
	}

	/**
	 * @return the stopReason
	 */
	public String getStopReason(){
		return stopReason;
	}

	/**
	 * @param stopReason the stopReason to set
	 */
	public void setStopReason(String stopReason){
		this.stopReason = stopReason;
	}

	/**
	 * @return the isStop
	 */
	public boolean isStop(){
		return isStop;
	}

	/**
	 * @param isStop the isStop to set
	 */
	public void setStop(boolean isStop){
		this.isStop = isStop;
	}

}
