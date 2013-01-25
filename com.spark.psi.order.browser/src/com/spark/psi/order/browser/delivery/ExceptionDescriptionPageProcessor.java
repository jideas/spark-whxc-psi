package com.spark.psi.order.browser.delivery;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class ExceptionDescriptionPageProcessor extends BaseFormPageProcessor {

	public static final String ID_Text_Description = "Text_Description";
	public static final String ID_Button_Confrim = "Button_Confirm";
	public static final String ID_Text_ReceivePackage = "Text_ReceivePackage";
	public static final String ID_Text_Formula = "Text_Formula";
	
	public static enum Method {
		Exception, Handle
	}
	
	private ExceptionDescriptionPageProcessor.Method method;
	@Override
	public void init(Situation context) {
		method = (ExceptionDescriptionPageProcessor.Method)getArgument();
	}

	@Override
	public void process(Situation context) {
		final Button button = createButtonControl(ID_Button_Confrim);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!doValidateInput()) return;
				if (Method.Exception == method) {
					Text countText = createTextControl(ID_Text_ReceivePackage);
					Text descText = createTextControl(ID_Text_Description);
					getContext().bubbleMessage(new MsgResponse(true, countText.getText(), descText.getText()));
				} else {
					Text text = createTextControl(ID_Text_Formula);
					getContext().bubbleMessage(new MsgResponse(true, text.getText()));
				}
			}
		});
	}

	private boolean doValidateInput() {
		boolean result = true;
		if (Method.Exception == method) {
			Text text = createControl(ID_Text_Description, Text.class);
			if (StringUtils.isEmpty(text.getText())) {
				alert("描述不能为空。");
				result = false;
			}
		} else {
			Text text = createControl(ID_Text_Formula, Text.class);
			if (StringUtils.isEmpty(text.getText())) {
				alert("处理方案不能为空。");
				result = false;
			}
		}
		return result;
	}
}
