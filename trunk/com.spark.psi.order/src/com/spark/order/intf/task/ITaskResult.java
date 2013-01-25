package com.spark.order.intf.task;

/**
 * <p>Task执行返回结果</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-11
 */
public interface ITaskResult {
	/**
	 * 执行返回条数。-1表示返回值无效
	 * @return int
	 */
	public int lenght();
	/**
	 * 执行是否成功
	 * @return boolean
	 */
	public boolean isSucceed();
}
