package com.spark.psi.base.browser.start;

import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model;

/**
 * <p>添加客户信息界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-13
 */

public class WizardCustomerInfoStepProcessor extends BatchSaveProcessor{
	
	@Override
    protected Model getModel(){
	    return Model.Customer;
    }

	@Override
    protected String getPageTitle(){
	    return "批量添加客户";
    }

	@Override
    protected String getTemplateFilePath(){
	    return "/template/partner.xls"; 
    }
}
