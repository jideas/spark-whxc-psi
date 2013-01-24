package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ɾ������ͨѶ¼��Ŀ
 */
public class DeleteContactInfoTask extends SimpleTask {

	/**
	 * ��ĿID
	 */
	private GUID id;

	/**
	 * ���캯��
	 * 
	 * @param id
	 */
	public DeleteContactInfoTask(GUID id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

}
