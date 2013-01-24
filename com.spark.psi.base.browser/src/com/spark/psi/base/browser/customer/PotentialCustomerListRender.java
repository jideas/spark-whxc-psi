package com.spark.psi.base.browser.customer;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.base.browser.customer.PotentialCustomerListProcessor.Columns;
import com.spark.psi.publish.base.partner.entity.CustomerItem;

/**
 * Ǳ�ڿͻ��б���ͼ
 */
public class PotentialCustomerListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		LWComboList cutomerScopeCombo = new LWComboList(headerLeftArea,
				JWT.APPEARANCE3);
		cutomerScopeCombo
				.setID(PotentialCustomerListProcessor.ID_COMBO_CUSTOMERSCOPE);
		new Label(headerLeftArea).setText("    ");
//		ComboList timeCombo = new ComboList(headerLeftArea, JWT.APPEARANCE3);
//		timeCombo.setID(PotentialCustomerListProcessor.ID_COMBO_TIME);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("�ͻ�������");

		Label label = new Label(headerLeftArea);
		label.setID(PotentialCustomerListProcessor.ID_LABEL_COUNT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd); //Ԥ���㹻���ֿ��
		
		//
		Text text = new SSearchText2(headerRightArea);
		text.setID(PotentialCustomerListProcessor.ID_TEXT_SEARCHTEXT);
		// new Button(headerRightArea)
		// .setID(PotentialCustomerListProcessor.ID_BUTTON_SEARCH);
		// Button detailButton = new Button(headerRightArea);
		// detailButton.setID(FormalCustomerListProcessor.ID_BUTTON_SEARCH);
		// detailButton.setText("�߼�����");
		//
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setID(PotentialCustomerListProcessor.ID_BUTTON_NEW);
		button.setText(" �����ͻ� ");
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setID(PotentialCustomerListProcessor.ID_BUTTON_ALLOCATE);
		button.setText(" ����ͻ� ");
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setID(PotentialCustomerListProcessor.ID_BUTTON_SHARE);
		button.setText(" ��Ϊ���� ");
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setID(PotentialCustomerListProcessor.ID_BUTTON_DELETE);
		button.setText(" ɾ���ͻ� ");
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[6];

		columns[0] = new STableColumn(Columns.Name.name(), 120, JWT.LEFT,
				"�ͻ�����");
		columns[0].setSortable(true);
		columns[1] = new STableColumn(Columns.Province.name(), 80, JWT.CENTER,
				"ʡ(��)");
		columns[1].setSortable(true);
		columns[2] = new STableColumn(Columns.City.name(), 80, JWT.CENTER,
				"��(��)");
		columns[2].setSortable(true);
		columns[3] = new STableColumn(Columns.Address.name(), 150, JWT.LEFT,
				"��ַ");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		columns[4] = new STableColumn(Columns.ContactPerson.name(), 100,
				JWT.CENTER, "��ϵ��");
		columns[4].setSortable(true);

		columns[5] = new STableColumn(Columns.SalesName.name(), 100,
				JWT.CENTER, "����");
		columns[5].setSortable(true);
		columns[0].setGrab(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		style.setSelectionMode(SSelectionMode.Multi);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		CustomerItem item = (CustomerItem) element;
		switch (columnIndex) {
		case 0:
			return StableUtil.toLink("edit", "", item.getShortName());
		case 1:
			return item.getProvince();
		case 2:
			return item.getCity();
		case 3:
			return item.getAddress();
		case 4:
			return item.getContactName();
		case 5:
			return item.getSalesmanName();
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		CustomerItem item = (CustomerItem) element;
		switch (columnIndex) {
		case 0:
			return item.getName();
		case 4:
			StringBuffer buffer  = new StringBuffer();
			buffer.append("�ֻ���");
			buffer.append(StringUtils.isEmpty(item.getContactMobileNo()) ? "��" : item.getContactMobileNo());
			buffer.append("\n");
			buffer.append("�̻���");
			buffer.append(StringUtils.isEmpty(item.getContactLandLineNumber()) ? "��" : item.getContactLandLineNumber());
			buffer.append("\n");
			buffer.append("�������䣺");
			buffer.append(StringUtils.isEmpty(item.getContactEmail()) ? "��" : item.getContactEmail());
			buffer.append("\n");
			return buffer.toString();
		default:
			return "";
		}
	}
}
