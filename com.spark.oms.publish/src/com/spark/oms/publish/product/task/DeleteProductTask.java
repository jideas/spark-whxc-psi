package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除产品规格
 */
public class DeleteProductTask extends SimpleTask{
	
	private GUID []id;
	
	/**
	 * 产品Id标识
	 * @return
	 */
	public GUID[] getId() {
		return id;
	}
	
	/**
	 * 设置产品标识
	 * @param id
	 */
	public void setId(GUID ...id) {
		this.id = id;
	}
	
}
