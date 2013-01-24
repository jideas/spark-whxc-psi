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
 * <p>配置向导视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-25
 */

public class ConfigurationWizardRender extends BaseFormPageRender{

	/**
	 * 是否自定义Form布局
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * 表单区域添加容器
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
		//快速启动区域
		Composite quickArea = new Composite(formArea);
		GridData quickAreaGridData = new GridData(GridData.FILL_VERTICAL);
		quickAreaGridData.widthHint = 380;
		quickArea.setLayoutData(quickAreaGridData);
		addQuickStartArea(quickArea);
		//中竖线
		Composite separtaor = new Composite(formArea);
		GridData separatorGridData = new GridData(GridData.FILL_VERTICAL);
		separatorGridData.widthHint = 2;
		separtaor.setLayoutData(separatorGridData);
		separtaor.setBackground(Color.COLOR_GRAY);
		//配置向导
		Composite wizardArea = new Composite(formArea);
		wizardArea.setLayoutData(GridData.INS_FILL_BOTH);
		addWizardArea(wizardArea);

	}

	/**
	 * 快速启动区域
	 * 
	 * @param container 容器
	 */
	private void addQuickStartArea(Composite container){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 10;
		container.setLayout(layout);
		//
		Label titleLabel = new Label(container);
		titleLabel.setText("快速启用系统");
		titleLabel.setForeground(getTitleColor());
		titleLabel.setFont(getTitleFont());
		//
		Label remarkLabel = new Label(container);
		remarkLabel.setText("通过配置必要信息快速启用系统");
		remarkLabel.setForeground(Color.COLOR_GRAY);
		remarkLabel.setFont(getWordFont());
		//
		Label quickStartButton = new Label(container, JWT.CENTER | JWT.MIDDLE);
		quickStartButton.setID(ConfigurationWizardProcessor.ID_Button_QuickStart);
		controls.put(ConfigurationWizardProcessor.ID_Button_QuickStart, quickStartButton);
		quickStartButton.setText("快速启用系统");
		quickStartButton.setFont(new Font("宋体", 22, JWT.FONT_STYLE_BOLD));
		quickStartButton.setBackimage(BaseImages.getImage("images/wizard/quick_start_button_enable.png"));
		GridData gridData = new GridData();
		gridData.widthHint = 220;
		gridData.heightHint = 81;
		gridData.horizontalIndent = 60;
		gridData.verticalIndent = 150;
		quickStartButton.setLayoutData(gridData);
	}

