package com.spark.oms.publish.receipt.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 销售人员缴款记录详情表
 * GetSalePaymentDetailItemKey &SaleReceiptItem
 */
public class GetSaleReceiptDetailItemKey {
	
	/**
	 * 销售人员Id
	 */
	private GUID saleId;
	
	private String serachkey;

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}

	public GUID getSaleId() {
		return saleId;
	}

	public void setSaleId(GUID saleId) {
		this.saleId = saleId;
	}
}