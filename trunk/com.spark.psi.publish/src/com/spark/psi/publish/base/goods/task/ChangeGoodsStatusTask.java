package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 改变商品信息的状态（一条或者多条）
 * 
 */
public class ChangeGoodsStatusTask extends SimpleTask {

	/**
	 * 商品id数组
	 */
	private GUID[] ids;  

	/**
	 * 停售或者开售
	 */
	private boolean turnOnOrOff;

	/**
	 * 构造函数
	 * 
	 * @param turnOnOrOff
	 *            停售或者开售
	 * @param ids
	 *            商品id数组
	 */
	public ChangeGoodsStatusTask(boolean turnOnOrOff, GUID... ids) {
		this.turnOnOrOff = turnOnOrOff;
		this.ids = ids;
	}

	/**
	 * 
	 * @return
	 */
	public GUID[] getIds() {
		return ids;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTurnOnOrOff() {
		return this.turnOnOrOff;
	}

}
