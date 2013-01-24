/**
 * 
 */
package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;

/**
 * 
 *
 */
public class CheckOutProcessor extends BaseFormPageProcessor {

	public final static String ID_Text_DeliveryPeople = "Text_DeliveryPeople";
	public final static String ID_Text_DeliveryCompany = "Text_DeliveryCompany";
	public final static String ID_Text_CertificateNo = "Text_CertificateNo";
	public final static String ID_Button_Check = "Button_Check";
	public final static String ID_Button_Cancel = "Button_Cancel";

	private Text deliveryPeople;
	private Text deliveryCompany;
	private Text certificateNo;

	@Override
	public void process(Situation context) {
		Button checkButton = this.createControl(ID_Button_Check, Button.class,
				JWT.NONE);
		deliveryPeople = this.createControl(ID_Text_DeliveryPeople, Text.class,JWT.APPEARANCE3);
		deliveryCompany = this.createControl(ID_Text_DeliveryCompany,
				Text.class,JWT.APPEARANCE3);
		certificateNo = this.createControl(ID_Text_CertificateNo, Text.class,JWT.APPEARANCE3);
		
		registerNotEmptyValidator(deliveryPeople, "�����");
		checkButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(!validateInput()||!validateInputLength()) {
					return;
				}
				getContext().bubbleMessage(
						new MsgResponse(true, deliveryPeople.getText(),
								deliveryCompany.getText(), certificateNo
										.getText()));

			}
		});

		Button cancelButton = this.createControl(ID_Button_Cancel,
				Button.class, JWT.NONE);
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	protected boolean validateInputLength() {
		boolean can = true;
		if(StringHelper.toStr(deliveryPeople.getText()).length()>20)
		{
			can = false;
			alert("��������ֻ������30���ַ���");
			return can;
		}
		if(StringHelper.toStr(deliveryCompany.getText()).length()>200)
		{
			can = false;
			alert("�����λ���ֻ������200���ַ���");
			return can;
		}
		if(StringHelper.toStr(certificateNo.getText()).length()>20)
		{
			can = false;
			alert("ƾ֤�����ֻ������50���ַ���");
			return can;
		}
		return can;
	}

}
