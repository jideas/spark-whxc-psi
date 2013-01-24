package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;

/**
 * ����̵㵥��Ŀ<br>
 * ��ѯ������ListEntity<InventoryCountSheetItem> + GetInventoryCountSheetListKey
 */
public class InventoryCountSheetItemImpl implements InventoryCountSheetItem{

	/**
	 * ����ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * ���ݺ�
	 * 
	 * @return
	 */
	private String sheetNumber;

	/**
	 * �̵㵥״̬
	 * 
	 * @return
	 */
	private InventoryCountStatus sheetstatus;

	/**
	 * ��ʼ����
	 * 
	 * @return
	 */
	private long startDate;

	/**
	 * ��������
	 * 
	 * @return
	 */
	private long endDate;

	/**
	 * �ֿ�ID
	 * 
	 * @return
	 */
	private GUID storeId;

	/**
	 * �ֿ�����
	 * 
	 * @return
	 */
	private String storeName;

	/**
	 * ��ӯ����
	 * 
	 * @return
	 */
	private int countProfit;

	/**
	 * �̿�����
	 * 
	 * @return
	 */
	private int countLoss;
	/**
	 * ����
	 */
	private InventoryCountType type;

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

	public InventoryCountStatus getSheetstatus() {
		return sheetstatus;
	}

	public void setSheetstatus(InventoryCountStatus sheetstatus) {
		this.sheetstatus = sheetstatus;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
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

	public int getCountProfit() {
		return countProfit;
	}

	public void setCountProfit(int countProfit) {
		this.countProfit = countProfit;
	}

	public int getCountLoss() {
		return countLoss;
	}

	public void setCountLoss(int countLoss) {
		this.countLoss = countLoss;
	}

	public InventoryCountType getType() {
		return type;
	}

	public void setType(InventoryCountType type) {
		this.type = type;
	}
	
	

}
