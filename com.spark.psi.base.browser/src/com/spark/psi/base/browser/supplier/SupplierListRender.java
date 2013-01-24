package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;
import com.spark.psi.publish.base.partner.entity.SupplierItem;
import com.spark.common.components.table.edit.SEditTableStyle;

/**
 * 供应商列表视图
 */
public class SupplierListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new Label(headerLeftArea).setText("供应商数量：");
		new Label(headerLeftArea).setID(SupplierListProcessor.ID_LABEL_COUNT);
		//
		Text text = new SSearchText2(headerRightArea);
		text.setHint("输入搜索内容");
		text.setID(SupplierListProcessor.ID_TEXT_SEARCHTEXT);

		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setID(SupplierListProcessor.ID_BUTTON_NEW);
		button.setText(" 新增供应商 ");

	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(SupplierListProcessor.Columns.Number.name(), 100, JWT.LEFT, "供应商编号");
		columns[1] = new STableColumn(SupplierListProcessor.Columns.Name.name(), 200, JWT.LEFT, "供应商名称");
		columns[1].setGrab(true);
		columns[2] = new STableColumn(SupplierListProcessor.Columns.SupplierType.name(), 100, JWT.CENTER,
				"供应商类型");
		columns[3] = new STableColumn(SupplierListProcessor.Columns.TaxRate.name(), 80, JWT.RIGHT, "税率");
		columns[4] = new STableColumn(SupplierListProcessor.Columns.TotalTradeAmount.name(), 100, JWT.RIGHT,
				"采购总额");
		columns[5] = new STableColumn(SupplierListProcessor.Columns.TotalTradeCount.name(), 100, JWT.RIGHT,
				"采购次数");
		columns[6] = new STableColumn(SupplierListProcessor.Columns.RecentTradeDate.name(), 100, JWT.CENTER,
				"最近采购");
		columns[7] = new STableColumn(SupplierListProcessor.Columns.BalanceAmount.name(), 100, JWT.RIGHT,
				"应付余额");
		columns[8] = new STableColumn(SupplierListProcessor.Columns.ContactPerson.name(), 100, JWT.CENTER,
				"联系人");
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		columns[2].setSortable(true);
		columns[3].setSortable(true);
		columns[4].setSortable(true);
		columns[5].setSortable(true);
		columns[6].setSortable(true);
		columns[7].setSortable(true);
		columns[8].setSortable(true);
		return columns;
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 1:
			return 2;
		case 4:
			return 2;
		default:
			return -1;
		}
	}

	public String getText(Object element, int columnIndex) {
		SupplierItem item = (SupplierItem) element;
		switch (columnIndex) {
		case 0:
			return item.getPartnerNo();
		case 1:
			return StableUtil.toLink("edit", "", item.getShortName());
		case 2:
			return getContext().find(EnumEntity.class,
					new BaseDictionaryKey(BaseDictionaryEnum.SupplierType, item.getSupplierType())).getName();
		case 3:
			return DoubleUtil.getRoundStr(item.getTaxRate());
		case 4:
			return DoubleUtil.getRoundStr(item.getTradeTotalAmount());
		case 5:
			return String.valueOf(item.getTradeTotalCount());
		case 6:
			return DateUtil.dateFromat(item.getRecentTradeDate());
		case 7:
			return DoubleUtil.getRoundStr(item.getBalanceAmount());
		case 8:
			return item.getContactName();
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		SupplierItem item = (SupplierItem) element;
		switch (columnIndex) {
		case 1:
			return item.getName();
		case 8:
			StringBuffer buffer = new StringBuffer();
			buffer.append("手机：");
			buffer.append(StringUtils.isEmpty(item.getContactMobileNo()) ? "无" : item.getContactMobileNo());
			buffer.append("\n");
			buffer.append("固话：");
			buffer.append(StringUtils.isEmpty(item.getContactLandLineNumber()) ? "无" : item
					.getContactLandLineNumber());
			buffer.append("\n");
			buffer.append("电子邮箱：");
			buffer.append(StringUtils.isEmpty(item.getContactEmail()) ? "无" : item.getContactEmail());
			buffer.append("\n");
			return buffer.toString();
		default:
			return "";
		}
	}
	
	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}

}
