package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

public class ModifyUserPassTask extends SimpleTask {

	private String passWord;
	private String oldPassWord;

	public ModifyUserPassTask(String passWord, String oldPassWord) {
		super();
		this.passWord = passWord;
		this.oldPassWord = oldPassWord;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public String getOldPassWord() {
		return oldPassWord;
	}
}