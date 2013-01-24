package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * 库存盘点
 */
public class InventoryCountTask extends Task<InventoryCountTask.Method> {

	/**
	 * 操作方法
	 */
	public enum Method {
		Insert, Modify, Finish
	}

	/**
	 * 
	 * <p>
	 * 错误信息
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author Wangtiancai
	 * @version 2012-6-26
	 */
	public enum Error {
		ConcurrentError("有其他人在操作该单据，请检查！");

		private String message;

		public String getMessage() {
			return message;
		}

		private Error(String message) {
			this.message = message;
		}
	}

	private boolean success;
	private Error[] errors;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Error[] getErrors() {
		return errors;
	}

	public void setErrors(Error[] errors) {
		this.errors = errors;
	}

	/**
	 * 盘点单ID
	 */
	private GUID sheetId;

	private DistributeInventoryItem[] inventorysAdd;
	private DistributeInventoryItem[] inventorysSub;

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	/**
	 * 商品库存盘点项目列表
	 */
	private TaskGoodsCountItem[] TaskGoodsCountItems;

	/**
	 * 其他库存盘点项目列表
	 */
	private TaskKitCountItem[] TaskKitCountItems;
	/**
	 * 仓库ID
	 */
	private GUID storeId;
	/**
	 * 盘点人名称
	 */
	private String name;

	/**
	 * 盘点对象类型(商品/物品)
	 */
	private InventoryCountType type;
	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 构造函数（商品库存盘点）
	 * 
	 * @param sheetId
	 * @param TaskGoodsCountItems
	 */
	public InventoryCountTask(GUID sheetId, TaskGoodsCountItem[] TaskGoodsCountItems) {
		super();
		this.sheetId = sheetId;
		this.TaskGoodsCountItems = TaskGoodsCountItems;
	}

	/**
	 * 构造函数（其他库存盘点）
	 * 
	 * @param sheetId
	 * @param TaskKitCountItems
	 */
	public InventoryCountTask(GUID sheetId, TaskKitCountItem[] TaskKitCountItems) {
		super();
		this.sheetId = sheetId;
		this.TaskKitCountItems = TaskKitCountItems;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sheetId
	 */
	public GUID getSheetId() {
		return sheetId;
	}

	/**
	 * @return the TaskGoodsCountItems
	 */
	public TaskGoodsCountItem[] getTaskGoodsCountItems() {
		return TaskGoodsCountItems;
	}

	/**
	 * @return the TaskKitCountItems
	 */
	public TaskKitCountItem[] getTaskKitCountItems() {
		return TaskKitCountItems;
	}

	public void setType(InventoryCountType type) {
		this.type = type;
	}

	public InventoryCountType getType() {
		return type;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRemark() {
		return memo;
	}

	/**
	 * 商品库存盘点项
	 */
	public final static class TaskGoodsCountItem {

		/**
		 * 商品条目ID
		 */
		private GUID goodsItemId;

		/**
		 * 实盘数量
		 */
		private double actualCount;
		/**
		 * 说明
		 */
		private String memo;

		public String getRemark() {
			return memo;
		}

		/**
		 * 构造函数
		 * 
		 * @param goodsItemId
		 * @param actualCount
		 */
		public TaskGoodsCountItem(GUID goodsItemId, double actualCount, String memo) {
			super();
			this.goodsItemId = goodsItemId;
			this.actualCount = actualCount;
			this.memo = memo;
		}

		/**
		 * @return the goodsItemId
		 */
		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		/**
		 * @return the actualCount
		 */
		public double getActualCount() {
			return actualCount;
		}

	}

	/**
	 * 其他库存盘点项
	 */
	public final static class TaskKitCountItem {

		/**
		 * 物品名称
		 */
		private String kitName;

		/**
		 * 物品描述
		 */
		private String kitDesc;

		/**
		 * 物品单位
		 */
		private String kitUnit;

		/**
		 * 实盘数量
		 */
		private double actualCount;

		/**
		 * 说明
		 */
		private String explain;

		public String getExplain() {
			return explain;
		}

		/**
		 * 构造函数
		 * 
		 * @param kitName
		 * @param kitDesc
		 * @param kitUnit
		 * @param actualCount
		 */
		public TaskKitCountItem(String kitName, String kitDesc, String kitUnit, double actualCount, String explain) {
			super();
			this.kitName = kitName;
			this.kitDesc = kitDesc;
			this.kitUnit = kitUnit;
			this.actualCount = actualCount;
			this.explain = explain;
		}

		/**
		 * @return the kitName
		 */
		public String getKitName() {
			return kitName;
		}

		/**
		 * @return the kitDesc
		 */
		public String getKitDesc() {
			return kitDesc;
		}

		/**
		 * @return the kitUnit
		 */
		public String getKitUnit() {
			return kitUnit;
		}

		/**
		 * @return the actualCount
		 */
		public double getActualCount() {
			return actualCount;
		}

	}

	public DistributeInventoryItem[] getInventorysAdd() {
		return inventorysAdd;
	}

	public void setInventorysAdd(DistributeInventoryItem[] inventorysAdd) {
		this.inventorysAdd = inventorysAdd;
	}

	public DistributeInventoryItem[] getInventorysSub() {
		return inventorysSub;
	}

	public void setInventorysSub(DistributeInventoryItem[] inventorysSub) {
		this.inventorysSub = inventorysSub;
	}

}
