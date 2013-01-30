package com.spark.psi.query.intf.publish.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

public class GetOnlineSalesListKey extends LimitKey {

	public GetOnlineSalesListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String goodsCode;// 商品编码
	private String goodsNo;// 商品条码
	private String goodsName;// 商品名称
	private long deliverDateBegin; // 发货日期
	private long deliverDateEnd; // 发货日期
	private String customerName;
	private GUID goodsCategoryId;
	private GUID stationId;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getDeliverDateBegin() {
		return deliverDateBegin;
	}

	public void setDeliverDateBegin(long deliverDateBegin) {
		this.deliverDateBegin = deliverDateBegin;
	}

	public long getCreateDateEnd() {
		return deliverDateEnd;
	}

	public void setCreateDateEnd(long createDateEnd) {
		this.deliverDateEnd = createDateEnd;
	}

	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setStationId(GUID stationId) {
		this.stationId = stationId;
	}

	public GUID getStationId() {
		return stationId;
	}

}
