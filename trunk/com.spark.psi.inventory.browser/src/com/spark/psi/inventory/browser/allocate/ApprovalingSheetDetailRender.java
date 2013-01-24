package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * <p>待审批详细调拔单视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-24
 */

public class ApprovalingSheetDetailRender extends AllocateSheetDetailBaseRender{

	/**
	 * 页面底部右边按钮区
	 */
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea){
		super.renderSheetButtonArea(sheetButtonArea);
		//批准申请
		Button approvalButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		approvalButton.setID(ApprovalingSheetDetailProcesser.ID_Button_Approval);
		approvalButton.setText("批准申请");
		approvalButton.setLayoutData(buttonGridData);
		//退回申请
		Button denyButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		denyButton.setID(ApprovalingSheetDetailProcesser.ID_Button_Deny);
		denyButton.setText("退回申请");
		denyButton.setLayoutData(buttonGridData);
	}
}
