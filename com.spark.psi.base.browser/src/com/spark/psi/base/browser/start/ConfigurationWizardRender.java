package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>��������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-25
 */

public class ConfigurationWizardRender extends BaseFormPageRender{

	/**
	 * �Ƿ��Զ���Form����
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * �������������
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout layout = new GridLayout(3);
		layout.horizontalSpacing = 10;
		layout.marginBottom = 10;
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.marginTop = 20;
		formArea.setLayout(layout);
		//������������
		Composite quickArea = new Composite(formArea);
		GridData quickAreaGridData = new GridData(GridData.FILL_VERTICAL);
		quickAreaGridData.widthHint = 380;
		quickArea.setLayoutData(quickAreaGridData);
		addQuickStartArea(quickArea);
		//������
		Composite separtaor = new Composite(formArea);
		GridData separatorGridData = new GridData(GridData.FILL_VERTICAL);
		separatorGridData.widthHint = 2;
		separtaor.setLayoutData(separatorGridData);
		separtaor.setBackground(Color.COLOR_GRAY);
		//������
		Composite wizardArea = new Composite(formArea);
		wizardArea.setLayoutData(GridData.INS_FILL_BOTH);
		addWizardArea(wizardArea);

	}

	/**
	 * ������������
	 * 
	 * @param container ����
	 */
	private void addQuickStartArea(Composite container){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 10;
		container.setLayout(layout);
		//
		Label titleLabel = new Label(container);
		titleLabel.setText("��������ϵͳ");
		titleLabel.setForeground(getTitleColor());
		titleLabel.setFont(getTitleFont());
		//
		Label remarkLabel = new Label(container);
		remarkLabel.setText("ͨ�����ñ�Ҫ��Ϣ��������ϵͳ");
		remarkLabel.setForeground(Color.COLOR_GRAY);
		remarkLabel.setFont(getWordFont());
		//
		Label quickStartButton = new Label(container, JWT.CENTER | JWT.MIDDLE);
		quickStartButton.setID(ConfigurationWizardProcessor.ID_Button_QuickStart);
		controls.put(ConfigurationWizardProcessor.ID_Button_QuickStart, quickStartButton);
		quickStartButton.setText("��������ϵͳ");
		quickStartButton.setFont(new Font("����", 22, JWT.FONT_STYLE_BOLD));
		quickStartButton.setBackimage(BaseImages.getImage("images/wizard/quick_start_button_enable.png"));
		GridData gridData = new GridData();
		gridData.widthHint = 220;
		gridData.heightHint = 81;
		gridData.horizontalIndent = 60;
		gridData.verticalIndent = 150;
		quickStartButton.setLayoutData(gridData);
	}

	/**
	 * ����������
	 *
	 *@param container ����
	 */
	private void addWizardArea(Composite container){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 20;
		container.setLayout(layout);
		//
		Label titleLabel = new Label(container);
		titleLabel.setText("����������ϵͳ");
		titleLabel.setForeground(getTitleColor());
		titleLabel.setFont(getTitleFont());
		//
		Label remarkLabel = new Label(container);
		remarkLabel.setText("����������ϸ���ø�����Ϣ������ϵͳ");
		remarkLabel.setForeground(Color.COLOR_GRAY);
		remarkLabel.setFont(getWordFont());
		//
		Composite stepArea = new Composite(container);
		GridData stepAreaGridData = new GridData();
		stepAreaGridData.widthHint = 610;
		stepAreaGridData.heightHint = 374;
		stepAreaGridData.grabExcessHorizontalSpace = true;
		stepArea.setLayoutData(stepAreaGridData);
		//
		stepWizardArea(stepArea);
	}
	
