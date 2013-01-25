package com.spark.psi.publish;

/**
 * 库存调拨状态
 * 
 */
public enum InventoryAllocateStatus {

	Submitting("01", "待提交", "", ""), //
	Submitted("02", "待审批", "待审批", "待审批"), //
	Allocating("03", "待出库", "待出库", "等待"), //
	AllocateOut("04", "待入库", "已出库", "待入库"), //
	AllocateIn("05", "已完成", "", ""), //
	Rejected("06", "已退回", "", "");

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
