package com.spark.psi.publish;

public enum VantagesType {
	Card("01", "��ֵ��"),
	Handsel("02","����"),
	Consume("03", "����"),Clear("05", "ϵͳ����"), Refund("04", "�˻�");

	private String code, name;

	private VantagesType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static VantagesType getType(String code) {
		if (Card.getCode().equals(code)) {
			return Card;
		}
		 else if(Clear.getCode().equals(code))
		 {
		 return Clear;
		 }
		else if (Consume.getCode().equals(code)) {
			return Consume;
		} else if (Refund.getCode().equals(code)) {
			return Refund;
		}
		return null;
	}

}