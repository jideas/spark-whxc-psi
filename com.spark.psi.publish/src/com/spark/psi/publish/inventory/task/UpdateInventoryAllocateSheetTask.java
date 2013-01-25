package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask.Error;

/**
 * 更新库存调拨单
 */
public class UpdateInventoryAllocateSheetTask extends
		CreateInventoryAllocateSheetTask {

	public enum Error
	{
		EntityError("单据不存在，请检查！"),
		StoreStopError("仓库已停用，请检查！"),
		StoreError("仓库停用或盘点中，请检查！"),
		InventoryCountError("库存数量不够，请检查！"),
		ConcurrentError("有其他人在操作该单据，请检查！");
		
		private String message;
		public String getMessage() {
			return message;
		}
		private Error(String message)
		{
			this.message = message;
		}
	}
	private boolean success;
	private Error[] errors;
	private GUID sheetId;

	public UpdateInventoryAllocateSheetTask(GUID sheetId, GUID sourceStoreId,
			GUID destinationStoreId, String cause, AllocateStockItem[] items) {
		super(sourceStoreId, destinationStoreId, cause, items);
		this.sheetId = sheetId;
	}

	/**
	 * @return the sheetId
	 */
	@Override
	public GUID getSheetId() {
		return sheetId;
	}

	public void setErrors(Error[] errors) {
		this.errors = errors;
	}

	public Error[] getErrors() {
		return errors;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
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

}
