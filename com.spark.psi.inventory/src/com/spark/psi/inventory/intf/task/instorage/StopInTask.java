/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.instorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>中止和重新执行入库单时的数据</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-14
 */

public class StopInTask extends SimpleTask{

	public StopInTask(GUID relaOrdGuid, String stopReason, boolean isStop){
		this.relaOrdGuid = relaOrdGuid;
		this.stopReason = stopReason;
		this.isStop = isStop;
	}

	/**
	 * 相关单据recid
	 */
	private GUID relaOrdGuid;

	/**
	 * 中止原因
	 */
	private String stopReason;

	/**
	 * 是否中止
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
