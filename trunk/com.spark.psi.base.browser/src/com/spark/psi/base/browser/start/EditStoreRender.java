package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>新增或编辑仓库视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-28
 */

public class EditStoreRender extends BaseFormPageRender{
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
		GridLayout gridLayout = new GridLayout(3);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 5;
		formArea.setLayout(gridLayout);
		//上部区域
		addUpArea(formArea);
		//中划线
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//下部区域　库管员及零售员
		addDownArea(formArea);
	}
	
	/**
	 * 中划线上部区域
	 */
	private void addUpArea(Composite formArea){
		//
		GridData secondLabelGridData = new GridData();
		secondLabelGridData.widthHint = 50;
		GridData secondTextGridData = new GridData();
		secondTextGridData.widthHint = 200;
		//第一行　仓库名称及固话
		new SAsteriskLabel(formArea, "仓库名称：").setLayoutData(getFirstGridData());
		//
		Composite firstContainer = new Composite(formArea);
		firstContainer.setLayout(new GridLayout(3));
		firstContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text nameText = new Text(firstContainer, JWT.APPEARANCE3);
		nameText.setID(EditStoreProcessor.ID_Text_Name);
		nameText.setMaximumLength(25);
		nameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label phoneLabel = new Label(firstContainer, JWT.RIGHT);
		phoneLabel.setText("固话：");
		phoneLabel.setLayoutData(secondLabelGridData);
		//
		Text phoneText = new Text(firstContainer, JWT.APPEARANCE3);
		phoneText.setID(EditStoreProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setLayoutData(secondTextGridData);
		phoneText.setRegExp(SAAS.Reg.REGEXP_PHONE);
		//
		new Label(formArea);
		//第二行　收货人及手机
		new SAsteriskLabel(formArea, "收货人：").setLayoutData(getFirstGridData());
		//
		Composite secondContainer = new Composite(formArea);
		secondContainer.setLayout(new GridLayout(3));
		secondContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text consigneeText = new Text(secondContainer, JWT.APPEARANCE3);
		consigneeText.setID(EditStoreProcessor.ID_Text_Consignee);
		consigneeText.setMaximumLength(10);
		consigneeText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label mobileLabel = new Label(secondContainer, JWT.RIGHT);
		mobileLabel.setText("手机：");
		mobileLabel.setLayoutData(secondLabelGridData);
		//
		Text mobileText = new Text(secondContainer, JWT.APPEARANCE3);
		mobileText.setID(EditStoreProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(11);
		mobileText.setLayoutData(secondTextGridData);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		//
		new Label(formArea);
		//第三行　地址
		new SAsteriskLabel(formArea, "地址：").setLayoutData(getFirstGridData());
		//
		Composite thirdContainer = new Composite(formArea);
		thirdContainer.setLayout(new GridLayout(3));
		thirdContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList provinceList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		provinceList.setID(EditStoreProcessor.ID_List_Province);
		provinceList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList cityList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		cityList.setID(EditStoreProcessor.ID_List_City);
		cityList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList districtList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		districtList.setID(EditStoreProcessor.ID_List_District);
		districtList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		new Label(formArea);
		//第四行　详细地址及邮编
		new Label(formArea).setLayoutData(getFirstGridData());
		//
		Composite fourContainer = new Composite(formArea);
		fourContainer.setLayout(new GridLayout(2));
		fourContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text addressText = new Text(fourContainer, JWT.APPEARANCE3);
		addressText.setID(EditStoreProcessor.ID_Text_Address);
		addressText.setHint("详细地址");
		addressText.setMaximumLength(100);
		addressText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text postCodeText = new Text(fourContainer, JWT.APPEARANCE3);
		postCodeText.setID(EditStoreProcessor.ID_Text_Postcode);
		postCodeText.setHint("邮编");
		postCodeText.setMaximumLength(6);
		postCodeText.setRegExp(SAAS.Reg.REGEXP_POSTCODE);
		GridData postCodeGridData = new GridData();
		postCodeGridData.widthHint = 120;
		postCodeText.setLayoutData(postCodeGridData);
		//
		new Label(formArea);
	}

	/**
	 * 中划线下部区域
	 */
	private void addDownArea(Composite formArea){
		//库管员
		new SAsteriskLabel(formArea, "库管员：").setLayoutData(getFirstGridData());
		//
		Text keeperText = new Text(formArea, JWT.APPEARANCE3);
		keeperText.setID(EditStoreProcessor.ID_Text_Keeper);
		keeperText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label labelAddKeepers = new Label(formArea);
		labelAddKeepers.setCursor(Cursor.HAND);
		labelAddKeepers.setID(EditStoreProcessor.ID_Label_AddKeeper);
		labelAddKeepers.setImage(BaseImages.getImage("images/store/saas_mark_add_manager.png"));
		//零售人员
		Label saleLabel = new Label(formArea, JWT.RIGHT);
		saleLabel.setText("零售人员：");
		saleLabel.setLayoutData(getFirstGridData());
		//
		Text saleText = new Text(formArea, JWT.APPEARANCE3);
		saleText.setID(EditStoreProcessor.ID_Text_Sales);
		saleText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label labelAddSales = new Label(formArea);
		labelAddSales.setCursor(Cursor.HAND);
		labelAddSales.setID(EditStoreProcessor.ID_Label_AddSales);
		labelAddSales.setImage(BaseImages.getImage("images/store/saas_mark_add_sale.png"));
	}

	/**
	 * 获得第一列label的数据项
	 */
	private GridData getFirstGridData(){
		GridData gridData = new GridData();
		gridData.widthHint = 75;
		return gridData;
	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout());
		//保存按钮
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(EditUserProcessor.ID_Button_Save);
		GridData saveButtonGridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButton.setText("保存");
		saveButton.setLayoutData(saveButtonGridData);
	}
}
