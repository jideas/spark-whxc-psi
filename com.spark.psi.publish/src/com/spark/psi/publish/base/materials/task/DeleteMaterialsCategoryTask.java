package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>删除材料分类task</p>
 *
 */

public class DeleteMaterialsCategoryTask extends SimpleTask{

	/**
	 * 分类id
	 */
	private GUID id;

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            分类id
	 */
	public DeleteMaterialsCategoryTask(GUID id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

}
