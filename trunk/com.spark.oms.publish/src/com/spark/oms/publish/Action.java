package com.spark.oms.publish;

/**
 * 所有数据的action定义
 */
public enum Action {
	
	confirmRefund("退款"),
	
	Draw("开票"),
	
	lookUp("查看"),
	
	configuration("配置"),
	
	edit("编辑"),

	Add("添加"),

	/**
	 * 
	 */
	Delete("删除"),

	/**
	 * 
	 */
	Clear("清空"),

	/**
	 * 
	 */
	Confirm("确认"),

	/**
	 * 
	 */
	Cancel("撤销"),

	/**
	 * 
	 */
	Submit("提交"),

	/**
	 * 
	 */
	Approval("审批"),

	/**
	 * 
	 */
	Stop("中止"),

	/**
	 * 
	 */
	ReExecute("重新执行"),

	/**
	 * 
	 */
	Consignment("确认发货"),

	/**
	 * 
	 */
	Deny("驳回"),

	/**
	 * 
	 */
	DepartmentConfig("设置部门"),

	/**
	 * 
	 */
	AuthConfig("设置权限"),

	/**
	 * 
	 */
	Resign("离职"),

	/**
	 * 
	 */
	Reinstatus("复职"),

	/**
	 * 
	 */
	OnSale("在售"),

	/**
	 * 
	 */
	OffSale("停售"),

	/**
	 * 
	 */
	Restore("恢复"),

	/**
	 * 
	 */
	Associate("关联"),

	/**
	 * 
	 */
	Pay("付款"),

	/**
	 * 
	 */
	Receipt("收款"),

	/**
	 * 
	 */
	SubmitReceipt("交款"),

	/**
	 *
	 */
	Invalidate("作废"),

	/**
	 * 
	 */
	Detail("详情"),

	/**
	 *
	 */
	Adjust("调整"),

	/**
	 * 
	 */
	Active("启用"),

	/**
	 * 
	 */
	DeActive("停用"),

	/**
	 * 
	 */
	CheckIn("入库"),

	/**
	 * 
	 */
	CheckOut("出库"),

	/**
	 * 
	 */
	AllocateIn("调入"),

	/**
	 * 
	 */
	AllocateOut("调出"),

	/**
	 * 
	 */
	Allocate("分配"),

	/**
	 * 
	 */
	CreditConfig("信用"),

	/**
	 *
	 */
	SetSupplier("设置供应商"),
	/**
	 * 
	 */
	LookInventory("查库"),
	/**
	 *
	 */
	Cause("原因"),
	
	/**
	 *
	 */
	InventoryInfo("设置库存阈值"),

	/**
	 *
	 */
	PurchaseInfo("采购情况"),

	/**
	 *
	 */
	SalesInfo("销售情况"),
	
	/**
	 *短信
	 */
	SMS("短信"),
	/**
	 * 设阈值
	 */
	SetThreshold("设阈值"),
	/**
	 *邮件
	 */
	Email("邮件");

	
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