package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * ���Ͷ���
 */
public class SendSMSTask extends SimpleTask {

	/**
	 * �绰����
	 */
	private String[] mobileNumbers;

	/**
	 * ��Ϣ
	 */
	private String msg;

	/**
	 * ���캯��
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
