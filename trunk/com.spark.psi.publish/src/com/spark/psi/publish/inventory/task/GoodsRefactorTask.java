package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 商品拆装
 */
public class GoodsRefactorTask extends SimpleTask {

	/**
	 * 
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 拆装源
	 */
	private SourceItem[] sourceItems;

	/**
	 * 目标
	 */
	private DestinationItem[] destinationItems;
	
	/**
	 * 
	 * <p>校验错误信息</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author Wangtiancai
	 * @version 2012-5-3
	 */
	public static enum Error
	{
		StoreError("仓库停用或盘点中，请检查！"),
		InventoryCountError("库存数量不够，请检查！");
		
		private String message;
		public String getMessage() {
			return message;
		}
		private Error(String message)
		{
			this.message = message;
		}
	}
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 错误信息
	 */
	private Error[] errors;

	/**
	 * 构造函数
	 * 
	 * @param storeId
	 * @param sourceItems
	 * @param destinationItems
	 */
	public GoodsRefactorTask(GUID storeId, SourceItem[] sourceItems,
			DestinationItem[] destinationItems) {
		super();
		this.storeId = storeId;
		this.sourceItems = sourceItems;
		this.destinationItems = destinationItems;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the sourceItems
	 */
	public SourceItem[] getSourceItems() {
		return sourceItems;
	}

	/**
	 * @return the destinationItems
	 */
	public DestinationItem[] getDestinationItems() {
		return destinationItems;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setErrors(Error[] errors) {
		this.errors = errors;
	}

	public Error[] getErrors() {
		return errors;
	}

	/**
	 * 拆装源商品条目信息
	 */
	public final static class SourceItem {
		/**
		 * 商品条目ID
		 */
		private GUID goodsItemId;

		/**
		 * 数量
		 */
		private double count;
		
		/**
		 * 平均库存成本
		 */
		private double averageCost;

		/**
		 * 构造函数
		 * 
		 * @param goodsItemId
		 * @param count
		 */
		public SourceItem(GUID goodsItemId, double count, double averageCost) {
			super();
			this.goodsItemId = goodsItemId;
			this.count = count;
			this.averageCost = averageCost;
		}

		/**
		 * @return the goodsItemId
		 */
		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}

		public double getAverageCost(){
        	return averageCost;
        }
	}

	/**
	 * 拆装源商品条目信息
	 */
	public final static class DestinationItem {
		/**
		 * 商品条目ID
		 */
		private GUID goodsItemId;

		/**
		 * 数量
		 */
		private double count;

		/**
		 * 价格
		 */
		private double price;

		/**
		 * 构造函数
		 * 
		 * @param goodsItemId
		 * @param count
		 * @param price
		 */
		public DestinationItem(GUID goodsItemId, double count, double price) {
			super();
			this.goodsItemId = goodsItemId;
			this.count = count;
			this.price = price;
		}

		/**
		 * @return the goodsItemId
		 */
		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}

		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}

	}

}
