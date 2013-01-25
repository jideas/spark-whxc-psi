package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ɾ�����Ϸ���task</p>
 *
 */

public class DeleteMaterialsCategoryTask extends SimpleTask{

	/**
	 * ����id
	 */
	private GUID id;

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ����id
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
