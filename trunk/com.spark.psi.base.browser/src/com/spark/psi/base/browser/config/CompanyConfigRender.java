package com.spark.psi.base.browser.config;

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
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 企业信息配置界面视图
 */
public class CompanyConfigRender extends BaseFormPageRender {

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout formAreaGridLayout = new GridLayout(2);
		formAreaGridLayout.horizontalSpacing = 0;
		formAreaGridLayout.verticalSpacing = 15;
		formArea.setLayout(formAreaGridLayout);		
		GridLayout gl1 = new GridLayout(2);
		gl1.marginLeft = 20;
		gl1.horizontalSpacing = 10;
		Composite firstCmp = new Composite(formArea);
		firstCmp.setLayout(gl1);
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 3;
		gd1.heightHint = CompanyConfigProcessor.DEFAULF_BACK.getHeight();
		gd1.grabExcessHorizontalSpace = true;
		gd1.horizontalAlignment = JWT.FILL;		
		firstCmp.setLayoutData(gd1);
		firstCmp.setBackimage(CompanyConfigProcessor.DEFAULF_BACK);// 设置背景
//		//1r0c
//		Composite cmp1 = new Composite(firstCmp);
//		cmp1.setLayout(new GridLayout());
//		GridData gd3 = new GridData();
//		gd3.widthHint = 10;
//		gd3.heightHint = 60;
//		cmp1.setLayoutData(gd3);
//		cmp1.setBackimage(CompanyConfigProcessor.DEFAULF_BACK);// 设置背景
//		// 1r1c
//		GridData lblLogoGridData = new GridData();
//		lblLogoGridData.heightHint = CompanyConfigProcessor.DEFAULF_BACK.getHeight();
//		lblLogoGridData.widthHint = 150;
		Label lbl_logo = new Label(firstCmp);
//		lbl_logo.setLayoutData(lblLogoGridData);
		lbl_logo.setID(CompanyConfigProcessor.ID_Label_Logo);
		lbl_logo.setImage(CompanyConfigProcessor.DEFAULF_LOGO);
		lbl_logo.setBackimage(CompanyConfigProcessor.DEFAULF_BACK);// 设置背景
//		// 1r2c
//		Composite Composite_System = new Composite(firstCmp);
//		Composite_System.setLayout(new GridLayout());
//		GridData GridData_09 = new GridData();
//		GridData_09.horizontalAlignment = JWT.FILL;
//		GridData_09.grabExcessHorizontalSpace = true;
//		GridData_09.heightHint = CompanyConfigProcessor.DEFAULF_BACK.getHeight();
//		Composite_System.setLayoutData(GridData_09);
//		Composite_System.setBackimage(CompanyConfigProcessor.DEFAULF_BACK);// 设置背景

		GridData lblTitleGridData = new GridData();
		lblTitleGridData.horizontalAlignment = JWT.FILL;
		lblTitleGridData.verticalAlignment = JWT.CENTER;
		lblTitleGridData.grabExcessHorizontalSpace = true;
		lblTitleGridData.grabExcessVerticalSpace = true;
		lblTitleGridData.horizontalIndent = 10;

		Label lbl_title = new Label(firstCmp);
		lbl_title.setLayoutData(lblTitleGridData);
		lbl_title.setForeground(Color.COLOR_WHITE);
		lbl_title.setFont(new Font(20, "幼圆", JWT.FONT_STYLE_BOLD));//方正准圆简体，22px #ffffff
		lbl_title.setText("柒号生活馆进销存管理系统");// id title
		lbl_title.setID(CompanyConfigProcessor.ID_Label_Title);

		// 2r
		Composite upIconComposite = new Composite(formArea);
		upIconComposite.setLayout(new GridLayout(4));
		GridData upIconCompositeGridData = new GridData();
		upIconCompositeGridData.horizontalAlignment = JWT.FILL;
		upIconCompositeGridData.grabExcessHorizontalSpace = true;
		upIconCompositeGridData.horizontalSpan = 2;
		upIconComposite.setLayoutData(upIconCompositeGridData);

