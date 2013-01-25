package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ��װ
 */
public class GoodsRefactorTask extends SimpleTask {

	/**
	 * 
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * ��װԴ
	 */
	private SourceItem[] sourceItems;

	/**
	 * Ŀ��
	 */
	private DestinationItem[] destinationItems;
	
	/**
	 * 
	 * <p>У�������Ϣ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author Wangtiancai
	 * @version 2012-5-3
	 */
	public static enum Error
	{
		StoreError("�ֿ�ͣ�û��̵��У����飡"),
		InventoryCountError("����������������飡");
		
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
	 * �Ƿ�ɹ�
	 */
	private boolean success;
	/**
	 * ������Ϣ
	 */
	private Error[] errors;

	/**
	 * ���캯��
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
	 * ��װԴ��Ʒ��Ŀ��Ϣ
	 */
	public final static class SourceItem {
		/**
		 * ��Ʒ��ĿID
		 */
		private GUID goodsItemId;

		/**
		 * ����
		 */
		private double count;
		
		/**
		 * ƽ�����ɱ�
		 */
		private double averageCost;

		/**
		 * ���캯��
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
	 * ��װԴ��Ʒ��Ŀ��Ϣ
	 */
	public final static class DestinationItem {
		/**
		 * ��Ʒ��ĿID
		 */
		private GUID goodsItemId;

		/**
		 * ����
		 */
		private double count;

		/**
		 * �۸�
		 */
		private double price;

		/**
		 * ���캯��
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
