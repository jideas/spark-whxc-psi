package com.spark.psi.publish;

import com.jiuqi.dna.core.def.obja.StructClass;

/**
 * ϵͳ����/ְ����<br>
 */
@StructClass
public enum Auth {
	Boss("0001", "��˾����"), //
	SalesManager("0002", "���۾���ְ��"), //
	Sales("0003", "����ְ��"), //
	PurchaseManager("0004", "�ɹ�����ְ��"), //
	Purchase("0005", "�ɹ�ְ��"), //
	AccountManager("0006", "������ְ��"), //
	Account("0007", "����ְ��"), //
	StoreKeeperManager("0008", "��ܾ���ְ��"), //
	StoreKeeper("0009", "���ְ��"), //
	Distribute("0010", "ͳ��Աְ��"), //
	Assistant("0011", "����ְ��"), //
	StationManager("0012", "վ�������ְ��"),
	Station("0013", "վ�㸺����ְ��") ,//
	ProduceManager("0014", "���侭��ְ��") ,//
	Produce("0015", "��������ְ��") ,//
	DeliverManager("0016", "���;���ְ��") ,//
	Deliver("0017", "����Աְ��") ,// 
	CustomerService("0018", "�ͷ�ְ��") ,// 
	/**
	 * ҵ�����ü�������
	 */
	MainFunction_ConfigManage("1001", "ҵ������"), //
	SubFunction_ConfigMange_Company("100101", "ά����ҵ��Ϣ"), SubFunction_ConfigMange_Department("100102", "������Ϣ"), SubFunction_ConfigMange_Employee(
			"100103", "ά��Ա��"), SubFunction_ConfigMange_Employee_SetDeparemnt("10010301", "���ò���"), SubFunction_ConfigMange_SalesmanCredit(
			"100104", "ά��������Ȩ"), SubFunction_ConfigMange_Approval("100105", "�������"), SubFunction_ConfigMange_Log("100106", "��־"), SubFunction_ConfigMange_GoodsCategory(
			"100107", "��Ʒ����"), SubFunction_ConfigMange_Goods("100108", "��Ʒ"), SubFunction_ConfigMange_Customer("100109", "�ͻ�"), SubFunction_ConfigMange_Purchase(
			"100110", "��Ӧ��"), SubFunction_CofiigMange_Store("100111", "�ֿ�"), SubFunction_ConfigWiazid_SelectAssistant("100112", "ѡ����������"),

	/**
	 * ͨѶ¼
	 */
	MainFunction_ContactManage("1002", "��ϵ��"), //
	Tag_ContactMange_Customer("100201", "�ͻ�"), Tab_ContactMange_Purchase("100202", "��Ӧ��"),

	/**
	 * ����
	 */
	MainFunction_NoticeManage("1003", "����"), //
	Tag_NoticeMange_NotRelease("100301", "δ����"), Tag_NoticeMange_Released("100302", "�ѷ���"), Tag_NoticeMange_History("100303", "��ʷ"),

	/**
	 * ��Ʒ����
	 */
	MainFunction_GoodsManage("1004", "��Ʒ����"), //
	SubFunction_GoodsMange_Category("100401", "�������"), 
	SubFunction_GoodsMange_UpdateGoods("100402", "�༭��Ʒ"), 
	SubFunction_GoodsMange_ChangeGoodsStatus("100403", "ͣ������"), 
	SubFunction_GoodsMange_EditThreshold("100404", "�༭��ֵ"), 
	SubFunction_GoodsMange_ShowSalesAmount("100405", "�鿴���ۼ۸�"), 
	SubFunction_GoodsMange_ShowPurchaseAmount("100406", "�鿴�ɹ��۸�"), 
	SubFunction_GoodsMange_EditSalesAmount("100407", "�޸����ۼ۸�"), 
	SubFunction_GoodsMange_ShowSaleInfo("100408", "�鿴�������"), 
	SubFunction_GoodsMange_ShowPurchaseInfo("100409", "�鿴�ɹ����"), 
	SubFunction_GoodsMange_ShowInventoryInfo("100410", "�鿴������"),

