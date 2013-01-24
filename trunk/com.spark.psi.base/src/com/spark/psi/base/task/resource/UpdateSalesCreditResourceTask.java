/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>更新销售授权资源</p>
 *


 *
 
 * @version 2012-4-24
 */

public class UpdateSalesCreditResourceTask extends Task<UpdateSalesCreditResourceTask.Method>{

	public enum Method{
		Put,
		Modify,
		Remove
	}

	private GUID id;
	
	public UpdateSalesCreditResourceTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
}

