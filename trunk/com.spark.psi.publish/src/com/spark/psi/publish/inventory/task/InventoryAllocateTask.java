package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ�������̴���
 * 
 */
public class InventoryAllocateTask extends Task<InventoryAllocateTask.Method>{

	/**
	 * 
	 * <p>У�������Ϣ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author Wangtiancai
	 * @version 2012-5-3
	 */
	public static enum Error{
		EntityError("���ݲ����ڣ����飡"),
		StoreStopError("�ֿ���ͣ�ã����飡"),
		StoreError("�ֿ�ͣ�û��̵��У����飡"),
		InventoryCountError("����������������飡"),
		ConcurrentError("���������ڲ����õ��ݣ����飡");

		private String message;

		public String getMessage(){
			return message;
		}

		private Error(String message){
			this.message = message;
		}
	}

	/**
	 * �Ƿ�ɹ�
	 */
	private boolean success;

	public boolean isSuccess(){
		return success;
	}

	/**
	 * �Ƿ�ִ�гɹ�
	 * 
	 * @param success void
	 */
	public void setSuccess(boolean success){
		this.success = success;
	}

	/**
	 * ������Ϣ
	 */
	private Error[] Errors;

	/**
	 * ��������
	 */
	public enum Method{
		Delete,
		Submit,
		Approval,
		Deny,
		AllocateOut,
		AllocateIn;
	}

	/**
	 * ������ID
	 */
	private GUID sheetId;

	/**
	 * �˻�ԭ��
	 */
	private String denyReason;
	
	private Item[] items;

	/**
	 * ���캯��
	 * 
	 * @param sheetId
	 */
	public InventoryAllocateTask(GUID sheetId){
		super();
		this.sheetId = sheetId;
	}

	/**
	 * @return the sheetId
	 */
	public GUID getSheetId(){
		return sheetId;
	}

	public void setErrors(Error[] errors){
		Errors = errors;
	}

	public Error[] getErrors(){
		return Errors;
	}

	public void setDenyReason(String denyReason){
		this.denyReason = denyReason;
	}

	public String getDenyReason(){
		return denyReason;
	}

	public boolean isCurrentError(){
		if(getErrors() != null && getErrors().length > 0){
			for(Error error : getErrors()){
				if(error.equals(Error.ConcurrentError)){
					return true;
				}
			}
		}
		return false;
	}
	

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}



	public class Item {
		private GUID id;
		private GUID shelfId;
		private int shelfNo;
		private int tiersNo;
		private GUID stockId;
		private String stockName;
		private GUID storeId;
		private double count;
		private long produceDate;
		private GUID allocateItemId;
		private GUID sheetId;
		public GUID getId() {
			return id;
		}
		public void setId(GUID id) {
			this.id = id;
		}
		public GUID getShelfId() {
			return shelfId;
		}
		public void setShelfId(GUID shelfId) {
			this.shelfId = shelfId;
		}
		public int getShelfNo() {
			return shelfNo;
		}
		public void setShelfNo(int shelfNo) {
			this.shelfNo = shelfNo;
		}
		public int getTiersNo() {
			return tiersNo;
		}
		public void setTiersNo(int tiersNo) {
			this.tiersNo = tiersNo;
		}
		public GUID getStockId() {
			return stockId;
		}
		public void setStockId(GUID stockId) {
			this.stockId = stockId;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
		public long getProduceDate() {
			return produceDate;
		}
		public void setProduceDate(long produceDate) {
			this.produceDate = produceDate;
		}
		public GUID getAllocateItemId() {
			return allocateItemId;
		}
		public void setAllocateItemId(GUID allocateItemId) {
			this.allocateItemId = allocateItemId;
		}
		public GUID getSheetId() {
			return sheetId;
		}
		public void setSheetId(GUID sheetId) {
			this.sheetId = sheetId;
		}
		public String getStockName() {
			return stockName;
		}
		public void setStockName(String stockName) {
			this.stockName = stockName;
		}
		public GUID getStoreId() {
			return storeId;
		}
		public void setStoreId(GUID storeId) {
			this.storeId = storeId;
		}
		
		
	}
}
