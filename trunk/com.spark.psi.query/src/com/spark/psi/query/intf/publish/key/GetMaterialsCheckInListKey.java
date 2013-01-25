package com.spark.psi.query.intf.publish.key;

import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.LimitKey;

public class GetMaterialsCheckInListKey extends LimitKey {

	public GetMaterialsCheckInListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String sheetNo;//入库单号
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private String purchaseSheetNo;
	private long checkinDateBegin;
	private long checkinDateEnd;
	private CheckingInType checkingInType;
	private String partnerName;
	private String partnerNo;
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPurchaseSheetNo() {
		return purchaseSheetNo;
	}
	public void setPurchaseSheetNo(String purchaseSheetNo) {
		this.purchaseSheetNo = purchaseSheetNo;
	} 
	public CheckingInType getCheckingInType() {
		return checkingInType;
	}
	public void setCheckingInType(CheckingInType checkingInType) {
		this.checkingInType = checkingInType;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerNo() {
		return partnerNo;
	}
	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}
	public long getCheckinDateBegin() {
		return checkinDateBegin;
	}
	public void setCheckinDateBegin(long checkinDateBegin) {
		this.checkinDateBegin = checkinDateBegin;
	}
	public long getCheckinDateEnd() {
		return checkinDateEnd;
	}
	public void setCheckinDateEnd(long checkinDateEnd) {
		this.checkinDateEnd = checkinDateEnd;
	}
}
