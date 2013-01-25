package com.spark.onlineorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;

/**
 * 网上订单列表<BR>
 * 查询方法：<BR>
 * OlineOrderListEntity<OnlineOrderItem>+GetOnlineOrderListKey;
 */
public class OnlineOrderItemImpl implements OnlineOrderItem {

	private GUID id;//	行标识
	private String billsNo;//	订单编号
	private GUID memberId;//	会员id
	private String realName;//	真实姓名
	private String consignee;//	收货人
	private String consigneeTel;//	收货人电话
	private String address;//	收货地址
	private long deliveryeDate;//	交货日期
	private String remark;//	备注
	private double disAmount;//	整单折扣
	private double totalAmount;//	总金额
	private String type;//	类型
	private GUID stationId;//	站点ID
	private String stationName;//	站点名称
	private String status;//	状态
	private long createDate;//	创建日期
	private GUID consignorId;//	发货人ID
	private String consignor;//	发货人
	private long consignedDate;//	发货日期
	private GUID deliverPersonId;//	配送人Id
	private String deliverPerson;//	配送人
	private long deliverDate;//	配送日期
	private GUID arrivedConfirmId;//	确认到货人Id
	private String arrivedConfirm;//	确认到货人
	private long arrivedConfirmDate;//	确认到货日期
	private String verificationCode;
	private String noVerificationReason;
	private boolean returnFlag;
	private boolean isToDoor;

	private OnlineOrderInfoItem[] items;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public GUID getMemberId() {
		return memberId;
	}
	public void setMemberId(GUID memberId) {
		this.memberId = memberId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDeliveryeDate() {
		return deliveryeDate;
	}
	public void setDeliveryeDate(long deliveryeDate) {
		this.deliveryeDate = deliveryeDate;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public GUID getConsignorId() {
		return consignorId;
	}
	public void setConsignorId(GUID consignorId) {
		this.consignorId = consignorId;
	}
	public String getConsignor() {
		return consignor;
	}
	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}
	public long getConsignedDate() {
		return consignedDate;
	}
	public void setConsignedDate(long consignedDate) {
		this.consignedDate = consignedDate;
	}
	public GUID getDeliverPersonId() {
		return deliverPersonId;
	}
	public void setDeliverPersonId(GUID deliverPersonId) {
		this.deliverPersonId = deliverPersonId;
	}
	public String getDeliverPerson() {
		return deliverPerson;
	}
	public void setDeliverPerson(String deliverPerson) {
		this.deliverPerson = deliverPerson;
	}
	public long getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(long deliverDate) {
		this.deliverDate = deliverDate;
	}
	public GUID getArrivedConfirmId() {
		return arrivedConfirmId;
	}
	public void setArrivedConfirmId(GUID arrivedConfirmId) {
		this.arrivedConfirmId = arrivedConfirmId;
	}
	public String getArrivedConfirm() {
		return arrivedConfirm;
	}
	public void setArrivedConfirm(String arrivedConfirm) {
		this.arrivedConfirm = arrivedConfirm;
	}
	public long getArrivedConfirmDate() {
		return arrivedConfirmDate;
	}
	public void setArrivedConfirmDate(long arrivedConfirmDate) {
		this.arrivedConfirmDate = arrivedConfirmDate;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getNoVerificationReason() {
		return noVerificationReason;
	}
	public void setNoVerificationReason(String noVerificationReason) {
		this.noVerificationReason = noVerificationReason;
	}
	public OnlineOrderInfoItem[] getItems() {
		return items;
	}
	public void setItems(OnlineOrderInfoItem[] items) {
		this.items = items;
	}
	public void setReturnFlag(boolean returnFlag) {
		this.returnFlag = returnFlag;
	}
	public boolean isReturnFlag() {
		return returnFlag;
	}
	public void setToDoor(boolean isToDoor) {
		this.isToDoor = isToDoor;
	}
	public boolean isToDoor() {
		return isToDoor;
	}
}
