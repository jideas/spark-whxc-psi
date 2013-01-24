/**
 * 
 */
package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseFormPageProcessor;

/**
 * 
 *
 */
public class AdjustGoodsCostProcessor extends BaseFormPageProcessor {

	public final static String ID_Text_GoodsNumber = "Text_GoodsNumber";
	public final static String ID_Label_FindImg = "Label_FindImg";
	public final static String ID_Label_GoodsName = "Label_GoodsName";
	public final static String ID_Label_GoodsProperty = "Label_GoodsProperty";
	public final static String ID_List_Store = "List_Store";
	public final static String ID_Label_GoodsCount = "Label_GoodsCount";
	public final static String ID_Label_OldCost = "Label_OldCost";
	public final static String ID_Label_OldInventoryAmount = "Label_OldInventoryAmount";
	public final static String ID_Label_AdjustCost = "Label_AdjustCost";
	public final static String ID_Label_AdjustInventoryAmount = "Label_AdjustInventoryAmount";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(Situation context) {
		Button saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 确定添加
				
			}
		});
		
		Button cancelButton = this.createControl(ID_Button_Cancel, Button.class, JWT.NONE);
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 放弃添加
				
			}
		});
	}

}
