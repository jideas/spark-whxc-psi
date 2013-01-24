package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 确认完成<BR>
 * 并发冲突时,throw throwable(msg);
 * 
 */
public class FinishTask extends SimpleTask {

	private GUID id;
	private String sheetNo;

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @param id 生产订单ID
	 */
	public FinishTask(GUID id) {
		super();
		this.id = id;
	}

	private Item[] items;

	public class Item {
		private GUID id;
		private Double count;
		private GUID goodsId;

		public GUID getGoodsId() {
			return goodsId;
		}

		public GUID getId() {
			return id;
		}

		public Double getCount() {
			return count;
		}

		/**
		 * 
		 * @param id 商品明细ID
		 * @param count 本次完成数量
		 * @param goodsId 商品id
		 */
		public Item(GUID id, Double count,GUID goodsId) {
			super();
			this.id = id;
			this.count = count;
			this.goodsId = goodsId;
		}

	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

}
