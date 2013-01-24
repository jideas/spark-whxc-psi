package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>欢迎界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-26
 */

public class WelcomeRender extends BaseFormPageRender{

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
	 * 是否隐藏底部
	 */
	protected boolean hideFooterArea(){
		return true;
	}

	/**
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		//
		Composite infoArea = new Composite(formArea);
		infoArea.setLocation(220, 210);
		infoArea.setSize(340, 100);
		//添加内容区域
		createInfoArea(infoArea);
		//添加背景
		Label backImageLabel = new Label(formArea);
		backImageLabel.setImage(BaseImages.getImage("images/wizard/wizard_welcome_bg.png"));
		backImageLabel.setLocation(0, 0);
		backImageLabel.setSize(614, 384);
	}

	/**
	 * 创建内容区域
	 */
	private void createInfoArea(Composite container){
		container.setLayout(new GridLayout(4));
		//第一行
		Label chooseLabel = new Label(container);
		chooseLabel.setText("您可以：");
		chooseLabel.setForeground(getWordColor());
		// 
		Button selfButton = new Button(container, JWT.RADIO);
		selfButton.setID(WelcomeProcessor.ID_Button_Self);
		selfButton.setText("自已来完成");
		selfButton.setForeground(getWordColor());
		GridData selfGridData = new GridData();
		selfGridData.horizontalSpan = 3;
		selfButton.setLayoutData(selfGridData);
		//第二行
		new Label(container);
		Button assistantButton = new Button(container, JWT.RADIO);
		assistantButton.setID(WelcomeProcessor.ID_Button_Assistant);
		assistantButton.setText("指派助理完成");
		assistantButton.setForeground(getWordColor());
		GridData assistantGridData = new GridData();
		assistantGridData.horizontalSpan = 3;
		assistantButton.setLayoutData(assistantGridData);
		//第三行
		new Label(container);
		Label nameLabel = new Label(container);
		nameLabel.setText(" 助理姓名：");
		nameLabel.setForeground(getWordColor());
		Text nameText = new Text(container, JWT.APPEARANCE3);
		nameText.setID(WelcomeProcessor.ID_Text_Name);
		nameText.setMaximumLength(10);
		GridData nameTextGridData = new GridData();
		nameTextGridData.widthHint = 140;
		nameTextGridData.horizontalSpan = 2;
		nameText.setLayoutData(nameTextGridData);
		//第四行
		new Label(container);
		Label mobileLabel = new Label(container);
		mobileLabel.setText(" 手机号码：");
		mobileLabel.setForeground(getWordColor());
		Text mobileText = new Text(container, JWT.APPEARANCE3);
		mobileText.setID(WelcomeProcessor.ID_Text_Mobile);
		GridData mobileTextGridData = new GridData();
		mobileText.setMaximumLength(11);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		mobileTextGridData.widthHint = 140;
		mobileText.setLayoutData(mobileTextGridData);
		//
		Button continuebutton = new Button(container, JWT.APPEARANCE2);
		continuebutton.setID(WelcomeProcessor.ID_Button_Continue);
		continuebutton.setText("继续");
		GridData continueGridData = new GridData();
		continueGridData.widthHint = 60;
		continueGridData.heightHint = 23;
		continuebutton.setLayoutData(continueGridData);

	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){

	}

	/**
	 * 获得颜色
	 */
	private Color getWordColor(){
		return new Color(0x537B70);
	}
}
