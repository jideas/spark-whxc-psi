package com.spark.psi.publish.inventory.checkout.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public class RealGoodsCheckOutTask extends SimpleTask {

	private GUID relaBillsId;

	private String relaBillsNo;

	private String remark;

	private GUID storeId;

	private List<RealGoodsCheckOutTaskItem> items;
	
	private CheckingOutType checkingOutType;

	public CheckingOutType getCheckingOutType() {
		return checkingOutType;
	}

	public void setCheckingOutType(CheckingOutType checkingOutType) {
		this.checkingOutType = checkingOutType;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
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

	public List<RealGoodsCheckOutTaskItem> getItems() {
		return items;
	}

	public void setItems(List<RealGoodsCheckOutTaskItem> items) {
		this.items = items;
	}
}
