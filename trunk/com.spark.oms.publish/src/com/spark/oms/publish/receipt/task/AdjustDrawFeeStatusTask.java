package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * È·¶¨¿ªÆ±
 * @author mengyongfeng
 *
 */
public class AdjustDrawFeeStatusTask  extends SimpleTask{
	
	public GUID id[];

	public GUID[] getId() {
		return id;
	}

	public void setId(GUID[] id) {
		this.id = id;
	}


	

}
