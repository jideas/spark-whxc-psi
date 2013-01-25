package com.spark.order;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.ITaskResult;

public abstract class OrderSimpleTaskFather extends SimpleTask implements ITaskResult{
	protected boolean succeed = true;
	protected int lenght = -1;

	public boolean isSucceed() {
		return succeed;
	}

	public int lenght() {
		return lenght;
	}

	/**
	 * this.succeed = succeed;
	 * @param succeed void
	 */
	@OrderTaskReturn(getParmName = "this.succeed", getReturnName = "succeed")
	protected abstract void setSucceed(boolean succeed);
	/**
	 * this.lenght = lenght;
	 * @param lenght void
	 */
	@OrderTaskReturn(getParmName = "this.lenght", getReturnName = "lenght")
	protected abstract void setLenght(int lenght);
	
}
