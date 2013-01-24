package com.spark.psi.inventory.browser.checkout;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.inventory.browser.checkout.KitsSelectProcessor.SelectItem;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;
import com.spark.psi.publish.Action;

/**
* 已选物品列表,悬浮显示
*/
public class SelectedKitsItemListProcessor extends PSIListPageProcessor<Kit>{	
	
	public final static String ID_Label_SelectedCount = "Label_SelectedCount"; 
	public final static String ID_Label_Clear = "Label_Clear";
	private Map<String, Kit> selectedItemList = new LinkedHashMap<String, Kit>();
	private Label selectedCountLabel;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(Situation situation) {
		super.init(situation);
		this.selectedItemList = (Map<String, Kit>) this.getArgument();
	}
	
	@Override
	public void process(Situation situation) {		
		super.process(situation);	
		selectedCountLabel = this.createControl(ID_Label_SelectedCount,Label.class);
		this.createControl(ID_Label_Clear, Label.class).addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				selectedItemList.clear();
				table.render();
				updateCountLabel();
				notifyListPage();
			}
		});		
		updateCountLabel();
	}
	
	/**
	 * 更新已选择材料数量的最新显示
	 */
	private void updateCountLabel() {
		selectedCountLabel.setText(String.valueOf(selectedItemList.size()));
	}
	
	/**
	 * 通知上级变化
	 */
	private void notifyListPage() {
		getContext().bubbleMessage(new SelectItem(selectedItemList));
	}

	@Override
	public String getElementId(Object element) {
		Kit item = (Kit) element;
		return item.getId();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return selectedItemList.values().toArray();
	}
	
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}
	
	@Override
	public void actionPerformed(final String rowId, String actionName,String actionValue) {

		if (Action.Delete.name().equals(actionName)) {			
			confirm("确认删除当前记录？",
				new Runnable() {
					public void run() {
						delete(rowId);
					}
				}
			);	
		}
	}

	protected void delete(String rowId) {
		table.removeRow(rowId);
		table.renderUpate();							
		selectedItemList.remove(rowId);
		updateCountLabel();
		notifyListPage();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}