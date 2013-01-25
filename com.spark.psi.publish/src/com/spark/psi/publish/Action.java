package com.spark.psi.publish;

/**
 * �������ݵ�action����
 */
public enum Action {

	Add("���"),

	/**
	 * 
	 */
	Delete("ɾ��"),

	/**
	 * 
	 */
	Clear("���"),

	/**
	 * 
	 */
	Confirm("ȷ��"),

	/**
	 * 
	 */
	Cancel("����"),

	/**
	 * 
	 */
	Submit("�ύ"),

	/**
	 * 
	 */
	Approval("����"),

	/**
	 * 
	 */
	Stop("��ֹ"),

	/**
	 * 
	 */
	ReExecute("����ִ��"),

	/**
	 * 
	 */
	Consignment("ȷ�Ϸ���"),

	/**
	 * 
	 */
	Deny("����"),

	/**
	 * 
	 */
	DepartmentConfig("���ò���"),

	/**
	 * 
	 */
	AuthConfig("����Ȩ��"),

	/**
	 * 
	 */
	Resign("��ְ"),

	/**
	 * 
	 */
	Reinstatus("��ְ"),

	/**
	 * 
	 */
	OnSale("����"),

	/**
	 * 
	 */
	OffSale("ͣ��"),

	/**
	 * 
	 */
	Restore("�ָ�"),

	/**
	 * 
	 */
	Associate("����"),

	/**
	 * 
	 */
	Pay("����"),

	/**
	 * 
	 */
	Receipt("�տ�"),

	/**
	 * 
	 */
	SubmitReceipt("����"),

	/**
	 *
	 */
	Invalidate("����"),

	/**
	 * 
	 */
	Detail("����"),

	/**
	 * 
	 */
	AccountDetail("��������"),
	/**
	 *
	 */
	Adjust("����"),

	/**
	 * 
	 */
	Active("����"),

	/**
	 * 
	 */
	DeActive("ͣ��"),

	/**
	 * 
	 */
	CheckIn("���"),

	/**
	 * 
	 */
	CheckOut("����"),

	/**
	 * 
	 */
	AllocateIn("����"),

	/**
	 * 
	 */
	AllocateOut("����"),

	/**
	 * 
	 */
	Allocate("����"),

	/**
	 * 
	 */
	CreditConfig("����"),

	/**
	 *
	 */
	SetSupplier("���ù�Ӧ��"),
	/**
	 * 
	 */
	LookInventory("���"),
	/**
	 *
	 */
	Cause("ԭ��"),
	
	/**
	 *
	 */
	InventoryInfo("���ÿ����ֵ"),

	/**
	 *
	 */
	PurchaseInfo("�ɹ����"),

	/**
	 *
	 */
	SalesInfo("�������"),
	
	
	Select("ѡ��"),
	
	/**
	 *����
	 */
//	SMS("����"),
	/**
	 * ����ֵ
	 */
	SetThreshold("����ֵ"),
	/**
	 *�ʼ�
	 */
//	Email("�ʼ�")
	;

	
	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 * @param id
	 * @param title
	 */
	private Action(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}
