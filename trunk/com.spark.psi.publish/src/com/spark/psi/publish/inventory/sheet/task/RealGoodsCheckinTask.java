package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class RealGoodsCheckinTask extends SimpleTask {

	private GUID relaBillsId,storeId;
	private String relaBillsNo,storeName,remark;
	private List<RealGoodsCheckinTaskItem> items;
	
	public RealGoodsCheckinTask(){
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RealGoodsCheckinTaskItem> getItems() {
		return items;
	}

	public void setItems(List<RealGoodsCheckinTaskItem> items) {
		this.items = items;
	}
}
