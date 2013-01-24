package com.spark.psi.base.browser.start;

import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model;

/**
 * <p>��ӿͻ���Ϣ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-13
 */

public class WizardCustomerInfoStepProcessor extends BatchSaveProcessor{
	
	@Override
    protected Model getModel(){
	    return Model.Customer;
    }

	@Override
    protected String getPageTitle(){
	    return "������ӿͻ�";
    }

	@Override
    protected String getTemplateFilePath(){
	    return "/template/partner.xls"; 
    }
}
