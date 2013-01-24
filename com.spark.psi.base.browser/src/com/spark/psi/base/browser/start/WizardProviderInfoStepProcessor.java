package com.spark.psi.base.browser.start;

import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model;

/**
 * <p>添加客户界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-13
 */

public class WizardProviderInfoStepProcessor extends BatchSaveProcessor{
	
	@Override
	protected Model getModel(){
		return Model.Supplier;
	}

	@Override
	protected String getPageTitle(){
		return "批量添加供应商";
	}

	@Override
	protected String getTemplateFilePath(){
		return "/template/partner.xls"; 
	}
}
