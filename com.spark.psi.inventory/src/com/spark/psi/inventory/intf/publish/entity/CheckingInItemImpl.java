package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.CheckingInItem;

/**
 * 入库单列表项目<br>
 * 查询方法：ListEntry<CheckingInItem>+GetCheckingInListKey
 * 
 */
public class CheckingInItemImpl implements CheckingInItem {

	/**
	 * 入库单id
	 */
	private GUID sheetId;
	private String remark;
	/**
	 * 创建日期
	 */
	private long createDate;

	/**
	 * 计划入库时间
	 */
	private long planCheckinDate;

	/**
	 * 上次入库时间
	 */
	private long lastCheckinDate;

	/**
	 * 相关单据编号
	 */
	private String relaBillsNo;

	/**
	 * 入库仓库id
	 */
	private GUID storeId;

	/**
	 * 入库仓库名称
	 */
	private String storeName;

	/**
	 * 获取入库单状态
	 */
	private CheckingInStatus status;

	/**
	 * 获取入库类型
	 */
	private CheckingInType type;

	/**
	 * 入库确认人
	 */
	private GUID[] checkinEmployeeIds;

	/**
	 * 获取入库确认人
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
