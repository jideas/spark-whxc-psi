package com.spark.psi.publish.inventory.sheet.task;

import com.jiuqi.dna.core.type.GUID;

public class CheckInSheetTaskItem {

	public CheckInSheetTaskItem(GUID id, double checkinCount) {
		this.id = id;
		this.checkinCount = checkinCount;
	}

	private GUID id;
	private double checkinCount; 

	public GUID getId() {
		return id;
	}

	public double getCheckinCount() {
		return checkinCount;
	} 
}