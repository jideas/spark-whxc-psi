package com.spark.deliver.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.deliver.entity.DeliverInfo;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;

/**
 * 
 * 配送单详情<BR>
 * 根据ID查询
 *
 */
public class DeliverInfoImpl implements DeliverInfo{

	private GUID id;//	行标识
	private String sheetNo;//	单据编号
	private String remark;//	备注
	private GUID creatorId;//	发货人Id
	private String creator;//	发货人
	private long createDate;//	发货日期
	private long finishDate;//	完成日期
	private GUID stationId;//	站点ID
	private String stationName;//	站点名称
	private String address;//	地址
	private DeliverStatus status;//	状态
	private GUID deliverPersonId;//	配送人Id
	private String deliverPerson;//	配送人
	private long deliverDate;//	配送日期
	private GUID consigneeId;//	收货人Id
	private String consignee;//	收货人
	private long consigneeDate;//	收货日期
	private int deliveredPackageCount;//	发货箱数
	private int receivedPackageCount;//	收货箱数
	private String description;//	异常情况
	private String formula;//	处理方案
	private GUID handlerId;//	处理人ID
	private String handler;//	处理人
	private long handleDate;//	处理时间
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
