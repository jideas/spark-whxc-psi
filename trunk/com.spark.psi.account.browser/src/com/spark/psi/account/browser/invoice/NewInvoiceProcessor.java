package com.spark.psi.account.browser.invoice;

import java.util.Date;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.account.browser.PartnerSearchMsg;
import com.spark.psi.account.browser.PartnerSelectPage;
import com.spark.psi.account.browser.PartnerSelectionMsg;
import com.spark.psi.publish.InvoiceType;
import com.spark.psi.publish.account.task.InvoiceTask;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;

/**
 * 新建开票处理器
 */
public class NewInvoiceProcessor extends BaseFormPageProcessor {

	public static final String ID_Label_Name = "Label_Name";
	public static final String ID_List_Type = "List_Type";
	public static final String ID_Text_Code = "Text_Code";
	public static final String ID_Text_Amount = "Text_Amount";
	public static final String ID_Text_Hander = "Text_Hander";
	public static final String ID_Date_Date = "Date_Date";
	public static final String ID_Button_Check = "Button_Check";
	public final static String ID_PartnerPage = "PartnerPage";
	public final static String ID_Search = "Search";
	
	private Text nameText;
	private LWComboList typeList;
	private Text codeText;
	private Text amountText;
	private Text handPersonText;
	private SDatePicker datePicker;
	private PartnerSelectPage partnerPage;
	
	private CustomerInfo customerInfo;
	
	@Override
	public void process(final Situation situation) {
		nameText = createControl(ID_Label_Name, Text.class);
		typeList = createControl(ID_List_Type, LWComboList.class);
		InvoiceTypeSource source = new InvoiceTypeSource();
		typeList.getList().setSource(source);
		typeList.getList().setInput(null);
		typeList.setSelection(InvoiceType.ValueAddesTax.getCode());
		codeText = createControl(ID_Text_Code, Text.class);
		amountText = createControl(ID_Text_Amount, Text.class);
		handPersonText = createControl(ID_Text_Hander, Text.class);
		datePicker = createControl(ID_Date_Date, SDatePicker.class);
		partnerPage = createControl(ID_PartnerPage, PartnerSelectPage.class);
		datePicker.setDate(new Date());
		
		handPersonText.setMaximumLength(5);
		codeText.setMaximumLength(50);
		
		registerNotEmptyValidator(nameText, "客户名称");
		registerNotEmptyValidator(codeText, "发票号");
		registerNotEmptyValidator(amountText, "开票金额");
		registerNotEmptyValidator(handPersonText, "开票人");
		
		registerInputValidator(new TextInputValidator(handPersonText, "开票人长度不能超过5") {
			
			@Override
			protected boolean validateText(String text) {
				if (text != null && text.trim().length() > 5) {
					return false;
				}
				return true;
			}
		});
		
		registerInputValidator(new TextInputValidator(codeText, "发票号长度不能超过50") {
			
			@Override
			protected boolean validateText(String text) {
				if (text != null && text.trim().length() > 50) {
					return false;
				}
				return true;
			}
		});
		loadContent(partnerPage.getDefaultSelectId());
		situation.regMessageListener(PartnerSelectionMsg.class, new MessageListener<PartnerSelectionMsg>() {

			public void onMessage(Situation context,
					PartnerSelectionMsg message,
					MessageTransmitter<PartnerSelectionMsg> transmitter) {
				loadContent(message.getPartnerId());
			}
		});
		
		final SSearchText2 search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				situation.broadcastMessage(new PartnerSearchMsg(search.getText()));
				loadContent(partnerPage.getDefaultSelectId());
				
			}
		});
		
		Button checkButton = createControl(ID_Button_Check, Button.class);
		checkButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!validateInput()) {
					return;
				}
				InvoiceTask task = new InvoiceTask();
				if(customerInfo == null) {
					alert("请选择客户");
					return;
				}
				task.setCustomerId(customerInfo.getId());
				if(amountText.getText() == null || "".equals(amountText.getText())) {
					alert("开票金额不能为空!");
					return;
				}
				if(codeText.getText() == null || "".equals(codeText.getText())) {
					alert("发票号不能为空!");
					return;
				}
				if(handPersonText.getText() == null || "".equals(handPersonText.getText())) {
					alert("开票人不能为空!");
					return;
				}
				task.setAmount(DoubleUtil.strToDouble(amountText.getText()));
				task.setInvoiceDate(datePicker.getDate().getTime());
				task.setInvoiceNumber(codeText.getText());
				task.setInvoiceTypeCode(typeList.getList().getSeleted());
				task.setDrawer(handPersonText.getText());
				situation.handle(task);
				
				hint("开票成功。");
				
				datePicker.setDate(new Date());
				typeList.setSelection("01");
				codeText.setText(null);
				amountText.setText(null);
				handPersonText.setText(null);
			}
		});
	}
	private void loadContent(GUID partnerId) {
		if(partnerId == null) return; 
		customerInfo = getContext().find(CustomerInfo.class, partnerId);
		nameText.setText(customerInfo.getName());
	}
}
