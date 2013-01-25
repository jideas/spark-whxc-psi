package com.spark.psi.publish.deliver.Event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

public class DeliverExceptionOverEvent extends Event {

	private GUID id;

	public DeliverExceptionOverEvent(GUID id) {
		this.id = id;
	}

	public GUID getId() {
		return id;
	}
}
