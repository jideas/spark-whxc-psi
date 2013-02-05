package com.spark.psi.publish;

import com.jiuqi.dna.core.def.obja.StructClass;

/**
 * 系统功能/职责定义<br>
 */
@StructClass
public enum Auth {
	Boss("0001", "公司管理"), //
	SalesManager("0002", "销售经理职责"), //
	Sales("0003", "销售职责"), //
	PurchaseManager("0004", "采购经理职责"), //
	Purchase("0005", "采购职责"), //
	AccountManager("0006", "财务经理职责"), //
	Account("0007", "财务职责"), //
	StoreKeeperManager("0008", "库管经理职责"), //
	StoreKeeper("0009", "库管职责"), //
	Distribute("0010", "统计员职责"), //
	Assistant("0011", "助理职责"), //
	StationManager("0012", "站点管理人职责"),
	Station("0013", "站点负责人职责") ,//
	ProduceManager("0014", "车间经理职责") ,//
	Produce("0015", "车间生产职责") ,//
	DeliverManager("0016", "配送经理职责") ,//
	Deliver("0017", "配送员职责") ,// 
	CustomerService("0018", "客服职责") ,// 
	/**
	 * 业务配置及配置向导
	 */
	MainFunction_ConfigManage("1001", "业务配置"), //
	SubFunction_ConfigMange_Company("100101", "维护企业信息"), SubFunction_ConfigMange_Department("100102", "部门信息"), SubFunction_ConfigMange_Employee(
			"100103", "维护员工"), SubFunction_ConfigMange_Employee_SetDeparemnt("10010301", "设置部门"), SubFunction_ConfigMange_SalesmanCredit(
			"100104", "维护销售授权"), SubFunction_ConfigMange_Approval("100105", "审核配置"), SubFunction_ConfigMange_Log("100106", "日志"), SubFunction_ConfigMange_GoodsCategory(
			"100107", "商品分类"), SubFunction_ConfigMange_Goods("100108", "商品"), SubFunction_ConfigMange_Customer("100109", "客户"), SubFunction_ConfigMange_Purchase(
			"100110", "供应商"), SubFunction_CofiigMange_Store("100111", "仓库"), SubFunction_ConfigWiazid_SelectAssistant("100112", "选择助理配置"),

	/**
	 * 通讯录
	 */
	MainFunction_ContactManage("1002", "联系人"), //
	Tag_ContactMange_Customer("100201", "客户"), Tab_ContactMange_Purchase("100202", "供应商"),

	/**
	 * 公告
	 */
	MainFunction_NoticeManage("1003", "公告"), //
	Tag_NoticeMange_NotRelease("100301", "未发布"), Tag_NoticeMange_Released("100302", "已发布"), Tag_NoticeMange_History("100303", "历史"),

	/**
	 * 商品管理
	 */
	MainFunction_GoodsManage("1004", "商品管理"), //
	SubFunction_GoodsMange_Category("100401", "管理分类"), 
	SubFunction_GoodsMange_UpdateGoods("100402", "编辑商品"), 
	SubFunction_GoodsMange_ChangeGoodsStatus("100403", "停售在售"), 
	SubFunction_GoodsMange_EditThreshold("100404", "编辑阀值"), 
	SubFunction_GoodsMange_ShowSalesAmount("100405", "查看销售价格"), 
	SubFunction_GoodsMange_ShowPurchaseAmount("100406", "查看采购价格"), 
	SubFunction_GoodsMange_EditSalesAmount("100407", "修改销售价格"), 
	SubFunction_GoodsMange_ShowSaleInfo("100408", "查看销售情况"), 
	SubFunction_GoodsMange_ShowPurchaseInfo("100409", "查看采购情况"), 
	SubFunction_GoodsMange_ShowInventoryInfo("100410", "查看库存情况"),

	MainFunction_MaterialManage("1027", "材料管理"),
	SubFunction_MaterialManage_Category("102701", "管理分类"), 
	SubFunction_MaterialManage_UpdateMaterial("102702", "编辑材料"), 
	SubFunction_MaterialManage_ChangeMaterialStatus("102703", "停售在售"), 
	SubFunction_MaterialManage_EditThreshold("102704", "编辑阀值"), 
	SubFunction_MaterialManage_ShowSalesAmount("102705", "查看销售价格"), 
	SubFunction_MaterialManage_ShowPurchaseAmount("102706", "查看采购价格"), 
	SubFunction_MaterialManage_EditSalesAmount("102707", "修改销售价格"), 
	SubFunction_MaterialManage_ShowSaleInfo("102708", "查看销售情况"), 
	SubFunction_MaterialManage_ShowPurchaseInfo("102709", "查看采购情况"), 
	SubFunction_MaterialManage_ShowInventoryInfo("102710", "查看库存情况"),

