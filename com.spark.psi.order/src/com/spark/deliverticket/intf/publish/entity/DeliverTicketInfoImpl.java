package com.spark.deliverticket.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfo;

/**
 * 
 * 发货单详情
 *
 */
public class DeliverTicketInfoImpl implements DeliverTicketInfo{

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
