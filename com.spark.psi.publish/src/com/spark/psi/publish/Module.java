package com.spark.psi.publish;

/**
 * 
 * <p>ҵ��ģ��</p>
 *


 *
 
 * @version 2012-3-8
 */
public enum Module{
	
	SalesOrder("01","���۶���"),
	SalesReturn("02","�����˻�"),
	PurchaseOrder("03","�ɹ�����"),
	PurchaseReturn("04","�ɹ��˻�"),
	InventoryAllocate("05","������"),
	Promotion("06","��Ʒ����");
	
	private String code;
	private String name;

	private Module(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	
}
