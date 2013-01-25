package com.spark.order.intf.type;

import com.spark.common.components.db.ERPTableNames;
import com.spark.order.intf.OrderEnum;
import com.spark.psi.base.CodeBuilder;
import com.spark.psi.base.SheetNumberType;

/**
 * <p>
 * 单据大模块，小模块枚举
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 莫迪
 * @version 2011-10-13
 */
public enum BillsEnum {
	/**
	 * 销售管理
	 */
	SALE(OrderEnum.Sales, SheetNumberType.Sales, ERPTableNames.Sales.Order.getTableName(), "ddxs", "1"),
	/**
	 * 采购管理
	 */
	PURCHASE(OrderEnum.Purchase, SheetNumberType.Purchaes, ERPTableNames.Purchase.Order.getTableName(), "cggl", "1"),
	/**
	 * 销售退货
	 */
	SALE_CANCEL(OrderEnum.Sales_Return, SheetNumberType.SalesReturn, ERPTableNames.Sales.Return.getTableName(), "ddxs", "2"),
	/**
	 * 采购退货
	 */
	PURCHASE_CANCEL(OrderEnum.Purchase_Return, SheetNumberType.PurchaseReturn, ERPTableNames.Purchase.Return.getTableName(),
			"cggl", "2"),
	/**
	 * 销售促销管理
	 */
	SALE_PROMOTION(OrderEnum.Sales_Promotion, null, "SA_SALE_PROMOTION", "xsgl_cxgl", ""),
	/**
	 * 销售配货
	 */
	SALE_DEPLOYMENT(null, null, "", "", ""),
	/**
	 * 零售订单
	 */
	Retail_Order(OrderEnum.Retail, SheetNumberType.Retail, "", "01", ""),
	/**
	 * 零售退货单
	 */
	Retail_Return(OrderEnum.Retail_Return, SheetNumberType.RetailReturn, "", "02", "");

	private OrderEnum orderEnum;
	private SheetNumberType sheetType;
	private String db_table;
	private String key;// 模块标识
	private String code;// 直接进入详情code

	// private Mode mode; //对应的审核枚举

	private BillsEnum(OrderEnum module, SheetNumberType sheetType, String db, String key, String code) {
		this.orderEnum = module;
		this.sheetType = sheetType;
		this.db_table = db;
		this.key = key;
		this.code = code;
		// this.mode = mode;
	}

	/**
	 * 获得新版单据类型枚举
	 * 
	 * @return OrderEnum
	 */
	public OrderEnum getOrderEnum() {
		return orderEnum;
	}

	/**
	 * 获得订单数据库表名
	 * 
	 * @return String
	 */
	public String getDb_table() {
		return db_table;
	}

	/**
	 * 获得订单界面显示标志
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	public SheetNumberType getSheetType() {
		return sheetType;
	}

	/**
	 * 根据key判断是否是当前枚举
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key) {
		return this.getKey().equals(key);
	}

	/**
	 * 当前枚举是指定枚举数组中的一个
	 * 
	 * @param enums
	 * @return boolean
	 */
	public boolean isInEnum(BillsEnum... enums) {
		for (BillsEnum be : enums) {
			if (this == be) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取得进入详情标志
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获得对应通用枚举
	 * 
	 * @return CodeBuilder
	 */
	public CodeBuilder getCodeBuilder() {
		return orderEnum == null ? null : orderEnum.getCodeBuilder();
	}

	/**
	 * 通过审核枚举获得对应的订单类型
	 * 
	 * @param mode
	 * @return BillsEnum public static BillsEnum getBillsEnumForExamMode(Mode
	 *         mode){ for(BillsEnum bill : BillsEnum.values()){ if(bill.mode ==
	 *         mode){ return bill; } } return null; }
	 */
}
