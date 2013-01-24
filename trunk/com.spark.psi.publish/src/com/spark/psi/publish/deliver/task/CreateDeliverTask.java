package com.spark.psi.publish.deliver.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * �������͵�
 *
 */
public class CreateDeliverTask extends SimpleTask {




	private GUID id;//	�б�ʶ
//	private String sheetNo;//	���ݱ��
	private String remark;//	��ע
//	private GUID creatorId;//	������Id
//	private String creator;//	������
//	private long createDate;//	��������
//	private long finishDate;//	�������
	private GUID stationId;//	վ��ID
	private String stationName;//	վ������
	private String address;//	��ַ
//	private DeliverStatus status;//	״̬
//	private GUID deliverPersonId;//	������Id
	private String deliverPerson;//	������
//	private long deliverDate;//	��������
//	private GUID consigneeId;//	�ջ���Id
//	private String consignee;//	�ջ���
//	private long consigneeDate;//	�ջ�����
	private int deliveredPackageCount;//	��������
//	private int receivedPackageCount;//	�ջ�����
//	private String description;//	�쳣���
//	private String formula;//	������
//	private GUID handlerId;//	������ID
//	private String handler;//	������
//	private long handleDate;//	����ʱ��
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
		private GUID id;//	�б�ʶ
		private GUID deliverSheetId;//	���͵�ID
		private GUID onlineOrderId;//	���϶���Id
		private String onlineOrderNo;//	�������
		private String memberRealName;//	��Ա����
//		private double orderAmount;//�������
		private GUID memberId;//	�ͻ�ID
		private String mobilePhone;//	�ֻ�����
		private GUID stationId;//	վ��Id
		private String stationName;//	վ������
		private String remark;//	��ע
		private double disAmount;//	�ۿ۽��
		private double totalAmount;//	�������
		private String address;//	��ַ
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