		GridData layoutData = new GridData();
		layoutData.widthHint = 150;
		FileChooser fileChooser = new FileChooser(upIconComposite,
				JWT.APPEARANCE3);
		fileChooser.setID(CompanyConfigProcessor.ID_FileChooser_Img);// id
		fileChooser.setLayoutData(layoutData);

		Button uploadButton = new Button(upIconComposite, JWT.APPEARANCE3);
		uploadButton.setID(CompanyConfigProcessor.ID_Button_UploadImg);// id
		uploadButton.setText(" 上传徽标 ");

		GridData txtTitleGridData = new GridData();
		txtTitleGridData.widthHint = 400;
		Text txtTitle = new Text(upIconComposite, JWT.APPEARANCE3);
		txtTitle.setLayoutData(txtTitleGridData);
		txtTitle.setHint("最多输入20个字");
		txtTitle.setMaximumLength(20);
		txtTitle.setID(CompanyConfigProcessor.ID_Text_Title);// id

		Button modifyTitleButton = new Button(upIconComposite, JWT.APPEARANCE3);
		modifyTitleButton.setText(" 修改标题 ");
		modifyTitleButton.setID(CompanyConfigProcessor.ID_Button_ModifyTitle);// id

		// 3r
		GridData layoutData_Comment = new GridData();
		layoutData_Comment.horizontalSpan = 2;
		Label lblComment = new Label(formArea);
		lblComment.setText("建议尺寸：高度<=50像素，宽度<=150像素。");
		lblComment.setLayoutData(layoutData_Comment);

		// 中间有一个隔离带
		Composite composite = new Composite(formArea);
		GridData compositelayoutData = new GridData();
		compositelayoutData.horizontalAlignment = JWT.FILL;
		compositelayoutData.heightHint = 20;
		compositelayoutData.horizontalSpan = 2;
		composite.setLayoutData(compositelayoutData);

		// 布局表单
		GridData formCmpData = new GridData();
		formCmpData.horizontalAlignment = JWT.FILL;
		formCmpData.horizontalSpan = 2;
		Composite formCmp = new Composite(formArea);
		GridLayout gl = new GridLayout(2);
		gl.verticalSpacing = 10;
		formCmp.setLayout(gl);
		formCmp.setLayoutData(formCmpData);
		//
		GridData gd2 = new GridData();
		gd2.widthHint = 75;
		Label lbl_presentation = new Label(formCmp,JWT.RIGHT);
		lbl_presentation.setText("提　　示：");
		lbl_presentation.setForeground(Color.COLOR_RED);
		lbl_presentation.setLayoutData(gd2);
		
		Label lbl_presentation_content = new Label(formCmp);
		lbl_presentation_content.setText("打印单据时，可以方便地选择以下公司信息。如果为您的客户开通网上下单功能，以下公司信息也会传递给您的客户。");
		lbl_presentation_content.setForeground(Color.COLOR_RED);
		//
		SAsteriskLabel lbl_shortName = new SAsteriskLabel(formCmp,"公司简称：");
		lbl_shortName.setLayoutData(gd2);
//		Label lbl_shortName = new Label(formCmp);
//		lbl_shortName.setText("公司简称：");
		Composite omposite_02 = new Composite(formCmp);
		omposite_02.setLayout(new GridLayout(3));
		//
		GridData gridData_01 = new GridData();
		gridData_01.widthHint = 120;
		Text txt_shortName = new Text(omposite_02, JWT.APPEARANCE3);
		txt_shortName.setLayoutData(gridData_01);
		txt_shortName.setID(CompanyConfigProcessor.ID_Text_ShortName);// id
		txt_shortName.setHint("6字以内简称");
		txt_shortName.setMaximumLength(6);

