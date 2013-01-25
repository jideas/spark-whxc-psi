package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask.Error;

/**
 * ���¿�������
 */
public class UpdateInventoryAllocateSheetTask extends
		CreateInventoryAllocateSheetTask {

	public enum Error
	{
		EntityError("���ݲ����ڣ����飡"),
		StoreStopError("�ֿ���ͣ�ã����飡"),
		StoreError("�ֿ�ͣ�û��̵��У����飡"),
		InventoryCountError("����������������飡"),
		ConcurrentError("���������ڲ����õ��ݣ����飡");
		
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
