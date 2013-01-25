package com.spark.psi.publish;

/**
 * 
 * <p>��Ʒ���Ԥ������</p>
 *	����Ʒ���������Ϣҳǩ��������
 *		�����Ԥ��
 *		������Ԥ��


 *
 
 * @version 2012-3-16
 */
public enum InventoryWarningType{
	/** �����вֿ��ܺͽ��Ԥ�� */
	ALL_Amount("01","�ܿ���"),
	/** �����вֿ��ܺ�����Ԥ�� */
	ALL_Count("02","�ܿ�����"),
	/** �������ֿ�Ľ��Ԥ��  */
	Store_Amount("03","�ֿ���"),
	/** �������ֿ������Ԥ�� */
	Store_Count("04","�ֿ�����");

	private String code;
	private String name;

	private InventoryWarningType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return code
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

	public static InventoryWarningType getGoodsWarnningType(String code) {
		for(InventoryWarningType entity : InventoryWarningType.values()){
			if(entity.code.equals(code)){
				return entity;
			}
        }
		return null;
	}

	
}
