package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * ����̵�
 */
public class InventoryCountTask extends Task<InventoryCountTask.Method> {

	/**
	 * ��������
	 */
	public enum Method {
		Insert, Modify, Finish
	}

	/**
	 * 
	 * <p>
	 * ������Ϣ
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author Wangtiancai
	 * @version 2012-6-26
	 */
	public enum Error {
		ConcurrentError("���������ڲ����õ��ݣ����飡");

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
	 * �̵㵥ID
	 */
	private GUID sheetId;

	private DistributeInventoryItem[] inventorysAdd;
	private DistributeInventoryItem[] inventorysSub;

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	/**
	 * ��Ʒ����̵���Ŀ�б�
	 */
	private TaskGoodsCountItem[] TaskGoodsCountItems;

	/**
	 * ��������̵���Ŀ�б�
	 */
	private TaskKitCountItem[] TaskKitCountItems;
	/**
	 * �ֿ�ID
	 */
	private GUID storeId;
	/**
	 * �̵�������
	 */
	private String name;

	/**
	 * �̵��������(��Ʒ/��Ʒ)
	 */
	private InventoryCountType type;
	/**
	 * ��ע
	 */
	private String memo;

	/**
	 * ���캯������Ʒ����̵㣩
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
	 * ���캯������������̵㣩
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
	 * ��Ʒ����̵���
	 */
	public final static class TaskGoodsCountItem {

		/**
		 * ��Ʒ��ĿID
		 */
		private GUID goodsItemId;

		/**
		 * ʵ������
		 */
		private double actualCount;
		/**
		 * ˵��
		 */
		private String memo;

		public String getRemark() {
			return memo;
		}

		/**
		 * ���캯��
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
	 * ��������̵���
	 */
	public final static class TaskKitCountItem {

		/**
		 * ��Ʒ����
		 */
		private String kitName;

		/**
		 * ��Ʒ����
		 */
		private String kitDesc;

		/**
		 * ��Ʒ��λ
		 */
		private String kitUnit;

		/**
		 * ʵ������
		 */
		private double actualCount;

		/**
		 * ˵��
		 */
		private String explain;

		public String getExplain() {
			return explain;
		}

		/**
		 * ���캯��
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
