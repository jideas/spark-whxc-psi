
package com.spark.psi.inventory.intf.task.outstorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.util.outstorage.dbox.OutstoProcess;
/**
 * 
 * <p>出库Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class OutstorageTask extends Task<Method>{

	private Outstorage entity;
	private List<OutstorageItem> list;
	public int count;
	
	private OutstoProcess process;
	
	public OutstorageTask(OutstoProcess ip) {
		this.process = ip;
	}
	public OutstorageTask() {
		// TODO Auto-generated constructor stub
	}
	public List<OutstorageItem> getList() {
		return list;
	}
	public void setList(List<OutstorageItem> list) {
		this.list = list;
	}
	public void setEntity(Outstorage entity) {
		this.entity = entity;
	}
	public Outstorage getEntity() {
		return entity;
	}
	public void setProcess(OutstoProcess process) {
		this.process = process;
	}
	public OutstoProcess getProcess() {
		return process;
	}
	
	

}
