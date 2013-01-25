package com.spark.psi.publish;

/**
 * �������
 */

public enum InventoryType {
	/**���Ͽ�� */
	Materials("01","���Ͽ��"),
	/** ��Ʒ��� */
	Goods("02","��Ʒ���"),
	/** ������� */
	Others("03","�������");
	
	private String code,name;
	private InventoryType(String code,String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static InventoryType getEnum(String code) {
		if(InventoryType.Goods.getCode().equals(code)) {
			return Goods;
		} else if(InventoryType.Others.getCode().equals(code)){
			return Others;
		}
		else if(InventoryType.Materials.getCode().equals(code)){
			return Materials;
		}else {
			return null;
		}
	}
	
}
