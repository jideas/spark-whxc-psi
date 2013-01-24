package com.spark.psi.account.browser.invoice;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageProcessor;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.account.task.InvalidateInvoiceTask;

public class InvalidatePageProcessor extends PageProcessor {
	
	public static final String ID_Text_Reason = "Text_Reason";
	public static final String ID_Button_Check = "Button_Check";
	public static final String ID_Button_Abort = "Button_Abort";
	
	
	private GUID itemId = null;
	
	@Override
	public void process(final Situation context) {
		if(getArgument() != null && getArgument() instanceof GUID) {
			itemId = (GUID)getArgument(); 
		}
		
		final Text reasonText = createControl(ID_Text_Reason, Text.class);
		
		registerNotEmptyValidator(reasonText, "×÷·ÏÔ­Òò");
		
		final Button checkButton = createControl(ID_Button_Check, Button.class);
		checkButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!validateInput()) {
					return;
				}
				InvalidateInvoiceTask task = new InvalidateInvoiceTask(itemId, reasonText.getText());
				context.handle(task);
				context.bubbleMessage(new MsgResponse(true));
			}
		});
		
		final Button abortButton = createControl(ID_Button_Abort, Button.class);
		abortButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.bubbleMessage(new MsgResponse(true));
			}
		});
	}

}
