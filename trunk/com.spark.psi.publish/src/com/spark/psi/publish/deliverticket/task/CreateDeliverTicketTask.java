package com.spark.psi.publish.deliverticket.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * 创建
 *
 */
public class CreateDeliverTicketTask extends SimpleTask {
	


	private GUID id;//	行标识
	private String sheetNo;//	单据编号
	private GUID onlineOrderId;//	网上订单id
	private String onlineOrderNo;//	网上订单编号
	private GUID memberId;//	客户ID
	private String memberRealName;//	客户名称
	private String mobilePhone;//	手机号码
	private GUID stationId;//	站点Id
	private String stationName;//	站点名称
	private String remark;//	备注
	private double disAmount;//	折扣金额
	private double totalAmount;//	订单金额
	private GUID creatorId;//	发货人Id
	private String creator;//	发货人
	private long createDate;//	发货日期
	private String address;//	地址
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
//		private GUID id;//	行标识
//		private GUID ticketId;//	订单GUID
//		private GUID goodsId;//	商品ID
//		private String goodsCode;//	商品编码
//		private String goodsNo;// 商品条码
//		private String goodsName;// 商品名称
//		private String goodsSpec;// 商品规格
//		private String unit;// 单位
//		private int goodsScale;// 商品小数位数
//		private double price;// 单价
//		private double count;// 数量
//		private double disRate;// 折扣率
//		private double disAmount;// 折扣额
//		private double amount;//	金额
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
