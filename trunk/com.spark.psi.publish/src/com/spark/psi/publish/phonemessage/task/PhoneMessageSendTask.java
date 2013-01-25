package com.spark.psi.publish.phonemessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.common.components.SendMessageType;

/**
 * �ֻ����ŷ���
 */
public class PhoneMessageSendTask extends SimpleTask {

	private String phoneNo;

	private String message;

	private ReturnFlag flag;

	private SendMessageType sendType;

	public SendMessageType getSendType() {
		return sendType;
	}

	public void setSendType(SendMessageType sendType) {
		this.sendType = sendType;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReturnFlag getFlag() {
		return flag;
	}

	public void setFlag(ReturnFlag flag) {
		this.flag = flag;
	}

	public enum ReturnFlag {

		Success("0", "�޴����ύ�ɹ�"),

		NullContant("1", "�û��������롢�ֻ��š�������������һ��Ϊ��"),

		LoginError("2", "�û��������������"),

		NeedMoney("3", "�˻�����"),

		WrongPhoneNo("4", "�ֻ������ʽ����"),

		MessageTooLong("5", "��������������������"),

		IllegalCharacter("6", "�ύ���ݰ����Ƿ��ַ�"),

		PhoneTooMany("7", "�ֻ����������������"),

		NetError("8", "������ϵ�����Ϣ�ύʧ��"),

		WrongCharset("9", "��֧�ֵ��ַ���"),

		Failure("-1", "�ύʧ��"),

		NotActiving("-2", "ϵͳ��ͣ�÷��Ͷ��Ź��ܣ�"),

		NullPhoneNumber("-3", "δ�ṩ�ֻ�����"),

		NullMessage("-4", "�������Ϳ���Ϣ��"),

		TimeOut("-5", "��ǰ���ŷ�������ֹͣ����");

		private String code;
		private String title;

		private ReturnFlag(String code, String title) {
			this.code = code;
			this.title = title;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public boolean isSuccess() {
			return this == Success;
		}

		public static ReturnFlag getFlag(String code) {
			if (null == code || "".equals(code)) {
				return null;
			}
			int i = Integer.parseInt(code);
			switch (i) {
			case -5:
				return TimeOut;
			case -4:
				return NullMessage;
			case -3:
				return NullPhoneNumber;
			case -2:
				return NotActiving;
			case -1:
				return Failure;
			case 0:
				return Success;
			case 1:
				return NullContant;
			case 2:
				return LoginError;
			case 3:
				return NeedMoney;
			case 4:
				return WrongPhoneNo;
			case 5:
				return MessageTooLong;
			case 6:
				return IllegalCharacter;
			case 7:
				return PhoneTooMany;
			case 8:
				return NetError;
			case 9:
				return WrongCharset;

			default:
				return null;
			}
		}
	}

}
