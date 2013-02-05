package com.spark.deliver.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.deliver.entity.DeliverInfo;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;

/**
 * 
 * ���͵�����<BR>
 * ����ID��ѯ
 *
 */
public class DeliverInfoImpl implements DeliverInfo{

	private GUID id;//	�б�ʶ
	private String sheetNo;//	���ݱ��
	private String remark;//	��ע
	private GUID creatorId;//	������Id
	private String creator;//	������
	private long createDate;//	��������
	private long finishDate;//	�������
	private GUID stationId;//	վ��ID
	private String stationName;//	վ������
	private String address;//	��ַ
	private DeliverStatus status;//	״̬
	private GUID deliverPersonId;//	������Id
	private String deliverPerson;//	������
	private long deliverDate;//	��������
	private GUID consigneeId;//	�ջ���Id
	private String consignee;//	�ջ���
	private long consigneeDate;//	�ջ�����
	private int deliveredPackageCount;//	��������
	private int receivedPackageCount;//	�ջ�����
	private String description;//	�쳣���
	private String formula;//	��������
	private GUID handlerId;//	������ID
	private String handler;//	������
	private long handleDate;//	����ʱ��
	private DeliverInfoItem[] items;
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public long getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(long finishDate) {
		this.finishDate = finishDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public DeliverStatus getStatus() {
		return status;
	}
	public void setStatus(DeliverStatus status) {
		this.status = status;
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
	public GUID getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(GUID consigneeId) {
		this.consigneeId = consigneeId;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public long getConsigneeDate() {
		return consigneeDate;
	}
	public void setConsigneeDate(long consigneeDate) {
		this.consigneeDate = consigneeDate;
	}
	public int getDeliveredPackageCount() {
		return deliveredPackageCount;
	}
	public void setDeliveredPackageCount(int deliveredPackageCount) {
		this.deliveredPackageCount = deliveredPackageCount;
	}
	public int getReceivedPackageCount() {
		return receivedPackageCount;
	}
	public void setReceivedPackageCount(int receivedPackageCount) {
		this.receivedPackageCount = receivedPackageCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public GUID getHandlerId() {
		return handlerId;
	}
	public void setHandlerId(GUID handlerId) {
		this.handlerId = handlerId;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public long getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(long handleDate) {
		this.handleDate = handleDate;
	}
	public void setItems(DeliverInfoItem[] items) {
		this.items = items;
	}
	public DeliverInfoItem[] getItems() {
		return items;
	}


}