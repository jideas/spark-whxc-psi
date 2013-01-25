package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>修改预计出库日期</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-28
 */
public class SalePlanOutDateTask extends SimpleTask implements ITaskResult{
	private long planDate;//预计出库日期
	private final GUID recid;
	
	public SalePlanOutDateTask(GUID recid) {
		this.recid = recid;
	}

	public SalePlanOutDateTask( GUID recid, long planDate) {
		this.planDate = planDate;
		this.recid = recid;
	}

	public GUID getRecid() {
		return recid;
	}
	
	public void setPlanDate(long planDate) {
		this.planDate = planDate;
	}
	
	public long getPlanDate() {
		return planDate;
	}

	private int lenght;
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}

}
