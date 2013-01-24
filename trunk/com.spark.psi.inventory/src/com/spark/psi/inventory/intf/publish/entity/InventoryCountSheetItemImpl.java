package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;

/**
 * 库存盘点单条目<br>
 * 查询方法：ListEntity<InventoryCountSheetItem> + GetInventoryCountSheetListKey
 */
public class InventoryCountSheetItemImpl implements InventoryCountSheetItem{

	/**
	 * 单据ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * 单据号
	 * 
	 * @return
	 */
	private String sheetNumber;

	/**
	 * 盘点单状态
	 * 
	 * @return
	 */
	private InventoryCountStatus sheetstatus;

	/**
	 * 开始日期
	 * 
	 * @return
	 */
	private long startDate;

	/**
	 * 结束日期
	 * 
	 * @return
	 */
	private long endDate;

	/**
	 * 仓库ID
	 * 
	 * @return
	 */
	private GUID storeId;

	/**
	 * 仓库名称
	 * 
	 * @return
	 */
	private String storeName;

	/**
	 * 盘盈数量
	 * 
	 * @return
	 */
	private int countProfit;

	/**
	 * 盘亏数量
	 * 
	 * @return
	 */
	private int countLoss;
	/**
	 * 类型
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
