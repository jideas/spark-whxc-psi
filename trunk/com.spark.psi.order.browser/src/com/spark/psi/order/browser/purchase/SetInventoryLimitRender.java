package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.CommonImages;
import com.spark.common.components.pages.PageRender;

public class SetInventoryLimitRender extends PageRender{

	@Override
	protected void beforeRender() {

		//容器构造
		contentArea.setLayout(new GridLayout(1));
		Composite selectCmp = new Composite(contentArea);
		selectCmp.setLayout(new GridLayout(3));
		new Label(selectCmp).setText("         ");
		final Button count = new Button(selectCmp, JWT.RADIO);
		count.setID(SetInventoryLimitProcessor.ID_SELECT_COUNT);
		count.setText("数量      ");
		count.setSelection(true);
		
		final Button amount = new Button(selectCmp, JWT.RADIO);
		amount.setID(SetInventoryLimitProcessor.ID_SELECT_AMOUNT);
		amount.setText("金额");
		
		
		Composite textCmp = new Composite(contentArea);
		textCmp.setLayout(new GridLayout(1));
		
		Composite textCmp1 = new Composite(textCmp);
		textCmp1.setLayout(new GridLayout(2));
		new Label(textCmp1).setText("库存上限：");
		final Text upper = new Text(textCmp1, JWT.APPEARANCE3);
		upper.setID(SetInventoryLimitProcessor.ID_TEXT_UPPER);
		
		
		final Composite textCmp2 = new Composite(textCmp);
		textCmp2.setLayout(new GridLayout(2));
		new Label(textCmp2).setText("库存下限：");
		final Text lower = new Text(textCmp2, JWT.APPEARANCE3);
		lower.setID(SetInventoryLimitProcessor.ID_TEXT_LOWER);
		
		
		//填充容器
		Composite foo1 = new Composite(contentArea);
		GridData fooData = new GridData(GridData.FILL_BOTH);
		foo1.setLayoutData(fooData);
		
		//button
		Composite footerArea = new Composite(contentArea);
		footerArea.setBackimage(CommonImages
				.getImage("img/page/MTabsarea_bottom.png"));
		GridData gdFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdFooter.heightHint = 34;
		footerArea.setLayoutData(gdFooter);
		footerArea.setLayout(new GridLayout(3));
		//填充容器
		Composite foo2 = new Composite(footerArea);
		foo2.setLayoutData(fooData);
		
		Button confrim = new Button(footerArea, JWT.APPEARANCE3);
		confrim.setID(SetInventoryLimitProcessor.ID_BUTTON_CONFIRM);
		confrim.setText(" 确认修改 ");
		//
		Button cancel = new Button(footerArea, JWT.APPEARANCE3);
		cancel.setID(SetInventoryLimitProcessor.ID_BUTTON_CANCEL);
		cancel.setText(" 放弃修改 ");
	}
	
	private final static GridData gdFooter;
	static {
		gdFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdFooter.heightHint = 34;
	}

	@Override
	protected void doRender() {
		
	}

}
