/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>删除供应商</p>
 *


 *
 
 * @version 2012-3-2
 */

public class DeleteCustomerTask extends SimpleTask{
	
	private GUID id;
	
	public DeleteCustomerTask(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }

	
}
