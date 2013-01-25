package com.spark.psi.publish.inventory.sheet.task;

public class CheckInKitTaskItem {

	public CheckInKitTaskItem(String name, String description, String unit, double count) {
		this.name = name;
		this.description = description;
		this.unit = unit;
		this.count = count;
	}

	private String name, description, unit;
	private double count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
