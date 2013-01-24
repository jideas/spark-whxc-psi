package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.layouts.RowLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;

public class GoodsCategoryDetailRender extends AbstractBoxPageRender {

	@Override
	protected void beforeFooterRender() {

		//
		Composite titleArea = new Composite(contentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 30;
		titleArea.setLayoutData(gdTitle);
		titleArea.setLayout(new RowLayout());
		Label label = new Label(titleArea);
		label.setText("��Ʒ��������");
		label.setFont(new Font(9, "����", JWT.FONT_STYLE_BOLD));
		new Label(titleArea).setText(" ˵������Ʒ�������ڶԷ�����Ʒ��������");

		Composite separatorHr = new Composite(contentArea);
		separatorHr.setBackground(new Color(0x78a9bf));
		GridData gdHr = new GridData(GridData.FILL_HORIZONTAL);
		gdHr.heightHint = 1;
		separatorHr.setLayoutData(gdHr);

		Composite headerArea = new Composite(contentArea);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.verticalIndent = 5;
		headerArea.setLayoutData(gdHeader);
		GridLayout gl = new GridLayout();
		gl.marginLeft = 4;
		gl.marginTop = 5;
		gl.numColumns = 7;
		headerArea.setLayout(gl);

		GridData gdLabel = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER);
		gdLabel.verticalIndent = 2;

		label = new Label(headerArea);
		label.setText("�������ƣ�");
		label.setLayoutData(gdLabel);

		label = new Label(headerArea);
		label.setID(GoodsCategoryDetailProcessor.ID_Label_CategoryName);
		GridData gdText = new GridData();
		gdText.widthHint = 120;
		label.setLayoutData(gdText);
		
		label = new Label(headerArea);
		label.setText(" ������룺");
		label.setLayoutData(gdLabel);
		//
		label = new Label(headerArea);
		label.setID(GoodsCategoryDetailProcessor.ID_Label_CategoryCode);
		GridData gdCode = new GridData();
		gdCode.widthHint = 80;
		label.setLayoutData(gdCode);
		
//		label = new Label(headerArea);
//		label.setText("��Ʒ������ʾ");
//		label.setLayoutData(gdLabel);
//
//		ComboList decimalCombo = new ComboList(headerArea, JWT.APPEARANCE3);
//		decimalCombo.setID(GoodsCategoryDetailProcessor.ID_ComboList_Decimal);
//		GridData gdCombo = new GridData();
//		gdCombo.widthHint = 50;
//		decimalCombo.setLayoutData(gdCombo);
//
//		label = new Label(headerArea);
//		label.setText("λС�� ");
//		label.setLayoutData(gdLabel);

		Composite tableArea = new Composite(contentArea);
		gl = new GridLayout();
		gl.numColumns = 3;
		gl.marginBottom = 0;
		GridData gdTableArea = new GridData(GridData.FILL_BOTH);
		gdTableArea.verticalIndent = 10;
		gdTableArea.horizontalIndent = 5;
		tableArea.setLayoutData(gdTableArea);
		tableArea.setLayout(gl);
		//
		SEditColumn[] columns = new SEditColumn[1];
		columns[0] = new STextEditColumn(
				GoodsCategoryDetailProcessor.PropertyColumns.name.name(), 200,
				JWT.CENTER, "��������");
		columns[0].setGrab(true);
		((STextEditColumn)columns[0]).setMaxLength(6);
		SActionInfo[] actionInfo = new SActionInfo[] { 
//				new SActionInfo(GoodsCategoryDetailProcessor.PropertyActions.up.name(),
//						"����", null, null),
//				new SActionInfo(GoodsCategoryDetailProcessor.PropertyActions.down.name(),
//						"����", null, null),
				new SActionInfo(GoodsCategoryDetailProcessor.PropertyActions.remove.name(),
						"ɾ��", null, null)};

		SEditTable table = new SEditTable(tableArea, columns, GoodsCategoryDetailProcessor.tableStyle,
				actionInfo);
		table.setID(GoodsCategoryDetailProcessor.ID_Table_Properties);
		table.setLabelProvider(new PropertyLabelProvider());
		table.setLayoutData(GridData.INS_FILL_BOTH);

		//
		Composite arrowArea = new Composite(tableArea);
		GridData gdArrow = new GridData(GridData.FILL_VERTICAL);
		gdArrow.widthHint = 30;
		arrowArea.setLayoutData(gdArrow);
		arrowArea.setLayout(new GridLayout());
		arrowArea.setID(GoodsCategoryDetailProcessor.ID_Composite_Arrow);
		//
		columns = new SEditColumn[1];
		columns[0] = new STextEditColumn(
				GoodsCategoryDetailProcessor.OptionColumns.item.name(), 200,
				JWT.CENTER, "��ѡ����ֵ");
		columns[0].setGrab(true);
		((STextEditColumn)columns[0]).setMaxLength(6);
		actionInfo = new SActionInfo[] {
//				new SActionInfo(GoodsCategoryDetailProcessor.OptionActions.up
//						.name(), "����", null, null),
//				new SActionInfo(GoodsCategoryDetailProcessor.OptionActions.down
//						.name(), "����", null, null),
				new SActionInfo(
						GoodsCategoryDetailProcessor.OptionActions.remove
								.name(), "ɾ��", null, null) };
		table = new SEditTable(tableArea, columns, GoodsCategoryDetailProcessor.tableStyle, actionInfo);
		table.setID(GoodsCategoryDetailProcessor.ID_Table_Options);
		table.setLabelProvider(new OptionLabelProvider());
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected void afterFooterRender() {
		if(!hideFooterArea()){
			Button button = new Button(footerArea, JWT.APPEARANCE3);
			button.setID(GoodsCategoryDetailProcessor.ID_Button_Save);
			button.setText(" ��  �� ");
			footerArea.setLayout(new GridLayout());
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END
					| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL
					| GridData.VERTICAL_ALIGN_END);
			gd.heightHint = 28;
			button.setLayoutData(gd);
		}
	}
}

class PropertyLabelProvider implements SLabelProvider {

	public String getText(Object element, int columnIndex) {
		// switch (columnIndex) {
		// case 1:
		// return PropertyInputType.INPUT.getName();
		// default:
		// break;
		// }
		switch (columnIndex) {
		case 0:
			return ((PropertyDefine) element).getName();
		}
		return "";
	}

	public String getToolTipText(Object element, int columnIndex) {
		return null;
	}

	public Color getBackground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getForeground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}

class OptionLabelProvider implements SLabelProvider {

	public String getText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((SNameValue) element).getValue();
		}
		return "";
	}

	public String getToolTipText(Object element, int columnIndex) {
		return null;
	}

	public Color getBackground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getForeground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
