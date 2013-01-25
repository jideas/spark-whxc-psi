package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>订单流程SimpleTask</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public class FlowTask extends SimpleTask implements ITaskResult{
	/**
	 * 订单类型
	 */
	public final BillsEnum billsEnum;
	public FlowTask(BillsEnum b){
		this.billsEnum = b;
	}
	/**
	 * 订单GUID
	 */
	public GUID billsRECID;
	/**
	 * 订单原status
	 */
	public String oldstatus;
	/**
	 * 订单新status
	 */
	public String newstatus;
	
	private GUID deptGuid = null;//修改部门GUID,非空时修改
	private GUID examDept = null;//修改销售审批部门，非空且为销售订单时修改
	public GUID getExamDept() {
		return examDept;
	}
	public void setExamDept(GUID examDept) {
		this.examDept = examDept;
	}
	public GUID getDeptGuid() {
		return deptGuid;
	}
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	
	private int lenght;
	
	/**
	 * 设置返回条数
	 * @param lenght void
	 */
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	public boolean isSucceed() {
		if(1 == lenght){
			return true;
		}
		return false;
	}
	public int lenght() {
		return lenght;
	}
}
