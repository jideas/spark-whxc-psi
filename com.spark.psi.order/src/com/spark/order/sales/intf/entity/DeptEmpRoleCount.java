package com.spark.order.sales.intf.entity;

import com.spark.order.intf.task.ITaskResult;

/**
 * <p>部门员工信息</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-23
 */
public class DeptEmpRoleCount implements ITaskResult{
	private int lenght;
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public boolean isSucceed() {
		return lenght > 0;
	}

	public int lenght() {
		return lenght;
	}
	
}