	/**
	 * 仓库管理
	 */
	MainFunction_StoreManage("1005", "仓库设置"), //
	SubFunction_StoreMange_Update("100501", "编辑仓库"), SubFunction_StoreMange_Changestatus("100502", "停用启用"), SubFunction_StoreMange_UpdateSaler(
			"100503", "编辑销售人员"), SubFunction_StoreMange_UpdateManger("100504", "编辑库管人员"),

	/**
	 * 客户管理
	 */
	MainFunction_CustomerMange("1006", "客户管理"), SubFunction_CustomerMange_BusPerson("100601", "分配业务负责人"), // 控制列表界面的按钮即详情界面的“销售人员”字段
	SubFunction_CustomerMange_Share("100602", "设置共享客户"), // 控制列表界面的按钮即详情界面的按钮
	SubFunction_CustomerMange_Credit("100603", "设置信用额度"), SubFunction_CustomerMange_Credit2("100609", "无限制设置信用额度"), Tag_CustomerMange_Trading(
			"100604", "未完交易"), Tag_CustomerMange_Traded("100605", "交易记录"), Tag_CustomerMange_Balance("100606", "往来账款"), Tag_CustomerMange_Invoice(
			"100607", "开票记录"),
	/**
	 * 供应商管理
	 */
	MainFunction_SupplierManage("1007", "供应商管理"), //
	Tag_SupplierManage_Trading("100701", "未完交易"), Tag_SupplierManage_Traded("100702", "交易记录"), SubFunction_SupplierManage_Credit("100703",
			"设置信用额度"), Tag_SupplierManage_Balance("100704", "往来账款"),

	SubFunction_PartnerMange_ShowCredit("100608", "显示信用额度"), // 控制列表界面的按钮即详情界面的“信用额度”、“账期天数”、“提前X天提醒”字段

	MainFunction_PurchaseManage("1008", "商品采购"), Tag_PurchaseMange_Order("100801", "订单页签"), Tag_PurchaseMange_Delete("10080101", "删除"), Tag_PurchaseMange_EditThreshold(
			"10080102", "修改阈值"), Tag_PurchaseMange_Return("100802", "退货页签"), Tag_PurchaseMange_Approval("100803", "审批"), Tag_PurchaseMange_Following(
			"100805", "跟踪"), Tag_PurchaseMange_Record("100806", "记录"),

	MainFunction_SalesManage("1009", "订单销售"), //
	Tag_SalesMange_Approval("100901", "订单审批"),
	/**
	 * 促销
	 */
	MainFunction_PromotionManage("1010", "促销管理"), //
	Tag_PromotionMange_Edit("101001", "编辑促销"), Tag_PromotionMange_Approval("101002", "审批"), Tag_PromotionMange_Promotioning("101003", "促销中"), Tag_PromotionMnage_Promotioned(
			"101004", "促销记录"), SubFunction_PromotionMange_Stop("101005", "中止促销"),
	/**
	 * 商品零售
	 */
	MainFunction_RetailManage("1011", "商品零售"), //	

	MainFunction_DistributeManage("1012", "销售配货"), //	

	/**
	 * 商品拆装
	 */
	MainFunction_GoodsRefactor("1013", "商品拆装"), //

	/**
	 * 库存报损
	 */
	MainFunction_InventoryReportLoss("1032", "库存报损"),
	SubFunction_ReportLoss_Create("1032001", "新增报损"),
	SubFunction_ReportLoss_Approval("1032002", "审批报损"),
	SubFunction_ReportLoss_AccountApproval("1032003", "财务审批"),

	/**
	 * 库存调拨管理
	 */
	MainFunction_InventoryAllocate("1014", "库存调拨"), //
	Tag_InventoryAllocate_Edit("101401", "编辑调拨单"), Tag_InventoryAllocate_Approval("101402", "审批调拨"), Tag_InventoryAllocate_Following(
			"101403", "跟踪"), Tag_InventoryAllocate_Record("101404", "记录"),

	/**
	 * 入库管理
	 */
	MainFunction_InventoryCheckin("1015", "入库管理"), //
	Tag_InventoryCheckin_Edit("101501", "采购入库"), Tag_InventoryCheckin_Return("101502", "退货"), Tag_InventoryCheckin_Other("101503", "其他入库"), Tag_InventoryCheckin_Record(
			"101504", "入库记录"),
	/**
	 * 若没有此功能 则需要在入库记录页签下增加 “新增领零采入库” 按钮
	 */
	SubFunction_InventoryCheckin_More("101505", "完整的入库管理"),

	/**
	 * 出库管理
	 */
	MainFunction_InventoryCheckout("1016", "出库管理"), //

	/**
	 * 库存盘点
	 */
	MainFunction_InventoryCount("1017", "库存盘点"), //
	SubFunction_InventoryCount_SelectObject("101701", "选择盘点对象"), // 控制新增盘点时不能选择盘点对象，默认为商品库存盘点

