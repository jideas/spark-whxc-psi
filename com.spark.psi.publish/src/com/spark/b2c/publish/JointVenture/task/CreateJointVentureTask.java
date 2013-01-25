package com.spark.b2c.publish.JointVenture.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;

public class CreateJointVentureTask extends SimpleTask {

	private List<JointVentureTaskItem> items;

	public List<JointVentureTaskItem> getItems() {
		return items;
	}

	public CreateJointVentureTask(List<JointVentureTaskItem> items) {
		this.items = items;
	}
}
