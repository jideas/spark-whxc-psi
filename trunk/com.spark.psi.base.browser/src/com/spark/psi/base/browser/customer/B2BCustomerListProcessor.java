package com.spark.psi.base.browser.customer;

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
import com.spark.psi.publish.base.partner.entity.CustomerItem;

/**
 * 授权客户列表页面处理器
 */
public class B2BCustomerListProcessor extends PSIListPageProcessor<CustomerItem> {
	
	public static final String ID_LABEL_USECOUNT = "Label_UserCount";
	public static final String ID_LABEL_REMAINCOUNT = "Label_RemainCount";
	public static final String ID_TEXT_SEARCHTEXT = "Text_SearchText";
	public static final String ID_BUTTON_SEARCH = "Button_Search";
	public static final String ID_BUTTON_NEW = "Button_New";
	public static final String ID_BUTTON_DEL = "Button_Del";
	
	private int userCount = 0;
	private int remainCount = 0;
	
	@Override
	public void process(Situation situation) {
		
		super.process(situation);
		
		Label useCountLabel = this.createControl(ID_LABEL_USECOUNT, Label.class, JWT.NONE);
		useCountLabel.setText(String.valueOf(userCount));
		
		Label remainCountLabel = this.createControl(ID_LABEL_REMAINCOUNT, Label.class, JWT.NONE);
		remainCountLabel.setText(String.valueOf(remainCount));
		
		Button newButton = this.createControl(ID_BUTTON_NEW, Button.class, JWT.NONE);
		newButton.setText("新增授权客户");
		newButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		Button delButton = this.createControl(ID_BUTTON_DEL, Button.class, JWT.NONE);
		delButton.setText("撤消授权");
		delButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return new Object[] {};
	}
	public String getElementId(Object element) {
		return ((CustomerItem)element).getName();
	}
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Cancel.name() };
	}
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Cancel.name())) {
			// TODO：处理启用动作
		}
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