	MainFunction_MaterialManage("1027", "���Ϲ���"),
	SubFunction_MaterialManage_Category("102701", "�������"), 
	SubFunction_MaterialManage_UpdateMaterial("102702", "�༭����"), 
	SubFunction_MaterialManage_ChangeMaterialStatus("102703", "ͣ������"), 
	SubFunction_MaterialManage_EditThreshold("102704", "�༭��ֵ"), 
	SubFunction_MaterialManage_ShowSalesAmount("102705", "�鿴���ۼ۸�"), 
	SubFunction_MaterialManage_ShowPurchaseAmount("102706", "�鿴�ɹ��۸�"), 
	SubFunction_MaterialManage_EditSalesAmount("102707", "�޸����ۼ۸�"), 
	SubFunction_MaterialManage_ShowSaleInfo("102708", "�鿴�������"), 
	SubFunction_MaterialManage_ShowPurchaseInfo("102709", "�鿴�ɹ����"), 
	SubFunction_MaterialManage_ShowInventoryInfo("102710", "�鿴������"),

	/**
	 * �ֿ����
	 */
	MainFunction_StoreManage("1005", "�ֿ�����"), //
	SubFunction_StoreMange_Update("100501", "�༭�ֿ�"), SubFunction_StoreMange_Changestatus("100502", "ͣ������"), SubFunction_StoreMange_UpdateSaler(
			"100503", "�༭������Ա"), SubFunction_StoreMange_UpdateManger("100504", "�༭�����Ա"),

	/**
	 * �ͻ�����
	 */
	MainFunction_CustomerMange("1006", "�ͻ�����"), SubFunction_CustomerMange_BusPerson("100601", "����ҵ������"), // �����б����İ�ť���������ġ�������Ա���ֶ�
	SubFunction_CustomerMange_Share("100602", "���ù���ͻ�"), // �����б����İ�ť���������İ�ť
	SubFunction_CustomerMange_Credit("100603", "�������ö��"), SubFunction_CustomerMange_Credit2("100609", "�������������ö��"), Tag_CustomerMange_Trading(
			"100604", "δ�꽻��"), Tag_CustomerMange_Traded("100605", "���׼�¼"), Tag_CustomerMange_Balance("100606", "�����˿�"), Tag_CustomerMange_Invoice(
			"100607", "��Ʊ��¼"),
	/**
	 * ��Ӧ�̹���
	 */
	MainFunction_SupplierManage("1007", "��Ӧ�̹���"), //
	Tag_SupplierManage_Trading("100701", "δ�꽻��"), Tag_SupplierManage_Traded("100702", "���׼�¼"), SubFunction_SupplierManage_Credit("100703",
			"�������ö��"), Tag_SupplierManage_Balance("100704", "�����˿�"),

	SubFunction_PartnerMange_ShowCredit("100608", "��ʾ���ö��"), // �����б����İ�ť���������ġ����ö�ȡ���������������������ǰX�����ѡ��ֶ�

	MainFunction_PurchaseManage("1008", "��Ʒ�ɹ�"), Tag_PurchaseMange_Order("100801", "����ҳǩ"), Tag_PurchaseMange_Delete("10080101", "ɾ��"), Tag_PurchaseMange_EditThreshold(
			"10080102", "�޸���ֵ"), Tag_PurchaseMange_Return("100802", "�˻�ҳǩ"), Tag_PurchaseMange_Approval("100803", "����"), Tag_PurchaseMange_Following(
			"100805", "����"), Tag_PurchaseMange_Record("100806", "��¼"),

