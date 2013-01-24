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
 * <p>�������ý�����ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-25
 */

public class QuickConfigurationRender extends BaseFormPageRender{

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
	 * �������������
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
		backLabel.setText("���������򵼽���");
		backLabel.setID(QuickConfigurationProcessor.ID_Label_BackWizard);
		backLabel.setCursor(Cursor.HAND);
		//�л���
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//��ҵ��Ϣ��Logo
		Composite companyArea = new Composite(formArea);
		GridData companyAreaData = new GridData(GridData.FILL_HORIZONTAL);
		companyAreaData.heightHint = 130;
		companyArea.setLayoutData(companyAreaData);
		addCompanyArea(companyArea);
		//�л���
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//�����û���ֿ�
		Composite userAndStoreArea = new Composite(formArea);
		userAndStoreArea.setLayoutData(GridData.INS_FILL_BOTH);
		addUserAndStoreArea(userAndStoreArea);
		afterRender();
	}

	/**
	 * ��ҵ�����󲿷�Ϊ��ҵ��Ϣ���Ҳ���Ϊ��ҵLogo
	 *
	 *@param container ����
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
	 * ��ҵ��Ϣ(��)
	 *
	 *@param container ����
	 */
	private void addCompanyInfo(Composite container){
		GridLayout layout = new GridLayout(2);
		layout.verticalSpacing = 6;
		container.setLayout(layout);
		//
		GridData firstGridData = new GridData();
		firstGridData.widthHint = 75;
		//����
		Label titleLabel = new Label(container);
		titleLabel.setText("������ҵ��Ϣ");
		titleLabel.setForeground(new Color(0x008015));
		titleLabel.setFont(getTitleFont());
		GridData titleGridData = new GridData();
		titleGridData.horizontalSpan = 2;
		titleLabel.setLayoutData(titleGridData);

		//��һ�С���˾��鼰����
		new SAsteriskLabel(container, "��˾��ƣ�").setLayoutData(firstGridData);
		Composite firstRightContainer = new Composite(container);
		firstRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		firstRightContainer.setLayout(new GridLayout(3));
		//
		Text shortNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		shortNameText.setID(QuickConfigurationProcessor.ID_Text_ShortName);
		shortNameText.setHint("6�����ڼ��");
		shortNameText.setMaximumLength(6);
		GridData shortNameGridData = new GridData();
		shortNameGridData.widthHint = 120;
		shortNameText.setLayoutData(shortNameGridData);
		//
		new SAsteriskLabel(firstRightContainer, "ȫ�����ƣ�").setLayoutData(firstGridData);
		Text fullNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		fullNameText.setID(QuickConfigurationProcessor.ID_Text_FullName);
		fullNameText.setMaximumLength(50);
		fullNameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�ڶ��С���ַ
		Label addressLabel = new Label(container, JWT.RIGHT);
		addressLabel.setText("�ء���ַ��");
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
		//�����С���ϸ��ַ���ʱ�
		new Label(container).setLayoutData(firstGridData);
		Composite thirdRightContainer = new Composite(container);
		thirdRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		thirdRightContainer.setLayout(new GridLayout(2));
		//
		Text addressText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		addressText.setID(QuickConfigurationProcessor.ID_Text_Address);
		addressText.setHint("��ϸ��ַ");
		addressText.setMaximumLength(100);
		addressText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text postCodeText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		postCodeText.setID(QuickConfigurationProcessor.ID_Text_PostCode);
		postCodeText.setHint("�ʱ�");
		postCodeText.setMaximumLength(6);
		postCodeText.setRegExp(SAAS.Reg.REGEXP_POSTCODE);
		GridData postCodeGridData = new GridData();
		postCodeGridData.widthHint = 120;
		postCodeText.setLayoutData(postCodeGridData);
		//�����С����漰�绰
		Label phoneLabel = new Label(container, JWT.RIGHT);
		phoneLabel.setText("�硡������");
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
		faxLabel.setText("�������棺");
		faxLabel.setLayoutData(firstGridData);
		//
		Text faxText = new Text(fourRightContainer, JWT.APPEARANCE3);
		faxText.setID(QuickConfigurationProcessor.ID_Text_Fax);
		faxText.setMaximumLength(20);
		faxText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
	}

	/**
	 * Logo����(�Ҳ�)
	 *
	 *@param container ����
	 */
	private void addLogoArea(Composite container){
		container.setLayout(new GridLayout());
		//��һ��Logo
		Label logoLabel = new Label(container);
		logoLabel.setID(QuickConfigurationProcessor.ID_Label_Logo);
		logoLabel.setImage(QuickConfigurationProcessor.DEFAULF_LOGO);
		logoLabel.setBackimage(QuickConfigurationProcessor.DEFAULF_BACK);
		GridData logoLabelGridData = new GridData();
		logoLabelGridData.widthHint = 250;
		logoLabelGridData.heightHint = 50;
		logoLabel.setLayoutData(logoLabelGridData);
		//�ڶ��С��ϴ�
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
		uploadButton.setText("�ϴ��ձ�");
		//�����С���ʾͼƬ��С
		Label logoSizeLabel = new Label(container);
		logoSizeLabel.setText("����ߴ磺�߶�<=50���أ����<=150���ء�");
		logoSizeLabel.setFont(new Font("����", 8, JWT.FONT_STYLE_BOLD));
		//�����С��޸ı���
		Composite modifyTitleArea = new Composite(container);
		modifyTitleArea.setLayout(new GridLayout(2));
		modifyTitleArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text titleText = new Text(modifyTitleArea, JWT.APPEARANCE3);
		titleText.setID(QuickConfigurationProcessor.ID_Text_Title);
		titleText.setHint("�������20����");
		titleText.setMaximumLength(20);
		titleText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Button modifyTitleButton = new Button(modifyTitleArea, JWT.APPEARANCE3);
		modifyTitleButton.setText("�޸ı���");
		modifyTitleButton.setID(QuickConfigurationProcessor.ID_Button_ModifyTitle);
	}

	/**
	 * �û���ֿ������󲿷�Ϊ�����û����Ҳ���Ϊ���òֿ�
	 *
	 *@param container ����
	 */
	private void addUserAndStoreArea(Composite container){
		GridLayout userAndStoreAreaLayout = new GridLayout(2);
		userAndStoreAreaLayout.horizontalSpacing = 20;
		container.setLayout(userAndStoreAreaLayout);
		//�û�����
		Composite userArea = new Composite(container);
		GridData userAreaGridData = new GridData();
		userAreaGridData.widthHint = 600;
		userAreaGridData.verticalAlignment = JWT.FILL;
		userArea.setLayoutData(userAreaGridData);
		ControllerPage setUserPage =
		        (ControllerPage)userArea.showPage(ControllerPage.NAME, new PageControllerInstance("QuickSetUserPage"));
		controls.put(QuickConfigurationProcessor.ID_Table_SetUser, ((SimpleListPageRender)setUserPage.getRender())
		        .getTable());
		//�ֿ�����
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
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		GridLayout layout = new GridLayout(3);
		layout.marginTop = 5;
		buttonArea.setLayout(layout);
		//���Label
		new Label(buttonArea).setText("ϵͳ����֮�����ͨ����ع���ģ����������ϵ���Ϣ���й���");
		//�հ�
		new Label(buttonArea).setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//����ϵͳ
		Button startSystemButton = new Button(buttonArea, JWT.APPEARANCE3);
		GridData startSystemButtonGridData = new GridData();
		startSystemButtonGridData.widthHint = 80;
		startSystemButtonGridData.heightHint = 30;
		startSystemButton.setText("����ϵͳ");
		startSystemButton.setID(QuickConfigurationProcessor.ID_Button_StartSystem);
		startSystemButton.setLayoutData(startSystemButtonGridData);
	}

	/**
	 * �������
	 */
	private Font getTitleFont(){
		return new Font("����", 9, JWT.FONT_STYLE_BOLD);
	}
}
