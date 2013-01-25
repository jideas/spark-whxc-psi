package com.spark.psi.publish.inventory.checkout.task;

public class KitCheckOutTaskItem {

	public KitCheckOutTaskItem(String kitName, String kitDesp, String kitUnit, int count) {
		this.kitName = kitName;
		this.kitDesp = kitDesp;
		this.kitUnit = kitUnit;
		this.count = count;
	}

	private String kitName, kitDesp, kitUnit;
	private int count;

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public String getKitDesp() {
		return kitDesp;
	}

	public void setKitDesp(String kitDesp) {
		this.kitDesp = kitDesp;
	}

	public String getKitUnit() {
		return kitUnit;
	}

	public void setKitUnit(String kitUnit) {
		this.kitUnit = kitUnit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
