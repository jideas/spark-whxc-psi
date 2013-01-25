package com.spark.order.intf.key;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>校验在订货页签（待提交或已退回状态）是否有单据</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public class ValiBillsByNewKey extends SelectKey{
	private final BillsEnum billsEnum;
	public ValiBillsByNewKey(BillsEnum billsEnum){
		this.billsEnum = billsEnum;
	}
	/**
	 * 获取当前
	 * @return BillsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
}