	/**
	 * 向导配置区域
	 *
	 *@param container 容器
	 */
	private void addWizardArea(Composite container){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 20;
		container.setLayout(layout);
		//
		Label titleLabel = new Label(container);
		titleLabel.setText("按流程配置系统");
		titleLabel.setForeground(getTitleColor());
		titleLabel.setFont(getTitleFont());
		//
		Label remarkLabel = new Label(container);
		remarkLabel.setText("按照流程详细配置各项信息并启用系统");
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
	 * 逐步设置区域
	 */
	private void stepWizardArea(Composite container){
		//第一步　配置企业信息
		Label stepOneIcon = new Label(container);
		stepOneIcon.setID(ConfigurationWizardProcessor.ID_Label_StepOneIcon);
		stepOneIcon.setLocation(280, 5);
		stepOneIcon.setSize(40, 32);
		Label stepOneLabel = new Label(container);
		stepOneLabel.setID(ConfigurationWizardProcessor.ID_Label_StepOneLabel);
		stepOneLabel.setText("1.配置企业信息");
		stepOneLabel.setLocation(325, 25);
		stepOneLabel.setSize(150, 22);
		//第二步　查看组织结构
		Label stepTwoIcon = new Label(container);
		stepTwoIcon.setID(ConfigurationWizardProcessor.ID_Label_StepTwoIcon);
		stepTwoIcon.setLocation(420, 75);
		stepTwoIcon.setSize(34, 38);
		Label stepTwoLabel = new Label(container);
		stepTwoLabel.setID(ConfigurationWizardProcessor.ID_Label_StepTwoLabel);
		stepTwoLabel.setText("2.查看组织结构");
		stepTwoLabel.setLocation(460, 100);
		stepTwoLabel.setSize(150, 22);
		//第三步　添加员工和用户
		Label stepThreeIcon = new Label(container);
		stepThreeIcon.setID(ConfigurationWizardProcessor.ID_Label_StepThreeIcon);
		stepThreeIcon.setLocation(450, 155);
		stepThreeIcon.setSize(36, 36);
		Label stepThreeLabel = new Label(container);
		stepThreeLabel.setID(ConfigurationWizardProcessor.ID_Label_StepThreeLabel);
		stepThreeLabel.setText("3.添加员工和用户");
		stepThreeLabel.setLocation(490, 180);
		stepThreeLabel.setSize(150, 22);
		//第四步　添加客户信息
		Label stepFourIcon = new Label(container);
		stepFourIcon.setID(ConfigurationWizardProcessor.ID_Label_StepFourIcon);
		stepFourIcon.setLocation(428, 232);
		stepFourIcon.setSize(33, 37);
		Label stepFourLabel = new Label(container);
		stepFourLabel.setID(ConfigurationWizardProcessor.ID_Label_StepFourLabel);
		stepFourLabel.setText("4.添加客户信息");
		stepFourLabel.setLocation(465, 260);
		stepFourLabel.setSize(150, 22);
		//第五步　添加供应商信息
		Label stepFiveIcon = new Label(container);
		stepFiveIcon.setID(ConfigurationWizardProcessor.ID_Label_StepFiveIcon);
		stepFiveIcon.setLocation(345, 300);
		stepFiveIcon.setSize(39, 35);
		Label stepFiveLabel = new Label(container);
		stepFiveLabel.setID(ConfigurationWizardProcessor.ID_Label_StepFiveLabel);
		stepFiveLabel.setText("5.添加供应商信息");
		stepFiveLabel.setLocation(390, 320);
		stepFiveLabel.setSize(150, 22);
		//第六步　添加商品分类
		Label stepSixIcon = new Label(container);
		stepSixIcon.setID(ConfigurationWizardProcessor.ID_Label_StepSixIcon);
		stepSixIcon.setLocation(218, 300);
		stepSixIcon.setSize(39, 32);
		Label stepSixLabel = new Label(container);
		stepSixLabel.setID(ConfigurationWizardProcessor.ID_Label_StepSixLabel);
		stepSixLabel.setText("6.添加商品分类");
		stepSixLabel.setLocation(130, 320);
		stepSixLabel.setSize(150, 22);
		//第七步　添加商品信息
		Label stepSevenIcon = new Label(container);
		stepSevenIcon.setID(ConfigurationWizardProcessor.ID_Label_StepSevenIcon);
		stepSevenIcon.setLocation(130, 237);
		stepSevenIcon.setSize(40, 32);
		Label stepSevenLabel = new Label(container);
		stepSevenLabel.setID(ConfigurationWizardProcessor.ID_Label_StepSevenLabel);
		stepSevenLabel.setText("7.添加商品信息");
		stepSevenLabel.setLocation(40, 255);
		stepSevenLabel.setSize(150, 22);
		//第八步　配置公司仓库
		Label stepEightIcon = new Label(container);
		stepEightIcon.setID(ConfigurationWizardProcessor.ID_Label_StepEightIcon);
		stepEightIcon.setLocation(112, 155);
		stepEightIcon.setSize(40, 39);
		Label stepEightLabel = new Label(container);
		stepEightLabel.setID(ConfigurationWizardProcessor.ID_Label_StepEightLabel);
		stepEightLabel.setText("8.配置公司仓库");
		stepEightLabel.setLocation(25, 175);
		stepEightLabel.setSize(150, 22);
		//第九步　启用系统
		Label stepNineIcon = new Label(container);
		stepNineIcon.setID(ConfigurationWizardProcessor.ID_Label_StepNineIcon);
		stepNineIcon.setLocation(150, 80);
		stepNineIcon.setSize(39, 33);
		Label stepNineLabel = new Label(container);
		stepNineLabel.setID(ConfigurationWizardProcessor.ID_Label_StepNineLabel);
		stepNineLabel.setText("9.启用系统");
		stepNineLabel.setLocation(85, 95);
		stepNineLabel.setSize(150, 22);
		
		//背景
		Label stepBgLabel = new Label(container);
		stepBgLabel.setLocation(0, 0);
		stepBgLabel.setSize(610, 374);
		stepBgLabel.setImage(BaseImages.getImage("images/wizard/wizard_step_bg.png"));
	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){

	}
	
	/**
	 * 获得Title字体
	 */
	private Font getTitleFont(){
		return new Font("宋体", 16, JWT.FONT_STYLE_BOLD);
	}
	
	/**
	 * 获得Title颜色
	 */
	private Color getTitleColor(){
		return new Color(0x308082);
	}
	
	/**
	 * 获得字体
	 */
	private Font getWordFont(){
		return new Font("宋体", 8, JWT.FONT_STYLE_PLAIN);
	}

}
