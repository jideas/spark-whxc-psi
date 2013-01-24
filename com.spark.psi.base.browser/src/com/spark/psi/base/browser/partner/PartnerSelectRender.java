package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.BasePublicImages;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.base.partner.entity.PartnerShortItem;

public abstract class PartnerSelectRender extends PSIListPageRender {

	// protected boolean contactSelect;
	// protected boolean delieverySelect;
	// protected boolean enableCreate;
	protected GUID partnerId;

	private final static ImageDescriptor imgTel = BasePublicImages.getImage("images/contact/saas_mark_phone.png");
	private final static ImageDescriptor imgPhone = BasePublicImages.getImage("images/contact/saas_mark_tel.png");

	public void init(final Situation context) {
		super.init(context);
		// contactSelect = (Boolean) this.getArgument();
		// delieverySelect = (Boolean) this.getArgument2();
		// enableCreate = (Boolean) this.getArgument3();
		if (this.getArgument4() != null) {
			partnerId = (GUID) this.getArgument4();
		}
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn("Name", 200, JWT.LEFT, "名称");
		columns[1] = new STableColumn("Address", 200, JWT.LEFT, "地址");
		columns[1].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.Single);
		return tableStyle;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((PartnerShortItem) element).getShortName();
		case 1:
			return ((PartnerShortItem) element).getAddress();
		}
		return "";
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((PartnerShortItem) element).getName();
		}
		return "";
	}

	@Override
	protected void afterTableRender() {
		super.afterTableRender();

		// if (contactSelect || delieverySelect) {
		// CBorder border = new CBorder();
		// border.setColor(0x78a9bf);
		// Composite composite = new Composite(contentArea);
		// composite.setBorder(border);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.verticalIndent = -1;
		// composite.setLayoutData(gd);
		// GridLayout gl = new GridLayout();
		// gl.numColumns = 2;
		// gl.verticalSpacing = 15;
		// gl.horizontalSpacing = 10;
		// gl.marginLeft = gl.marginTop = gl.marginBottom = gl.marginRight = 10;
		// composite.setLayout(gl);
		//
		// Label label = new Label(composite);
		// label.setID(PartnerSelectProcessor.ID_Label_PartnerInfo);
		//
		// gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.horizontalSpan = 2;
		// label.setLayoutData(gd);
		//
		// GridData gdContact = new GridData();
		// gdContact.widthHint = 270;
		//
		// if (contactSelect) {
		// //
		// Composite contactArea = new Composite(composite);
		// contactArea.setLayoutData(gdContact);
		// contactArea.setLayout(new GridLayout(4));
		//
		// label = new Label(contactArea);
		// label.setText(getPartnerType() + "联系人：");
		//
		// ComboList comboList = new ComboList(contactArea,
		// JWT.APPEARANCE3);
		// comboList.setID(PartnerSelectProcessor.ID_List_ContactInfo);
		//
		// label = new Label(contactArea);
		// label.setImage(BaseImages.getImage("images/icons/edit1.png"));
		// label.setCursor(Cursor.HAND);
		// label.setID(PartnerSelectProcessor.ID_Label_ContactEdit);
		// label.setToolTipText("编辑联系人");
		// label = new Label(contactArea);
		// label.setCursor(Cursor.HAND);
		// label.setImage(BaseImages.getImage("images/icons/add1.png"));
		// label.setID(PartnerSelectProcessor.ID_Label_ContactAdd);
		// label.setToolTipText("新增联系人");
		// }
		//
		// Composite addressArea = new Composite(composite);
		// addressArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		// addressArea.setLayout(new GridLayout(4));
		// if (delieverySelect) {
		//
		// //
		// label = new Label(addressArea);
		// label.setText("收货地址：");
		//
		// ComboList comboList = new ComboList(addressArea,
		// JWT.APPEARANCE3);
		// comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		// comboList.setID(PartnerSelectProcessor.ID_List_DeliverInfo);
		// label = new Label(addressArea);
		// label.setCursor(Cursor.HAND);
		// label.setImage(BaseImages.getImage("images/icons/edit1.png"));
		// label.setID(PartnerSelectProcessor.ID_Label_DeliverEdit);
		// label.setToolTipText("编辑收货地址");
		// label = new Label(addressArea);
		// label.setID(PartnerSelectProcessor.ID_Label_DeliverAdd);
		// label.setCursor(Cursor.HAND);
		// label.setImage(BaseImages.getImage("images/icons/add1.png"));
		// label.setToolTipText("新增收货地址");
		// } else {
		// new Label(addressArea);
		// }
		//
		// if (contactSelect) {
		// Composite contactArea2 = new Composite(composite);
		// contactArea2.setLayoutData(gdContact);
		// contactArea2.setLayout(new GridLayout(4));
		// //
		// new Label(contactArea2).setImage(imgPhone);
		// new Label(contactArea2)
		// .setID(PartnerSelectProcessor.ID_Label_ContactMobile);
		// new Label(contactArea2).setImage(imgTel);
		// new Label(contactArea2)
		// .setID(PartnerSelectProcessor.ID_Label_ContactLandline);
		// }
		//
		// if (delieverySelect) {
		// Composite addressArea2 = new Composite(composite);
		// addressArea2.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		// addressArea2.setLayout(new GridLayout(8));
		// //
		// new Label(addressArea2).setText("收货人：");
		// new Label(addressArea2)
		// .setID(PartnerSelectProcessor.ID_Label_DeliverMan);
		// new Label(addressArea2).setText("(");
		// new Label(addressArea2).setImage(imgPhone);
		// new Label(addressArea2)
		// .setID(PartnerSelectProcessor.ID_Label_DeliverMobile);
		// new Label(addressArea2).setImage(imgTel);
		// new Label(addressArea2)
		// .setID(PartnerSelectProcessor.ID_Label_DeliverLandline);
		// new Label(addressArea2).setText(")");
		// }
		// }
	}

	protected void afterFooterRender() {
		super.afterFooterRender();

		//
		Text text = new SSearchText2(headerRightArea);
		text.setID(PartnerSelectProcessor.ID_Text_Search);

		Button button = null;
		// if (enableCreate) {
		// button = new Button(footerLeftArea, JWT.APPEARANCE2);
		// button.setID(PartnerSelectProcessor.ID_Button_New);
		// button.setText(" 新增" + getPartnerType() + " ");
		// }
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setID(PartnerSelectProcessor.ID_Button_Confirm);
		button.setText(" 确定选择 ");
		button.setEnabled(false);
		new Label(footerRightArea).setText(" ");
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setID(PartnerSelectProcessor.ID_Button_Cancel);
		button.setText(" 放弃选择 ");
	}

	protected abstract String getPartnerType();
}
