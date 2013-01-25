package com.spark.psi.publish.phonemessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.common.components.SendMessageType;

public class PhoneMessageLogTask extends SimpleTask {

	private String phoneNo;
	private SendMessageType sendType;

	public String getPhoneNo() {
		return phoneNo;
	}

	public PhoneMessageLogTask(String phoneNo, SendMessageType sendType) {
		this.phoneNo = phoneNo;
		this.sendType = sendType;
	}

	public SendMessageType getSendType() {
		return sendType;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
