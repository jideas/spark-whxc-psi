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
import com.spark.psi.base.internal.entity.LevelTree;

/**
 * <p>增加级次表关联</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-17
 */

public class NewLevelTreeTask extends SimpleTask{
	
	private final LevelTree levelTree;
	
	private GUID parentRecid;
	
	private String stauts = LevelTree.STAUTS_CHILD;
	
	private String path;
	
	private GUID tenantId;
	
	public GUID getParentRecid(){
    	return parentRecid;
    }

	public void setParentRecid(GUID parentRecid,GUID tenantId){
    	this.parentRecid = parentRecid;
    }

	public LevelTree getLevelTree(){
    	return levelTree;
    }

	public NewLevelTreeTask(final GUID RECID,final GUID parentRecid,GUID tenantId){
		levelTree = new LevelTree();
		levelTree.setRECID(RECID);
		levelTree.setStauts(stauts);
		levelTree.setTenantId(tenantId); 
		this.parentRecid = parentRecid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
