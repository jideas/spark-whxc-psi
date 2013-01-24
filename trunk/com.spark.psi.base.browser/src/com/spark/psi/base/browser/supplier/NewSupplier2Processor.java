/**
 * 
 */
package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.browser.partner.NewPartner2Processor;
import com.spark.psi.publish.base.partner.task.UpdateSupplierTask;

/**
 * 
 *
 */
public class NewSupplier2Processor extends NewPartner2Processor {

	protected String getPartnerType() {
		return "π©”¶…Ã";
	}
	
	protected GUID handleSave() {
		UpdateSupplierTask task = new UpdateSupplierTask();
		fillTaskData(task);
		getContext().handle(task, UpdateSupplierTask.Method.CREATE);
		return task.getId();
	}

}
