package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SimpleListPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>快速配置界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-25
 */

public class QuickConfigurationRender extends BaseFormPageRender{

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
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout layout = new GridLayout();
		formArea.setLayout(layout);
		//
		Composite backArea = new Composite(formArea);
		backArea.setLayout(new GridLayout(2));
		//
		Label backIconLabel = new Label(backArea);
		backIconLabel.setImage(BaseImages.getImage("images/wizard/wizard_back_icon.png"));
		backIconLabel.setID(QuickConfigurationProcessor.ID_Icon_BackWizard);
		backIconLabel.setCursor(Cursor.HAND);
		//
		Label backLabel = new Label(backArea);
		backLabel.setText("返回配置向导界面");
		backLabel.setID(QuickConfigurationProcessor.ID_Label_BackWizard);
		backLabel.setCursor(Cursor.HAND);
		//中划线
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//企业信息与Logo
		Composite companyArea = new Composite(formArea);
		GridData companyAreaData = new GridData(GridData.FILL_HORIZONTAL);
		companyAreaData.heightHint = 130;
		companyArea.setLayoutData(companyAreaData);
		addCompanyArea(companyArea);
		//中划线
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//设置用户与仓库
		Composite userAndStoreArea = new Composite(formArea);
		userAndStoreArea.setLayoutData(GridData.INS_FILL_BOTH);
		addUserAndStoreArea(userAndStoreArea);
		afterRender();
	}

	/**
	 * 企业区域，左部份为企业信息，右部份为企业Logo
	 *
	 *@param container 容器
	 */
	private void addCompanyArea(Composite container){
		GridLayout companyAreaLayout = new GridLayout(2);
		companyAreaLayout.horizontalSpacing = 20;
		container.setLayout(companyAreaLayout);
		//
		Composite companyInfoArea = new Composite(container);
		GridData companyInfoAreaData = new GridData();
		companyInfoAreaData.widthHint = 600;
		companyInfoArea.setLayoutData(companyInfoAreaData);
		addCompanyInfo(companyInfoArea);
		//Logo
		Composite companyLogoArea = new Composite(container);
		companyLogoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		addLogoArea(companyLogoArea);
	}

	/**
	 * 企业信息(左部)
	 *
	 *@param container 容器
	 */
	private void addCompanyInfo(Composite container){
		GridLayout layout = new GridLayout(2);
		layout.verticalSpacing = 6;
		container.setLayout(layout);
		//
		GridData firstGridData = new GridData();
		firstGridData.widthHint = 75;
		//标题
		Label titleLabel = new Label(container);
		titleLabel.setText("配置企业信息");
		titleLabel.setForeground(new Color(0x008015));
		titleLabel.setFont(getTitleFont());
		GridData titleGridData = new GridData();
		titleGridData.horizontalSpan = 2;
		titleLabel.setLayoutData(titleGridData);

		//第一行　公司简介及名称
		new SAsteriskLabel(container, "公司简称：").setLayoutData(firstGridData);
		Composite firstRightContainer = new Composite(container);
		firstRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		firstRightContainer.setLayout(new GridLayout(3));
		//
		Text shortNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		shortNameText.setID(QuickConfigurationProcessor.ID_Text_ShortName);
		shortNameText.setHint("6字以内简称");
		shortNameText.setMaximumLength(6);
		GridData shortNameGridData = new GridData();
		shortNameGridData.widthHint = 120;
		shortNameText.setLayoutData(shortNameGridData);
		//
		new SAsteriskLabel(firstRightContainer, "全　　称：").setLayoutData(firstGridData);
		Text fullNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		fullNameText.setID(QuickConfigurationProcessor.ID_Text_FullName);
		fullNameText.setMaximumLength(50);
		fullNameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//第二行　地址
		Label addressLabel = new Label(container, JWT.RIGHT);
		addressLabel.setText("地　　址：");
		addressLabel.setLayoutData(firstGridData);
		Composite secondRightContainer = new Composite(container);
		secondRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		secondRightContainer.setLayout(new GridLayout(3));
		//
		LWComboList provinceList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		provinceList.setID(QuickConfigurationProcessor.ID_List_Province);
		provinceList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList cityList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		cityList.setID(QuickConfigurationProcessor.ID_List_City);
		cityList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList districtList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		districtList.setID(QuickConfigurationProcessor.ID_List_District);
		districtList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//第三行　详细地址及邮编
		new Label(container).setLayoutData(firstGridData);
		Composite thirdRightContainer = new Composite(container);
		thirdRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		thirdRightContainer.setLayout(new GridLayout(2));
		//
		Text addressText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		addressText.setID(QuickConfigurationProcessor.ID_Text_Address);
		addressText.setHint("详细地址");
		addressText.setMaximumLength(100);
		addressText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text postCodeText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		postCodeText.setID(QuickConfigurationProcessor.ID_Text_PostCode);
		postCodeText.setHint("邮编");
		postCodeText.setMaximumLength(6);
		postCodeText.setRegExp(SAAS.Reg.REGEXP_POSTCODE);
		GridData postCodeGridData = new GridData();
		postCodeGridData.widthHint = 120;
		postCodeText.setLayoutData(postCodeGridData);
		//第四行　传真及电话
		Label phoneLabel = new Label(container, JWT.RIGHT);
		phoneLabel.setText("电　　话：");
		phoneLabel.setLayoutData(firstGridData);
		//
		Composite fourRightContainer = new Composite(container);
		fourRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		fourRightContainer.setLayout(new GridLayout(3));
		//
		Text phoneText = new Text(fourRightContainer, JWT.APPEARANCE3);
		phoneText.setID(QuickConfigurationProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label faxLabel = new Label(fourRightContainer, JWT.RIGHT);
		faxLabel.setText("传　　真：");
		faxLabel.setLayoutData(firstGridData);
		//
		Text faxText = new Text(fourRightContainer, JWT.APPEARANCE3);
		faxText.setID(QuickConfigurationProcessor.ID_Text_Fax);
		faxText.setMaximumLength(20);
		faxText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
	}

	/**
	 * Logo区域(右部)
	 *
	 *@param container 容器
	 */
	private void addLogoArea(Composite container){
		container.setLayout(new GridLayout());
		//第一行Logo
		Label logoLabel = new Label(container);
		logoLabel.setID(QuickConfigurationProcessor.ID_Label_Logo);
		logoLabel.setImage(QuickConfigurationProcessor.DEFAULF_LOGO);
		logoLabel.setBackimage(QuickConfigurationProcessor.DEFAULF_BACK);
		GridData logoLabelGridData = new GridData();
		logoLabelGridData.widthHint = 250;
		logoLabelGridData.heightHint = 50;
		logoLabel.setLayoutData(logoLabelGridData);
		//第二行　上传
		Composite upIconArea = new Composite(container);
		upIconArea.setLayout(new GridLayout(2));
		GridData upIconAreaGridData = new GridData();
		upIconAreaGridData.widthHint = 250;
		upIconArea.setLayoutData(upIconAreaGridData);
		//
		FileChooser fileChooser = new FileChooser(upIconArea, JWT.APPEARANCE3);
		fileChooser.setID(QuickConfigurationProcessor.ID_FileChooser_Img);
		fileChooser.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Button uploadButton = new Button(upIconArea, JWT.APPEARANCE3);
		uploadButton.setID(QuickConfigurationProcessor.ID_Button_UploadImg);
		uploadButton.setText("上传徽标");
		//第三行　提示图片大小
		Label logoSizeLabel = new Label(container);
		logoSizeLabel.setText("建议尺寸：高度<=50像素，宽度<=150像素。");
		logoSizeLabel.setFont(new Font("宋体", 8, JWT.FONT_STYLE_BOLD));
		//第四行　修改标题
		Composite modifyTitleArea = new Composite(container);
		modifyTitleArea.setLayout(new GridLayout(2));
		modifyTitleArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text titleText = new Text(modifyTitleArea, JWT.APPEARANCE3);
		titleText.setID(QuickConfigurationProcessor.ID_Text_Title);
		titleText.setHint("最多输入20个字");
		titleText.setMaximumLength(20);
		titleText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Button modifyTitleButton = new Button(modifyTitleArea, JWT.APPEARANCE3);
		modifyTitleButton.setText("修改标题");
		modifyTitleButton.setID(QuickConfigurationProcessor.ID_Button_ModifyTitle);
	}

	/**
	 * 用户与仓库区域，左部份为设置用户，右部份为设置仓库
	 *
	 *@param container 容器
	 */
	private void addUserAndStoreArea(Composite container){
		GridLayout userAndStoreAreaLayout = new GridLayout(2);
		userAndStoreAreaLayout.horizontalSpacing = 20;
		container.setLayout(userAndStoreAreaLayout);
		//用户区域
		Composite userArea = new Composite(container);
		GridData userAreaGridData = new GridData();
		userAreaGridData.widthHint = 600;
		userAreaGridData.verticalAlignment = JWT.FILL;
		userArea.setLayoutData(userAreaGridData);
		ControllerPage setUserPage =
		        (ControllerPage)userArea.showPage(ControllerPage.NAME, new PageControllerInstance("QuickSetUserPage"));
		controls.put(QuickConfigurationProcessor.ID_Table_SetUser, ((SimpleListPageRender)setUserPage.getRender())
		        .getTable());
		//仓库区域
		Composite storeArea = new Composite(container);
		storeArea.setLayoutData(GridData.INS_FILL_BOTH);
		PageControllerInstance instance = new PageControllerInstance("QuickSetStorePage",
                ((SimpleListPageRender)setUserPage.getRender()).getTable());
		ControllerPage setStoragePage =
		        (ControllerPage)storeArea.showPage(ControllerPage.NAME, instance);
		controls.put(QuickConfigurationProcessor.ID_Table_SetStorage,
		        ((SimpleListPageRender)setStoragePage.getRender()).getTable());
	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		GridLayout layout = new GridLayout(3);
		layout.marginTop = 5;
		buttonArea.setLayout(layout);
		//左边Label
		new Label(buttonArea).setText("系统启动之后，你可通过相关功能模块继续对以上的信息进行管理。");
		//空白
		new Label(buttonArea).setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//启用系统
		Button startSystemButton = new Button(buttonArea, JWT.APPEARANCE3);
		GridData startSystemButtonGridData = new GridData();
		startSystemButtonGridData.widthHint = 80;
		startSystemButtonGridData.heightHint = 30;
		startSystemButton.setText("启用系统");
		startSystemButton.setID(QuickConfigurationProcessor.ID_Button_StartSystem);
		startSystemButton.setLayoutData(startSystemButtonGridData);
	}

	/**
	 * 获得字体
	 */
	private Font getTitleFont(){
		return new Font("宋体", 9, JWT.FONT_STYLE_BOLD);
	}
}
