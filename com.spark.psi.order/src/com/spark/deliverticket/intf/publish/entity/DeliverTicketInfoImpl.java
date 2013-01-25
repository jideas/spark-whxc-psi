package com.spark.deliverticket.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfo;

/**
 * 
 * ����������
 *
 */
public class DeliverTicketInfoImpl implements DeliverTicketInfo{

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
	private DeliverTicketType deliverTicketType;
	private DeliverTicketInfoItemImpl[] items;
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
	public DeliverTicketInfoItemImpl[] getItems() {
		return items;
	}
	public void setItems(DeliverTicketInfoItemImpl[] items) {
		this.items = items;
	}
	public void setDeliverTicketType(DeliverTicketType deliverTicketType) {
		this.deliverTicketType = deliverTicketType;
	}
	public DeliverTicketType getDeliverTicketType() {
		return deliverTicketType;
	}
}
