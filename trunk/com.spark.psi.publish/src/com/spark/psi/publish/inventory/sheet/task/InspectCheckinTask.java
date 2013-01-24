package com.spark.psi.publish.inventory.sheet.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class InspectCheckinTask extends SimpleTask {

	private boolean receipt;

	public InspectCheckinTask(boolean isReceipt) {
		receipt = isReceipt;
	}

	private List<InspectItem> items;

	private boolean success;

	public boolean isReceipt() {
		return receipt;
	}

	public void setReceipt(boolean receipt) {
		this.receipt = receipt;
	}

	public List<InspectItem> getItems() {
		return items;
	}

	public void setItems(List<InspectItem> items) {
		this.items = items;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public class InspectItem {

		private double oldInspectCount;
		private GUID id;
		private double checkinCount;

		public InspectItem(GUID id, double checkinCount, double oldInspectCount) {
			this.id = id;
			this.checkinCount = checkinCount;
			this.oldInspectCount = oldInspectCount;
		}

		public double getOldInspectCount() {
			return oldInspectCount;
		}

		public void setOldInspectCount(double oldInspectCount) {
			this.oldInspectCount = oldInspectCount;
		}

		public GUID getId() {
			return id;
		}

		public double getCheckinCount() {
			return checkinCount;
		}
	}

}
