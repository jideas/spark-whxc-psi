/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.leveltree.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.leveltree.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改父级节点</p>
 * 更新所有子孙节点的级次关联
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-17
 */

public class UpdateLevelTreePathTask extends SimpleTask{
	
	public GUID RECID;
	
	public GUID tenantId;
	
	public GUID parentRecid;
	
	public UpdateLevelTreePathTask(GUID RECID,GUID parentRecid,GUID tenantId){
		this.parentRecid = parentRecid;
		this.RECID = RECID;
		this.tenantId = tenantId;
	}
	
}
