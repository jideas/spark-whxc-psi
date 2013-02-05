package com.spark.common.components.db;

/**
 * 所有数据库表名登记
 */
public abstract class ERPTableNames {

	public enum Base {
		/**
		 * 网站会员
		 */
		Member("B2C_Base_Member"), /**
		 * 客户信息
		 */
		Customer("PSI_Base_Customer"), /**
		 * 供应商信息
		 */
		Supplier("PSI_BASE_SUPPLIER"), /**
		 * 供应商银行账号
		 */
		BankAccount("PSI_Base_BankAccount"), /**
		 * 供应商联营商品
		 */
		JointVenture("PSI_Base_JointVenture"), /**
		 * 仓库
		 */
		Store("PSI_Base_Store"), /**
		 * 库管员
		 */
		Store_Manager("PSI_Base_Store_Manager"), /**
		 * 货位
		 */
		Shelf("PSI_Base_Shelf"), /**
		 * 站点
		 */
		Station("PSI_Base_Station"), /**
		 * 商品分类
		 */
		GoodsCategory("PSI_Base_GoodsCategory"), /**
		 * 商品
		 */
		Goods("PSI_Base_Goods"), /**
		 * 商品条目
		 */
		GoodsItem("PSI_Base_GoodsItem"), /**
		 * BOM表
		 */
		Bom("PSI_Base_Bom"), /**
		 * BOM表明细
		 */
		BomDetail("PSI_Base_BomDetail"),
		/**
		 * BOM表生效历史
		 */
		BOMHistory("PSI_Base_BomHistory"),
		/**
		 * 材料分类
		 */
		MaterialsCategory("PSI_Base_MaterialCategory"), /**
		 * 材料
		 */
		Materials("PSI_Base_Material"), /**
		 * 材料条目
		 */
		MaterialsItem("PSI_Base_MaterialItem"), /**
		 * 公告
		 */
		Notice("PSI_Base_Notice"), /**
		 * 公告关联部门
		 */
		Notice_Det("PSI_Base_Notice_Det"), /**
		 * 联系人
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
		 * 销售订单
		 */
		Order("PSI_Sales_Order"),
		/**
		 * 销售订单明细
		 */
		Order_Det("PSI_Sales_Order_Det"),
		/**
		 * 销售退货单
		 */
		Return("PSI_Sales_Return"),
		/**
		 * 销售退货明细
		 */
		Return_Det("PSI_Sales_Return_Det"),
		/**
		 * 网上订单退货
		 */
		OnlineReturn("PSI_SALES_ONLINERETURN"),
		/**
		 * 网上订单退货明细
		 */
		OnlineReturn_Det("PSI_SALES_OLRETURN_DET"),
		/**
		 * 网上订单
		 */
		OnlineOrder("ERP_Sales_OnlineOrder"),
		/**
		 * 网上订单明细
		 */
		OnlineOrder_Det("ERP_Sales_OnlineOrder_Det"),
		/**
		 * 网上订单跟踪
		 */
		OnlineOrder_Log("ERP_Sales_OnlineOrder_Log"),
		/**
		 * 生产订单
		 */
		Produce_Order("PSI_Produce_Order"),
		/**
		 * 生产订单商品明细
		 */
		Produce_GoodsDet("PSI_Produce_GoodsDet"),
		/**
		 * 生产订单材料明细
		 */
		Produce_MaterialsDet("PSI_Produce_MaterialDet"),
		/**
		 * 生产订单完成商品记录
		 */
		Produce_FinishedLog("PSI_Produce_FinishedLog"),
		/**
		 * 配送单
		 */
		Deliver_Sheet("PSI_Deliver_Sheet"),
		/**
		 * 配送单明细
		 */
		Deliver_Det("PSI_Deliver_Det"),
		/**
		 * 发货单
		 */
		Deliver_Ticket("PSI_Deliver_Ticket"),
		/**
		 * 发货单明细
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
		 * 采购订单
		 */
		Order("PSI_Purchase_Order"),
		/**
		 * 采购订单明细
		 */
		Purchase_Det("PSI_Purchase_Det"),
		/**
		 * 采购退货
		 */
		Return("PSI_Purchase_Return"),
		/**
		 * 采购退货明细
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
		 * 往来款
		 */
		DEALING("PSI_ACCOUNT_DEALING"),
		/**
		 * 往来款明细
		 */
		DEALING_Det("PSI_ACCOUNT_DEAL"),
		/**
		 * 付款申请
		 */
		Payment("PSI_ACCOUNT_Payment"),
		/**
		 * 付款明细
		 */
		Payment_Det("PSI_ACCOUNT_Payment_Det"),
		/**
		 * 付款记录
		 */
		Payment_Log("PSI_ACCOUNT_Payment_Log"),
		/**
		 * 收款通知单
		 */
		Receipts("PSI_ACCOUNT_Receipts"),
		/**
		 * 收款明细
		 */
		Receipts_Det("PSI_ACCOUNT_Receipts_Det"),
		/**
		 * 收款记录
		 */
		Receipts_Log("PSI_ACCOUNT_Receipts_Log"),
		/**
		 * 开票记录
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
		 * 入库中间表
		 */
		Checkingin("PSI_Inventory_Checkining"), /**
		 * 入库中间表明细
		 */
		Checkingin_Det("PSI_Inventory_Checkining_Det"), /**
		 * 入库单
		 */
		CheckinSheet("PSI_Inventory_Checkin"), /**
		 * 入库单明细
		 */
		CheckinSheet_Det("PSI_Inventory_Checkin_Det"),
		/**
		 * 出库中间表
		 */
		Checkingout("PSI_Inventory_Checkouting"), /**
		 * 出库中间表明细
		 */
		Checkingout_Det("PSI_Inventory_Checkouting_Det"), /**
		 * 出库单
		 */
		CheckoutSheet("PSI_Inventory_Checkout"), /**
		 * 出库单明细
		 */
		CheckoutSheet_Det("PSI_Inventory_Checkout_Det"),
		/**
		 * 库存
		 */
		Inventory("PSI_Inventory"),
		/**
		 * 库存明细
		 */
		Inventory_Det("PSI_Inventory_Det"),
		// /**
		// * 材料库存
		// */
		// MaterialStorage("PSI_Inventory_Material"),
		// /**
		// * 材料库存明细
		// */
		// MaterialStorage_Det("PSI_Inventory_MaterialDet"),
		/**
		 * 报损单
		 */
		ReportLoss("PSI_Inventory_ReportLoss"),
		/**
		 * 报损单明细
		 */
		ReportLoss_Det("PSI_Inventory_Loss_Det"),
		/**
		 * 报损明细之明细
		 */
		ReportLoss_Shelf_Det("PSI_Inventory_Loss_shelf"),
		/**
		 * 库存调拨
		 */
		Allocate("PSI_Inventory_Allocate"),
		/**
		 * 调拨单明细
		 */
		Allocate_Det("PSI_Inventory_Allocate_Det"),
		/**
		 * 库存盘点
		 */
		// Check("PSI_Inventory_Check"),
		/**
		 * 盘点单明细
		 */
		// Check_Det("PSI_Inventory_Check_Det"),
		/**
		 * 库存流水
		 */
		Inventory_Log("PSI_Inventory_Log"),
		/**
		 * 库存台账
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
		 * 联营商品交易记录
		 */
		Joint_Record("PSI_Joint_Record"),
		/**
		 * 联营商品信息记录
		 */
		Materials_Joint_Log("PSI_Materials_Joint_Log"),
		/**
		 * 联营结算
		 */
		Joint_Settlement("PSI_Joint_Settlement"),
		/**
		 * 联营结算明细
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
