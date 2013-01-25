package com.spark.order;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>订单Task父类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-27
 */
public abstract class OrderTaskFather<T extends Enum<T>> extends Task<T> implements ITaskResult{
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
