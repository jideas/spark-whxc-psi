package com.spark.order.intf.type;

import com.spark.common.components.db.ERPTableNames;
import com.spark.order.intf.OrderEnum;
import com.spark.psi.base.CodeBuilder;
import com.spark.psi.base.SheetNumberType;

/**
 * <p>
 * ���ݴ�ģ�飬Сģ��ö��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author Ī��
 * @version 2011-10-13
 */
public enum BillsEnum {
	/**
	 * ���۹���
	 */
	SALE(OrderEnum.Sales, SheetNumberType.Sales, ERPTableNames.Sales.Order.getTableName(), "ddxs", "1"),
	/**
	 * �ɹ�����
	 */
	PURCHASE(OrderEnum.Purchase, SheetNumberType.Purchaes, ERPTableNames.Purchase.Order.getTableName(), "cggl", "1"),
	/**
	 * �����˻�
	 */
	SALE_CANCEL(OrderEnum.Sales_Return, SheetNumberType.SalesReturn, ERPTableNames.Sales.Return.getTableName(), "ddxs", "2"),
	/**
	 * �ɹ��˻�
	 */
	PURCHASE_CANCEL(OrderEnum.Purchase_Return, SheetNumberType.PurchaseReturn, ERPTableNames.Purchase.Return.getTableName(),
			"cggl", "2"),
	/**
	 * ���۴�������
	 */
	SALE_PROMOTION(OrderEnum.Sales_Promotion, null, "SA_SALE_PROMOTION", "xsgl_cxgl", ""),
	/**
	 * �������
	 */
	SALE_DEPLOYMENT(null, null, "", "", ""),
	/**
	 * ���۶���
	 */
	Retail_Order(OrderEnum.Retail, SheetNumberType.Retail, "", "01", ""),
	/**
	 * �����˻���
	 */
	Retail_Return(OrderEnum.Retail_Return, SheetNumberType.RetailReturn, "", "02", "");

	private OrderEnum orderEnum;
	private SheetNumberType sheetType;
	private String db_table;
	private String key;// ģ���ʶ
	private String code;// ֱ�ӽ�������code

	// private Mode mode; //��Ӧ�����ö��

	private BillsEnum(OrderEnum module, SheetNumberType sheetType, String db, String key, String code) {
		this.orderEnum = module;
		this.sheetType = sheetType;
		this.db_table = db;
		this.key = key;
		this.code = code;
		// this.mode = mode;
	}

	/**
	 * ����°浥������ö��
	 * 
	 * @return OrderEnum
	 */
	public OrderEnum getOrderEnum() {
		return orderEnum;
	}

	/**
	 * ��ö������ݿ����
	 * 
	 * @return String
	 */
	public String getDb_table() {
		return db_table;
	}

	/**
	 * ��ö���������ʾ��־
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
	 * ����key�ж��Ƿ��ǵ�ǰö��
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key) {
		return this.getKey().equals(key);
	}

	/**
	 * ��ǰö����ָ��ö�������е�һ��
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
	 * ȡ�ý��������־
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ��ö�Ӧͨ��ö��
	 * 
	 * @return CodeBuilder
	 */
	public CodeBuilder getCodeBuilder() {
		return orderEnum == null ? null : orderEnum.getCodeBuilder();
	}

	/**
	 * ͨ�����ö�ٻ�ö�Ӧ�Ķ�������
	 * 
	 * @param mode
	 * @return BillsEnum public static BillsEnum getBillsEnumForExamMode(Mode
	 *         mode){ for(BillsEnum bill : BillsEnum.values()){ if(bill.mode ==
	 *         mode){ return bill; } } return null; }
	 */
}
