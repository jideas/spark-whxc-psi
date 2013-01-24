package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;

/**
 * 保存指定仓库的初始化库存列表
 * 
 */
public class SaveInitInventoryTask extends SimpleTask {

	/**
	 * 
	 * <p>校验错误信息</p>
	 *
	 */
	public static enum Error
	{
		StoreError("仓库停用或盘点中，请检查！");
		
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
	
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 是否执行成功
	 * 
	 * @param success void
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 错误信息
	 */
	private Error[] Errors;
	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 是否启用仓库
	 */
	private boolean enable;
	/**
	 * 初始化存货列表
	 */
	private InitInventoryItem[] items;
	
	/**
	 * 构造函数
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
