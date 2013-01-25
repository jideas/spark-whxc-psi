package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>标识材料条目已使用</p>
 *
 */

public class MarkMaterialsItemUsedTask extends SimpleTask{
	
	private GUID id;
	
	public MarkMaterialsItemUsedTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

}
