package com.spark.psi.account.browser.invoice;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.psi.account.browser.PartnerSelectPage;

/**
 * 新建开票视图
 */
public class NewInvoiceRender extends AbstractBoxPageRender {

	protected Composite headerArea;
	protected Composite leftArea;
	protected Composite rightArea;
	
	private static GridData gdLabel;
	private static GridData gdInput;
	
	static  {
		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gdInput = new GridData(GridData.FILL_HORIZONTAL);
	}
	
	@Override
	protected void afterFooterRender() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void beforeFooterRender() {
		Composite mainArea = new Composite(contentArea);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.horizontalSpacing = 5;
		mainArea.setLayout(gl);
		mainArea.setLayoutData(GridData.INS_FILL_BOTH);
		
		
		headerArea = new Composite(mainArea);
		GridData gdHead = new GridData();
		gdHead.heightHint = 24;
		gdHead.horizontalSpan = 2;
		gdHead.widthHint = 270;
		headerArea.setLayoutData(gdHead);
		GridLayout glHead = new GridLayout(2);
		glHead.horizontalSpacing = 20;
		headerArea.setLayout(glHead);

		//
		leftArea = new Composite(mainArea);
		GridData gdLeft = new GridData(GridData.FILL_VERTICAL);
		gdLeft.widthHint = gdHead.widthHint;
		leftArea.setLayoutData(gdLeft);
		leftArea.setLayout(new FillLayout());
		
		rightArea = new Composite(mainArea);
		GridData gdRight = new GridData(GridData.FILL_VERTICAL);
		gdRight.widthHint = 300;
		rightArea.setLayoutData(gdRight);
		GridLayout glRight = new GridLayout();
		glRight.numColumns = 2;
		glRight.verticalSpacing = 6;
		glRight.marginLeft = 10;
		glRight.marginTop = 10;
		rightArea.setLayout(glRight);
		
		Label selectedObjLabel = new Label(headerArea);
		selectedObjLabel.setText("选择客户：");
		selectedObjLabel.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));
		SSearchText2 search = new SSearchText2(headerArea);
		search.setID(NewInvoiceProcessor.ID_Search);
		search.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new PartnerSelectPage(leftArea, PartnerSelectPage.ContentStyle.CustomerOnly).setID(NewInvoiceProcessor.ID_PartnerPage);
		
		
		SAsteriskLabel label0 = new SAsteriskLabel(rightArea, "客户名称：");
		label0.setLayoutData(gdLabel);
		Text text0 = new Text(rightArea, JWT.APPEARANCE3);
		text0.setID(NewInvoiceProcessor.ID_Label_Name);
		text0.setLayoutData(gdInput);
		
		SAsteriskLabel label1 = new SAsteriskLabel(rightArea, "发票类型：");
		label1.setLayoutData(gdLabel);
		LWComboList typeList = new LWComboList(rightArea, JWT.APPEARANCE3);
		typeList.setID(NewInvoiceProcessor.ID_List_Type);
		typeList.setLayoutData(gdInput);
		
		SAsteriskLabel label2 = new SAsteriskLabel(rightArea, "发 票 号：");
		label2.setLayoutData(gdLabel);
		Text text2 = new Text(rightArea, JWT.APPEARANCE3);
		text2.setID(NewInvoiceProcessor.ID_Text_Code);
		text2.setLayoutData(gdInput);
		
		SAsteriskLabel label3 = new SAsteriskLabel(rightArea, "开票金额：");
		label3.setLayoutData(gdLabel);
		Text text3 = new Text(rightArea, JWT.APPEARANCE3);
		text3.setID(NewInvoiceProcessor.ID_Text_Amount);
		text3.setLayoutData(gdInput);
		text3.setRegExp(TextRegexp.POSITIVE_DOUBLE_LIMIT);
		
		SAsteriskLabel label4 = new SAsteriskLabel(rightArea, "开 票 人：");
		label4.setLayoutData(gdLabel);
		Text text4 = new Text(rightArea, JWT.APPEARANCE3);
		text4.setID(NewInvoiceProcessor.ID_Text_Hander);
		text4.setLayoutData(gdInput);
		
		SAsteriskLabel label5 = new SAsteriskLabel(rightArea, "开票日期：");
		label5.setLayoutData(gdLabel);
		SDatePicker text5 = new SDatePicker(rightArea);
		text5.setID(NewInvoiceProcessor.ID_Date_Date);
		text5.setLayoutData(gdInput);
		
		Button checkButton = new Button(rightArea, JWT.APPEARANCE3);
		checkButton.setID(NewInvoiceProcessor.ID_Button_Check);
		checkButton.setText(" 确认开票 ");
		GridData gdCheck = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gdCheck.horizontalSpan = 2;
		checkButton.setLayoutData(gdCheck);
	}



}
