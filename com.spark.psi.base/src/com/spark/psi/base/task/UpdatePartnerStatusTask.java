/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerStatus;

/**
 * <p>设置客户状态为正式客户</p>
 *


 *
 
 * @version 2012-4-22
 */

public class UpdatePartnerStatusTask extends SimpleTask{
	
	private GUID partnerId;
	
	private PartnerStatus PartnerStatus;
	
	public UpdatePartnerStatusTask(GUID partnerId){
	    this.partnerId = partnerId;
	    this.PartnerStatus = PartnerStatus.Official;
    }
	
	public UpdatePartnerStatusTask(GUID partnerId,PartnerStatus status){
	    this.partnerId = partnerId;
	    this.PartnerStatus = status;
    }

	public GUID getPartnerId(){
    	return partnerId;
    }

	public PartnerStatus getPartnerStatus(){
    	return PartnerStatus;
    }
	
	
}
