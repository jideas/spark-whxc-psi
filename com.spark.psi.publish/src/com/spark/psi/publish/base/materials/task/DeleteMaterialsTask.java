package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ɾ��������Ϣ��һ�����߶�����
 * 
 */
public class DeleteMaterialsTask extends SimpleTask {

	/**
	 * ����id����
	 */
	private GUID[] ids;

	/**
	 * ���캯��
	 * 
	 * @param ids
	 *            ����id����
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
