package com.spark.common.components.db;

/**
 * �������ݿ�����Ǽ�
 */
public abstract class ERPTableNames {

	public enum Base {
		/**
		 * ��վ��Ա
		 */
		Member("B2C_Base_Member"), /**
		 * �ͻ���Ϣ
		 */
		Customer("PSI_Base_Customer"), /**
		 * ��Ӧ����Ϣ
		 */
		Supplier("PSI_BASE_SUPPLIER"), /**
		 * ��Ӧ�������˺�
		 */
		BankAccount("PSI_Base_BankAccount"), /**
		 * ��Ӧ����Ӫ��Ʒ
		 */
		JointVenture("PSI_Base_JointVenture"), /**
		 * �ֿ�
		 */
		Store("PSI_Base_Store"), /**
		 * ���Ա
		 */
		Store_Manager("PSI_Base_Store_Manager"), /**
		 * ��λ
		 */
		Shelf("PSI_Base_Shelf"), /**
		 * վ��
		 */
		Station("PSI_Base_Station"), /**
		 * ��Ʒ����
		 */
		GoodsCategory("PSI_Base_GoodsCategory"), /**
		 * ��Ʒ
		 */
		Goods("PSI_Base_Goods"), /**
		 * ��Ʒ��Ŀ
		 */
		GoodsItem("PSI_Base_GoodsItem"), /**
		 * BOM��
		 */
		Bom("PSI_Base_Bom"), /**
		 * BOM����ϸ
		 */
		BomDetail("PSI_Base_BomDetail"),
		/**
		 * BOM����Ч��ʷ
		 */
		BOMHistory("PSI_Base_BomHistory"),
		/**
		 * ���Ϸ���
		 */
		MaterialsCategory("PSI_Base_MaterialCategory"), /**
		 * ����
		 */
		Materials("PSI_Base_Material"), /**
		 * ������Ŀ
		 */
		MaterialsItem("PSI_Base_MaterialItem"), /**
		 * ����
		 */
		Notice("PSI_Base_Notice"), /**
		 * �����������
		 */
		Notice_Det("PSI_Base_Notice_Det"), /**
		 * ��ϵ��
		 */
		Linkman("PSI_Base_Linkman"), SendMessageLog("PSI_SENDMESSAGE_LOG"), PhoneMsgConfig("PSI_BASE_MSGCONFIG");
		private String name;

