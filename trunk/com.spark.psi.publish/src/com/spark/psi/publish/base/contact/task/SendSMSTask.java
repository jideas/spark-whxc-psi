package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 发送短信
 */
public class SendSMSTask extends SimpleTask {

	/**
	 * 电话号码
	 */
	private String[] mobileNumbers;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 构造函数
	 * 
	 * @param msg
	 * @param mobileNumbers
	 */
	public SendSMSTask(String msg, String... mobileNumbers) {
		super();
		this.mobileNumbers = mobileNumbers;
		this.msg = msg;
	}

	/**
	 * @return the ids
	 */
	public String[] getMobileNos() {
		return mobileNumbers;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
