
package com.spark.psi.inventory.intf.task.instorage;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.util.instorage.dbox.InstoProcess;

/**
 * 
 * <p>入库Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public class InstorageTask extends Task<Method>{


	private Instorage instorageEntity;
	
	private InstoProcess process;

	public int count;

	public void setInstorageEntity(Instorage instorageEntity) {
		this.instorageEntity = instorageEntity;
	}

	public InstorageTask(InstoProcess process){
		this.process = process;
	}
	public InstorageTask()
	{}
	public Instorage getInstorageEntity() {
		return instorageEntity;
	}

	public void setProcess(InstoProcess process) {
		this.process = process;
	}

	public InstoProcess getProcess() {
		return process;
	}


}
