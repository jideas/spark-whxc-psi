package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����֤����ɾ��ָ���û�
 * 
 */
public class RemoveUserTask extends SimpleTask {

	private GUID userId;

	public RemoveUserTask(GUID userId) {
		super();
		this.userId = userId;
	}

	public GUID getUserId() {
		return userId;
	}

}
