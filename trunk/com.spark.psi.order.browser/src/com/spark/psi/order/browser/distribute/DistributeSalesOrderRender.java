/**
 * 
 */
package com.spark.psi.order.browser.distribute;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.BasePublicImages;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.SalesOrderInfo;

/**
 * �����������ϸ��ͼ
 * 
 */
public class DistributeSalesOrderRender extends BaseFormPageRender {

	private SalesOrderInfo orderInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		this.orderInfo = ((DistributeSalesOrderProcessor) this.processor).orderInfo;
	}

	/**
	 * �Ƿ��Զ���Form����
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 10;
		formArea.setLayout(gl);

		PartnerInfo customerInfo = orderInfo.getPartnerInfo();
		String deliveryInfo = orderInfo.getConsignee();
		String linkMan = orderInfo.getLinkman();
		String faxNumber = customerInfo.getFax();
		String deliveryAddress = null;
		String deliveryName = null;
		String deliveryMobileNo = null;
		String deliveryLandLineNumber = null;
		deliveryName = deliveryInfo;
		String contactPersonName = null;
		String contactMobileNo = null;
		String contactLandLineNumber = null;
		contactPersonName = linkMan;

		//
		StringBuffer labelBuffer = new StringBuffer();
		//
		Label label = new Label(formArea);

		labelBuffer.append("���۵��ţ�");
		labelBuffer.append(orderInfo.getOrderNumber());
		labelBuffer.append("  ");
		labelBuffer.append(orderInfo.getOrderType().getName());
		label.setText(labelBuffer.toString());
		labelBuffer.setLength(0);

		//
		Composite customerInfoArea1 = new Composite(formArea);
		customerInfoArea1.setLayout(new GridLayout(15));

		label = new Label(customerInfoArea1);
		labelBuffer.append("�ͻ����ƣ�");
		labelBuffer.append(orderInfo.getPartnerInfo().getName());
		if (StringUtils.isEmpty(faxNumber)) {
			label.setText(labelBuffer.toString());
			labelBuffer.setLength(0);
		} else {
			labelBuffer.append("(");
			label.setText(labelBuffer.toString());
			labelBuffer.setLength(0);
			//
			new Label(customerInfoArea1).setImage(BasePublicImages.getImage("images/contact/saas_mark_fax.png"));
			label = new Label(customerInfoArea1);
			labelBuffer.append(faxNumber);
			labelBuffer.append(")");
			label.setText(labelBuffer.toString());
			labelBuffer.setLength(0);
		}

		label = new Label(customerInfoArea1);
		labelBuffer.append("    ��ϵ�ˣ�");

		if (!StringUtils.isEmpty(contactPersonName)) {
			labelBuffer.append(contactPersonName);
			if (!StringUtils.isEmpty(contactMobileNo) || !StringUtils.isEmpty(contactLandLineNumber)) {
				labelBuffer.append("(");
				if (!StringUtils.isEmpty(contactMobileNo)) {
					new Label(customerInfoArea1).setImage(BasePublicImages.getImage("images/contact/saas_mark_tel.png"));
					new Label(customerInfoArea1).setText(contactMobileNo + ")");
				} else {
					new Label(customerInfoArea1).setImage(BasePublicImages.getImage("images/contact/saas_mark_phone.png"));
					new Label(customerInfoArea1).setText(contactLandLineNumber + ")");
				}
			}
		} else {
			labelBuffer.append("��");
		}
		label.setText(labelBuffer.toString());
		labelBuffer.setLength(0);

		label = new Label(customerInfoArea1);
		labelBuffer.append("    �ջ��ˣ�");
		if (!StringUtils.isEmpty(deliveryName)) {
			labelBuffer.append(deliveryName);
			if (!StringUtils.isEmpty(deliveryMobileNo) || !StringUtils.isEmpty(deliveryLandLineNumber)) {
				labelBuffer.append("(");
				if (!StringUtils.isEmpty(deliveryMobileNo)) {
					new Label(customerInfoArea1).setImage(BasePublicImages.getImage("images/contact/saas_mark_tel.png"));
					new Label(customerInfoArea1).setText(deliveryMobileNo + ")");
				} else {
					new Label(customerInfoArea1).setImage(BasePublicImages.getImage("images/contact/saas_mark_phone.png"));
					new Label(customerInfoArea1).setText(deliveryLandLineNumber + ")");
				}
			}
		} else {
			labelBuffer.append("��");
		}
		label.setText(labelBuffer.toString());
		labelBuffer.setLength(0);

		//
		label = new Label(customerInfoArea1);
		labelBuffer.append("    �ջ���ַ��");
		if (!StringUtils.isEmpty(deliveryAddress)) {
			labelBuffer.append(deliveryAddress);
		} else {
			labelBuffer.append("��");
		}
		label.setText(labelBuffer.toString());

		//
		new SSeparator(formArea, JWT.HORIZONTAL, 1);

		Composite workArea = new Composite(formArea);
		workArea.setLayoutData(GridData.INS_FILL_BOTH);
		workArea.setLayout(new GridLayout(3));

		Composite leftArea = new Composite(workArea);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 250;
		leftArea.setLayoutData(gd);

		Composite emptyArea = new Composite(workArea);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 1;
		emptyArea.setLayoutData(gd);

		Composite rightArea = new Composite(workArea);
		rightArea.setLayoutData(GridData.INS_FILL_BOTH);

		renderLeftArea(leftArea);
		renderRightArea(rightArea);

		//
		new Label(formArea).setText("��ע��" + (StringUtils.isEmpty(orderInfo.getRemark()) ? "��" : orderInfo.getRemark()));
	}

	private void renderLeftArea(Composite parent) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		parent.setLayout(gl);

		Composite header = new Composite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 25;
		header.setLayoutData(gd);
		header.setLayout(new GridLayout(1));

		Label label = new Label(header);
		label.setText("ѡ��ֿ⣺");
		label.setID(DistributeSalesOrderProcessor.ID_Label_StoreInfo);
		gd = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL);
		label.setLayoutData(gd);

		//
		STableColumn column = new STableColumn("name", 100, JWT.LEFT, "�ֿ�����");
		column.setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.None);
		STable table = new STable(parent, new STableColumn[] { column }, tableStyle);
		table.setID(DistributeSalesOrderProcessor.ID_Table_StoreList);
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}

	private void renderRightArea(Composite parent) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		parent.setLayout(gl);

		Composite header = new Composite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 25;
		header.setLayoutData(gd);
		header.setLayout(new GridLayout(5));

		Label label = new Label(header);
		label.setText("������ϣ�");

		label = new Label(header);
		label.setText("�������ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryDate()) + "      ���ⷢ�����ڣ�");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_VERTICAL);
		label.setLayoutData(gd);

		DatePicker datePicker = new SDatePicker(header);
		datePicker.setID(DistributeSalesOrderProcessor.ID_Text_Date);
		datePicker.setEnabled(false);
		new Label(header).setText("  ");
		label = new Label(header);
		label.setID(DistributeSalesOrderProcessor.ID_Button_ResolveAll);
		label.setText(" ʣ����ϴӱ��ⷢ�� ");
		label.setForeground(Color.COLOR_BLUE);
		label.setCursor(Cursor.HAND);
		label.setLayoutData(new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_CENTER));

		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "��������");
		columns[1] = new STableColumn("GoodsProperties", 100, JWT.LEFT, "���Ϲ��");
		columns[2] = new STableColumn("GoodsUnit", 100, JWT.CENTER, "��λ");
		columns[3] = new STableColumn("OrderCount", 100, JWT.RIGHT, "��������");
		columns[4] = new STableColumn("UnAllocate", 100, JWT.RIGHT, "δ������");
		columns[5] = new STableColumn("Available", 100, JWT.RIGHT, "�������");
		columns[6] = new SNumberEditColumn("Allocate", 100, JWT.RIGHT, "�������");
		((SNumberEditColumn) columns[6]).setFormulas(new SAsignFormula("[#Allocating]-[Allocate]", "UnAllocate"));
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		SEditTable table = new SEditTable(parent, columns, tableStyle, null);
		table.setID(DistributeSalesOrderProcessor.ID_Table_GoodsList);
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE2);
		button.setText(" ���·��� ");
		button.setID(DistributeSalesOrderProcessor.ID_Button_Reset);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" ȷ������ ");
		button.setID(DistributeSalesOrderProcessor.ID_Button_Confirm);
	}

}