		private Base(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

	public enum Sales {
		/**
		 * ���۶���
		 */
		Order("PSI_Sales_Order"),
		/**
		 * ���۶�����ϸ
		 */
		Order_Det("PSI_Sales_Order_Det"),
		/**
		 * �����˻���
		 */
		Return("PSI_Sales_Return"),
		/**
		 * �����˻���ϸ
		 */
		Return_Det("PSI_Sales_Return_Det"),
		/**
		 * ���϶����˻�
		 */
		OnlineReturn("PSI_SALES_ONLINERETURN"),
		/**
		 * ���϶����˻���ϸ
		 */
		OnlineReturn_Det("PSI_SALES_OLRETURN_DET"),
		/**
		 * ���϶���
		 */
		OnlineOrder("ERP_Sales_OnlineOrder"),
		/**
		 * ���϶�����ϸ
		 */
		OnlineOrder_Det("ERP_Sales_OnlineOrder_Det"),
		/**
		 * ���϶�������
		 */
		OnlineOrder_Log("ERP_Sales_OnlineOrder_Log"),
		/**
		 * ��������
		 */
		Produce_Order("PSI_Produce_Order"),
		/**
		 * ����������Ʒ��ϸ
		 */
		Produce_GoodsDet("PSI_Produce_GoodsDet"),
		/**
		 * ��������������ϸ
		 */
		Produce_MaterialsDet("PSI_Produce_MaterialDet"),
		/**
		 * �������������Ʒ��¼
		 */
		Produce_FinishedLog("PSI_Produce_FinishedLog"),
		/**
		 * ���͵�
		 */
		Deliver_Sheet("PSI_Deliver_Sheet"),
		/**
		 * ���͵���ϸ
		 */
		Deliver_Det("PSI_Deliver_Det"),
		/**
		 * ������
		 */
		Deliver_Ticket("PSI_Deliver_Ticket"),
		/**
		 * ��������ϸ
		 */
		Ticket_Det("PSI_DeliverTicket_Det");
		private String name;

		private Sales(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

	public enum Purchase {
		/**
		 * �ɹ�����
		 */
		Order("PSI_Purchase_Order"),
		/**
		 * �ɹ�������ϸ
		 */
		Purchase_Det("PSI_Purchase_Det"),
		/**
		 * �ɹ��˻�
		 */
		Return("PSI_Purchase_Return"),
		/**
		 * �ɹ��˻���ϸ
		 */
		Return_Det("PSI_Purchase_Return_Det"), ;
		private String name;

		private Purchase(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

	public enum Account {
		/**
		 * ������
		 */
		DEALING("PSI_ACCOUNT_DEALING"),
		/**
		 * ��������ϸ
		 */
		DEALING_Det("PSI_ACCOUNT_DEAL"),
		/**
		 * ��������
		 */
		Payment("PSI_ACCOUNT_Payment"),
		/**
		 * ������ϸ
		 */
		Payment_Det("PSI_ACCOUNT_Payment_Det"),
		/**
		 * �����¼
		 */
		Payment_Log("PSI_ACCOUNT_Payment_Log"),
		/**
		 * �տ�֪ͨ��
		 */
		Receipts("PSI_ACCOUNT_Receipts"),
		/**
		 * �տ���ϸ
		 */
		Receipts_Det("PSI_ACCOUNT_Receipts_Det"),
		/**
		 * �տ��¼
		 */
		Receipts_Log("PSI_ACCOUNT_Receipts_Log"),
		/**
		 * ��Ʊ��¼
		 */
		Invoice("SA_FINANCE_INVOICE");
		private String name;

		private Account(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

	public enum Inventory {
		/**
		 * ����м��
		 */
		Checkingin("PSI_Inventory_Checkining"), /**
		 * ����м����ϸ
		 */
		Checkingin_Det("PSI_Inventory_Checkining_Det"), /**
		 * ��ⵥ
		 */
		CheckinSheet("PSI_Inventory_Checkin"), /**
		 * ��ⵥ��ϸ
		 */
		CheckinSheet_Det("PSI_Inventory_Checkin_Det"),
		/**
		 * �����м��
		 */
		Checkingout("PSI_Inventory_Checkouting"), /**
		 * �����м����ϸ
		 */
		Checkingout_Det("PSI_Inventory_Checkouting_Det"), /**
		 * ���ⵥ
		 */
		CheckoutSheet("PSI_Inventory_Checkout"), /**
		 * ���ⵥ��ϸ
		 */
		CheckoutSheet_Det("PSI_Inventory_Checkout_Det"),
		/**
		 * ���
		 */
		Inventory("PSI_Inventory"),
		/**
		 * �����ϸ
		 */
		Inventory_Det("PSI_Inventory_Det"),
		// /**
		// * ���Ͽ��
		// */
		// MaterialStorage("PSI_Inventory_Material"),
		// /**
		// * ���Ͽ����ϸ
		// */
		// MaterialStorage_Det("PSI_Inventory_MaterialDet"),
		/**
		 * ����
		 */
		ReportLoss("PSI_Inventory_ReportLoss"),
		/**
		 * ������ϸ
		 */
		ReportLoss_Det("PSI_Inventory_Loss_Det"),
		/**
		 * ������ϸ֮��ϸ
		 */
		ReportLoss_Shelf_Det("PSI_Inventory_Loss_shelf"),
		/**
		 * ������
		 */
		Allocate("PSI_Inventory_Allocate"),
		/**
		 * ��������ϸ
		 */
		Allocate_Det("PSI_Inventory_Allocate_Det"),
		/**
		 * ����̵�
		 */
		// Check("PSI_Inventory_Check"),
		/**
		 * �̵㵥��ϸ
		 */
		// Check_Det("PSI_Inventory_Check_Det"),
		/**
		 * �����ˮ
		 */
		Inventory_Log("PSI_Inventory_Log"),
		/**
		 * ���̨��
		 */
		Inventory_Book("PSI_Inventory_Book"),
		
		GoodsSplitSheet("PSI_GOODSSPLIT"),
		
		GoodsSplitDet_Goods("PSI_GOODSSPLIT_GOODS"),
		
		GoodsSplitDet_Material("PSI_GOODSSPLIT_MATERIAL"),

		;
		private String name;

		private Inventory(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

	public enum Joint {
		/**
		 * ��Ӫ��Ʒ���׼�¼
		 */
		Joint_Record("PSI_Joint_Record"),
		/**
		 * ��Ӫ��Ʒ��Ϣ��¼
		 */
		Materials_Joint_Log("PSI_Materials_Joint_Log"),
		/**
		 * ��Ӫ����
		 */
		Joint_Settlement("PSI_Joint_Settlement"),
		/**
		 * ��Ӫ������ϸ
		 */
		Joint_Settlement_Det("PSI_Joint_Settlement_Det"),

		;
		private String name;

		private Joint(String name) {
			this.name = name;
		}

		public String getTableName() {
			return this.name;
		}
	}

}
