package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除材料信息（一条或者多条）
 * 
 */
public class DeleteMaterialsTask extends SimpleTask {

	/**
	 * 材料id数组
	 */
	private GUID[] ids;

	/**
	 * 构造函数
	 * 
	 * @param ids
	 *            材料id数组
	 */
	public DeleteMaterialsTask(GUID... ids) {
		this.ids = ids;
	}

	/**
	 * 
	 * @return
	 */
	public GUID[] getIds() {
		return ids;
	}

}
