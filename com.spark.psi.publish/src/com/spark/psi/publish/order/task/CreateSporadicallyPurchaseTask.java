package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * ���ǲɹ����
 */
public class CreateSporadicallyPurchaseTask extends SimpleTask {

	/**
	 * ��Ӧ��ID
	 */
	private GUID supplierId;

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * �ɹ���
	 */
	private String purchaser;

	/**
	 * �ɹ�����
	 */
	private long purchaseDate;

	/**
	 * �ɹ���Ʒ����б�
	 */
	private CheckingGoodsItem[] items;

	/**
	 * ��ע
	 */
	private String memo;

	/**
	 * ���ʽ
	 */
	private String dealingsWayCode;
	private DistributeInventoryItem[] inventoryItems;

	public DistributeInventoryItem[] getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(DistributeInventoryItem[] inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	/**
	 * @return the supplierId
	 */
	public GUID getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the purchaser
	 */
	public String getPurchaser() {
		return purchaser;
	}

	/**
	 * @param purchaser
	 *            the purchaser to set
	 */
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	/**
	 * @return the purchaseDate
	 */
	public long getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate
	 *            the purchaseDate to set
	 */
	public void setPurchaseDate(long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the dealingsWayCode
	 */
	public String getDealingsWayCode() {
		return dealingsWayCode;
	}

	/**
	 * @param dealingsWayCode
	 *            the dealingsWayCode to set
	 */
	public void setDealingsWayCode(String dealingsWayCode) {
		this.dealingsWayCode = dealingsWayCode;
	}

	/**
	 * @return the items
	 */
	public CheckingGoodsItem[] getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(CheckingGoodsItem[] items) {
		this.items = items;
	}

	/**
	 * @return the memo
	 */
	public String getRemark() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
