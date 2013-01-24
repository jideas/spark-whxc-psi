package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * <p>部门信息界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-6
 */

public class WizardDepartmentInfoStepRender extends WizardBaseStepRender{

	/**
	 * 是否有操作按钮
	 */
	protected boolean hasOperateButton(){
		return false;
	}

	/**
	 * 标题
	 */
	@Override
	protected String getTitle(){
		return "查看组织结构";
	}

	/**
	 * 内容信息区
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		infoArea.showPage("PSI_DepartmentTreePage");
	}

	/**
	 * 底部左边按钮区
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){

	}

}
