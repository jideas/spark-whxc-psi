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
 * <p>向导配置步骤基础界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-6
 */

public abstract class WizardBaseStepRender extends BaseFormPageRender{
	
	/**操作按钮*/
	protected Button operateButton;
	
	/**
	 * 是否自定义Form布局
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * 是否自定义按钮布局
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout(){
		return true;
	}
	
	/**
	 * 是否有操作按钮
	 */
	protected boolean hasOperateButton() {
		return true;
	}

	/**
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout layout = new GridLayout();
		formArea.setLayout(layout);
		//向导步骤
		Composite stepArea = new Composite(formArea);
		stepArea.setLayout(new GridLayout(3));
		//
		Label wizardLabel = new Label(stepArea);
		wizardLabel.setText("配置步骤");
		wizardLabel.setForeground(getWizardStepColor());
		wizardLabel.setHoverForeground(getWizardStepHoverColor());
		wizardLabel.setID(WizardBaseStepProcessor.ID_Label_Wizard);
		wizardLabel.setCursor(Cursor.HAND);
		//
		Label separatorLabel = new Label(stepArea);
		separatorLabel.setText(">");
		separatorLabel.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		//
		Label curPageLabel = new Label(stepArea);
		curPageLabel.setText(getTitle());
		//中划线
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//
		Composite infoArea = new Composite(formArea);
		infoArea.setLayoutData(GridData.INS_FILL_BOTH);
		addInfoArea(infoArea);
	}
	
	/**获得向导步骤标题*/
	protected abstract String getTitle();
	
	/**
	 * 添加信息区域
	 */
	protected abstract void addInfoArea(Composite infoArea);
	
	/**
	 * 操作按钮名称(默认为保存)
	 */
	protected String getButtonTitle(){
		return "保存";
	}
	
	/**
	 * 操作按钮
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
		//左部区域
		Composite leftButtonArea = new Composite(buttonArea);
		leftButtonArea.setLayoutData(GridData.INS_FILL_BOTH);
		renderLeftButtonArea(leftButtonArea);
		//操作按钮
		if(hasOperateButton()){
			operateButton = new Button(buttonArea, JWT.APPEARANCE3);
			operateButton.setID(WizardBaseStepProcessor.ID_Button_Operate);
			operateButton.setText(getButtonTitle());
			operateButton.setLayoutData(buttonGridData);
		}
		//上一页不为空
		if(CheckIsNull.isNotEmpty(node.getPrevPageName())){
			Button prevButton = new Button(buttonArea, JWT.APPEARANCE3);
			prevButton.setID(WizardBaseStepProcessor.ID_Button_Prev);
			prevButton.setText("上一步");
			prevButton.setLayoutData(buttonGridData);
		}
		//下一页不为空
		if(CheckIsNull.isNotEmpty(node.getNextPageName())){
			Button nextButton = new Button(buttonArea, JWT.APPEARANCE3);
			nextButton.setID(WizardBaseStepProcessor.ID_Button_Next);
			nextButton.setText("下一步");
			nextButton.setLayoutData(buttonGridData);
		}
	}
	
	/**
	 * 左部操作按钮
	 */
	protected abstract void renderLeftButtonArea(Composite leftButtonArea);
	
	/**
	 * 获得配置步骤的颜色
	 */
	protected Color getWizardStepColor(){
		return new Color(0x4F56E4);
	}

	/**
	 * 获得配置步骤的Hover颜色
	 */
	protected Color getWizardStepHoverColor(){
		return new Color(0xDF8A18);
	}
}
