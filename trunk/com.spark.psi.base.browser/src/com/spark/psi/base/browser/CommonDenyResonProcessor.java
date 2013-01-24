package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class CommonDenyResonProcessor extends BaseFormPageProcessor {

	public static final String ID_Label_ReasonLabel = "Label_ReasonLabel";
	public static final String ID_Text_ReasonText = "Text_ReasonText";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	private boolean isNullAble = false;
	private String labelTitle  = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		if (null != getArgument()) {
			isNullAble = (Boolean) getArgument();
		}
		if (null != getArgument2()) {
			labelTitle = (String)getArgument2();
		}
	}

	@Override
	public void process(Situation context) {
		final Text text = createTextControl(ID_Text_ReasonText);
		final Button confirmButton = createButtonControl(ID_Button_Confirm);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		
		if (null != labelTitle) {
			Label label = createLabelControl(ID_Label_ReasonLabel);
			label.setText(labelTitle);
		}
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定
				if (!isNullAble) {
					if (StringUtils.isEmpty(text.getText())) {
						String alertMsg = "退回原因不能为空。";
						if (null != labelTitle) {
							alertMsg = labelTitle + "不能为空。 ";
						}
						alert(alertMsg);
						return ;
					}
				}
				getContext().bubbleMessage(new MsgResponse(true, true, text.getText()));
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 取消
				getContext().bubbleMessage(new MsgResponse(true, false));
			}
		});
	}

}
