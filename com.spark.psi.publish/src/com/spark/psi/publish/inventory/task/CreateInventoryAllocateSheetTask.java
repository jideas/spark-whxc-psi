package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������������
 */
public class CreateInventoryAllocateSheetTask extends SimpleTask {

	/**
	 * �����ֿ�ID
	 */
	private GUID sourceStoreId;

	/**
	 * ����ֿ�ID
	 */
	private GUID destinationStoreId;

	/**
	 * ԭ��
	 */
	private String cause;

	/**
	 * ������Ʒ��Ŀ�б�
	 */
	private AllocateStockItem[] items;
	
	private GUID sheetId;

	/**
	 * 
	 * @param sourceStoreId
	 * @param destinationStoreId
	 * @param cause
	 * @param items
	 */
	public CreateInventoryAllocateSheetTask(GUID sourceStoreId,
			GUID destinationStoreId, String cause, AllocateStockItem[] items) {
		super();
		this.sourceStoreId = sourceStoreId;
		this.destinationStoreId = destinationStoreId;
		this.cause = cause;
		this.items = items;
	}

	/**
	 * @return the sourceStoreId
	 */
	public GUID getSourceStoreId() {
		return sourceStoreId;
	}

	/**
	 * @return the destinationStoreId
	 */
	public GUID getDestinationStoreId() {
		return destinationStoreId;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @return the items
	 */
	public AllocateStockItem[] getItems() {
		return items;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	/**
	 * ������Ʒ��Ŀ
	 */
	public final static class AllocateStockItem {

		/**
		 * ��Ʒ��ĿID
		 */
		private GUID goodsItemId;

		/**
		 * ��������
		 */
		private double allocateCount;

		/**
		 * ���캯��
		 * 
		 * @param goodsItemId
		 * @param allocateCount
		 */
		public AllocateStockItem(GUID goodsItemId, double allocateCount) {
			super();
			this.goodsItemId = goodsItemId;
			this.allocateCount = allocateCount;
		}

		/**
		 * @return the goodsItemId
		 */
		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		/**
		 * @return the allocateCount
		 */
		public double getAllocateCount() {
			return allocateCount;
		}

	}
}
