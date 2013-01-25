package com.spark.psi.publish;

/**
 * �ֿ�����
 */
public enum StoreType {

	/**
	 * ���ϲֿ�
	 */
	MerterialsStore("01", "���ϲֿ�"), //
	/**
	 * ��Ʒ�ֿ�
	 */
	GoodsStore("02", "��Ʒ�ֿ�");

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private StoreType(String code, String name) {
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
	public final static StoreType getCheckingInType(String code) {
		if (MerterialsStore.code.equals(code)) {
			return MerterialsStore;
		} else if (GoodsStore.code.equals(code)) {
			return GoodsStore;
		}
		return null;
	}

}