	MainFunction_SalesManage("1009", "��������"), //
	Tag_SalesMange_Approval("100901", "��������"),
	/**
	 * ����
	 */
	MainFunction_PromotionManage("1010", "��������"), //
	Tag_PromotionMange_Edit("101001", "�༭����"), Tag_PromotionMange_Approval("101002", "����"), Tag_PromotionMange_Promotioning("101003", "������"), Tag_PromotionMnage_Promotioned(
			"101004", "������¼"), SubFunction_PromotionMange_Stop("101005", "��ֹ����"),
	/**
	 * ��Ʒ����
	 */
	MainFunction_RetailManage("1011", "��Ʒ����"), //	

	MainFunction_DistributeManage("1012", "�������"), //	

	/**
	 * ��Ʒ��װ
	 */
	MainFunction_GoodsRefactor("1013", "��Ʒ��װ"), //

	/**
	 * ��汨��
	 */
	MainFunction_InventoryReportLoss("1032", "��汨��"),
	SubFunction_ReportLoss_Create("1032001", "��������"),
	SubFunction_ReportLoss_Approval("1032002", "��������"),
	SubFunction_ReportLoss_AccountApproval("1032003", "��������"),

	/**
	 * ����������
	 */
	MainFunction_InventoryAllocate("1014", "������"), //
	Tag_InventoryAllocate_Edit("101401", "�༭������"), Tag_InventoryAllocate_Approval("101402", "��������"), Tag_InventoryAllocate_Following(
			"101403", "����"), Tag_InventoryAllocate_Record("101404", "��¼"),

	/**
	 * ������
	 */
	MainFunction_InventoryCheckin("1015", "������"), //
	Tag_InventoryCheckin_Edit("101501", "�ɹ����"), Tag_InventoryCheckin_Return("101502", "�˻�"), Tag_InventoryCheckin_Other("101503", "�������"), Tag_InventoryCheckin_Record(
			"101504", "����¼"),
	/**
	 * ��û�д˹��� ����Ҫ������¼ҳǩ������ �������������⡱ ��ť
	 */
	SubFunction_InventoryCheckin_More("101505", "������������"),

	/**
	 * �������
	 */
	MainFunction_InventoryCheckout("1016", "�������"), //

	/**
	 * ����̵�
	 */
	MainFunction_InventoryCount("1017", "����̵�"), //
	SubFunction_InventoryCount_SelectObject("101701", "ѡ���̵����"), // ���������̵�ʱ����ѡ���̵����Ĭ��Ϊ��Ʒ����̵�

	/**
	 * ����ѯ
	 * 
	 */
	MainFunction_InventoryQuery("1018", "����ѯ"), //
	Tab_InventoryQuery_Other("101801", "�������ҳǩ"), Tag_InventoryQuery_Goods("101802", "��Ʒ���"), Tag_InventoryQuery_StrSteam("101803", "�����ˮ"), Tag_InventoryQuery_Book(
			"101804", "���̨��"), SubFunction_InventoryQuery_Price("101805", "��������"), SubFunction_InventoryQuery_Count("101806", "����������"),

	MainFunction_BalanceManage("1019", "��������"), //
	Tag_BalanceManage_Customer("101901", "�ͻ�����"), Tag_BalanceManage_Supplier("101902", "��Ӧ������"), SubFunction_BalanceMange_Init("101903",
			"��ʼ��������"), MainFunction_InvoiceManange("1020", "��Ʊ����"), //
	
	
	MainFunction_ApprovalManage("1023", "��������"), //
	// MainFunction_Report("1024", "��Ӫ�ܿ�"), //
	// MainFunction_SalesMonitor("1025", "���ۼ��"), //
	MainFunction_UserConfig("1026", "��������"),