	/**
	 * ����������
	 */
	private void stepWizardArea(Composite container){
		//��һ����������ҵ��Ϣ
		Label stepOneIcon = new Label(container);
		stepOneIcon.setID(ConfigurationWizardProcessor.ID_Label_StepOneIcon);
		stepOneIcon.setLocation(280, 5);
		stepOneIcon.setSize(40, 32);
		Label stepOneLabel = new Label(container);
		stepOneLabel.setID(ConfigurationWizardProcessor.ID_Label_StepOneLabel);
		stepOneLabel.setText("1.������ҵ��Ϣ");
		stepOneLabel.setLocation(325, 25);
		stepOneLabel.setSize(150, 22);
		//�ڶ������鿴��֯�ṹ
		Label stepTwoIcon = new Label(container);
		stepTwoIcon.setID(ConfigurationWizardProcessor.ID_Label_StepTwoIcon);
		stepTwoIcon.setLocation(420, 75);
		stepTwoIcon.setSize(34, 38);
		Label stepTwoLabel = new Label(container);
		stepTwoLabel.setID(ConfigurationWizardProcessor.ID_Label_StepTwoLabel);
		stepTwoLabel.setText("2.�鿴��֯�ṹ");
		stepTwoLabel.setLocation(460, 100);
		stepTwoLabel.setSize(150, 22);
		//�����������Ա�����û�
		Label stepThreeIcon = new Label(container);
		stepThreeIcon.setID(ConfigurationWizardProcessor.ID_Label_StepThreeIcon);
		stepThreeIcon.setLocation(450, 155);
		stepThreeIcon.setSize(36, 36);
		Label stepThreeLabel = new Label(container);
		stepThreeLabel.setID(ConfigurationWizardProcessor.ID_Label_StepThreeLabel);
		stepThreeLabel.setText("3.���Ա�����û�");
		stepThreeLabel.setLocation(490, 180);
		stepThreeLabel.setSize(150, 22);
		//���Ĳ�����ӿͻ���Ϣ
		Label stepFourIcon = new Label(container);
		stepFourIcon.setID(ConfigurationWizardProcessor.ID_Label_StepFourIcon);
		stepFourIcon.setLocation(428, 232);
		stepFourIcon.setSize(33, 37);
		Label stepFourLabel = new Label(container);
		stepFourLabel.setID(ConfigurationWizardProcessor.ID_Label_StepFourLabel);
		stepFourLabel.setText("4.��ӿͻ���Ϣ");
		stepFourLabel.setLocation(465, 260);
		stepFourLabel.setSize(150, 22);
		//���岽����ӹ�Ӧ����Ϣ
		Label stepFiveIcon = new Label(container);
		stepFiveIcon.setID(ConfigurationWizardProcessor.ID_Label_StepFiveIcon);
		stepFiveIcon.setLocation(345, 300);
		stepFiveIcon.setSize(39, 35);
		Label stepFiveLabel = new Label(container);
		stepFiveLabel.setID(ConfigurationWizardProcessor.ID_Label_StepFiveLabel);
		stepFiveLabel.setText("5.��ӹ�Ӧ����Ϣ");
		stepFiveLabel.setLocation(390, 320);
		stepFiveLabel.setSize(150, 22);
		//�������������Ʒ����
		Label stepSixIcon = new Label(container);
		stepSixIcon.setID(ConfigurationWizardProcessor.ID_Label_StepSixIcon);
		stepSixIcon.setLocation(218, 300);
		stepSixIcon.setSize(39, 32);
		Label stepSixLabel = new Label(container);
		stepSixLabel.setID(ConfigurationWizardProcessor.ID_Label_StepSixLabel);
		stepSixLabel.setText("6.�����Ʒ����");
		stepSixLabel.setLocation(130, 320);
		stepSixLabel.setSize(150, 22);
		//���߲��������Ʒ��Ϣ
		Label stepSevenIcon = new Label(container);
		stepSevenIcon.setID(ConfigurationWizardProcessor.ID_Label_StepSevenIcon);
		stepSevenIcon.setLocation(130, 237);
		stepSevenIcon.setSize(40, 32);
		Label stepSevenLabel = new Label(container);
		stepSevenLabel.setID(ConfigurationWizardProcessor.ID_Label_StepSevenLabel);
		stepSevenLabel.setText("7.�����Ʒ��Ϣ");
		stepSevenLabel.setLocation(40, 255);
		stepSevenLabel.setSize(150, 22);
		//�ڰ˲������ù�˾�ֿ�
		Label stepEightIcon = new Label(container);
		stepEightIcon.setID(ConfigurationWizardProcessor.ID_Label_StepEightIcon);
		stepEightIcon.setLocation(112, 155);
		stepEightIcon.setSize(40, 39);
		Label stepEightLabel = new Label(container);
		stepEightLabel.setID(ConfigurationWizardProcessor.ID_Label_StepEightLabel);
		stepEightLabel.setText("8.���ù�˾�ֿ�");
		stepEightLabel.setLocation(25, 175);
		stepEightLabel.setSize(150, 22);
		//�ھŲ�������ϵͳ
		Label stepNineIcon = new Label(container);
		stepNineIcon.setID(ConfigurationWizardProcessor.ID_Label_StepNineIcon);
		stepNineIcon.setLocation(150, 80);
		stepNineIcon.setSize(39, 33);
		Label stepNineLabel = new Label(container);
		stepNineLabel.setID(ConfigurationWizardProcessor.ID_Label_StepNineLabel);
		stepNineLabel.setText("9.����ϵͳ");
		stepNineLabel.setLocation(85, 95);
		stepNineLabel.setSize(150, 22);
		
		//����
		Label stepBgLabel = new Label(container);
		stepBgLabel.setLocation(0, 0);
		stepBgLabel.setSize(610, 374);
		stepBgLabel.setImage(BaseImages.getImage("images/wizard/wizard_step_bg.png"));
	}

	/**
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea){

	}
	
	/**
	 * ���Title����
	 */
	private Font getTitleFont(){
		return new Font("����", 16, JWT.FONT_STYLE_BOLD);
	}
	
	/**
	 * ���Title��ɫ
	 */
	private Color getTitleColor(){
		return new Color(0x308082);
	}
	
	/**
	 * �������
	 */
	private Font getWordFont(){
		return new Font("����", 8, JWT.FONT_STYLE_PLAIN);
	}

}
