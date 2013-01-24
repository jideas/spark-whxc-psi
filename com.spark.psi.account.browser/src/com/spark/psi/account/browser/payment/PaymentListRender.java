package com.spark.psi.account.browser.payment;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.account.entity.PaymentItem;

/**
 * �����¼��ͼ
 * 
 */
public class PaymentListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		// ����
		LWComboList deptList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		deptList.setID(PaymentListProcessor.ID_COMBO_TIME);
		GridData gdDeptList = new GridData();
		gdDeptList.widthHint = 100;
		deptList.setLayoutData(gdDeptList);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("�����¼��");
		new Label(headerLeftArea).setID(PaymentListProcessor.ID_LABEL_COUNT);
		new Label(headerLeftArea).setText("��");
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("�����");
		new Label(headerLeftArea).setID(PaymentListProcessor.ID_LABEL_AMOUNT);
		new Label(headerLeftArea).setText("Ԫ");
		// ����
		new SSearchText2(headerRightArea)
				.setID(PaymentListProcessor.ID_TEXT_SEARCHTEXT);
//		Button advanceSearchBtn = new Button(headerRightArea, JWT.APPEARANCE3);
//		advanceSearchBtn.setID(PaymentListProcessor.ID_Button_AdvanceSearch);
//		advanceSearchBtn.setText(" �߼����� ");
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(PaymentListProcessor.ColumnName.payDate.name(), 120, JWT.CENTER, "��������");
		columns[1] = new STableColumn(PaymentListProcessor.ColumnName.sheetNo.name(), 150, JWT.LEFT, "���ݱ��");
		columns[2] = new STableColumn(PaymentListProcessor.ColumnName.partnerName.name(), 120, JWT.CENTER, "�������");
		columns[3] = new STableColumn(PaymentListProcessor.ColumnName.payType.name(), 120, JWT.CENTER, "��������");
		columns[4] = new STableColumn(PaymentListProcessor.ColumnName.applyAmount.name(), 120, JWT.RIGHT, "������");
		columns[5] = new STableColumn(PaymentListProcessor.ColumnName.applier.name(), 120, JWT.CENTER, "������");
		for (int index = 0; index < columns.length; index++) {
			columns[index].setGrab(true);
		}
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		PaymentItem item = (PaymentItem)element;
		switch(columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPayDate());
		case 1:
			return StableUtil.toLink(Action.Detail.name(), "", item.getPaymentNo());
		case 2:
			return item.getPartnerName();
		case 3:
			if (item.getPaymentType() == null) {
				return "ȱʧ";
			}
			return item.getPaymentType().getName();
		case 4:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 5:
			return item.getCreator();
		default:
			return null;
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		PaymentItem item = (PaymentItem) element;
		switch (columnIndex) {
//		case 0:
//			return DateUtil.dateFromat(item.getDate());
//		case 1:
//			return item.getRelatedNumber();
//		case 2:
//			return item.getPaymentTargetName();
//		case 3:
//			return item.getPaymentTypeName(); 
//		case 4:
//			return item.getPaymentWayName();
//		case 5:
//			return DoubleUtil.getRoundStr(item.getAmount());
//		case 6:
//			StringBuffer text = new StringBuffer();
//			text.append(item.getPayer());
//			if(CheckIsNull.isNotEmpty(item.getConfirmName()))
//			{
//				text.append("��").append(item.getConfirmName()).append("��");
//			}
//			return text.toString();
		case 7:
			return item.getRemark();
		default:
			return null;
		}
	}
	
	
}
