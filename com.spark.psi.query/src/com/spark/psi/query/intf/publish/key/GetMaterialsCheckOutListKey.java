package com.spark.psi.query.intf.publish.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.LimitKey;

public class GetMaterialsCheckOutListKey extends LimitKey {

	public GetMaterialsCheckOutListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String sheetNo;//	出库单号
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private CheckingOutType[] checkingOutType;
	private long createDateBegin;
	private long createDateEnd;
	private String department;
	private String produceSheetNo;
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
	public CheckingOutType[] getCheckingOutType() {
		return checkingOutType;
	}
	public void setCheckingOutType(CheckingOutType[] checkingOutType) {
		this.checkingOutType = checkingOutType;
	}
	
	public long getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(long createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public long getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(long createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProduceSheetNo() {
		return produceSheetNo;
	}
	public void setProduceSheetNo(String produceSheetNo) {
		this.produceSheetNo = produceSheetNo;
	}
	
	
}
