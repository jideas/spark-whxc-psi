package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.browser.partner.NewPartner2Processor;
import com.spark.psi.publish.base.partner.task.UpdateCustomerTask;

/**
 * ���������ͻ�������
 */
public class NewCustomer2Processor extends NewPartner2Processor {

	protected String getPartnerType() {
		return "�ͻ�";
	}
	
	
	protected GUID handleSave() {
		UpdateCustomerTask task = new UpdateCustomerTask();
		fillTaskData(task);
		getContext().handle(task, UpdateCustomerTask.Method.CREATE);
		return task.getId();
	}
	
}
