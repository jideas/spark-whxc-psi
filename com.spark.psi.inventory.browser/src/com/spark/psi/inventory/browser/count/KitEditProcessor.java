package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;

/**
 * ����������Ʒ���ڣ�����̵㵥����ã�
 */
public class KitEditProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	public final static String ID_Button_Confirm_New = "Button_Confirm_New";
	public final static String ID_Button_GiveUp_New = "Button_GiveUp_New";
	
	public final static String ID_Text_KitName = "Text_KitName";
	public final static String ID_Text_KitDescription = "Text_KitDescription";
	public final static String ID_Text_Unit = "Text_Unit";
	public final static String ID_Text_ActualCount = "Text_ActualCount";
	public final static String ID_Text_Memo = "Text_Memo";
	
	private Text textKitName;
	private Text textKitDescription;
	private Text textUnit;
	private Text textActualCount;
	private Text textMemo;
		
	@Override
	public void process(final Situation context) {	
		
		textKitName = this.createControl(ID_Text_KitName, Text.class);
		textKitDescription = this.createControl(ID_Text_KitDescription, Text.class);
		textUnit = this.createControl(ID_Text_Unit, Text.class);
		textActualCount = this.createControl(ID_Text_ActualCount, Text.class);
		textMemo = this.createControl(ID_Text_Memo, Text.class);	
		
		registerNotEmptyValidator(textKitName, "��Ʒ����");
		registerNotEmptyValidator(textKitDescription, "��Ʒ����");
		registerNotEmptyValidator(textUnit, "��λ");
//		registerNotEmptyValidator(textActualCount, "ʵ������");
		
		this.createControl(ID_Button_Confirm_New, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}			
		});		
		this.createControl(ID_Button_GiveUp_New, Button.class).addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				context.bubbleMessage(new MsgResponse(true,null));
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
		
		Kit kit = new Kit();
		kit.setKitName(textKitName.getText());
		kit.setKitDesc(textKitDescription.getText());
		kit.setKitUnit(textUnit.getText());
		kit.setActualCount(Double.parseDouble(textActualCount.getText()));//ʵ������
		kit.setMemo(textMemo.getText());
		getContext().bubbleMessage(new MsgResponse(true,kit));
		return true;
	}
}