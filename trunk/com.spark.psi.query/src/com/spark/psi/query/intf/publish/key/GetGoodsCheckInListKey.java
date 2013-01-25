package com.spark.psi.query.intf.publish.key;

import com.spark.psi.publish.LimitKey;

public class GetGoodsCheckInListKey extends LimitKey {

	public GetGoodsCheckInListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String goodsCode;// 商品编码
	private String goodsName;// 商品名称
	private String produceSheetNo;
	private String department;
	private long createDateBegin;
	private long createDateEnd;
	private Boolean needProduce;
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
	public String getProduceSheetNo() {
		return produceSheetNo;
	}
	public void setProduceSheetNo(String produceSheetNo) {
		this.produceSheetNo = produceSheetNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public void setNeedProduce(Boolean needProduce) {
		this.needProduce = needProduce;
	}
	public Boolean isNeedProduce() {
		return needProduce;
	} 
}
