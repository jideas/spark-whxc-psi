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
 * <p>��˾�ֿ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-11
 */

public class WizardStoreInfoStepRender extends WizardBaseStepRender{

	/**
	 * �Ƿ��в�����ť
	 */
	protected boolean hasOperateButton() {
		return false;
	}
	
	/**
	 * ����
	 */
	@Override
	protected String getTitle(){
		return "���ù�˾�ֿ�";
	}

	/**
	 * ������Ϣ��
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
	 * �ײ���߰�ť��
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout();
		leftButtonArea.setLayout(layout);
		//
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 30;
		//�����ֿ�
		Button addButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		addButton.setID(WizardStoreInfoStepProcessor.ID_Button_Add);
		addButton.setText("�����ֿ�");
		addButton.setLayoutData(buttonGridData);
	}
	
	/**
	 * �ֿ��б�
	 */
	public static class InnerClass extends StoreListRender{
		/**
		 * ���صײ���
		 */
		protected boolean hideFooterArea() {
			return true;
		}
	}
}
