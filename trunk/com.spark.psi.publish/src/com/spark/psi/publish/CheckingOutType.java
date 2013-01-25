package com.spark.psi.publish;

/**
 * ��������
 */
public enum CheckingOutType {

	Sales("01", "���۳���"), //
	Return("02", "�ɹ��˻�"), //
	RealGoods("03", "��Ʒ����"), //
	Kit("04", "��������"), //
	Mateiral_Take("05", "���ϳ���"), //
	Mateiral_Return("06", "���ϳ���"), //
	Joint("07", "��Ӫ����"), //
	Gift("08", "��Ʒ����");

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;

	public boolean isMaterialTakeOrReturn() {
		return this == Mateiral_Take || this == Mateiral_Return || this == RealGoods;
	}

	public boolean isRealGoods() {
		return this == RealGoods;
	}

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private CheckingOutType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���ݴ����ȡö�ٶ���
	 * 
	 * @param code
	 * @return
	 */
	public final static CheckingOutType getCheckingOutType(String code) {
		if (Sales.code.equals(code)) {
			return Sales;
		} else if (Return.code.equals(code)) {
			return Return;
		} else if (RealGoods.code.equals(code)) {
			return RealGoods;
		} else if (Kit.code.equals(code)) {
			return Kit;
		} else if (Mateiral_Return.code.equals(code)) {
			return Mateiral_Return;
		} else if (Mateiral_Take.code.equals(code)) {
			return Mateiral_Take;
		} else if (Joint.code.equals(code)) {
			return Joint;
		} else if (Gift.code.equals(code)) {
			return Gift;
		}
		return null;
	}

}
