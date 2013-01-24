package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * 网上订单退货生效后，回写退货标识
 *
 */
public class SetOnlineOrderReturnFlagTask extends SimpleTask {

	private GUID id;

	public SetOnlineOrderReturnFlagTask(GUID id) {
		super();
		this.id = id;
	}

	public GUID getId() {
		return id;
	}
	
}
