package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * <p>������Ϣ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public class WizardDepartmentInfoStepRender extends WizardBaseStepRender{

	/**
	 * �Ƿ��в�����ť
	 */
	protected boolean hasOperateButton(){
		return false;
	}

	/**
	 * ����
	 */
	@Override
	protected String getTitle(){
		return "�鿴��֯�ṹ";
	}

	/**
	 * ������Ϣ��
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		infoArea.showPage("PSI_DepartmentTreePage");
	}

	/**
	 * �ײ���߰�ť��
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){

	}

}
