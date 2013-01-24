package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.CheckingInItem;

/**
 * ��ⵥ�б���Ŀ<br>
 * ��ѯ������ListEntry<CheckingInItem>+GetCheckingInListKey
 * 
 */
public class CheckingInItemImpl implements CheckingInItem {

	/**
	 * ��ⵥid
	 */
	private GUID sheetId;
	private String remark;
	/**
	 * ��������
	 */
	private long createDate;

	/**
	 * �ƻ����ʱ��
	 */
	private long planCheckinDate;

	/**
	 * �ϴ����ʱ��
	 */
	private long lastCheckinDate;

	/**
	 * ��ص��ݱ��
	 */
	private String relaBillsNo;

	/**
	 * ���ֿ�id
	 */
	private GUID storeId;

	/**
	 * ���ֿ�����
	 */
	private String storeName;

	/**
	 * ��ȡ��ⵥ״̬
	 */
	private CheckingInStatus status;

	/**
	 * ��ȡ�������
	 */
	private CheckingInType type;

	/**
	 * ���ȷ����
	 */
	private GUID[] checkinEmployeeIds;

	/**
	 * ��ȡ���ȷ����
	 */
	private String checkinEmployees;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getPlanCheckinDate() {
		return planCheckinDate;
	}

	public void setPlanCheckinDate(long planCheckinDate) {
		this.planCheckinDate = planCheckinDate;
	}

	public long getLastCheckinDate() {
		return lastCheckinDate;
	}

	public void setLastCheckinDate(long lastCheckinDate) {
		this.lastCheckinDate = lastCheckinDate;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public CheckingInStatus getStatus() {
		return status;
	}

	public void setStatus(CheckingInStatus status) {
		this.status = status;
	}

	public CheckingInType getType() {
		return type;
	}

	public void setType(CheckingInType type) {
		this.type = type;
	}

	public GUID[] getCheckinEmployeeIds() {
		return checkinEmployeeIds;
	}

	public void setCheckinEmployeeIds(GUID[] checkinEmployeeIds) {
		this.checkinEmployeeIds = checkinEmployeeIds;
	}

	public String getCheckinEmployees() {
		return checkinEmployees;
	}

	public void setCheckinEmployees(String checkinEmployees) {
		this.checkinEmployees = checkinEmployees;
	}

}
