package com.spark.onlineorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;

/**
 * ���϶����б�<BR>
 * ��ѯ������<BR>
 * OlineOrderListEntity<OnlineOrderItem>+GetOnlineOrderListKey;
 */
public class OnlineOrderItemImpl implements OnlineOrderItem {

	private GUID id;//	�б�ʶ
	private String billsNo;//	�������
	private GUID memberId;//	��Աid
	private String realName;//	��ʵ����
	private String consignee;//	�ջ���
	private String consigneeTel;//	�ջ��˵绰
	private String address;//	�ջ���ַ
	private long deliveryeDate;//	��������
	private String remark;//	��ע
	private double disAmount;//	�����ۿ�
	private double totalAmount;//	�ܽ��
	private String type;//	����
	private GUID stationId;//	վ��ID
	private String stationName;//	վ������
	private String status;//	״̬
	private long createDate;//	��������
	private GUID consignorId;//	������ID
	private String consignor;//	������
	private long consignedDate;//	��������
	private GUID deliverPersonId;//	������Id
	private String deliverPerson;//	������
	private long deliverDate;//	��������
	private GUID arrivedConfirmId;//	ȷ�ϵ�����Id
	private String arrivedConfirm;//	ȷ�ϵ�����
	private long arrivedConfirmDate;//	ȷ�ϵ�������
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
