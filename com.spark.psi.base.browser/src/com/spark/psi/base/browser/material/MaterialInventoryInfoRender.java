package com.spark.psi.base.browser.material;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.psi.base.browser.goods.GoodsInventoryInfoProcessor;

public class MaterialInventoryInfoRender extends AbstractBoxPageRender {

	@Override
	protected void afterFooterRender() {
		footerArea.setLayout(new GridLayout());
		Button saveBtn = new Button(footerArea, JWT.APPEARANCE3);
		saveBtn.setID(GoodsInventoryInfoProcessor.ID_Button_Save);
		saveBtn.setText(" 保存阈值 ");
		GridData gdBtn = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_END | GridData.GRAB_VERTICAL);
		gdBtn.heightHint = 29;
		saveBtn.setLayoutData(gdBtn);
	}

	@Override
	protected void beforeFooterRender() {
		Composite headerArea = new Composite(contentArea);
		headerArea.setLayout(new GridLayout(2));
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.heightHint = 32;
		headerArea.setLayoutData(gdHeader);

		GridData gdHLeft = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.GRAB_HORIZONTAL);
		gdHLeft.heightHint = 24;
		GridData gdHRight = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		gdHRight.heightHint = 24;
		
		Composite headerLeftArea = new Composite(headerArea);
		Composite headerRightArea = new Composite(headerArea);
		
		GridLayout glHLeft = new GridLayout();
		glHLeft.numColumns = 5;
		
		GridLayout glHRight = new GridLayout();
		glHRight.numColumns = 5;
		
		headerLeftArea.setLayout(glHLeft);
		headerRightArea.setLayout(glHRight);
		headerLeftArea.setLayoutData(gdHLeft);
		headerRightArea.setLayoutData(gdHRight);
//		
		Composite tableArea = new Composite(contentArea);
		tableArea.setID(GoodsInventoryInfoProcessor.ID_Area_Content);
		tableArea.setLayoutData(GridData.INS_FILL_BOTH);
		tableArea.setLayout(new FillLayout());
		
//		
		GridData gdLabel = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_CENTER);
		
		Label label = new Label(headerLeftArea);
		label.setText("编号："); 
		label.setLayoutData(gdLabel);
		new Label(headerLeftArea).setID(GoodsInventoryInfoProcessor.ID_Label_Code);
		new Label(headerLeftArea).setText("  ");
		new Label(headerLeftArea).setText("材料名称：");
		new Label(headerLeftArea).setID(GoodsInventoryInfoProcessor.ID_Label_Name);
		
		GridData gdList = new GridData();
		gdList.widthHint = 90;
		label = new Label(headerRightArea);
		label.setLayoutData(gdLabel);
		label.setText("          阈值控制范围：");
		LWComboList list = new LWComboList(headerRightArea, JWT.APPEARANCE3);
		list.setID(GoodsInventoryInfoProcessor.ID_List_Scope);
		list.setLayoutData(gdList);
		new Label(headerRightArea).setText("  ");
		new Label(headerRightArea).setText("控制类型：");
		list = new LWComboList(headerRightArea, JWT.APPEARANCE3);
		list.setID(GoodsInventoryInfoProcessor.ID_List_Type);
		list.setLayoutData(gdList);
	}


}
