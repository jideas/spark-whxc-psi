package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;

public class PayProcessor extends BaseFormPageProcessor {

	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Label_PaidAmount = "Label_PaidAmount";
	public static final String ID_Label_MolingAmount = "Label_MolingAmount";
	public static final String ID_Text_CurrMolingAmount = "Text_CurrMolingAmount";
	public static final String ID_Text_PayAmount = "Text_PayAmount";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	private JointSettlementInfo info = null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		info = (JointSettlementInfo) getArgument();
	}


	@Override
	public void process(Situation context) {
		final Label amountLabel = createLabelControl(ID_Label_Amount);
		final Label paidLabel = createLabelControl(ID_Label_PaidAmount);
		final Label molingLabel = createLabelControl(ID_Label_MolingAmount);
		final Text currMolingText = createTextControl(ID_Text_CurrMolingAmount);
		final Text payText = createTextControl(ID_Text_PayAmount);
		final Button confirmButton = createButtonControl(ID_Button_Confirm);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		
		registerNotEmptyValidator(payText, "本次付款金额");

		registerInputValidator(new TextInputValidator(payText, "本次付款金额必须大于0，且付款总额不能大于应付总额。") {
			
			@Override
			protected boolean validateText(String text) {
				if (StringUtils.isEmpty(text)) return false;
				double payAmount = DoubleUtil.strToDouble(text);
				if (payAmount <= 0) {
					return false;
				}
				double molingAmount = 0.0;
				if (StringUtils.isNotEmpty(currMolingText.getText())) {
					molingAmount = DoubleUtil.strToDouble(currMolingText.getText());
				}
				if (DoubleUtil.sum(DoubleUtil.sum(molingAmount,payAmount), DoubleUtil.sum(info.getPaidAmount(), info.getMolingAmount())) > info.getAmount()) {
					return false;
				}
				return true;
			}
		});
		
		amountLabel.setText(DoubleUtil.getRoundStr(info.getAmount()));
		paidLabel.setText(DoubleUtil.getRoundStr(info.getPaidAmount()));
		molingLabel.setText(DoubleUtil.getRoundStr(info.getMolingAmount()));
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定付款
				if (!validateInput()) return;
				double molingAmount = 0.0;
				if (StringUtils.isNotEmpty(currMolingText.getText())) {
					molingAmount = DoubleUtil.strToDouble(currMolingText.getText());
				}
				getContext().bubbleMessage(new MsgResponse(true, DoubleUtil.strToDouble(payText.getText()), molingAmount));
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 取消
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

}
