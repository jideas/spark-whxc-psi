package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;

/**
 * ����ָ���ֿ�ĳ�ʼ������б�
 * 
 */
public class SaveInitInventoryTask extends SimpleTask {

	/**
	 * 
	 * <p>У�������Ϣ</p>
	 *
	 */
	public static enum Error
	{
		StoreError("�ֿ�ͣ�û��̵��У����飡");
		
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
	
	public boolean isSuccess() {
		return success;
	}

	/**
	 * �Ƿ�ִ�гɹ�
	 * 
	 * @param success void
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * ������Ϣ
	 */
	private Error[] Errors;
	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * �Ƿ����òֿ�
	 */
	private boolean enable;
	/**
	 * ��ʼ������б�
	 */
	private InitInventoryItem[] items;
	
	/**
	 * ���캯��
	 * 
	 * @param storeId
	 * @param items
	 */
	public SaveInitInventoryTask(GUID storeId, InitInventoryItem[] items) {
		super();
		this.storeId = storeId;
		this.items = items;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the items
	 */
	public InitInventoryItem[] getItems() {
		return items;
	}

	public void setItems(InitInventoryItem[] items) {
		this.items = items;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setErrors(Error[] errors) {
		Errors = errors;
	}

	public Error[] getErrors() {
		return Errors;
	}

}
