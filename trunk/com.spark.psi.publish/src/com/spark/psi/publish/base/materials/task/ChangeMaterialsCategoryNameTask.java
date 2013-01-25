/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改材料分类名称</p>
 *
 */

public class ChangeMaterialsCategoryNameTask extends SimpleTask{
	
	private GUID categoryId;
	
	private String name;
	
	public ChangeMaterialsCategoryNameTask(GUID categoryId,String name){
		this.categoryId = categoryId;
		this.name = name;
	}

	public GUID getCategoryId(){
    	return categoryId;
    }

	public String getName(){
    	return name;
    }
	
	
}
