package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * 指定商品的库存情况列表处理器
 * 
 */
public class GoodsInventoryListProcessor extends
		PSIListPageProcessor<InventoryInfo> {
	public final static String ID_Label_Number = "Label_Number";
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_List_Scope = "List_Scope";
	public final static String ID_List_Type = "List_Type";
	public final static String ID_Label_Property = "Label_Property";
	public final static String ID_Label_Unit = "Label_Unit";
	public final static String ID_Label_status = "Label_status";
	public final static String ID_Button_Save = "Button_Save";
	@Override
	public void process(Situation situation) {
		super.process(situation);
		
		Label numberLabel = this.createControl(ID_Label_Number, Label.class, JWT.NONE);
		numberLabel.setText("sp2012000000");
		
		Label nameLabel = this.createControl(ID_Label_Name, Label.class, JWT.NONE);
		nameLabel.setText("18万的龙井");
		
		Label propertyLabel = this.createControl(ID_Label_Property, Label.class, JWT.NONE);
		propertyLabel.setText("[盒]");
		
		Label unitLabel = this.createControl(ID_Label_Unit, Label.class, JWT.NONE);
		unitLabel.setText("克");
		
		Label statusLabel = this.createControl(ID_Label_status, Label.class, JWT.NONE);
		statusLabel.setText("在售");
		
		Button saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 保存
				
			}
		});
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return null;
	}

	public String getElementId(Object element) {
		return "";
	}

	public String getValue(Object element, int columnIndex) {
		InventoryInfo item = (InventoryInfo) element;
		switch (columnIndex) {
		case 0:
			// TODO 取仓库名称
//			return item.getStoreId();
		case 1:
			return String.valueOf(item.getUpperLimitAmount());
		case 2:
			return String.valueOf(item.getCount());
		case 3:
			return String.valueOf(item.getPurchasingCount());
		case 4:
			return String.valueOf(item.getDeliveryingCount());
		default:
			return null;
		}
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Clear.name() };
	}

	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Clear.name() };
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
//		if (actionName.equals(Action.Clear.name())) {
//			// TODO：清空
//		} 
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
