package com.spark.psi.order.browser.online;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.encrypt.MD5;
import com.spark.portal.browser.MsgResponse;

public class ConfirmFinishProcessor extends BaseFormPageProcessor {

	public static final String ID_Text_Code = "Text_Code";
	public static final String ID_Check_NoCode = "Check_NoCode";
	public static final String ID_Label_ReasonLabel = "Label_ReasonLabel";
	public static final String ID_Text_Reason = "Text_Reason";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	private String sourceCode = "";
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		sourceCode = (String) getArgument();
	}


	@Override
	public void process(Situation context) {
		final Text codeText = createTextControl(ID_Text_Code); 
		final Text reasonText = createTextControl(ID_Text_Reason);
		final Button noCheck = createButtonControl(ID_Check_NoCode);
		final Button confirmButton = createButtonControl(ID_Button_Confirm);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		
		reasonText.setEnabled(false);
		
		noCheck.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 无验证码
				if (noCheck.getSelection()) {
					reasonText.setEnabled(true);
					
					codeText.setText(null);
					codeText.setEnabled(false);
				} else {
					reasonText.setEnabled(false);
					
					codeText.setEnabled(true);
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定
				if (noCheck.getSelection()) {
					String reason = reasonText.getText();
					if (StringUtils.isEmpty(reason)) {
						alert("原因不能为空。");
						return;
					}
					boolean isSuccess = true;
					getContext().bubbleMessage(new MsgResponse(true, isSuccess, reason));
				} else {
					String desCode = codeText.getText();
					if (StringUtils.isEmpty(desCode)) {
						alert("验证码不能为空。");
						return;
					}
					if (new MD5().getMD5ofStr(desCode.trim()).equals(sourceCode)) {
						boolean isSuccess = true;
						getContext().bubbleMessage(new MsgResponse(true, isSuccess));
					} else {
						alert("验证码不正确。");
					}
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 取消
				boolean isSuccess = false;
				getContext().bubbleMessage(new MsgResponse(true, isSuccess));
			}
		});

	}

}
