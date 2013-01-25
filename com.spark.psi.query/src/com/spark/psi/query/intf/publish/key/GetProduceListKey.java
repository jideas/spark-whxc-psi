package com.spark.psi.query.intf.publish.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

public class GetProduceListKey extends LimitKey {

	public GetProduceListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	
	private String billsNo;//	�������
	private String goodsCode;//	��Ʒ���
	private String goodsName;//	��Ʒ����
	private long createDateBegin;//�Ƶ�����
	private long createDateEnd;//�Ƶ�����
	private long planDateBegin;//Ԥ���������
	private long planDateEnd;//Ԥ���������
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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
	public long getPlanDateBegin() {
		return planDateBegin;
	}
	public void setPlanDateBegin(long planDateBegin) {
		this.planDateBegin = planDateBegin;
	}
	public long getPlanDateEnd() {
		return planDateEnd;
	}
	public void setPlanDateEnd(long planDateEnd) {
		this.planDateEnd = planDateEnd;
	}
	
	
	

}
