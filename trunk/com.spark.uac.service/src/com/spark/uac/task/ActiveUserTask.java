package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����ָ���û�
 * 
 */
public class ActiveUserTask extends SimpleTask {

	/**
	 * ��Ӧ������Ϣid
	 */
	private GUID activationId;

	/**
	 * �û�����
	 */
	private String password;

	public ActiveUserTask(GUID activationId, String password) {
		super();
		this.activationId = activationId;
		this.password = password;
	}

	/**
	 * @return the activationId
	 */
	public GUID getActivationId() {
		return activationId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
