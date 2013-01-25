package com.spark.order.intf;

import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.CodeBuilder;

public enum OrderEnum{
	/**
	 * 销售管理
	 */
	Sales(ApprovalConfig.Mode.SALES_BILLS,CodeBuilder.SALES_BILLS,"PSI_Sales_Order","PSI_Sales_Order_Det" ),//"SA_SALES_ORDER", "SA_SALES_ORDER_ITEM"),
	/**
	 * 采购管理
	 */
	Purchase(ApprovalConfig.Mode.BUY_ORDER, CodeBuilder.BUY_ORDER,"PSI_Purchase_Order","PSI_Purchase_Det"),
	/**
	 * 销售退货
	 */
	Sales_Return(ApprovalConfig.Mode.SALES_RETURN, CodeBuilder.SALES_RETURN,"PSI_Sales_Return","PSI_Sales_Return_Det" ),//"SA_SALES_RETURN","SA_SALES_RETURN_ITEM"),
	/**
	 * 采购退货
	 */
	Purchase_Return(ApprovalConfig.Mode.BUY_RETURN, CodeBuilder.BUY_RETURN, "PSI_Purchase_Return","PSI_Purchase_Return_Det"),
	/**
	 * 采购清单
	 */
	Purchase_Goods(null, null, "SA_PURCHASE_GOODS","SA_PURCHASE_GOODS_Direct"),
	/**
	 * 销售促销管理
	 */
	Sales_Promotion(ApprovalConfig.Mode.PROMOTION, null, "SA_GOODS_PROMOTION", ""),
	/**
	 * 零售订单
	 */
	Retail(null, null, "SA_SALE_RETAIL", "SA_SALE_RETAIL_DET"),
	/**
	 * 零售退货单
	 */
	Retail_Return(null, null, "SA_SALE_RETAIL", "SA_SALE_RETAIL_DET");
	//审核模块枚举
	private final  ApprovalConfig.Mode  approvalConfigMode;
	private final CodeBuilder codeBuilder;
	private final String db_table;
	private final String db_table_item;

	/**
	 * 指定枚举是不是当前枚举
	 * @param codeBuilder
	 * @return boolean是返回true
	 */
	public boolean isThis(CodeBuilder codeBuilder){
		if(this.codeBuilder == codeBuilder){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 根据审核枚举，获得订单枚举，无返回null
	 * @param mode
	 * @return OrderEnum
	 */
	public static OrderEnum getOrderEnum(ApprovalConfig.Mode mode){
		return OrderConstant2.approvalMap.get(mode);
	}
	
	/**
	 * 判断当前类型是不是指定集合中的一种，是返回true
	 * @param orderEnums
	 * @return boolean
	 */
	public boolean isIn(OrderEnum...orderEnums){
		for(OrderEnum oe:orderEnums){
			if(this == oe){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param codeBuilder
	 * @param dbTable
	 * @param dbTableItem
	 */
	private OrderEnum(ApprovalConfig.Mode mode, CodeBuilder codeBuilder, String dbTable,
			String dbTableItem) {
		this.approvalConfigMode = mode;
		this.codeBuilder = codeBuilder;
		db_table = dbTable;
		db_table_item = dbTableItem;
		OrderConstant2.approvalMap.put(mode, this);
	}
	/**
	 * 获得审核枚举
	 * @return ApprovalConfig.Mode
	 */
	public ApprovalConfig.Mode getApprovalConfigMode() {
		return approvalConfigMode;
	}
	public CodeBuilder getCodeBuilder() {
		return codeBuilder;
	}
	public String getDb_table() {
		return db_table;
	}
	public String getDb_table_item() {
		return db_table_item;
	}
}
