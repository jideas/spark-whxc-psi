package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * <p>��������ϸ���ε���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-24
 */

public class ApprovalingSheetDetailRender extends AllocateSheetDetailBaseRender{

	/**
	 * ҳ��ײ��ұ߰�ť��
	 */
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea){
		super.renderSheetButtonArea(sheetButtonArea);
		//��׼����
		Button approvalButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		approvalButton.setID(ApprovalingSheetDetailProcesser.ID_Button_Approval);
		approvalButton.setText("��׼����");
		approvalButton.setLayoutData(buttonGridData);
		//�˻�����
		Button denyButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		denyButton.setID(ApprovalingSheetDetailProcesser.ID_Button_Deny);
		denyButton.setText("�˻�����");
		denyButton.setLayoutData(buttonGridData);
	}
}
