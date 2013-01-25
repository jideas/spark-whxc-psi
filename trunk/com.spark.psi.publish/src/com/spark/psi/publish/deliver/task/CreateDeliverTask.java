package com.spark.psi.publish.deliver.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * 创建配送单
 *
 */
public class CreateDeliverTask extends SimpleTask {




	private GUID id;//	行标识
//	private String sheetNo;//	单据编号
	private String remark;//	备注
//	private GUID creatorId;//	发货人Id
//	private String creator;//	发货人
//	private long createDate;//	发货日期
//	private long finishDate;//	完成日期
	private GUID stationId;//	站点ID
	private String stationName;//	站点名称
	private String address;//	地址
//	private DeliverStatus status;//	状态
//	private GUID deliverPersonId;//	配送人Id
	private String deliverPerson;//	配送人
//	private long deliverDate;//	配送日期
//	private GUID consigneeId;//	收货人Id
//	private String consignee;//	收货人
//	private long consigneeDate;//	收货日期
	private int deliveredPackageCount;//	发货箱数
//	private int receivedPackageCount;//	收货箱数
//	private String description;//	异常情况
//	private String formula;//	处理方案
//	private GUID handlerId;//	处理人ID
//	private String handler;//	处理人
//	private long handleDate;//	处理时间
	private Item[] items;
	private long planDate;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
//	public String getSheetNo() {
//		return sheetNo;
//	}
//	public void setSheetNo(String sheetNo) {
//		this.sheetNo = sheetNo;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public GUID getCreatorId() {
//		return creatorId;
//	}
//	public void setCreatorId(GUID creatorId) {
//		this.creatorId = creatorId;
//	}
//	public String getCreator() {
//		return creator;
//	}
//	public void setCreator(String creator) {
//		this.creator = creator;
//	}
//	public long getCreateDate() {
//		return createDate;
//	}
//	public void setCreateDate(long createDate) {
//		this.createDate = createDate;
//	}
//	public long getFinishDate() {
//		return finishDate;
//	}
//	public void setFinishDate(long finishDate) {
//		this.finishDate = finishDate;
//	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public DeliverStatus getStatus() {
//		return status;
//	}
//	public void setStatus(DeliverStatus status) {
//		this.status = status;
//	}
//	public GUID getDeliverPersonId() {
//		return deliverPersonId;
//	}
//	public void setDeliverPersonId(GUID deliverPersonId) {
//		this.deliverPersonId = deliverPersonId;
//	}
//	public String getDeliverPerson() {
//		return deliverPerson;
//	}
//	public void setDeliverPerson(String deliverPerson) {
//		this.deliverPerson = deliverPerson;
//	}
//	public long getDeliverDate() {
//		return deliverDate;
//	}
//	public void setDeliverDate(long deliverDate) {
//		this.deliverDate = deliverDate;
//	}
//	public GUID getConsigneeId() {
//		return consigneeId;
//	}
//	public void setConsigneeId(GUID consigneeId) {
//		this.consigneeId = consigneeId;
//	}
//	public String getConsignee() {
//		return consignee;
//	}
//	public void setConsignee(String consignee) {
//		this.consignee = consignee;
//	}
//	public long getConsigneeDate() {
//		return consigneeDate;
//	}
//	public void setConsigneeDate(long consigneeDate) {
//		this.consigneeDate = consigneeDate;
//	}
	public int getDeliveredPackageCount() {
		return deliveredPackageCount;
	}
	public void setDeliveredPackageCount(int deliveredPackageCount) {
		this.deliveredPackageCount = deliveredPackageCount;
	}
//	public int getReceivedPackageCount() {
//		return receivedPackageCount;
//	}
//	public void setReceivedPackageCount(int receivedPackageCount) {
//		this.receivedPackageCount = receivedPackageCount;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getFormula() {
//		return formula;
//	}
//	public void setFormula(String formula) {
//		this.formula = formula;
//	}
//	public GUID getHandlerId() {
//		return handlerId;
//	}
//	public void setHandlerId(GUID handlerId) {
//		this.handlerId = handlerId;
//	}
//	public String getHandler() {
//		return handler;
//	}
//	public void setHandler(String handler) {
//		this.handler = handler;
//	}
//	public long getHandleDate() {
//		return handleDate;
//	}
//	public void setHandleDate(long handleDate) {
//		this.handleDate = handleDate;
//	}
	
	public void setItems(Item[] items) {
		this.items = items;
	}
	public Item[] getItems() {
		return items;
	}

	public void setDeliverPerson(String deliverPerson) {
		this.deliverPerson = deliverPerson;
	}
	public String getDeliverPerson() {
		return deliverPerson;
	}

	public void setPlanDate(long planDate) {
		this.planDate = planDate;
	}
	public long getPlanDate() {
		return planDate;
	}

	public class Item
	{
		private GUID id;//	行标识
		private GUID deliverSheetId;//	配送单ID
		private GUID onlineOrderId;//	网上订单Id
		private String onlineOrderNo;//	订单编号
		private String memberRealName;//	会员名称
//		private double orderAmount;//订单金额
		private GUID memberId;//	客户ID
		private String mobilePhone;//	手机号码
		private GUID stationId;//	站点Id
		private String stationName;//	站点名称
		private String remark;//	备注
		private double disAmount;//	折扣金额
		private double totalAmount;//	订单金额
		private String address;//	地址
		public GUID getId() {
			return id;
		}
		public void setId(GUID id) {
			this.id = id;
		}
		public GUID getDeliverSheetId() {
			return deliverSheetId;
		}
		public void setDeliverSheetId(GUID deliverSheetId) {
			this.deliverSheetId = deliverSheetId;
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
		public String getMemberRealName() {
			return memberRealName;
		}
		public void setMemberRealName(String memberRealName) {
			this.memberRealName = memberRealName;
		}
//		public double getOrderAmount() {
//			return orderAmount;
//		}
//		public void setOrderAmount(double orderAmount) {
//			this.orderAmount = orderAmount;
//		}
		public GUID getMemberId() {
			return memberId;
		}
		public void setMemberId(GUID memberId) {
			this.memberId = memberId;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}



}
