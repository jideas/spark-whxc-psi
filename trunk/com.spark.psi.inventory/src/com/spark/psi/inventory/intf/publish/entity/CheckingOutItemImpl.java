package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;

/**
 * ���������б���Ŀ<br>
 * ��ѯ������ListEntry<CheckingOutItem>+GetCheckingOutListKey
 * 
 */
public class CheckingOutItemImpl implements  CheckingOutItem{

	/**
	 * ���ⵥid
	 */
	private GUID sheetId;

	/**
	 * ���ⵥ���
	 */
	private String sheetNumber;

	/**
	 * ��������
	 */
	private long createDate;

	/**
	 * �ƻ�����ʱ��
	 */
	private long planCheckoutDate;

	/**
	 * �ϴγ���ʱ��
	 */
	private long lastCheckoutDate;

	/**
	 * ��ص��ݱ��
	 */
	private String relatedNumber;

	/**
	 * ����ֿ�id
	 */
	private GUID storeId;

	/**
	 * ����ֿ�����
	 */
	private String storeName;

	/**
	 * ���ⵥ״̬
	 */
	private CheckingOutStatus status;

	/**
	 * ��������
	 */
	private CheckingOutType type;

	/**
	 * ����ȷ����
	 */
	private GUID[] checkoutEmployeeIds;

	/**
	 * ��ȡ����ȷ����
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