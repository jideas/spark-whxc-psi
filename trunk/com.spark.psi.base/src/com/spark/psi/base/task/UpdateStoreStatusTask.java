/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-26    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-26    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>修改仓库状态</p>
 *


 *
 
 * @version 2012-3-26
 */

public class UpdateStoreStatusTask extends SimpleTask{
	
	private GUID id;
	
	private StoreStatus StoreStatus;
	
	public UpdateStoreStatusTask(GUID id,StoreStatus status){
		this.id = id;
		this.StoreStatus = status;
	}

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public StoreStatus getStoreStatus(){
    	return StoreStatus;
    }

	public void setStoreStatus(StoreStatus StoreStatus){
    	this.StoreStatus = StoreStatus;
    }
	
	
	
}
