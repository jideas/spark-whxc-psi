package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;

/**
 * 盘点单详细信息<br>
 * 查询方法：根据盘点单ID查询InventoryCountSheetInfo对象
 */
public class InventoryCountSheetInfoImpl implements InventoryCountSheetInfo{

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
	 * 盘点单类型
	 * 
	 * @return
	 */
	private InventoryCountType sheetType;

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
	 * 制单人ID
	 * 
	 * @return
	 */
	private GUID creatorId;

	/**
	 * 制单人姓名
	 * 
	 * @return
	 */
	private String creatorName;
	/**
	 * 盘点人姓名
	 */
	private String name;

	/**
	 * 备注信息
	 * 
	 * @return
	 */
	private String memo;

	/**
	 * 商品盘点条目列表
	 * 
	 * @return
	 */
	private GoodsCountItemImpl[] goodsCountItems;

	/**
	 * 其他物品盘点条目列表
	 * 
	 * @return
	 */
	private KitCountItemImpl[] kitCountItems;

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

	public InventoryCountType getSheetType() {
		return sheetType;
	}

	public void setSheetType(InventoryCountType sheetType) {
		this.sheetType = sheetType;
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

	public GUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public GoodsCountItemImpl[] getGoodsCountItems() {
		return goodsCountItems;
	}

	public void setGoodsCountItems(GoodsCountItemImpl[] goodsCountItems) {
		this.goodsCountItems = goodsCountItems;
	}

	public KitCountItemImpl[] getKitCountItems() {
		return kitCountItems;
	}

	public void setKitCountItems(KitCountItemImpl[] kitCountItems) {
		this.kitCountItems = kitCountItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * 商品盘点条目
	 */
	public static class GoodsCountItemImpl implements GoodsCountItem {

		/**
		 * 商品条目ID
		 * 
		 * @return
		 */
		private GUID goodsItemId;

		/**
		 * 商品条目编码
		 * 
		 * @return
		 */
		private String goodsCode;
		private String goodsNo;

		/**
		 * 商品条目名称
		 * 
		 * @return
		 */
		private String goodsItemName;

		/**
		 * 商品条目属性
		 * 
		 * @return
		 */
		private String goodsItemProperties;

		/**
		 * 商品条目单位
		 * 
		 * @return
		 */
		private String goodsItemUnit;

		/**
		 * 商品数量小数位
		 * 
		 * @return
		 */
		private int countDecimal;

		/**
		 * 商品账面数量
		 * 
		 * @return
		 */
		private double count;

		/**
		 * 商品实际数量
		 * 
		 * @return
		 */
		private double actualCount;

		/**
		 * 获取说明
		 * 
		 * @return
		 */
		private String memo;
		
		/**
		 * 是否库存物品
		 */
		private boolean existInventory;

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public String getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsNo() {
			return goodsNo;
		}

		public void setGoodsNo(String goodsNo) {
			this.goodsNo = goodsNo;
		}

		public String getGoodsItemName() {
			return goodsItemName;
		}

		public void setGoodsItemName(String goodsItemName) {
			this.goodsItemName = goodsItemName;
		}

		public String getGoodsItemProperties() {
			return goodsItemProperties;
		}

		public void setGoodsItemProperties(String goodsItemProperties) {
			this.goodsItemProperties = goodsItemProperties;
		}

		public String getGoodsItemUnit() {
			return goodsItemUnit;
		}

		public void setGoodsItemUnit(String goodsItemUnit) {
			this.goodsItemUnit = goodsItemUnit;
		}

		public int getScale() {
			return countDecimal;
		}

		public void setCountDecimal(int countDecimal) {
			this.countDecimal = countDecimal;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getActualCount() {
			return actualCount;
		}

		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		public String getRemark() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}

		public boolean isExistInventory() {
			return existInventory;
		}
		
		

	}

	/**
	 * 其他物品盘点条目
	 */
	public static class KitCountItemImpl implements  KitCountItem{
		/**
		 * 物品名称
		 * 
		 * @return
		 */
		private String kitName;

		/**
		 * 物品描述
		 * 
		 * @return
		 */
		private String kitDesc;

		/**
		 * 物品单位
		 * 
		 * @return
		 */
		private String kitUnit;

		/**
		 * 物品账面数量
		 * 
		 * @return
		 */
		private double count;

		/**
		 * 物品实际数量
		 * 
		 * @return
		 */
		private double actualCount;

		/**
		 * 说明
		 * 
		 * @return
		 */
		private String memo;

		/**
		 * 是否库存物品
		 */
		private boolean existInventory;
		
		public String getKitName() {
			return kitName;
		}

		public void setKitName(String kitName) {
			this.kitName = kitName;
		}

		public String getKitDesc() {
			return kitDesc;
		}

		public void setKitDesc(String kitDesc) {
			this.kitDesc = kitDesc;
		}

		public String getKitUnit() {
			return kitUnit;
		}

		public void setKitUnit(String kitUnit) {
			this.kitUnit = kitUnit;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getActualCount() {
			return actualCount;
		}

		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		public String getRemark() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}

		public boolean isExistInventory() {
			return existInventory;
		}
		

	}

}
