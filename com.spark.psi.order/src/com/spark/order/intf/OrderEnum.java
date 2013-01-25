package com.spark.order.intf;

import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.CodeBuilder;

public enum OrderEnum{
	/**
	 * ���۹���
	 */
	Sales(ApprovalConfig.Mode.SALES_BILLS,CodeBuilder.SALES_BILLS,"PSI_Sales_Order","PSI_Sales_Order_Det" ),//"SA_SALES_ORDER", "SA_SALES_ORDER_ITEM"),
	/**
	 * �ɹ�����
	 */
	Purchase(ApprovalConfig.Mode.BUY_ORDER, CodeBuilder.BUY_ORDER,"PSI_Purchase_Order","PSI_Purchase_Det"),
	/**
	 * �����˻�
	 */
	Sales_Return(ApprovalConfig.Mode.SALES_RETURN, CodeBuilder.SALES_RETURN,"PSI_Sales_Return","PSI_Sales_Return_Det" ),//"SA_SALES_RETURN","SA_SALES_RETURN_ITEM"),
	/**
	 * �ɹ��˻�
	 */
	Purchase_Return(ApprovalConfig.Mode.BUY_RETURN, CodeBuilder.BUY_RETURN, "PSI_Purchase_Return","PSI_Purchase_Return_Det"),
	/**
	 * �ɹ��嵥
	 */
	Purchase_Goods(null, null, "SA_PURCHASE_GOODS","SA_PURCHASE_GOODS_Direct"),
	/**
	 * ���۴�������
	 */
	Sales_Promotion(ApprovalConfig.Mode.PROMOTION, null, "SA_GOODS_PROMOTION", ""),
	/**
	 * ���۶���
	 */
	Retail(null, null, "SA_SALE_RETAIL", "SA_SALE_RETAIL_DET"),
	/**
	 * �����˻���
	 */
	Retail_Return(null, null, "SA_SALE_RETAIL", "SA_SALE_RETAIL_DET");
	//���ģ��ö��
	private final  ApprovalConfig.Mode  approvalConfigMode;
	private final CodeBuilder codeBuilder;
	private final String db_table;
	private final String db_table_item;

	/**
	 * ָ��ö���ǲ��ǵ�ǰö��
	 * @param codeBuilder
	 * @return boolean�Ƿ���true
	 */
	public boolean isThis(CodeBuilder codeBuilder){
		if(this.codeBuilder == codeBuilder){
			return true;
		}
		return false;
	}
	
	
	/**
	 * �������ö�٣���ö���ö�٣��޷���null
	 * @param mode
	 * @return OrderEnum
	 */
	public static OrderEnum getOrderEnum(ApprovalConfig.Mode mode){
		return OrderConstant2.approvalMap.get(mode);
	}
	
	/**
	 * �жϵ�ǰ�����ǲ���ָ�������е�һ�֣��Ƿ���true
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
	 * ������ö��
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
