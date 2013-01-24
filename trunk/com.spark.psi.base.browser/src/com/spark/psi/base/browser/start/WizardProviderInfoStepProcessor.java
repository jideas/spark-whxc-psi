package com.spark.psi.base.browser.start;

import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model;

/**
 * <p>��ӿͻ����洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-13
 */

public class WizardProviderInfoStepProcessor extends BatchSaveProcessor{
	
	@Override
	protected Model getModel(){
		return Model.Supplier;
	}

	@Override
	protected String getPageTitle(){
		return "������ӹ�Ӧ��";
	}

	@Override
	protected String getTemplateFilePath(){
		return "/template/partner.xls"; 
	}
}
