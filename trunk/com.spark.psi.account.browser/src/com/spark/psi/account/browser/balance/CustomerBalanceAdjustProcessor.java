package com.spark.psi.account.browser.balance;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.account.entity.BalanceInfo;
import com.spark.psi.publish.account.entity.DealingAdjustInfo;
import com.spark.psi.publish.account.task.AdjustCustomerBalanceTask;

/**
 * 客户往来调整处理器
 * 
 */
public class CustomerBalanceAdjustProcessor extends BaseFormPageProcessor {

	public static String ID_LABEL_CUSTOMERNAME = "Label_CustomerName";
	public static String ID_LABEL_DEUAMOUNT = "Label_DueAmount";
	public static String ID_TEXT_ADJUSTAMOUNT = "Text_AdjustAmount";
	public static String ID_TEXT_ADJUSTREASON = "Text_AdjustReason";
	public static String ID_BUTTON_CHECKADJUST = "Button_CheckAdjust";
	public static String ID_BUTTON_CANCELADJUST = "Button_CancelAdjust";

	private BalanceInfo balanceInfo;
	private DealingAdjustInfo adjustInfo;
	@Override
	public void init(Situation context) {
		if (getArgument() != null) {
			balanceInfo = (BalanceInfo) getArgument();
		}
		if(getArgument3() != null && getArgument3() instanceof GUID) {
			adjustInfo = context.find(DealingAdjustInfo.class, (GUID)getArgument3());
		}
	}

	@Override
	public void process(final Situation situation) {

		final Text amountText = createControl(ID_TEXT_ADJUSTAMOUNT, Text.class);
		final Text reasonText = createControl(ID_TEXT_ADJUSTREASON, Text.class);
		
		registerNotEmptyValidator(amountText, "调整金额");
		registerNotEmptyValidator(reasonText, "调整原因");

		Label customerNameLabel = this.createControl(ID_LABEL_CUSTOMERNAME,
				Label.class, JWT.NONE);
		customerNameLabel.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));
		customerNameLabel.setText(balanceInfo.getPartnerName());

		final Label dueAmountLabel = this.createControl(ID_LABEL_DEUAMOUNT,
				Label.class, JWT.NONE);
		dueAmountLabel.setText(DoubleUtil.getRoundStr(balanceInfo.getAmount()));

		// Text adjustAmountText = this.createControl(ID_TEXT_ADJUSTAMOUNT,
		// Text.class, JWT.NONE);

		final Button checkButton = this.createControl(ID_BUTTON_CHECKADJUST,
				Button.class, JWT.APPEARANCE3);
		checkButton.setText(" 确认调整 ");
		checkButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(!validateInput()) {
					return;
				}
				String amountStr = amountText.getText();
				String reasonString = reasonText.getText();
//				if (StringHelper.isEmpty(amountStr)) {
//					MessageDialog.alert("调整金额不能为空！");
//					return;
//				}
//				if (StringHelper.isEmpty(reasonString)) {
//					MessageDialog.alert("调整原因不能为空！");
//					return;
//				}
				AdjustCustomerBalanceTask task = new AdjustCustomerBalanceTask(
						balanceInfo.getPartnerId(), DoubleUtil
								.strToDouble(amountStr), reasonString);
				situation.handle(task);
				situation.bubbleMessage(new MsgResponse(true));
			}
		});

		final Button cancelButton = this.createControl(ID_BUTTON_CANCELADJUST,
				Button.class, JWT.APPEARANCE3);
		cancelButton.setText(" 放弃调整 ");
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				situation.bubbleMessage(new MsgResponse(true));
			}
		});
		if(getArgument2() != null && getArgument2() instanceof Boolean) {
			if(null != adjustInfo) {
				amountText.setText(DoubleUtil.getRoundStr(adjustInfo.getAdjustAmount()));
				reasonText.setText(adjustInfo.getAdjustReason());
			}
			
			amountText.setEnabled((Boolean)getArgument2());
			reasonText.setEnabled((Boolean)getArgument2());
			checkButton.setVisible((Boolean)getArgument2());
			cancelButton.setText(" 关闭 ");
		}
	}

}
