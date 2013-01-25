/**
 * 
 */
package com.spark.psi.report.constant;

import com.spark.common.utils.character.CheckIsNull;

/**
 * 报表数据指标枚举类
 */
public abstract class TargetEnum{

	/**
	 * 时间指标
	 */
	public enum DateTimeEnum{
		Year("Year"),
		HalfYear("HalfYear"),
		Season("Season"),
		Month("Month"),
		TenDays("TenDays"),
		Week("Week"),
		Day("Day"),
		YearStr("YearStr"),
		HalfYearStr("HalfYearStr"),
		SeasonStr("SeasonStr"),
		MonthStr("MonthStr"),
		TenDaysStr("TenDaysStr"),
		WeekStr("WeekStr"),
		DayStr("DayStr");
		private String code;

		private DateTimeEnum(String code){
			this.code = code;
		}

		public String getCode(){
			return this.code;
		}

		public String getColumnName(){
			switch(this){
				case Month:
					return "monthNo";
				case Day:
					return "dateNo";
				case Season:
					return "quarter";
			}

			return null;
		}

		public static DateTimeEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("Year".toUpperCase().equals(code)){
				return Year;
			}
			else if("HalfYear".toUpperCase().equals(code)){
				return HalfYear;
			}
			else if("Season".toUpperCase().equals(code)){
				return Season;
			}
			else if("Month".toUpperCase().equals(code)){
				return Month;
			}
			else if("TenDays".toUpperCase().equals(code)){
				return TenDays;
			}
			else if("Week".toUpperCase().equals(code)){
				return Week;
			}
			else if("Day".toUpperCase().equals(code)){
				return Day;
			}
			else if("YearStr".toUpperCase().equals(code)){
				return YearStr;
			}
			else if("HalfYearStr".toUpperCase().equals(code)){
				return HalfYearStr;
			}
			else if("SeasonStr".toUpperCase().equals(code)){
				return SeasonStr;
			}
			else if("MonthStr".toUpperCase().equals(code)){
				return MonthStr;
			}
			else if("TenDaysStr".toUpperCase().equals(code)){
				return TenDaysStr;
			}
			else if("WeekStr".toUpperCase().equals(code)){
				return WeekStr;
			}
			else if("DayStr".toUpperCase().equals(code)){
				return DayStr;
			}
			return null;
		}
	}

	/**
	 * 商品
	 */
	public enum GoodsItemEnum{
		Id("Id"),
		GoodsName("GoodsName"),
		GoodsAttr("GoodsAttr"),
		GoodsUnit("GoodsUnit"),
		// 销售金额
		SalesAmount("SalesAmount"),
		SalesAmount_HuanBi("SalesAmount_HuanBi"),
		SalesAmount_OfYear("SalesAmount_OfYear"),
		// 销售数量
		SalesCount("SalesCount"),
		SalesCount_HuanBi("SalesCount_HuanBi"),
		SalesCount_OfYear("SalesCount_OfYear"),
		// 销售退货金额
		SalesReturnAmount("SalesReturnAmount"),
		SalesReturnAmount_HuanBi("SalesReturnAmount_HuanBi"),
		SalesReturnAmount_OfYear("SalesReturnAmount_OfYear"),
		// 出入库金额
		OutStoreCount("OutStoreCount"),
		InStoreCount("InStoreCount"),
		// 收付款金额
		PayAmount("PayAmount"),
		ReceiptAmount("ReceiptAmount"),
		// 采购金额
		PurchaseAmount("PurchaseAmount"),
		PurchaseAmount_HuanBi("PurchaseAmount_HuanBi"),
		PurchaseAmount_OfYear("PurchaseAmount_OfYear"),
		// 采购数量
		PurchaseCount("PurchaseCount"),
		PurchaseCount_HuanBi("PurchaseCount_HuanBi"),
		PurchaseCount_OfYear("PurchaseCount_OfYear"),
		// 采购退货金额
		PurchaseReturnAmount("PurchaseReturnAmount"),
		PurchaseReturnAmount_HuanBi("PurchaseReturnAmount_HuanBi"),
		PurchaseReturnAmount_OfYear("PurchaseReturnAmount_OfYear"),
		// 采购中数量
		//				PurchasingCount("PurchasingCount"),
		// 库存数量
		InventoryCount("InventoryCount"),
		InventoryAmount("InventoryAmount"),
		// 交付需求数量
		//				DeliveryingCount("DeliveryingCount"),
		// 库存差额
		InventoryDifference("InventoryDifference");

		private String code;

		private GoodsItemEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static GoodsItemEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("SalesAmount".toUpperCase().equals(code)){
				return SalesAmount;
			}
			else if("SalesCount".toUpperCase().equals(code)){
				return SalesCount;
			}
			else if("SalesReturnAmount".toUpperCase().equals(code)){
				return SalesReturnAmount;
			}
			else if("PurchaseAmount".toUpperCase().equals(code)){
				return PurchaseAmount;
			}
			else if("PurchaseCount".toUpperCase().equals(code)){
				return PurchaseCount;
			}
			else if("PurchaseReturnAmount".toUpperCase().equals(code)){
				return PurchaseReturnAmount;
			}
			//			else if("PurchasingCount".toUpperCase().equals(code)){
			//				return PurchasingCount;
			//			}
			else if("InventoryCount".toUpperCase().equals(code)){
				return InventoryCount;
			}
			//			else if("DeliveryingCount".toUpperCase().equals(code)){
			//				return DeliveryingCount;
			//			}
			else if("InventoryDifference".toUpperCase().equals(code)){
				return InventoryDifference;
			}
			else if("OutStoreCount".toUpperCase().equals(code)){
				return OutStoreCount;
			}
			else if("InStoreCount".toUpperCase().equals(code)){
				return InStoreCount;
			}
			else if("PayAmount".toUpperCase().equals(code)){
				return PayAmount;
			}
			else if("ReceiptAmount".toUpperCase().equals(code)){
				return ReceiptAmount;
			}
			else if("InventoryAmount".toUpperCase().equals(code)){
				return InventoryAmount;
			}
			else if("GoodsName".toUpperCase().equals(code)){
				return GoodsName;
			}
			else if("GoodsAttr".toUpperCase().equals(code)){
				return GoodsAttr;
			}
			else if("GoodsUnit".toUpperCase().equals(code)){
				return GoodsUnit;
			}
			else if("SalesAmount_HuanBi".toUpperCase().equals(code)){
				return SalesAmount_HuanBi;
			}
			else if("SalesAmount_OfYear".toUpperCase().equals(code)){
				return SalesAmount_OfYear;
			}
			else if("SalesCount_HuanBi".toUpperCase().equals(code)){
				return SalesCount_HuanBi;
			}
			else if("SalesCount_OfYear".toUpperCase().equals(code)){
				return SalesCount_OfYear;
			}
			else if("SalesReturnAmount_HuanBi".toUpperCase().equals(code)){
				return SalesReturnAmount_HuanBi;
			}
			else if("SalesReturnAmount_OfYear".toUpperCase().equals(code)){
				return SalesReturnAmount_OfYear;
			}
			else if("Id".toUpperCase().equals(code)){
				return Id;
			}
			else if("PurchaseAmount_HuanBi".toUpperCase().equals(code)){
				return PurchaseAmount_HuanBi;
			}
			else if("PurchaseAmount_OfYear".toUpperCase().equals(code)){
				return PurchaseAmount_OfYear;
			}
			else if("PurchaseCount_HuanBi".toUpperCase().equals(code)){
				return PurchaseCount_HuanBi;
			}
			else if("PurchaseCount_OfYear".toUpperCase().equals(code)){
				return PurchaseCount_OfYear;
			}
			else if("PurchaseReturnAmount_HuanBi".toUpperCase().equals(code)){
				return PurchaseReturnAmount_HuanBi;
			}
			else if("PurchaseReturnAmount_OfYear".toUpperCase().equals(code)){
				return PurchaseReturnAmount_OfYear;
			}
			return null;
		}
	}

	/**
	 * 客户
	 */
	public enum CustomerEnum{
		// 客户名称
		Name("Name"),
		ShortName("ShortName"),
		// 销售金额
		SalesAmount("SalesAmount"),
		// 出库金额
		OutstoAmount("OutstoAmount"),
		// 收款金额
		ReceiptAmount("ReceiptAmount"),
		// 退货金额
		SalesReturnAmount("SalesReturnAmount"),
		// 未订货天数
		NotOrderDays("NotOrderDays"),
		// 当前应收金额
		DueReceiptingAmount("DueReceiptingAmount"),
		// 本年累计收款
		TotalReceiptedAmountOfYear("TotalReceiptedAmountOfYear"), Id("Id");

		private String code;

		private CustomerEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static CustomerEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("SalesAmount".toUpperCase().equals(code)){
				return SalesAmount;
			}
			else if("ReceiptAmount".toUpperCase().equals(code)){
				return ReceiptAmount;
			}
			else if("SalesReturnAmount".toUpperCase().equals(code)){
				return SalesReturnAmount;
			}
			else if("NotOrderDays".toUpperCase().equals(code)){
				return NotOrderDays;
			}
			else if("DueReceiptingAmount".toUpperCase().equals(code)){
				return DueReceiptingAmount;
			}
			else if("TotalReceiptedAmountOfYear".toUpperCase().equals(code)){
				return TotalReceiptedAmountOfYear;
			}
			else if("Name".toUpperCase().equals(code)){
				return Name;
			}
			else if("OutstoAmount".toUpperCase().equals(code)){
				return OutstoAmount;
			}
			else if("ShortName".toUpperCase().endsWith(code)){
				return ShortName;
			}
			else if("Id".toUpperCase().endsWith(code)){
				return Id;
			}
			return null;
		}

		/**
		 * 返回对应的表字段
		 * 
		 * @param type
		 * @return
		 */
		public String getfield(){
			switch(this){
				case OutstoAmount:
					return "outstoAmount";
				case SalesAmount:
					return "ordAmount";
				case ReceiptAmount:
					return "receiptAmount";
				case SalesReturnAmount:
					return "rtnAmount";
			}
			return null;
		}
	}

	/**
	 * 客户并销售人员
	 */
	public enum Customer_EmployeeEnum{
		Id("Id"),
		// 客户名称
		CustemerShortName("CustemerShortName"),
		CustemerName("Name"),
		SalesName("SalesName"),
		// 出库金额
		OutstoAmount("OutstoAmount"),
		// 销售金额
		SalesAmount("SalesAmount"),
		SalesAmount_HuanBi("SalesAmount_HuanBi"),
		SalesAmount_OfYear("SalesAmount_OfYear"),
		// 收款金额
		ReceiptAmount("ReceiptAmount"),
		// 销售收款
		SalesReceiptAmount("SalesReceiptAmount"),
		SalesReceiptAmount_HuanBi("SalesReceiptAmount_HuanBi"),
		SalesReceiptAmount_OfYear("SalesReceiptAmount_OfYear"),
		// 退货金额
		SalesReturnAmount("SalesReturnAmount"),
		SalesReturnAmount_HuanBi("SalesReturnAmount_HuanBi"),
		SalesReturnAmount_OfYear("SalesReturnAmount_OfYear"),
		// 应收余额
		DueReceiptingAmount("DueReceiptingAmount"),
		// 未订货天数
		NotOrderDays("NotOrderDays");

		private String code;

		private Customer_EmployeeEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static Customer_EmployeeEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("SalesAmount".toUpperCase().equals(code)){
				return SalesAmount;
			}
			else if("ReceiptAmount".toUpperCase().equals(code)){
				return ReceiptAmount;
			}
			else if("SalesReceiptAmount".toUpperCase().equals(code)){
				return SalesReceiptAmount;
			}
			else if("SalesReturnAmount".toUpperCase().equals(code)){
				return SalesReturnAmount;
			}
			else if("DueReceiptingAmount".toUpperCase().equals(code)){
				return DueReceiptingAmount;
			}
			else if("NotOrderDays".toUpperCase().equals(code)){
				return NotOrderDays;
			}
			else if("CustemerName".toUpperCase().equals(code)){
				return CustemerName;
			}
			else if("OutstoAmount".toUpperCase().equals(code)){
				return OutstoAmount;
			}
			else if("SalesName".toUpperCase().equals(code)){
				return SalesName;
			}
			else if("SalesAmount_OfYear".toUpperCase().equals(code)){
				return SalesAmount_OfYear;
			}
			else if("SalesReceiptAmount_OfYear".toUpperCase().equals(code)){
				return SalesReceiptAmount_OfYear;
			}
			else if("SalesReturnAmount_OfYear".toUpperCase().equals(code)){
				return SalesReturnAmount_OfYear;
			}
			else if("SalesAmount_HuanBi".toUpperCase().equals(code)){
				return SalesAmount_HuanBi;
			}
			else if("SalesReceiptAmount_HuanBi".toUpperCase().equals(code)){
				return SalesReceiptAmount_HuanBi;
			}
			else if("SalesReturnAmount_HuanBi".toUpperCase().equals(code)){
				return SalesReturnAmount_HuanBi;
			}
			else if("CustemerShortName".toUpperCase().equals(code)){
				return CustemerShortName;
			}
			else if("Id".toUpperCase().equals(code)){
				return Id;
			}
			return null;
		}

		/**
		 * 返回对应的表字段
		 * 
		 * @param type
		 * @return
		 */
		public String getfield(){
			switch(this){
				case OutstoAmount:
					return "outstoAmount";
				case SalesAmount:
					return "ordAmount";
				case ReceiptAmount:
					return "receiptAmount";
				case SalesReturnAmount:
					return "rtnAmount";
			}
			return null;
		}
	}

	/**
	 * 供应商
	 */
	public enum SupplierEnum{
		Id("Id"),
		Name("Name"),
		ShortName("ShortName"),
		InstoAmount("InstoAmount"),
		// 采购金额
		PurchaseAmount("PurchaseAmount"),
		PurchaseAmount_HuanBi("PurchaseAmount_HuanBi"),
		PurchaseAmount_OfYear("PurchaseAmount_OfYear"),
		// 付款金额
		PayAmount("PayAmount"),
		PayAmount_HuanBi("PayAmount_HuanBi"),
		PayAmount_OfYear("PayAmount_OfYear"),
		// 退货金额
		PurchaseReturnAmount("PurchaseReturnAmount"),
		PurchaseReturnAmount_HuanBi("PurchaseReturnAmount_HuanBi"),
		PurchaseReturnAmount_OfYear("PurchaseReturnAmount_OfYear"),
		// 未订货天数
		NotOrderDays("NotOrderDays"),
		// 应付余额
		DuePayingAmount("DuePayingAmount"),
		// 信用额度
		CreditAmount("CreditAmount"),
		// 账期
		CreditDay("CreditDay"),
		// 本年累计付款
		TotalPaidAmountOfYear("TotalPayedAmountOfYear");

		private String code;

		private SupplierEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static SupplierEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("PurchaseAmount".toUpperCase().equals(code)){
				return PurchaseAmount;
			}
			else if("PayAmount".toUpperCase().equals(code)){
				return PayAmount;
			}
			else if("PurchaseReturnAmount".toUpperCase().equals(code)){
				return PurchaseReturnAmount;
			}
			else if("NotOrderDays".toUpperCase().equals(code)){
				return NotOrderDays;
			}
			else if("DuePayingAmount".toUpperCase().equals(code)){
				return DuePayingAmount;
			}
			else if("CreditAmount".toUpperCase().equals(code)){
				return CreditAmount;
			}
			else if("CreditDay".toUpperCase().equals(code)){
				return CreditDay;
			}
			else if("TotalPaidAmountOfYear".toUpperCase().equals(code)){
				return TotalPaidAmountOfYear;
			}
			else if("Name".toUpperCase().equals(code)){
				return Name;
			}
			else if("InstoAmount".toUpperCase().equals(code)){
				return InstoAmount;
			}
			else if("ShortName".toUpperCase().equals(code)){
				return ShortName;
			}
			else if("PurchaseAmount_HuanBi".toUpperCase().equals(code)){
				return PurchaseAmount_HuanBi;
			}
			else if("PurchaseAmount_OfYear".toUpperCase().equals(code)){
				return PurchaseAmount_OfYear;
			}
			else if("PayAmount_HuanBi".toUpperCase().equals(code)){
				return PayAmount_HuanBi;
			}
			else if("PayAmount_OfYear".toUpperCase().equals(code)){
				return PayAmount_OfYear;
			}
			else if("PurchaseReturnAmount_HuanBi".toUpperCase().equals(code)){
				return PurchaseReturnAmount_HuanBi;
			}
			else if("PurchaseReturnAmount_OfYear".toUpperCase().equals(code)){
				return PurchaseReturnAmount_OfYear;
			}
			else if("id".toUpperCase().equals(code)){
				return Id;
			}
			return null;
		}

		/**
		 * 返回对应的表字段
		 * 
		 * @param type
		 * @return
		 */
		public String getfield(){
			switch(this){
				case InstoAmount:
					return "outstoAmount";
				case PurchaseAmount:
					return "ordAmount";
				case PayAmount:
					return "receiptAmount";
				case PurchaseReturnAmount:
					return "rtnAmount";
			}
			return null;
		}
	}

	/**
	 * 库存台账
	 */
	public enum InventoryBookEnum{
		// 商品条目编号
		GoodsItemNumber("GoodsItemNumber"),
		// 商品条目名称
		GoodsItemName("GoodsItemName"),
		// 商品条目属性
		GoodsProperties("GoodsProperties"),
		// 商品条目单位
		GoodsUnit("GoodsUnit"),
		// 期初库存数量
		InitialInventoryCount("InitialInventoryCount"),
		// 期初库存金额
		InitialInventoryAmount("InitialInventoryAmount"),
		// 入库数量
		CheckInCount("CheckInCount"),
		// 入库金额
		CheckInAmount("CheckInAmount"),
		// 出库数量
		CheckOutCount("CheckOutCount"),
		// 出库金额
		CheckOutAmount("CheckOutAmount"),
		// 期末库存数量
		TerminalInventoryCount("TerminalInventoryCount"),
		// 期末库存金额
		TerminalInventoryAmount("TerminalInventoryAmount");

		private String code;

		private InventoryBookEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static InventoryBookEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("GoodsItemNumber".toUpperCase().equals(code)){
				return GoodsItemNumber;
			}
			else if("GoodsItemName".toUpperCase().equals(code)){
				return GoodsItemName;
			}
			else if("GoodsProperties".toUpperCase().equals(code)){
				return GoodsProperties;
			}
			else if("GoodsUnit".toUpperCase().equals(code)){
				return GoodsUnit;
			}
			else if("InitialInventoryCount".toUpperCase().equals(code)){
				return InitialInventoryCount;
			}
			else if("InitialInventoryAmount".toUpperCase().equals(code)){
				return InitialInventoryAmount;
			}
			else if("CheckInCount".toUpperCase().equals(code)){
				return CheckInCount;
			}
			else if("CheckInAmount".toUpperCase().equals(code)){
				return CheckInAmount;
			}
			else if("CheckOutCount".toUpperCase().equals(code)){
				return CheckOutCount;
			}
			else if("CheckOutAmount".toUpperCase().equals(code)){
				return CheckOutAmount;
			}
			else if("TerminalInventoryCount".toUpperCase().equals(code)){
				return TerminalInventoryCount;
			}
			else if("TerminalInventoryAmount".toUpperCase().equals(code)){
				return TerminalInventoryAmount;
			}
			return null;
		}
	}

	/**
	 * 租户
	 */
	public enum CompanyEnum{
		// 正式客户数量
		OfficialCustomersCount("OfficialCustomersCount"),
		// 潜在客户数量
		PotentialCustomersCount("PotentialCustomersCount"),
		// 采购总金额
		TotalPurchaseAmount("TotalPurchaseAmount"),
		// 销售总金额
		TotalSalesAmount("TotalSalesAmount"),
		TotalSalesReturnAmount("TotalSalesReturnAmount"),
		// 采购付款总金额
		TotalPurchasePayAmount("TotalPurchasePayAmount"),
		// 销售收款总金额
		TotalSalesReceiptAmount("TotalSalesReceiptAmount"),
		// 销售出库总金额
		TotalSalesCheckOutAmount("TotalSalesCheckOutAmount"),
		// 采购入库总金额
		TotalPurchaseCheckInAmount("TotalPurchaseCheckInAmount"),
		// 当前供应商数量
		SuppliersCount("SuppliersCount"),
		// 本月采购金额总数
		TotalPurchaseAmountOfThisMonth("TotalPurchaseAmountOfThisMonth"),
		// 库存异常商品条目数量
		InventoryDefferenceGoodsItemCount("InventoryDefferenceGoodsItemCount"),
		// 逾期未到货金额
		ExceedTimeLimitNotDeliveredAmount("ExceedTimeLimitNotDeliveredAmount"),
		// 超账期欠款金额
		OverCreditDayAmount("OverCreditDayAmount"),
		// 本月销售达成
		SalesSuccessAmountOfThisMonth("SalesSuccessAmountOfThisMonth"),
		// 本年销售达成
		SalesSuccessAmountOfThisYear("SalesSuccessAmountOfThisYear"),
		// 本月回款达成
		ReceiptedSuccessAmountOfThisMonth("ReceiptedSuccessAmountOfThisMonth"),
		ReceiptedSuccessAmountOfThisYear("ReceiptedSuccessAmountOfThisYear"),
		//本月收款
		ReceiptedAmountOfThisMonth("ReceiptedAmountOfThisMonth"),
		// 本年回款达成
		ReceiptedAmountOfThisYear("ReceiptedAmountOfThisYear"),
		// 本年累计付款
		PaidAmountOfThisYear("PaidAmountOfThisYear"),
		// 客户数量
		CustomersCount("CustomersCount"),
		// 本月销售退货金额
		SalesReturnAmountOfThisMonth("SalesReturnAmountOfThisMonth"),
		// 库存余额
		InventoryBalance("InventoryBalance"),
		// 应收余额总数
		TotalReceiptingBanlance("TotalReceiptingBanlance"),
		// 本月付款
		PaidAmountOfThisMonth("PaidAmountOfThisMonth"),
		// 应付余额总数
		TotalPayingBanlance("TotalPayingBanlance"),
		//销售目标
		SalesAmountTarget("SalesAmountTarget"),
		//回款目标
		SalesReceiptAmountTarget("SalesReceiptAmountTarget");
		private String code;

		private CompanyEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static CompanyEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("OfficialCustomersCount".toUpperCase().equals(code)){
				return OfficialCustomersCount;
			}
			else if("PotentialCustomersCount".toUpperCase().equals(code)){
				return PotentialCustomersCount;
			}
			else if("SalesAmountTarget".toUpperCase().equals(code)){
				return SalesAmountTarget;
			}
			else if("SalesReceiptAmountTarget".toUpperCase().equals(code)){
				return SalesReceiptAmountTarget;
			}
			else if("TotalPurchaseAmount".toUpperCase().equals(code)){
				return TotalPurchaseAmount;
			}
			else if("TotalSalesAmount".toUpperCase().equals(code)){
				return TotalSalesAmount;
			}
			else if("TotalPurchasePayAmount".toUpperCase().equals(code)){
				return TotalPurchasePayAmount;
			}
			else if("TotalSalesReceiptAmount".toUpperCase().equals(code)){
				return TotalSalesReceiptAmount;
			}
			else if("TotalSalesCheckOutAmount".toUpperCase().equals(code)){
				return TotalSalesCheckOutAmount;
			}
			else if("TotalPurchaseCheckInAmount".toUpperCase().equals(code)){
				return TotalPurchaseCheckInAmount;
			}
			else if("SuppliersCount".toUpperCase().equals(code)){
				return SuppliersCount;
			}
			else if("TotalPurchaseAmountOfThisMonth".toUpperCase().equals(code)){
				return TotalPurchaseAmountOfThisMonth;
			}
			else if("InventoryDefferenceGoodsItemCount".toUpperCase().equals(code)){
				return InventoryDefferenceGoodsItemCount;
			}
			else if("ExceedTimeLimitNotDeliveredAmount".toUpperCase().equals(code)){
				return ExceedTimeLimitNotDeliveredAmount;
			}
			else if("OverCreditDayAmount".toUpperCase().equals(code)){
				return OverCreditDayAmount;
			}
			else if("SalesSuccessAmountOfThisMonth".toUpperCase().equals(code)){
				return SalesSuccessAmountOfThisMonth;
			}
			else if("SalesSuccessAmountOfThisYear".toUpperCase().equals(code)){
				return SalesSuccessAmountOfThisYear;
			}
			else if("ReceiptedAmountOfThisMonth".toUpperCase().equals(code)){
				return ReceiptedAmountOfThisMonth;
			}
			else if("ReceiptedAmountOfThisYear".toUpperCase().equals(code)){
				return ReceiptedAmountOfThisYear;
			}
			else if("CustomersCount".toUpperCase().equals(code)){
				return CustomersCount;
			}
			else if("SalesReturnAmountOfThisMonth".toUpperCase().equals(code)){
				return SalesReturnAmountOfThisMonth;
			}
			else if("InventoryBalance".toUpperCase().equals(code)){
				return InventoryBalance;
			}
			else if("TotalReceiptingBanlance".toUpperCase().equals(code)){
				return TotalReceiptingBanlance;
			}
			else if("PaidAmountOfThisMonth".toUpperCase().equals(code)){
				return PaidAmountOfThisMonth;
			}
			else if("TotalPayingBanlance".toUpperCase().equals(code)){
				return TotalPayingBanlance;
			}
			else if("PaidAmountOfThisYear".toUpperCase().equals(code)){
				return PaidAmountOfThisYear;
			}
			else if("TotalSalesReturnAmount".toUpperCase().equals(code)){
				return TotalSalesReturnAmount;
			}
			else if("ReceiptedSuccessAmountOfThisMonth".toUpperCase().equals(code)){
				return ReceiptedSuccessAmountOfThisMonth;
			}
			else if("ReceiptedSuccessAmountOfThisYear".toUpperCase().equals(code)){
				return ReceiptedSuccessAmountOfThisYear;
			}
			return null;
		}

		/**
		 * @return
		 */
		public String getfield(){
			switch(this){
				case TotalSalesAmount:
					return "ordAmount";
				case TotalSalesCheckOutAmount:
					return "outstoAmount";
				case TotalSalesReceiptAmount:
					return "receiptAmount";
				default:
					return null;
			}
		}
	}

	/**
	 * 员工
	 */
	public enum EmployeeEnum{
		Id("Id"),
		Name("Name"),
		MonthNo("MonthNo"),
		Quarter("Quarter"),
		DayNo("Day"),
		// 正式客户数量
		OfficialCustomersCount("OfficialCustomersCount"),
		// 潜在客户数量
		PotentialCustomersCount("PotentialCustomersCount"),
		// 销售总金额
		TotalSalesAmount("TotalSalesAmount"),
		TotalSalesAmount_HuanBi("TotalSalesAmount_HuanBi"),
		TotalSalesAmount_OfYear("TotalSalesAmount_OfYear"),
		TotalSalesReturnAmount_HuanBi("TotalSalesReturnAmount_HuanBi"),
		TotalSalesReturnAmount_OfYear("TotalSalesReturnAmount_OfYear"),
		DepartmentName("DepartmentName"),
		// 应收余额总额
		TotalReceiptingBanlance("TotalReceiptingBanlance"),
		// 超账期欠款金额
		OverCreditDayAmount("OverCreditDayAmount"),
		// 本月销售退货金额
		TotalSalesReturnAmount("TotalSalesReturnAmount"),
		// 回款金额
		TotalReceiptedAmount("TotalReceiptedAmount"),
		// 销售目标
		SalesTargetAmount("SalesTargetAmount"),
		// 回款目标
		TotalReceiptedTargetAmount("TotalReceiptedTargetAmount"),
		// 新增客户数
		CustomersIncrementCount("CustomersIncrementCount");

		private String code;

		private EmployeeEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static EmployeeEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("OfficialCustomersCount".toUpperCase().equals(code)){
				return OfficialCustomersCount;
			}
			else if("PotentialCustomersCount".toUpperCase().equals(code)){
				return PotentialCustomersCount;
			}
			else if("TotalSalesAmount".toUpperCase().equals(code)){
				return TotalSalesAmount;
			}
			else if("TotalReceiptingBanlance".toUpperCase().equals(code)){
				return TotalReceiptingBanlance;
			}
			else if("OverCreditDayAmount".toUpperCase().equals(code)){
				return OverCreditDayAmount;
			}
			else if("TotalSalesReturnAmount".toUpperCase().equals(code)){
				return TotalSalesReturnAmount;
			}
			else if("TotalReceiptedAmount".toUpperCase().equals(code)){
				return TotalReceiptedAmount;
			}
			else if("SalesTargetAmount".toUpperCase().equals(code)){
				return SalesTargetAmount;
			}
			else if("TotalReceiptedTargetAmount".toUpperCase().equals(code)){
				return TotalReceiptedTargetAmount;
			}
			else if("CustomersIncrementCount".toUpperCase().equals(code)){
				return CustomersIncrementCount;
			}
			else if("Name".toUpperCase().equals(code)){
				return Name;
			}
			else if("MonthNo".toUpperCase().equals(code)){
				return MonthNo;
			}
			else if("Quarter".toUpperCase().equals(code)){
				return Quarter;
			}
			else if("DayNo".toUpperCase().equals(code)){
				return DayNo;
			}
			else if("Id".toUpperCase().equals(code)){
				return Id;
			}
			else if("TotalSalesAmount_HuanBi".toUpperCase().equals(code)){
				return TotalSalesAmount_HuanBi;
			}
			else if("TotalSalesAmount_OfYear".toUpperCase().equals(code)){
				return TotalSalesAmount_OfYear;
			}
			else if("TotalSalesReturnAmount_HuanBi".toUpperCase().equals(code)){
				return TotalSalesReturnAmount_HuanBi;
			}
			else if("TotalSalesReturnAmount_OfYear".toUpperCase().equals(code)){
				return TotalSalesReturnAmount_OfYear;
			}
			else if("DepartmentName".toUpperCase().equals(code)){
				return DepartmentName;
			}
			return null;
		}
	}

	/**
	 * 部门
	 */
	public enum DepartmentEnum{
		Id("Id"),
		Name("Name"),
		MonthNo("MonthNo"),
		Quarter("Quarter"),
		DayNo("Day"),
		// 销售总金额
		TotalSalesAmount("TotalSalesAmount"),
		TotalSalesAmount_HuanBi("TotalSalesAmount_HuanBi"),
		TotalSalesAmount_OfYear("TotalSalesAmount_OfYear"),
		// 应收余额总额
		TotalReceiptingBanlance("TotalReceiptingBanlance"),
		// 超账期欠款金额
		OverCreditDayAmount("OverCreditDayAmount"),
		// 销售退货金额
		TotalSalesReturnAmount("TotalSalesReturnAmount"),
		TotalSalesReturnAmount_HuanBi("TotalSalesReturnAmount_HuanBi"),
		TotalSalesReturnAmount_OfYear("TotalSalesReturnAmount_OfYear"),
		// 回款金额
		TotalReceiptedAmount("TotalReceiptedAmount"),
		// 销售目标
		SalesTargetAmount("SalesTargetAmount"),
		// 回款目标
		TotalReceiptedTargetAmount("TotalReceiptedTargetAmount"),
		// 当前员工数
		EmployeesCount("EmployeesCount");

		private String code;

		private DepartmentEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static DepartmentEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("TotalSalesAmount".toUpperCase().equals(code)){
				return TotalSalesAmount;
			}
			else if("TotalReceiptingBanlance".toUpperCase().equals(code)){
				return TotalReceiptingBanlance;
			}
			else if("OverCreditDayAmount".toUpperCase().equals(code)){
				return OverCreditDayAmount;
			}
			else if("TotalSalesReturnAmount".toUpperCase().equals(code)){
				return TotalSalesReturnAmount;
			}
			else if("TotalReceiptedAmount".toUpperCase().equals(code)){
				return TotalReceiptedAmount;
			}
			else if("SalesTargetAmount".toUpperCase().equals(code)){
				return SalesTargetAmount;
			}
			else if("TotalReceiptedTargetAmount".toUpperCase().equals(code)){
				return TotalReceiptedTargetAmount;
			}
			else if("EmployeesCount".toUpperCase().equals(code)){
				return EmployeesCount;
			}
			else if("Name".toUpperCase().equals(code)){
				return Name;
			}
			else if("MonthNo".toUpperCase().equals(code)){
				return MonthNo;
			}
			else if("Quarter".toUpperCase().equals(code)){
				return Quarter;
			}
			else if("DayNo".toUpperCase().equals(code)){
				return DayNo;
			}
			else if("Id".toUpperCase().endsWith(code)){
				return Id;
			}
			else if("TotalSalesAmount_HuanBi".toUpperCase().endsWith(code)){
				return TotalSalesAmount_HuanBi;
			}
			else if("TotalSalesAmount_OfYear".toUpperCase().endsWith(code)){
				return TotalSalesAmount_OfYear;
			}
			else if("TotalSalesReturnAmount_HuanBi".toUpperCase().endsWith(code)){
				return TotalSalesReturnAmount_HuanBi;
			}
			else if("TotalSalesReturnAmount_OfYear".toUpperCase().endsWith(code)){
				return TotalSalesReturnAmount_OfYear;
			}
			return null;
		}
	}

	/**
	 * 地区
	 */
	public enum AreaEnum{
		AreaName("AreaName"),
		// 客户数量
		CustomersCountOfArea("CustomersCountOfArea"),

		TotalCustomerCount("TotalCustomerCount");

		private String code;

		private AreaEnum(String code){
			this.code = code;
		}

		public String toString(){
			return code;
		}

		/**
		 * 根据code返回枚举值
		 * 
		 * @param code
		 * @return
		 */
		public static AreaEnum getTarget(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("CustomersCountOfArea".toUpperCase().equals(code)){
				return CustomersCountOfArea;
			}
			else if("TotalCustomerCount".toUpperCase().equals(code)){
				return TotalCustomerCount;
			}
			else if("AreaName".toUpperCase().equals(code)){
				return AreaName;
			}
			return null;
		}
	}
}
