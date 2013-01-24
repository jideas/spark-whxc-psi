
package com.spark.psi.inventory.intf.task.dismounting;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.internal.entity.Dismounting;
import com.spark.psi.inventory.internal.entity.DismountingItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>拆装Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class DismountingTask extends Task<Method> {
	
	
	private Dismounting dismounting;
	private List<DismountingItem> list;
	
	
	public List<DismountingItem> getList() {
		return list;
	}
	public void setList(List<DismountingItem> list) {
		this.list = list;
	}
	public void setDismounting(Dismounting dismounting) {
		this.dismounting = dismounting;
	}
	public Dismounting getDismounting() {
		return dismounting;
	}

	
}
