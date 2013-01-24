package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;

/**
 * <p>������ҵ��Ϣ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public class WizardCompanyInfoStepRender extends WizardBaseStepRender{

	/**
	 * ����
	 */
	@Override
    protected String getTitle(){
	    return "������ҵ��Ϣ";
    }
	
	/**
	 * ������Ϣ��
	 */
	@Override
    protected void addInfoArea(Composite infoArea){
		addCompanyArea(infoArea);
    }

	/**
	 * ��ҵ�����ϲ���Ϊ��ҵLogo���²���Ϊ��ҵ��Ϣ
	 *
	 *@param container ����
	 */
	private void addCompanyArea(Composite container){
		GridLayout companyAreaLayout = new GridLayout();
		companyAreaLayout.horizontalSpacing = 20;
		container.setLayout(companyAreaLayout);
		//Logo
		Composite companyLogoArea = new Composite(container);
		GridData companyLogoGridData = new GridData(GridData.FILL_HORIZONTAL);
		companyLogoGridData.heightHint = 150;
		companyLogoArea.setLayoutData(companyLogoGridData);
		addLogoArea(companyLogoArea);
		//��ҵ��Ϣ
		Composite companyInfoArea = new Composite(container);
		GridData companyInfoGridData = new GridData();
		companyInfoGridData.heightHint = 300;
		companyInfoArea.setLayoutData(companyInfoGridData);
		addCompanyInfo(companyInfoArea);
	}
	
	/**
	 * Logo����(�ϲ�)
	 *
	 *@param container ����
	 */
	private void addLogoArea(Composite container){
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		//��һ��Logo
		Composite logoArea = new Composite(container);
		logoArea.setLayout(new GridLayout(2));
		logoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		logoArea.setBackimage(WizardCompanyInfoStepProcessor.DEFAULF_BACK);
		//
		Label logoLabel = new Label(logoArea);
		logoLabel.setID(WizardCompanyInfoStepProcessor.ID_Label_Logo);
		logoLabel.setImage(WizardCompanyInfoStepProcessor.DEFAULF_LOGO);
		//
		Label titleLabel = new Label(logoArea);
		titleLabel.setForeground(Color.COLOR_WHITE);
		titleLabel.setFont(new Font(20, "��Բ", JWT.FONT_STYLE_BOLD));
		titleLabel.setText("С΢��ҵ��Ӫ������Ϣ���Ʒ���ƽ̨");
		titleLabel.setID(WizardCompanyInfoStepProcessor.ID_Label_Title);
		GridData titleLabelGridData = new GridData();
		titleLabelGridData.horizontalAlignment = JWT.FILL;
		titleLabelGridData.verticalAlignment = JWT.CENTER;
		titleLabelGridData.grabExcessHorizontalSpace = true;
		titleLabelGridData.grabExcessVerticalSpace = true;
		titleLabelGridData.horizontalIndent = 10;
		titleLabel.setLayoutData(titleLabelGridData);
		//�ڶ��С��ϴ�
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 23;
		//
		Composite upIconArea = new Composite(container);
		upIconArea.setLayout(new GridLayout(4));
		upIconArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		FileChooser fileChooser = new FileChooser(upIconArea, JWT.APPEARANCE3);
		fileChooser.setID(WizardCompanyInfoStepProcessor.ID_FileChooser_Img);
		GridData fileChooserGridData = new GridData();
		fileChooserGridData.widthHint = 200;
		fileChooser.setLayoutData(fileChooserGridData);
		//
		Button uploadButton = new Button(upIconArea, JWT.APPEARANCE3);
		uploadButton.setID(WizardCompanyInfoStepProcessor.ID_Button_UploadImg);
		uploadButton.setText("�ϴ��ձ�");
		uploadButton.setLayoutData(buttonGridData);
		//
		Text titleText = new Text(upIconArea, JWT.APPEARANCE3);
		titleText.setID(WizardCompanyInfoStepProcessor.ID_Text_Title);
		titleText.setHint("�������20����");
		titleText.setMaximumLength(20);
		GridData titleTextGridData = new GridData();
		titleTextGridData.widthHint = 320;
		titleText.setLayoutData(titleTextGridData);
		//
		Button modifyTitleButton = new Button(upIconArea, JWT.APPEARANCE3);
		modifyTitleButton.setText("�޸ı���");
		modifyTitleButton.setID(WizardCompanyInfoStepProcessor.ID_Button_ModifyTitle);
		modifyTitleButton.setLayoutData(buttonGridData);
		
		//�����С���ʾͼƬ��С
		Label logoSizeLabel = new Label(container);
		logoSizeLabel.setText("����ߴ磺�߶�<=50���أ����<=150���ء�");
	}


	/**
	 * ��ҵ��Ϣ(�²�)
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
		//��һ�С���ʾ
		Label presentationLabel = new Label(container, JWT.RIGHT);
		presentationLabel.setText("�ᡡ��ʾ��");
		presentationLabel.setForeground(Color.COLOR_RED);
		presentationLabel.setLayoutData(firstGridData);
		//
		Label presentationContentLabel = new Label(container);
		presentationContentLabel.setText("��ӡ����ʱ�����Է����ѡ�����¹�˾��Ϣ�����Ϊ���Ŀͻ���ͨ�����µ����ܣ����¹�˾��ϢҲ�ᴫ�ݸ����Ŀͻ���");
		presentationContentLabel.setForeground(Color.COLOR_RED);
		//�ڶ��С���˾��鼰����
		new SAsteriskLabel(container, "��˾��ƣ�").setLayoutData(firstGridData);
		Composite firstRightContainer = new Composite(container);
		firstRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		firstRightContainer.setLayout(new GridLayout(3));
		//
		Text shortNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		shortNameText.setID(WizardCompanyInfoStepProcessor.ID_Text_ShortName);
		shortNameText.setHint("6�����ڼ��");
		shortNameText.setMaximumLength(6);
		GridData shortNameGridData = new GridData();
		shortNameGridData.widthHint = 120;
		shortNameText.setLayoutData(shortNameGridData);
		//
		new SAsteriskLabel(firstRightContainer, "ȫ�����ƣ�").setLayoutData(firstGridData);
		Text fullNameText = new Text(firstRightContainer, JWT.APPEARANCE3);
		fullNameText.setID(WizardCompanyInfoStepProcessor.ID_Text_FullName);
		fullNameText.setMaximumLength(50);
		fullNameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�����С���ַ
		Label addressLabel = new Label(container, JWT.RIGHT);
		addressLabel.setText("�ء���ַ��");
		addressLabel.setLayoutData(firstGridData);
		Composite secondRightContainer = new Composite(container);
		secondRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		secondRightContainer.setLayout(new GridLayout(3));
		//
		LWComboList provinceList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		provinceList.setID(WizardCompanyInfoStepProcessor.ID_List_Province);
		provinceList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList cityList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		cityList.setID(WizardCompanyInfoStepProcessor.ID_List_City);
		cityList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList districtList = new LWComboList(secondRightContainer, JWT.APPEARANCE3);
		districtList.setID(WizardCompanyInfoStepProcessor.ID_List_District);
		districtList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�����С���ϸ��ַ���ʱ�
		new Label(container).setLayoutData(firstGridData);
		Composite thirdRightContainer = new Composite(container);
		thirdRightContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		thirdRightContainer.setLayout(new GridLayout(2));
		//
		Text addressText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		addressText.setID(WizardCompanyInfoStepProcessor.ID_Text_Address);
		addressText.setHint("��ϸ��ַ");
		addressText.setMaximumLength(100);
		addressText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text postCodeText = new Text(thirdRightContainer, JWT.APPEARANCE3);
		postCodeText.setID(WizardCompanyInfoStepProcessor.ID_Text_PostCode);
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
		phoneText.setID(WizardCompanyInfoStepProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label faxLabel = new Label(fourRightContainer, JWT.RIGHT);
		faxLabel.setText("�������棺");
		faxLabel.setLayoutData(firstGridData);
		//
		Text faxText = new Text(fourRightContainer, JWT.APPEARANCE3);
		faxText.setID(WizardCompanyInfoStepProcessor.ID_Text_Fax);
		faxText.setMaximumLength(20);
		faxText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
	}

	/**
	 * �ײ���߰�ť��
	 */
	@Override
    protected void renderLeftButtonArea(Composite leftButtonArea){
	    
    }

}

