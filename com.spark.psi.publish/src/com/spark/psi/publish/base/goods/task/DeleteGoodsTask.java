package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除商品信息（一条或者多条）
 * 
 */
public class DeleteGoodsTask extends SimpleTask {

	/**
	 * 商品id数组
	 */
	private GUID[] ids;

	/**
	 * 构造函数
	 * 
	 * @param ids
	 *            商品id数组
	 */
	public DeleteGoodsTask(GUID... ids) {
		this.ids = ids;
	}

	/**
	 * 
	 * @return
	 */
	public GUID[] getIds() {
		return ids;
	}

}
