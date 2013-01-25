package com.spark.psi.query.intf.publish.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.publish.LimitKey;

public class GetTicketListKey extends LimitKey {

	public GetTicketListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	
	private String goodsCode;// ��Ʒ����
	private String goodsNo;// ��Ʒ����
	private String goodsName;// ��Ʒ����
	private String sheetNo;// ���ݱ��
	private long createDateBegin;// ��������
	private long createDateEnd;// ��������
	private DeliverTicketType deliverTicketType;// ��������
	private String memberRealName;//	�ͻ�����
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
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
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
	public DeliverTicketType getDeliverTicketType() {
		return deliverTicketType;
	}
	public void setDeliverTicketType(DeliverTicketType deliverTicketType) {
		this.deliverTicketType = deliverTicketType;
	}
	public String getMemberRealName() {
		return memberRealName;
	}
	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}

	
}
