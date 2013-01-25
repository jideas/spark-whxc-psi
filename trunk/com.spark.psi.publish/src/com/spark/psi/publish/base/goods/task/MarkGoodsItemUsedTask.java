/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>标识商品条目已使用</p>
 *


 *
 
 * @version 2012-4-22
 */

public class MarkGoodsItemUsedTask extends SimpleTask{
	
	private GUID id;
	
	public MarkGoodsItemUsedTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

}
