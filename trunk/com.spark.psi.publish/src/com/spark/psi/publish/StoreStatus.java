/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.store.intf.common
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       zhongxin        
 * ============================================================*/

package com.spark.psi.publish;

/**
 * �ֿ�״̬
 */
public enum StoreStatus{
	ALL("-1", "ȫ��״̬ "), //
	/**
	 * δ����
	 */
	DISABLED("0", "δ����"), //
	/**
	 * ������
	 */
	ENABLE("1", "���� "), //
	/**
	 * �̵���
	 */
	ONCOUNTING("2", "�̵��� "), //
	/**
	 * ��ͣ��
	 */
	STOP("3", "ͣ��"),
	/**
	 * ͣ���̵�
	 */
	STOPANDONCOUNTING("4", "�̵���");;

	private String code;
	private String name;

	private StoreStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static StoreStatus getStatusByCode(String code) {
		if (DISABLED.code.equals(code)) {
			return DISABLED;
		} else if (ENABLE.code.equals(code)) {
			return ENABLE;
		} else if (ONCOUNTING.code.equals(code)) {
			return ONCOUNTING;
		} else if (STOP.code.equals(code)) {
			return STOP;
		} 
		else if (STOPANDONCOUNTING.code.equals(code)) {
			return STOPANDONCOUNTING;
		}else {
			return null;
		}
	}
}
