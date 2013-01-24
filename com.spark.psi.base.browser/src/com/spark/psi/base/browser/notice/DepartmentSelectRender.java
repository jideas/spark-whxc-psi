package com.spark.psi.base.browser.notice;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Tree;
import com.spark.common.components.pages.AbstractBoxPageRender;

/**
 * <p>部门选择视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-23
 */
public class DepartmentSelectRender extends AbstractBoxPageRender {

	/**
	 * 内容区
	 */
	@Override
	protected void beforeFooterRender() {
		Tree tree = new Tree(contentArea, JWT.CHECK);
		tree.setID(DepartmentSelectProcessor.ID_TREE);
		tree.setLayoutData(GridData.INS_FILL_BOTH);
	}

	/**
	 * 底部
	 */
	@Override
	protected void afterFooterRender() {
		footerArea.setLayout(new GridLayout(3));
		Label label = new Label(footerArea);
		label.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button finishButton = new Button(footerArea, JWT.APPEARANCE3);
		finishButton.setText("完成选择");
		finishButton.setID(DepartmentSelectProcessor.ID_FINISH_SELECTED_BUTTON);
		Button cancelButton = new Button(footerArea, JWT.APPEARANCE3);
		cancelButton.setText("放弃选择");
		cancelButton.setID(DepartmentSelectProcessor.ID_CANCEL_SELECT_BUTTON);
	}
}