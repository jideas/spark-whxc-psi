package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.psi.base.browser.AbstractFormRender;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;

public abstract class AbstractGoodsSplitOrderRender extends AbstractFormRender {
	
//	protected ProduceOrderInfo orderInfo  =  null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
//		GUID orderId = (GUID)getArgument();
//		orderInfo = context.find(ProduceOrderInfo.class, orderId);
	}

	@Override
	protected final void fillContent() {
		Composite tableArea = new Composite(contentArea);
		GridLayout glTable = new GridLayout();
		glTable.numColumns = 2;
		glTable.horizontalSpacing = 10;
		tableArea.setLayout(glTable);
		tableArea.setLayoutData(GridData.INS_FILL_BOTH);
		
		renderTable(tableArea);
		
		Composite remarkArea = new Composite(contentArea);
		GridLayout glRemark = new GridLayout(2);
		glRemark.marginTop = 5;
		glRemark.marginBottom = 5;
		glRemark.marginLeft = 10;
		glRemark.marginRight = 10;
		remarkArea.setLayout(glRemark);
		remarkArea.setBackground(Color.COLOR_WHITE);
		remarkArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));
		
		GridData gdMemoMain = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoMain.heightHint = 60;
		gdMemoMain.verticalIndent = 5;
		remarkArea.setLayoutData(gdMemoMain);

		Label titleLabel = new Label(remarkArea);
		titleLabel.setText("备注：");
		titleLabel.setLayoutData(new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END));

		Text text = new Text(remarkArea, JWT.APPEARANCE3);
		GridData gdMemo = new GridData(GridData.FILL_BOTH);
		text.setLayoutData(gdMemo);
		text.setID(AbstractGoodsSplitOrderProcessor.ID_Text_Remark);
		
		Label label = new Label(remarkArea);
		label.setID(AbstractGoodsSplitOrderProcessor.ID_Label_StopCause);
		GridData gdLabel = new GridData(GridData.FILL_HORIZONTAL);
		gdLabel.horizontalSpan = 2;
		label.setLayoutData(gdLabel);
	}

	@Override
	protected final void fillHeader() {
//		GridData gd = new GridData();
//		gd.widthHint = 120;
//		
//		new Label(headerLeftArea).setText("计划完成日期：");
//		Label lb  = new Label(headerLeftArea);
//		lb.setID(AbstractGoodsSplitOrderProcessor.ID_Label_PlanDate);
//		lb.setLayoutData(gd);
//		
//		new Label(headerRightArea).setID(AbstractGoodsSplitOrderProcessor.ID_Label_ReceiveReturn);
//		new Label(headerRightArea).setText("   单据状态：");
//		new Label(headerRightArea).setID(AbstractGoodsSplitOrderProcessor.ID_Label_Status);
	}
	
	protected abstract void renderTable(Composite tableArea);
	
}
