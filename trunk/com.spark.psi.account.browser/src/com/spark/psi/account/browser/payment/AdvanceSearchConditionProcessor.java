package com.spark.psi.account.browser.payment;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.key.GetPaymentListKey;

/**
 * 高级搜索对话框页面处理器
 */
public class AdvanceSearchConditionProcessor extends PageProcessor {
	
	public static final String ID_Text_PayTarget = "Text_PayTarget";
	public static final String ID_Date_PayDateBegin = "Date_PayDateBegin";
	public static final String ID_Date_PayDateEnd = "Date_PayDateEnd";
	public static final String ID_Text_Payer = "Text_Payer";
	public static final String ID_Check_TypePurchase = "Check_TypePurchase";
	public static final String ID_Check_TypeSaleReturn = "Check_TypeSaleReturn";
	public static final String ID_Check_TypeRetailReturn = "Check_TypeRetailReturn";
	public static final String ID_Check_TypeIrregular = "Check_TypeIrregular";
	public static final String ID_Text_PayAmountBegin = "Text_PayAmountBegin";
	public static final String ID_Text_PayAmountEnd = "Text_PayAmountEnd";
	public static final String ID_Check_WayCash = "Check_WayCash";
	public static final String ID_Check_WayCard = "Check_WayCard";
	public static final String ID_Check_WayCheck = "Check_WayCheck";
	public static final String ID_Check_WayAccount = "Check_WayAccount";
	public static final String ID_Button_Check = "Button_Check";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation situation) {
		final Text targetText = createControl(ID_Text_PayTarget, Text.class);
		final SDatePicker beginDate = createControl(ID_Date_PayDateBegin, SDatePicker.class);
		final SDatePicker endDate = createControl(ID_Date_PayDateEnd, SDatePicker.class);
		final Text payerText = createControl(ID_Text_Payer, Text.class);
		final Button typePurchase = createControl(ID_Check_TypePurchase, Button.class);
		final Button typeSaleReturn = createControl(ID_Check_TypeSaleReturn, Button.class);
		final Button typeRetailReturn = createControl(ID_Check_TypeRetailReturn, Button.class);
		final Button typeIrregular = createControl(ID_Check_TypeIrregular, Button.class);
		final Text begainAmountText = createControl(ID_Text_PayAmountBegin, Text.class);
		final Text endAmountText = createControl(ID_Text_PayAmountEnd, Text.class);
		final Button cashCheck = createControl(ID_Check_WayCash, Button.class);
		final Button cardCheck = createControl(ID_Check_WayCard, Button.class);
		final Button checkCheck = createControl(ID_Check_WayCheck, Button.class);
		final Button accountCheck = createControl(ID_Check_WayAccount, Button.class);
		
//		beginDate.setDate(new Date());
//		endDate.setDate(new Date());
		begainAmountText.setRegExp(TextRegexp.POSITIVE_DOUBLE_LIMIT);
		endAmountText.setRegExp(TextRegexp.POSITIVE_DOUBLE_LIMIT);
		
		final Button checkBtn = createControl(ID_Button_Check, Button.class);
		checkBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GetPaymentListKey.AdvanceCondition condition = new GetPaymentListKey.AdvanceCondition();
				condition.setPaymentTargetName(targetText.getText());
				if (endDate.getDate() != null) {
					condition.setEndDate(endDate.getDate().getTime());
				}
				if (beginDate.getDate() != null) {
					condition.setStartDate(beginDate.getDate().getTime());
				}
				if (endAmountText.getText() != null) {
					condition.setMaxAmount(DoubleUtil.strToDouble(endAmountText.getText()));
				}
				if (begainAmountText.getText() != null) {
					condition.setMinAmount(DoubleUtil.strToDouble(begainAmountText.getText()));
				}
				condition.setPayer(payerText.getText());
				List<PaymentType> payTypeList = new ArrayList<PaymentType>();
				if (typePurchase.getSelection()) {
					payTypeList.add(PaymentType.PAY_CGFK);
				}
				if (typeSaleReturn.getSelection()) {
					payTypeList.add(PaymentType.PAY_XSTK);
				}
				if (typeRetailReturn.getSelection()) {
					payTypeList.add(PaymentType.PAY_LSTK);
				}
				if (typeIrregular.getSelection()) {
					payTypeList.add(PaymentType.PAY_LCFK);
				}
				if (payTypeList.size() > 0) {
					condition.setPaymentTypes(payTypeList.toArray(new PaymentType[payTypeList.size()]));
				}
				
				List<DealingsWay> wayList = new ArrayList<DealingsWay>();
				if (cashCheck.getSelection()) {
					wayList.add(DealingsWay.Cash);
				}
				if (cardCheck.getSelection()) {
					wayList.add(DealingsWay.ChargePay);
				}
				if (checkCheck.getSelection()) {
					wayList.add(DealingsWay.Check);
				}
				if (accountCheck.getSelection()) {
					wayList.add(DealingsWay.Account);
				}
				if (wayList.size() > 0) {
					condition.setPaymentWay(wayList.toArray(new DealingsWay[wayList.size()]));
				}
				
				MsgResponse response = new MsgResponse(true, condition);
				situation.bubbleMessage(response);
			}
		});
		
		final Button cancelBtn = createControl(ID_Button_Cancel, Button.class);
		cancelBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				situation.bubbleMessage(new MsgCancel());
			}
		});
	}

}
