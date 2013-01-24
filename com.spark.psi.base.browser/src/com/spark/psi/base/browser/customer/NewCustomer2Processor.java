package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.browser.partner.NewPartner2Processor;
import com.spark.psi.publish.base.partner.task.UpdateCustomerTask;

/**
 * 快速新增客户处理器
 */
public class NewCustomer2Processor extends NewPartner2Processor {

	protected String getPartnerType() {
		return "客户";
	}
	
	
	protected GUID handleSave() {
		UpdateCustomerTask task = new UpdateCustomerTask();
		fillTaskData(task);
		getContext().handle(task, UpdateCustomerTask.Method.CREATE);
		return task.getId();
	}
	
}
