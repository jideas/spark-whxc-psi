package com.spark.psi.inventory.intf.task.inventory;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;

public class StoStreamTask extends Task<StoStreamTask.Task>{
	public enum Task{
		add,
		del;
	}
	
	private InventoryLogEntity stoStream;
	private List<InventoryLogEntity> list = new ArrayList<InventoryLogEntity>();

	public void setStoStream(InventoryLogEntity stoStream) {
		this.stoStream = stoStream;
	}

	public InventoryLogEntity getStoStream() {
		return stoStream;
	}

	public void setList(List<InventoryLogEntity> list) {
		this.list = list;
	}

	public List<InventoryLogEntity> getList() {
		return list;
	}
	
	
}
