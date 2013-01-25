/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * <p>标识客户供应商已使用过了</p>
 * 
 * @version 2012-4-20
 */

public class MarkPartnerUsedTask extends SimpleTask{
	
	private GUID id;
	private PartnerType type; 
	
	public MarkPartnerUsedTask(GUID id,PartnerType type){
	    this.id = id;
	    this.type = type;
    }
	 
	public GUID getId(){
    	return id;
    }
	
	public PartnerType getType(){
		return type;
	}
 
}
