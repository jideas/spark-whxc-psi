package com.spark.psi.inventory.browser.init;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;

/**
 * 仓库启用设置-其它库存-添加物品表单
 * @author 龚海淘
 * @version 2012-4-24
 */
public class KitEditProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	public final static String ID_Button_ConfirmNew = "Button_ConfirmNew";
	public final static String ID_Button_GiveUpNew = "Button_GiveUpNew";	
	public final static String ID_Text_KitName = "Text_KitName";
	public final static String ID_Text_KitDescription = "Text_KitDescription";
	public final static String ID_Text_Unit = "Text_Unit";
	public final static String ID_Text_Count = "Text_Count";	
	private Text textKitName;
	private Text textKitDescription;
	private Text textUnit;
	private Text textCount;	
	private Button btnOk;
	private Button btnCancel;

	@Override
	public void process(final Situation context) {
		super.postProcess(context);
		textKitName = this.createControl(ID_Text_KitName, Text.class);
		textKitDescription = this.createControl(ID_Text_KitDescription, Text.class);
		textUnit = this.createControl(ID_Text_Unit, Text.class);
		textCount = this.createControl(ID_Text_Count, Text.class);
		btnOk = this.createControl(ID_Button_ConfirmNew, Button.class);		
		btnCancel = this.createControl(ID_Button_GiveUpNew, Button.class);
		registerNotEmptyValidator(textKitName, "物品名称");
		registerNotEmptyValidator(textKitDescription, "物品描述");
		registerNotEmptyValidator(textUnit, "单位");
		registerNotEmptyValidator(textCount, "初始数量");		
		initEvent();		
	}

	private void initEvent() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}			
		});		
		btnCancel.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true,null));
			}
		});
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (!validateInput()) {
			return false;
		}
		String kitName = textKitName.getText();
		String kitDescription = textKitDescription.getText();
		String kitUnit = textUnit.getText();
		String kitCount = textCount.getText();
		StoreInitKitItem item = new StoreInitKitItem();
		item.setKitName(kitName);
		item.setKitDescription(kitDescription);
		item.setUnit(kitUnit);
		item.setCount(Double.parseDouble(kitCount));
		getContext().bubbleMessage(new MsgResponse(true,item));
		return true;
	}
}