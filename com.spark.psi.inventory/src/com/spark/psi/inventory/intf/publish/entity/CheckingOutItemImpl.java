package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;

/**
 * 出库需求列表项目<br>
 * 查询方法：ListEntry<CheckingOutItem>+GetCheckingOutListKey
 * 
 */
public class CheckingOutItemImpl implements  CheckingOutItem{

	/**
	 * 出库单id
	 */
	private GUID sheetId;

	/**
	 * 出库单编号
	 */
	private String sheetNumber;

	/**
	 * 创建日期
	 */
	private long createDate;

	/**
	 * 计划出库时间
	 */
	private long planCheckoutDate;

	/**
	 * 上次出库时间
	 */
	private long lastCheckoutDate;

	/**
	 * 相关单据编号
	 */
	private String relatedNumber;

	/**
	 * 出库仓库id
	 */
	private GUID storeId;

	/**
	 * 出库仓库名称
	 */
	private String storeName;

	/**
	 * 出库单状态
	 */
	private CheckingOutStatus status;

	/**
	 * 出库类型
	 */
	private CheckingOutType type;

	/**
	 * 出库确认人
	 */
	private GUID[] checkoutEmployeeIds;

	/**
	 * 获取出库确认人
	 */
	private String checkoutEmployees;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getPlanCheckoutDate() {
		return planCheckoutDate;
	}

	public void setPlanCheckoutDate(long planCheckoutDate) {
		this.planCheckoutDate = planCheckoutDate;
	}

	public long getLastCheckoutDate() {
		return lastCheckoutDate;
	}

	public void setLastCheckoutDate(long lastCheckoutDate) {
		this.lastCheckoutDate = lastCheckoutDate;
	}

	public String getRelatedNumber() {
		return relatedNumber;
	}

	public void setRelatedNumber(String relatedNumber) {
		this.relatedNumber = relatedNumber;
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

	public CheckingOutStatus getStatus() {
		return status;
	}

	public void setStatus(CheckingOutStatus status) {
		this.status = status;
	}

	public CheckingOutType getType() {
		return type;
	}

	public void setType(CheckingOutType type) {
		this.type = type;
	}

	public GUID[] getCheckoutEmployeeIds() {
		return checkoutEmployeeIds;
	}

	public void setCheckoutEmployeeIds(GUID[] checkoutEmployeeIds) {
		this.checkoutEmployeeIds = checkoutEmployeeIds;
	}

	public String getCheckoutEmployees() {
		return checkoutEmployees;
	}

	public void setCheckoutEmployees(String checkoutEmployees) {
		this.checkoutEmployees = checkoutEmployees;
	}

	public String getRelaBillsNo() {
		return relatedNumber;
	}
}