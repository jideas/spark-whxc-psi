/**
 * 
 */
package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.inventory.browser.internal.InventoryImages;

/**
 * 
 *
 */
public class AdjustGoodsCostRender extends BaseFormPageRender {

	@Override
	protected void renderFormArea(Composite formArea) {
		new Text(formArea).setID(AdjustGoodsCostProcessor.ID_Text_GoodsNumber);
		Label findLabel = new Label(formArea);
		findLabel.setID(AdjustGoodsCostProcessor.ID_Label_FindImg);
		findLabel.setBackimage(InventoryImages
				.getImage("images/query/saas_mark_find.png"));
		new Label(formArea).setID(AdjustGoodsCostProcessor.ID_Label_GoodsName);
		new Label(formArea)
				.setID(AdjustGoodsCostProcessor.ID_Label_GoodsProperty);
		new ComboList(formArea).setID(AdjustGoodsCostProcessor.ID_List_Store);
		new Label(formArea).setID(AdjustGoodsCostProcessor.ID_Label_GoodsCount);
		new Label(formArea).setID(AdjustGoodsCostProcessor.ID_Label_OldCost);
		new Label(formArea)
				.setID(AdjustGoodsCostProcessor.ID_Label_OldInventoryAmount);
		new Label(formArea).setID(AdjustGoodsCostProcessor.ID_Label_AdjustCost);
		new Label(formArea)
				.setID(AdjustGoodsCostProcessor.ID_Label_AdjustInventoryAmount);

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea);
		saveButton.setID(AdjustGoodsCostProcessor.ID_Button_Save);
		saveButton.setText("确定添加");

		Button cancelButton = new Button(buttonArea);
		cancelButton.setID(AdjustGoodsCostProcessor.ID_Button_Cancel);
		cancelButton.setText("放弃添加");

	}

}
