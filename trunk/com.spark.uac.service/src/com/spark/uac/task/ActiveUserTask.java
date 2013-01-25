package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 激活指定用户
 * 
 */
public class ActiveUserTask extends SimpleTask {

	/**
	 * 对应激活信息id
	 */
	private GUID activationId;

	/**
	 * 用户密码
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
