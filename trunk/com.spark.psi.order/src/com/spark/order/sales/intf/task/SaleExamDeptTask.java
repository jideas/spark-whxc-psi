package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>销售订单更改部门Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public class SaleExamDeptTask extends SimpleTask implements ITaskResult{
	/**
	 * 销售订单GUID
	 */
	public GUID recid;
	/**
	 * 新部门GUID
	 */
	public GUID examDeptGuid;
	/**
	 * 老部门GUID
	 */
	public GUID oldExamDetp;
	/**
	 * 审核信息
	 */
	public String examinStr;
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
