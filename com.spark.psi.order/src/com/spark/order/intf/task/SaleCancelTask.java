/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.intf.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;

/**
 * <p>销售退货Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-10
 */

public class SaleCancelTask extends Task<TaskEnum> implements ITaskResult{
	public int lenght;
	private SaleCancel entity;
	private List<SaleCancelItem> list;

	public void setEntity(SaleCancel entity) {
		this.entity = entity;
	}

	public SaleCancel getEntity() {
		return entity;
	}

	public void setList(List<SaleCancelItem> list) {
		this.list = list;
	}

	public List<SaleCancelItem> getList() {
		return list;
	}

	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}
}
