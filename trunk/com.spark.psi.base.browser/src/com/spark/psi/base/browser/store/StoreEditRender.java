package com.spark.psi.base.browser.store;

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
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.base.browser.store.StoreEditProcessor.ShelfTableColumn;

public class StoreEditRender extends BaseFormPageRender {

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 5;
		gl.verticalSpacing = 10;
		gl.horizontalSpacing = 5;
		formArea.setLayout(gl);

		GridData gdlLabel = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_HORIZONTAL);

		GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
		gd3.horizontalSpan = 3;

		SAsteriskLabel asteristLabel = new SAsteriskLabel(formArea, "仓库名称：");
		asteristLabel.setLayoutData(gdlLabel);

		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_Name);
		text.setMaximumLength(25);

		GridData gd = new GridData();
		gd.widthHint = 250;
		text.setLayoutData(gd);

		Label label = new Label(formArea);
		label.setText("  仓库编号：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_StoreCode);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
		gd = new GridData();
		gd.widthHint = 200;
		text.setLayoutData(gd);
		label.setLayoutData(gdlLabel);
		new Label(formArea);

		label = new Label(formArea);
		label.setText("收货人：");
		label.setLayoutData(gdlLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_Consignee);
		text.setMaximumLength(10);
		gd = new GridData();
		gd.widthHint = 250;
		text.setLayoutData(gd);
		text.setLayoutData(gd);

		label = new Label(formArea);
		label.setText("      手机：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_MobileNo);
//		text.setMaximumLength(11);
		text.setRegExp(Reg.REGEXP_mob);
		label.setLayoutData(gdlLabel);
		new Label(formArea);

		label = new Label(formArea);
		label.setText("地址：");
		label.setLayoutData(gdlLabel);

		Composite listArea = new Composite(formArea);
		listArea.setLayoutData(gd3);
		listArea.setLayout(new GridLayout(3));
		LWComboList provinceList = new LWComboList(listArea, JWT.APPEARANCE3);
		provinceList.setID(StoreEditProcessor.ID_List_Province);
		provinceList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		LWComboList cityList = new LWComboList(listArea, JWT.APPEARANCE3);
		cityList.setID(StoreEditProcessor.ID_List_City);
		cityList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		LWComboList districtList = new LWComboList(listArea, JWT.APPEARANCE3);
		districtList.setID(StoreEditProcessor.ID_List_District);
		districtList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		new Label(formArea);

		new Label(formArea).setLayoutData(gdlLabel);

		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_Address);
		text.setHint("详细地址");
		text.setMaximumLength(50);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		text.setLayoutData(gd);

		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_PostCode);
		text.setHint("邮编");
		text.setMaximumLength(6);
		text.setRegExp(Reg.REGEXP_NUM);
		new Label(formArea);

		label = new Label(formArea);
		label.setText("库管员：");
		label.setLayoutData(gdlLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(StoreEditProcessor.ID_Text_Keepers);
		text.setLayoutData(gd3);
		Label labelAddKeepers = new Label(formArea);
		labelAddKeepers.setCursor(Cursor.HAND);
		labelAddKeepers.setID(StoreEditProcessor.ID_Label_AddKeepers);
		labelAddKeepers.setImage(BaseImages
				.getImage("images/store/saas_mark_add_manager.png"));
		
		new SSeparator(formArea, JWT.HORIZONTAL, 5);
		
		GridData gdCount = new GridData(GridData.FILL_HORIZONTAL);
		gdCount.horizontalSpan = 5;
		GridLayout glCount = new GridLayout();
		glCount.numColumns = 6;
		glCount.marginLeft = 12;
		glCount.horizontalSpacing = 5;
		
		Composite countArea = new Composite(formArea);
		countArea.setLayoutData(gdCount);
		countArea.setLayout(glCount);
		
		GridData gdCountText = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gdCountText.widthHint = 120;
		
		asteristLabel = new SAsteriskLabel(countArea, "货位个数：");
		text = new Text(countArea, JWT.APPEARANCE3);
		text.setRegExp(TextRegexp.NUMBER);
		text.setID(StoreEditProcessor.ID_Text_ShelfCount);
		text.setLayoutData(gdCountText);
		
		asteristLabel = new SAsteriskLabel(countArea, "默认层数：");
		text = new Text(countArea, JWT.APPEARANCE3);
		text.setRegExp(TextRegexp.NUMBER);
		text.setID(StoreEditProcessor.ID_Text_TiersCount);
		text.setLayoutData(gdCountText);
		
		Button confirmBtn = new Button(countArea, JWT.APPEARANCE2);
		confirmBtn.setText(" 确定 ");
		confirmBtn.setID(StoreEditProcessor.ID_Button_ConfirmShelf);
		
		Button cancelBtn = new Button(countArea, JWT.APPEARANCE2);
		cancelBtn.setText(" 重置 ");
		cancelBtn.setID(StoreEditProcessor.ID_Button_ResetShelf);
		
		GridData gdTable = new GridData(GridData.FILL_BOTH);
		gdTable.horizontalSpan = 5;
		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn(ShelfTableColumn.name.name(), 150, JWT.LEFT, "货位");
		columns[1] = new SNumberEditColumn(ShelfTableColumn.tiersCount.name(), 150, JWT.RIGHT, "层数");
		((SNumberEditColumn)columns[1]).setDecimal(0);
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setPageAble(false);
		SEditTable shelfTable = new SEditTable(formArea, columns, tableStyle, null);
		
//		SEditTable shelfTable = new SEditTable(formArea, columns, new SEditTableStyle(), null);
		shelfTable.setLayoutData(gdTable);
		shelfTable.setID(StoreEditProcessor.ID_Table_Shelf);
	}

	protected void renderButton(Composite buttonArea) {
		Button buttonSave = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSave.setID(StoreEditProcessor.ID_Button_Save);
		buttonSave.setText("   保存   ");
	}

}
