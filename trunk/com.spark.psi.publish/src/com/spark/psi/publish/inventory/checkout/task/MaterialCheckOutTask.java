package com.spark.psi.publish.inventory.checkout.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 新增材料出库
 */
public class MaterialCheckOutTask extends SimpleTask {
	
	private boolean isReturn;

	public MaterialCheckOutTask(boolean isReturn){
		this.isReturn = isReturn;
	}
	
	private GUID relaBillsId;
	
	private String relaBillsNo;
	
	private String remark;
	
	private GUID storeId;
	
	private List<MaterialCheckOutTaskItem> items;

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public boolean isReturn() {
		return isReturn;
	}

	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public List<MaterialCheckOutTaskItem> getItems() {
		return items;
	}

	public void setItems(List<MaterialCheckOutTaskItem> items) {
		this.items = items;
	}

	
}
