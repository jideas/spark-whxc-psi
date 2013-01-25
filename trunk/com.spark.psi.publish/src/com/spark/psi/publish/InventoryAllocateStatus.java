package com.spark.psi.publish;

/**
 * ������״̬
 * 
 */
public enum InventoryAllocateStatus {

	Submitting("01", "���ύ", "", ""), //
	Submitted("02", "������", "������", "������"), //
	Allocating("03", "������", "������", "�ȴ�"), //
	AllocateOut("04", "�����", "�ѳ���", "�����"), //
	AllocateIn("05", "�����", "", ""), //
	Rejected("06", "���˻�", "", "");

	private String code;
	private String name;
	private String allocateOutstatusName;
	private String allocateInstatusName;

	private InventoryAllocateStatus(String code, String name,
			String allocateOutstatusName, String allocateInstatusName) {
		this.code = code;
		this.name = name;
		this.allocateOutstatusName = allocateOutstatusName;
		this.allocateInstatusName = allocateInstatusName;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return the allocateInstatusName
	 */
	public String getAllocateInstatusName() {
		return allocateInstatusName;
	}

	/**
	 * @return the allocateOutstatusName
	 */
	public String getAllocateOutstatusName() {
		return allocateOutstatusName;
	}

	public static InventoryAllocateStatus getStatusByCode(String code) {
		if (Submitting.code.equals(code)) {
			return Submitting;
		} else if (Submitted.code.equals(code)) {
			return Submitted;
		} else if (Allocating.code.equals(code)) {
			return Allocating;
		} else if (AllocateOut.code.equals(code)) {
			return AllocateOut;
		} else if (AllocateIn.code.equals(code)) {
			return AllocateIn;
		} else if (Rejected.code.equals(code)) {
			return Rejected;
		}
		return null;
	}
}