	/**
	 * 库存查询
	 * 
	 */
	MainFunction_InventoryQuery("1018", "库存查询"), //
	Tab_InventoryQuery_Other("101801", "其他库存页签"), Tag_InventoryQuery_Goods("101802", "商品库存"), Tag_InventoryQuery_StrSteam("101803", "库存流水"), Tag_InventoryQuery_Book(
			"101804", "库存台帐"), SubFunction_InventoryQuery_Price("101805", "库存金额相关"), SubFunction_InventoryQuery_Count("101806", "库存数量相关"),

	MainFunction_BalanceManage("1019", "往来管理"), //
	Tag_BalanceManage_Customer("101901", "客户往来"), Tag_BalanceManage_Supplier("101902", "供应商往来"), SubFunction_BalanceMange_Init("101903",
			"初始化往来款"), MainFunction_InvoiceManange("1020", "开票管理"), //
	
	
	MainFunction_ApprovalManage("1023", "单据审批"), //
	// MainFunction_Report("1024", "经营管控"), //
	// MainFunction_SalesMonitor("1025", "销售监控"), //
	MainFunction_UserConfig("1026", "个人设置"),

	/**
	 * 付款
	 */
	MainFunction_PaymentManage("1021", "付款管理"), //
	Tag_PaymentManage_Paying("102101", "应付情况页签"), 
	Tag_PaymentManage_Add("102102", "新增付款页签"), 
	Tag_PaymentManage_List("102103", "付款记录页签"),
	SubFunction_PaymentManage_Pay("102101001", "付款"),
	SubFunction_PaymentManage_Approval("102101002", "审批"),
	/**
	 * 收款
	 */
	MainFunction_ReceiptManage("1022", "收款管理"), //
	Tag_ReceiptManage_Receipting("102201", "应收情况页签"), 
	Tag_ReceiptManage_Add("102202", "新增收款页签"), 
	Tag_ReceiptManage_List("102203", "收款记录页签"), 
	Tag_ReceiptManage_Retial("102204", "零售交款页签"),
	SubFunction_ReceiptManage_Receip("102201001", "收款"),

	
	MainFunction_StationManage("1030", "站点管理"), //
	Tab_StationManageList("103001", "站点列表"), Tag_StationManage_Create("103002", "新增站点"),

	MainFunction_GoodsBomManage("1031", "商品BOM管理"), Tab_GoodsBom_GodosItemList("103101", "商品列表"), Tab_GoodsBom_SubmitingList("103102",
			"待提交bom列表"), Tab_GoodsBom_ApprovingList("103103", "待审批bom列表"),

	MainFunction_OnlineOrderManager("1033", "网上订单"), 
	SubFunction_OnlineOrder_Summary("1033001", "汇总网上订单"),
	SubFunction_OnlineOrder_Deliver("1033002", "配送网上订单"),
	SubFunction_OnlineOrder_ConfirmArrive("1033003", "确认到货"),
	SubFunction_OnlineOrder_ConfirmFinish("1033004", "确认完成"),
	SubFunction_OnlineOrder_DeliverOnly("1033005", "只有配送权限"),
	SubFunction_OnlineOrder_Separate("1033006", "拆分订单"),
	MainFunction_ProduceOrderManager("1034", "生产订单"),
	SubFunction_ProduceOrder_Create("1034001", "新增生产订单"),
	SubFunction_ProduceOrder_Approval("1034002", "审批生产订单"),
	SubFunction_ProduceOrder_Produce("1034003", "生产包括领料|退料|完成"),
	MainFunction_DeliveryOrderManager("1035", "配送单"),
	Tab_Delivery_UnHandle("1035100", "待配送"),
	Tab_Delivery_Delivering("1035101", "配送中"),
	Tab_Delivery_Arrived("1035102", "已到货"),
	Tab_Delivery_Exception("1035103", "异常"),
	SubFunction_DeliveryOrder_Deliver("1035001", "确认配送"), 
	SubFunction_DeliveryOrder_Confirm("1035002", "确认到货"),
	SubFunction_DeliveryOrder_HandlException("1035003", "处理异常"), 
	MainFunction_DeliveryTicketManager("1036", "发货单"), 
	MainFunction_QueryManager("1037", "查询管理"), 
	MainFunction_OnlineReturnManager("1040", "网上订单退货"),
	SubFunction_OnlineReturn_Create("1040001", "创建网上退货"),
	SubFunction_OnlineReturn_Approval("1040002", "审批网上退货"),
	SubFunction_OnlineReturn_Confirm("1040003", "确认网上退货"),
	MainFunction_JointSettleMent("1041", "联营供应商结算"),
	MainFunction_RealGoodsSales("1042","成品销售"),

	MainFunction_GoodsSplitManage("1050","成品拆分"),
	SubFunction_GoodsSplitManage_Create("1050001","创建成品拆分"),
	SubFunction_GoodsSplitManage_Distribution("1050003","分配材料仓库"),
	SubFunction_GoodsSplitManage_Approval("1050002","审批成品拆分");
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