	/**
	 * ����
	 */
	MainFunction_PaymentManage("1021", "�������"), //
	Tag_PaymentManage_Paying("102101", "Ӧ�����ҳǩ"), 
	Tag_PaymentManage_Add("102102", "��������ҳǩ"), 
	Tag_PaymentManage_List("102103", "�����¼ҳǩ"),
	SubFunction_PaymentManage_Pay("102101001", "����"),
	SubFunction_PaymentManage_Approval("102101002", "����"),
	/**
	 * �տ�
	 */
	MainFunction_ReceiptManage("1022", "�տ����"), //
	Tag_ReceiptManage_Receipting("102201", "Ӧ�����ҳǩ"), 
	Tag_ReceiptManage_Add("102202", "�����տ�ҳǩ"), 
	Tag_ReceiptManage_List("102203", "�տ��¼ҳǩ"), 
	Tag_ReceiptManage_Retial("102204", "���۽���ҳǩ"),
	SubFunction_ReceiptManage_Receip("102201001", "�տ�"),

	
	MainFunction_StationManage("1030", "վ�����"), //
	Tab_StationManageList("103001", "վ���б�"), Tag_StationManage_Create("103002", "����վ��"),

	MainFunction_GoodsBomManage("1031", "��ƷBOM����"), Tab_GoodsBom_GodosItemList("103101", "��Ʒ�б�"), Tab_GoodsBom_SubmitingList("103102",
			"���ύbom�б�"), Tab_GoodsBom_ApprovingList("103103", "������bom�б�"),

	MainFunction_OnlineOrderManager("1033", "���϶���"), 
	SubFunction_OnlineOrder_Summary("1033001", "�������϶���"),
	SubFunction_OnlineOrder_Deliver("1033002", "�������϶���"),
	SubFunction_OnlineOrder_ConfirmArrive("1033003", "ȷ�ϵ���"),
	SubFunction_OnlineOrder_ConfirmFinish("1033004", "ȷ�����"),
	SubFunction_OnlineOrder_DeliverOnly("1033005", "ֻ������Ȩ��"),
	SubFunction_OnlineOrder_Separate("1033006", "��ֶ���"),
	MainFunction_ProduceOrderManager("1034", "��������"),
	SubFunction_ProduceOrder_Create("1034001", "������������"),
	SubFunction_ProduceOrder_Approval("1034002", "������������"),
	SubFunction_ProduceOrder_Produce("1034003", "������������|����|���"),
	MainFunction_DeliveryOrderManager("1035", "���͵�"),
	Tab_Delivery_UnHandle("1035100", "������"),
	Tab_Delivery_Delivering("1035101", "������"),
	Tab_Delivery_Arrived("1035102", "�ѵ���"),
	Tab_Delivery_Exception("1035103", "�쳣"),
	SubFunction_DeliveryOrder_Deliver("1035001", "ȷ������"), 
	SubFunction_DeliveryOrder_Confirm("1035002", "ȷ�ϵ���"),
	SubFunction_DeliveryOrder_HandlException("1035003", "�����쳣"), 
	MainFunction_DeliveryTicketManager("1036", "������"), 
	MainFunction_QueryManager("1037", "��ѯ����"), 
	MainFunction_OnlineReturnManager("1040", "���϶����˻�"),
	SubFunction_OnlineReturn_Create("1040001", "���������˻�"),
	SubFunction_OnlineReturn_Approval("1040002", "���������˻�"),
	SubFunction_OnlineReturn_Confirm("1040003", "ȷ�������˻�"),
	MainFunction_JointSettleMent("1041", "��Ӫ��Ӧ�̽���"),
	MainFunction_RealGoodsSales("1042","��Ʒ����"),

	MainFunction_GoodsSplitManage("1050","��Ʒ���"),
	SubFunction_GoodsSplitManage_Create("1050001","������Ʒ���"),
	SubFunction_GoodsSplitManage_Distribution("1050003","������ϲֿ�"),
	SubFunction_GoodsSplitManage_Approval("1050002","������Ʒ���");
	/**
	 * ����/ְ�����
	 */
	private String code;

	/**
	 * ����/ְ������
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
		throw new IllegalArgumentException(code + "����һ����ȷ��Ȩ��ö��");
	}

}
