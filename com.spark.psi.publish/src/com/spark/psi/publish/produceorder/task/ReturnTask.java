package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * ����
 * ������ͻʱ,throw throwable(msg);
 */
public class ReturnTask extends SimpleTask {
	private GUID id;

	private String sheetNo;
	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @param id ��������ID
	 */
	public ReturnTask(GUID id) {
		super();
		this.id = id;
	}
	private Item[] items;
	
	public class Item
	{
		private GUID id;
		private Double count;
		private GUID storeId;
		private GUID materialId;
		public GUID getMaterialId() {
			return materialId;
		}
		public GUID getStoreId() {
			return storeId;
		}
		public GUID getId() {
			return id;
		}
		public Double getCount() {
			return count;
		}
		/**
		 * 
		 * @param id ������ϸID
		 * @param count ������������
		 * @param storeId id �ֿ�ID
		 * @param materialId id ����ID
		 */
		public Item(GUID id, Double count,GUID storeId,GUID materialId) {
			super();
			this.id = id;
			this.count = count;
			this.storeId = storeId;
			this.materialId = materialId;
		}
		
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getSheetNo() {
		return sheetNo;
	}

}
