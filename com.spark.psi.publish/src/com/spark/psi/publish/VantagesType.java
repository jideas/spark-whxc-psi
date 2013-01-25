package com.spark.psi.publish;

public enum VantagesType {
	Card("01", "面值卡"),
	Handsel("02","赠送"),
	Consume("03", "消费"),Clear("05", "系统清零"), Refund("04", "退货");

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