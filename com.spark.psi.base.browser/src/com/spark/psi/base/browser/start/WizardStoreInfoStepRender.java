package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SimpleListPageRender;
import com.spark.psi.base.browser.store.StoreListRender;

/**
 * <p>公司仓库界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class WizardStoreInfoStepRender extends WizardBaseStepRender{

	/**
	 * 是否有操作按钮
	 */
	protected boolean hasOperateButton() {
		return false;
	}
	
	/**
	 * 标题
	 */
	@Override
	protected String getTitle(){
		return "配置公司仓库";
	}

	/**
	 * 内容信息区
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		PageController pageController =
		        new PageController(WizardStoreInfoStepProcessor.InnerClass.class,
		        		WizardStoreInfoStepRender.InnerClass.class);
		ControllerPage page =
		        (ControllerPage)infoArea.showPage(ControllerPage.NAME, new PageControllerInstance(pageController));
		controls.put(WizardStoreInfoStepProcessor.ID_Table_Store, ((SimpleListPageRender)page
		        .getRender()).getTable());
	}

	/**
	 * 底部左边按钮区
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout();
		leftButtonArea.setLayout(layout);
		//
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 30;
		//新增仓库
		Button addButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		addButton.setID(WizardStoreInfoStepProcessor.ID_Button_Add);
		addButton.setText("新增仓库");
		addButton.setLayoutData(buttonGridData);
	}
	
	/**
	 * 仓库列表
	 */
	public static class InnerClass extends StoreListRender{
		/**
		 * 隐藏底部区
		 */
		protected boolean hideFooterArea() {
			return true;
		}
	}
}
