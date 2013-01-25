package com.spark.psi.publish.deliverticket.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ����
 *
 */
public class CreateDeliverTicketTask extends SimpleTask {
	


	private GUID id;//	�б�ʶ
	private String sheetNo;//	���ݱ��
	private GUID onlineOrderId;//	���϶���id
	private String onlineOrderNo;//	���϶������
	private GUID memberId;//	�ͻ�ID
	private String memberRealName;//	�ͻ�����
	private String mobilePhone;//	�ֻ�����
	private GUID stationId;//	վ��Id
	private String stationName;//	վ������
	private String remark;//	��ע
	private double disAmount;//	�ۿ۽��
	private double totalAmount;//	�������
	private GUID creatorId;//	������Id
	private String creator;//	������
	private long createDate;//	��������
	private String address;//	��ַ
	private String deliverTicketType;
//	private Item[] items;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public GUID getOnlineOrderId() {
		return onlineOrderId;
	}
	public void setOnlineOrderId(GUID onlineOrderId) {
		this.onlineOrderId = onlineOrderId;
	}
	public String getOnlineOrderNo() {
		return onlineOrderNo;
	}
	public void setOnlineOrderNo(String onlineOrderNo) {
		this.onlineOrderNo = onlineOrderNo;
	}
	public GUID getMemberId() {
		return memberId;
	}
	public void setMemberId(GUID memberId) {
		this.memberId = memberId;
	}
	public String getMemberRealName() {
		return memberRealName;
	}
	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public GUID getStationId() {
		return stationId;
	}
	public void setStationId(GUID stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public void setItems(Item[] items) {
//		this.items = items;
//	}
//	public Item[] getItems() {
//		return items;
//	}
//	public class Item
//	{
//
//		private GUID id;//	�б�ʶ
//		private GUID ticketId;//	����GUID
//		private GUID goodsId;//	��ƷID
//		private String goodsCode;//	��Ʒ����
//		private String goodsNo;// ��Ʒ����
//		private String goodsName;// ��Ʒ����
//		private String goodsSpec;// ��Ʒ���
//		private String unit;// ��λ
//		private int goodsScale;// ��ƷС��λ��
//		private double price;// ����
//		private double count;// ����
//		private double disRate;// �ۿ���
//		private double disAmount;// �ۿ۶�
//		private double amount;//	���
//		public GUID getId() {
//			return id;
//		}
//		public void setId(GUID id) {
//			this.id = id;
//		}
//		public GUID getTicketId() {
//			return ticketId;
//		}
//		public void setTicketId(GUID ticketId) {
//			this.ticketId = ticketId;
//		}
//		public GUID getGoodsId() {
//			return goodsId;
//		}
//		public void setGoodsId(GUID goodsId) {
//			this.goodsId = goodsId;
//		}
//		public String getGoodsCode() {
//			return goodsCode;
//		}
//		public void setGoodsCode(String goodsCode) {
//			this.goodsCode = goodsCode;
//		}
//		public String getGoodsNo() {
//			return goodsNo;
//		}
//		public void setGoodsNo(String goodsNo) {
//			this.goodsNo = goodsNo;
//		}
//		public String getGoodsName() {
//			return goodsName;
//		}
//		public void setGoodsName(String goodsName) {
//			this.goodsName = goodsName;
//		}
//		public String getGoodsSpec() {
//			return goodsSpec;
//		}
//		public void setGoodsSpec(String goodsSpec) {
//			this.goodsSpec = goodsSpec;
//		}
//		public String getUnit() {
//			return unit;
//		}
//		public void setUnit(String unit) {
//			this.unit = unit;
//		}
//		public int getGoodsScale() {
//			return goodsScale;
//		}
//		public void setGoodsScale(int goodsScale) {
//			this.goodsScale = goodsScale;
//		}
//		public double getPrice() {
//			return price;
//		}
//		public void setPrice(double price) {
//			this.price = price;
//		}
//		public double getCount() {
//			return count;
//		}
//		public void setCount(double count) {
//			this.count = count;
//		}
//		public double getDisRate() {
//			return disRate;
//		}
//		public void setDisRate(double disRate) {
//			this.disRate = disRate;
//		}
//		public double getDisAmount() {
//			return disAmount;
//		}
//		public void setDisAmount(double disAmount) {
//			this.disAmount = disAmount;
//		}
//		public double getAmount() {
//			return amount;
//		}
//		public void setAmount(double amount) {
//			this.amount = amount;
//		}
//
//	}
	public void setDeliverTicketType(String deliverTicketType) {
		this.deliverTicketType = deliverTicketType;
	}
	public String getDeliverTicketType() {
		return deliverTicketType;
	}


}
