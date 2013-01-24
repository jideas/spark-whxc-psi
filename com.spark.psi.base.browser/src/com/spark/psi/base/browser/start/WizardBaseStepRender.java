package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.utils.character.CheckIsNull;

/**
 * <p>�����ò������������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public abstract class WizardBaseStepRender extends BaseFormPageRender{
	
	/**������ť*/
	protected Button operateButton;
	
	/**
	 * �Ƿ��Զ���Form����
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * �Ƿ��Զ��尴ť����
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout(){
		return true;
	}
	
	/**
	 * �Ƿ��в�����ť
	 */
	protected boolean hasOperateButton() {
		return true;
	}

	/**
	 * �������������
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout layout = new GridLayout();
		formArea.setLayout(layout);
		//�򵼲���
		Composite stepArea = new Composite(formArea);
		stepArea.setLayout(new GridLayout(3));
		//
		Label wizardLabel = new Label(stepArea);
		wizardLabel.setText("���ò���");
		wizardLabel.setForeground(getWizardStepColor());
		wizardLabel.setHoverForeground(getWizardStepHoverColor());
		wizardLabel.setID(WizardBaseStepProcessor.ID_Label_Wizard);
		wizardLabel.setCursor(Cursor.HAND);
		//
		Label separatorLabel = new Label(stepArea);
		separatorLabel.setText(">");
		separatorLabel.setFont(new Font("����", 9, JWT.FONT_STYLE_BOLD));
		//
		Label curPageLabel = new Label(stepArea);
		curPageLabel.setText(getTitle());
		//�л���
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//
		Composite infoArea = new Composite(formArea);
		infoArea.setLayoutData(GridData.INS_FILL_BOTH);
		addInfoArea(infoArea);
	}
	
	/**����򵼲������*/
	protected abstract String getTitle();
	
	/**
	 * �����Ϣ����
	 */
	protected abstract void addInfoArea(Composite infoArea);
	
	/**
	 * ������ť����(Ĭ��Ϊ����)
	 */
	protected String getButtonTitle(){
		return "����";
	}
	
	/**
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		WizardStepNode node = (WizardStepNode)this.getArgument();
		//
		GridLayout layout = new GridLayout(4);
		layout.marginTop = 5;
		buttonArea.setLayout(layout);
		//
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 30;
		//������
		Composite leftButtonArea = new Composite(buttonArea);
		leftButtonArea.setLayoutData(GridData.INS_FILL_BOTH);
		renderLeftButtonArea(leftButtonArea);
		//������ť
		if(hasOperateButton()){
			operateButton = new Button(buttonArea, JWT.APPEARANCE3);
			operateButton.setID(WizardBaseStepProcessor.ID_Button_Operate);
			operateButton.setText(getButtonTitle());
			operateButton.setLayoutData(buttonGridData);
		}
		//��һҳ��Ϊ��
		if(CheckIsNull.isNotEmpty(node.getPrevPageName())){
			Button prevButton = new Button(buttonArea, JWT.APPEARANCE3);
			prevButton.setID(WizardBaseStepProcessor.ID_Button_Prev);
			prevButton.setText("��һ��");
			prevButton.setLayoutData(buttonGridData);
		}
		//��һҳ��Ϊ��
		if(CheckIsNull.isNotEmpty(node.getNextPageName())){
			Button nextButton = new Button(buttonArea, JWT.APPEARANCE3);
			nextButton.setID(WizardBaseStepProcessor.ID_Button_Next);
			nextButton.setText("��һ��");
			nextButton.setLayoutData(buttonGridData);
		}
	}
	
	/**
	 * �󲿲�����ť
	 */
	protected abstract void renderLeftButtonArea(Composite leftButtonArea);
	
	/**
	 * ������ò������ɫ
	 */
	protected Color getWizardStepColor(){
		return new Color(0x4F56E4);
	}

	/**
	 * ������ò����Hover��ɫ
	 */
	protected Color getWizardStepHoverColor(){
		return new Color(0xDF8A18);
	}
}
