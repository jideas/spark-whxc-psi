package com.spark.psi.publish.inventory.checkout.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class KitCheckOutTask extends SimpleTask {

	private GUID storeId;
	private String goodsFrom, goodsUse, takePerson, takeUnit, VoucherNumber, remark;

	private List<KitCheckOutTaskItem> items;

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	public String getGoodsUse() {
		return goodsUse;
	}

	public void setGoodsUse(String goodsUse) {
		this.goodsUse = goodsUse;
	}

	public String getTakePerson() {
		return takePerson;
	}

	public void setTakePerson(String takePerson) {
		this.takePerson = takePerson;
	}

	public String getTakeUnit() {
		return takeUnit;
	}

	public void setTakeUnit(String takeUnit) {
		this.takeUnit = takeUnit;
	}

	public String getVoucherNumber() {
		return VoucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		VoucherNumber = voucherNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<KitCheckOutTaskItem> getItems() {
		return items;
	}

	public void setItems(List<KitCheckOutTaskItem> items) {
		this.items = items;
	}

}
