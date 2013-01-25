/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>删除一个促销</p>
 *


 *
 
 * @version 2012-3-6
 */

public class DeletePromotionTask extends SimpleTask{
	
	private final GUID id;
	
	public DeletePromotionTask(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }
	
	
	
}
