/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.contact.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-24    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.contact.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-24    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改联系人或收货地址的最后一次时候时间</p>
 *


 *
 
 * @version 2012-7-24
 */

public class UpdateContactLastDateTask extends SimpleTask{
	
	private GUID id;
	
	public UpdateContactLastDateTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
	
}
