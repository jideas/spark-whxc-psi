package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class UpdateActivationPasswordTask extends SimpleTask {

	private GUID activationId;

	private String password;

	public UpdateActivationPasswordTask(GUID activationId, String password) {
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
