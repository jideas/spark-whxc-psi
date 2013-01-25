package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ɾ����Ʒ��Ϣ��һ�����߶�����
 * 
 */
public class DeleteGoodsTask extends SimpleTask {

	/**
	 * ��Ʒid����
	 */
	private GUID[] ids;

	/**
	 * ���캯��
	 * 
	 * @param ids
	 *            ��Ʒid����
	 */
	public DeleteGoodsTask(GUID... ids) {
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
