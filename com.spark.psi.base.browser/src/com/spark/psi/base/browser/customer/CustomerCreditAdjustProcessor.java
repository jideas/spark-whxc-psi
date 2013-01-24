package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.config.key.GetSalesManResidualCreditAmountKey;

/**
 * 客户信用额度调整处理器
 * 
 */
public class CustomerCreditAdjustProcessor extends BaseFormPageProcessor {

	public static String ID_Text_CreditAmount = "Text_CreditAmount";
	public static String ID_Text_CreditDa = "Text_CreditDay";
	public static String ID_Text_RemindDay = "Text_RemindDay";
	public static String ID_Button_Save = "Button_Save";
	public static String ID_Button_Cancel = "Button_Cancel";

	private String creditAmount;
	
	private String creditDay,remindDay;
	
	private GUID[] ids;

	public void init(Situation context) {
		String[] strs = (String[])this.getArgument();
		ids = new GUID[strs.length];
		for(int i=0;i<strs.length;i++){
	        ids[i] = GUID.valueOf(strs[i]);
        }
		if (this.getArgument2() != null) {
			String[] arguments = (String[])this.getArgument2();
			creditAmount = arguments[0];
			creditDay = arguments[1];
			remindDay = arguments[2];
		} 
		
	}

	@Override
	public void process(Situation context) {
		final Text creditAmountText = this.createControl(ID_Text_CreditAmount,
				Text.class);
		final Text creditDayText = this.createControl(ID_Text_CreditDa,
				Text.class);
		final Text remindDayText = this.createControl(ID_Text_RemindDay,
				Text.class);
		creditAmountText.setText(creditAmount);
		creditDayText.setText(creditDay);
		remindDayText.setText(remindDay);
		final LoginInfo login = getContext().find(LoginInfo.class);
		final SalesmanCreditInfo sc = getContext().find(SalesmanCreditInfo.class,login.getEmployeeInfo().getId());
		if(!login.hasAuth(Auth.SubFunction_CustomerMange_Credit2)){
			registerInputValidator(new TextInputValidator(creditAmountText,"信用额度超过授权范围！"){
				
				@Override
				protected boolean validateText(String text){
					String t = creditAmountText.getText();
					double amount = Double.parseDouble(StringUtils.isEmpty(t) ? "0" : t);
					return sc.getCustomerCreditLimit()>=amount;
				}
			});
			
			registerInputValidator(new TextInputValidator(creditAmountText,"超出了可设置的累计信用额度！"){
				
				@Override
				protected boolean validateText(String text){
					LoginInfo employee = getContext().find(LoginInfo.class);
					if(!employee.hasAuth(Auth.Boss)){
						text = StringUtils.isEmpty(text) ? "0" : text;
						double totalAmount = Double.parseDouble(text)*ids.length;
						GetSalesManResidualCreditAmountKey getSalesManResidualCreditAmountKey
							= new GetSalesManResidualCreditAmountKey(ids);
						double kszz = getContext().find(Double.class,getSalesManResidualCreditAmountKey);
						return totalAmount<kszz;
					}
					return true;
				}
			});
			
			registerInputValidator(new TextInputValidator(creditDayText,"帐期天数超过授权范围！"){
				
				@Override
				protected boolean validateText(String text){
					int creditDay = Integer.parseInt(StringUtils.isEmpty(creditDayText.getText()) ? "0" : creditDayText.getText()); 
					return sc.getCustomerCreditDayLimit()>=creditDay;
				}
			});
		}

		this.createControl(ID_Button_Save, Button.class).addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						if (!validateInput()) {
							return ;
						}

						Double[] returnValue = new Double[3];
						try {
							returnValue[0] = Double
									.parseDouble(creditAmountText.getText());
						} catch (Throwable t) {
						}
						try {
							returnValue[1] = Double.parseDouble(creditDayText
									.getText());
						} catch (Throwable t) {
						}
						try {
							returnValue[2] = Double.parseDouble(remindDayText
									.getText());
						} catch (Throwable t) {
						}
						MsgResponse response = new MsgResponse(true,
								returnValue);
						getContext().bubbleMessage(response);
					}
				});
		this.createControl(ID_Button_Cancel, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContext().bubbleMessage(new MsgCancel());
					}
				});
	}

}
