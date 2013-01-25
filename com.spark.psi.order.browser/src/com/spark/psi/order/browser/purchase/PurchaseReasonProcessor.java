/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;

/**
 * 
 *
 */
public class PurchaseReasonProcessor extends BaseFormPageProcessor {
	public final static String ID_Text_Reason = "Text_Reason";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Cancel = "Button_Cancel";

	private Text reasonText;

	private String reason;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			reason = (String) this.getArgument();
		}
	}

	@Override
	public void process(Situation context) {
		reasonText = createControl(ID_Text_Reason, Text.class);
		if (reason != null) {
			reasonText.setText(reason);
		}
		Button saveButton = this.createControl(ID_Button_Save, Button.class,
				JWT.NONE);
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String str = reasonText.getText();
				String head = null;
				if(null != getArgument2() && getArgument2() instanceof String){
					head = (String)getArgument2();
				}
				if((null != head && !"".equals(head)) && (null == str || "".equals(str.trim()))){
					alert(head+"²»ÄÜÎª¿Õ£¡");
					return;
				}
				getContext().bubbleMessage(
						new MsgResponse(true, str.trim()));

			}
		});

		Button cancelButton = this.createControl(ID_Button_Cancel,
				Button.class, JWT.NONE);
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
	}
}
