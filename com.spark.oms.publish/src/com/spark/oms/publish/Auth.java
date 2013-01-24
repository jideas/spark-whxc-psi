package com.spark.oms.publish;

/**
 * 系统功能/职责定义<
 */
public enum Auth {	
	
	/**
	 * 角色
	 */
	SalesCommissionerRole("JS001","销售专员"),
	HRAssistantRole("JS002","助理(人力资源)"),
	PinTubeCommissionerRole("JS003","销管专员"),
	FinanceSpecialistRole("JS004","财务专员"),
	ProductSpecialistRole("JS005","产品专员"),
	SalesDirectorRole("JS006","销售总监"),
	DivisionLeadersRole("JS007","事业部领导"),
	
	/**
	 * 客户管理
	 */
	CustomerManagement("01","客户管理"),
	CustomerManagement_Query("0101","查询"),
	CustomerManagement_RefundRequest("0102","退款申请"),
	CustomerManagement_NewTenant("0103","新建租户"),
	CustomerManagement_UpdateTenant("0104","修改租户(一般信息)"),
	CustomerManagement_UpdateTenantCore("0105","修改租户核心信息"),
	CustomerManagement_SendMessage("0106","发送短信"),
	CustomerManagement_ConfigTenantSystem("0107","配置租户系统"),
	CustomerManagement_CommunicationPrepaidPhone("0108","通讯充值"),
	CustomerManagement_StopRequest("0109","停用申请"),
	CustomerManagement_ChangeService("0110","变更服务"),
	CustomerManagement_RenewalService("0111","续签服务"),
	CustomerManagement_RecoverService("0112","恢复服务"),
	
	/**
	 * 产品管理
	 */
	ProductManagement("02","产品管理"),
	ProductManagement_Query("0201","查询"),
	ProductManagement_New("0202","新建"),
	ProductManagement_Update("0203","修改"),
	ProductManagement_Delete("0204","删除"),
	//
	ProductManagement_Stop("0205","停用"),
	
	
	/**
	 * 订单管理
	 */
	OrderManagement("03","订单管理"),
	OrderManagement_Query("0301","查询"),
	OrderManagement_New("0302","新建"),
	OrderManagement_Update("0303","修改"),
	OrderManagement_Print("0304","打印"),
	
	/**
	 * 
	 * 收款管理
	 */
	CollectionManagement("04","收款管理"),
	CollectionManagement_Query("0401","查询"),
	CollectionManagement_Confirm("0402","确认收款"),
	CollectionManagement_ConfirmRefund("0403","确认退款"),
	
	/**
	 * 开票记录
	 */
	BillingRecord("05","开票记录"),
	BillingRecord_Query("0501","查询"),
	BillingRecord_Billing("0502","开票"),
	
	/**
	 * 运维管理
	 */
	OperationalManagement("06","运维管理"),
	OperationalManagement_ChannelsSet("0601","通道设置"),
	OperationalManagement_PrintMessageFlow("0602","打印短信流水"),
	OperationalManagement_ExportMessageFlow("0603","导出短信流水"),
	OperationalManagement_LookupMessageFlow("0604","查看短信流水"),
	
	/**
	 * 收支记录
	 */
	IncomingAndOutgoingsRecord("07","收支记录"),
	IncomingAndOutgoingsRecord_Query("0701","查询"),
	IncomingAndOutgoingsRecord_New("0702","新建"),
	
	/**
	 * 用户管理
	 */
	UserManagement("08","用户管理"),
	UserManagement_Query("0801","查询"),
	UserManagement_New("0802","新建"),
	UserManagement_Update("0803","修改"),
	UserManagement_LeaveOffice("0804","离职"),
	UserManagement_BlockUp("0805","停用"),
	
	/**
	 * 角色管理
	 */
	RoleManagement("09","角色管理" ),
	RoleManagement_Query("0901","查询"),
	RoleManagement_New("0902","新建"),
	RoleManagement_Update("0903","修改"),
	RoleManagement_Delete("0904","删除"),
	
	
	/**
	 * 服务停用确认
	 */
	OutOfService("10","服务停用确认" ),
	OutOfService_Query("1001","查询"),
	OutOfService_Confirm("1002","确认停用");
	
	/**
	 * 功能/职责代码
	 */
	private String code;

	/**
	 * 功能/职责名称
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private Auth(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public static Auth getAuthByCode(String code) {
		for (Auth auth : Auth.values()) {
			if (auth.getCode().equals(code)) {
				return auth;
			}
		}
		throw new IllegalArgumentException(code + "不是一个正确的权限枚举");
	}
}