package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除个人通讯录条目
 */
public class DeleteContactInfoTask extends SimpleTask {

	/**
	 * 条目ID
	 */
	private GUID id;

	/**
	 * 构造函数
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