		SAsteriskLabel lbl_fullName = new SAsteriskLabel(omposite_02,"全　　称：");
//		Label lbl_fullName = new Label(omposite_02, JWT.APPEARANCE3);
//		lbl_fullName.setText("全　　称：");
		GridData gridData_02 = new GridData();
		gridData_02.widthHint = 570;
		Text txt_fullName = new Text(omposite_02, JWT.APPEARANCE3);
		txt_fullName.setLayoutData(gridData_02);
		txt_fullName.setID(CompanyConfigProcessor.ID_Text_FullName);// id
		txt_fullName.setMaximumLength(50);
		//
		GridData gridData_03 = new GridData();
		gridData_03.verticalAlignment = JWT.BEGINNING;
		gridData_03.widthHint = 75;
		Label lbl_address = new Label(formCmp, JWT.RIGHT);
		lbl_address.setText("地　　址：");
		lbl_address.setLayoutData(gridData_03);


		Composite composite_01 = new Composite(formCmp);
		composite_01.setLayout(new GridLayout());

		Composite areaCmp = new Composite(composite_01);
		areaCmp.setLayout(new GridLayout(3));

		LWComboList cbl_province = new LWComboList(areaCmp, JWT.APPEARANCE3);
		cbl_province.setID(CompanyConfigProcessor.ID_List_Province);

		LWComboList cbl_city = new LWComboList(areaCmp, JWT.APPEARANCE3);
		cbl_city.setID(CompanyConfigProcessor.ID_List_City);

		LWComboList cbl_district = new LWComboList(areaCmp, JWT.APPEARANCE3);
		cbl_district.setID(CompanyConfigProcessor.ID_List_District);

		Composite addressCmp = new Composite(composite_01);
		addressCmp.setLayout(new GridLayout(2));
		GridData gridData_04 = new GridData();
		gridData_04.widthHint = 645;
		Text txt_address = new Text(addressCmp, JWT.APPEARANCE3);
		txt_address.setLayoutData(gridData_04);
		txt_address.setID(CompanyConfigProcessor.ID_Text_Address);
		txt_address.setHint("详细地址");
		txt_address.setMaximumLength(100);

		GridData gridData_05 = new GridData();
		gridData_05.widthHint = 120;
		Text txt_postCode = new Text(addressCmp, JWT.APPEARANCE3);
		txt_postCode.setLayoutData(gridData_05);
		txt_postCode.setID(CompanyConfigProcessor.ID_Text_PostCode);
		txt_postCode.setHint("邮编");
		txt_postCode.setMaximumLength(6);
		txt_postCode.setRegExp(SAAS.Reg.REGEXP_POSTCODE);

		Label lbl_phone = new Label(formCmp,JWT.RIGHT);
		lbl_phone.setText("电　　话：");
		lbl_phone.setLayoutData(gd2);
		Composite dhCmp = new Composite(formCmp);
		dhCmp.setLayout(new GridLayout(3));
		GridData gridData_06 = new GridData();
		gridData_06.widthHint = 350;
		Text txt_phone = new Text(dhCmp, JWT.APPEARANCE3);
		txt_phone.setLayoutData(gridData_06);
		txt_phone.setID(CompanyConfigProcessor.ID_Text_Phone);
		txt_phone.setMaximumLength(20);
		txt_phone.setRegExp("^\\d*?$");

		Label lbl_fax = new Label(dhCmp);
		lbl_fax.setText("传　　真：");
		GridData gridData_07 = new GridData();
		gridData_07.widthHint = 350;
		Text txt_fax = new Text(dhCmp, JWT.APPEARANCE3);
		txt_fax.setLayoutData(gridData_07);
		txt_fax.setID(CompanyConfigProcessor.ID_Text_Fax);
		txt_fax.setMaximumLength(20);
		txt_fax.setRegExp("^\\d*?$");

		// 在企业信息设置界面，显示鱼儿多号 0222
		Label lbl_saasNo = new Label(formCmp);
//		lbl_saasNo.setText("鱼儿多号：");
		lbl_saasNo.setEnabled(false);
		Label lbl_saas_No = new Label(formCmp);
		lbl_saas_No.setID(CompanyConfigProcessor.ID_Label_SaasNo);
		lbl_saas_No.setEnabled(false);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 保存设置 ");
		button.setID(CompanyConfigProcessor.ID_Button_Save);
	}
}