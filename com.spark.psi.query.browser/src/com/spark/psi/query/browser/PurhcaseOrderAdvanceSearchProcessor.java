package com.spark.psi.query.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class PurhcaseOrderAdvanceSearchProcessor extends BaseFormPageProcessor {
	
	public static final String ID_Text_SupplierNo = "Text_Supplier";
	public static final String ID_Text_SupplierName = "Text_SupplierName";
	public static final String ID_Text_BillsNo = "Text_BillsNo";
	public static final String ID_Text_MaterialNo = "Text_MaterailNo";
	public static final String ID_Text_MaterialName = "Text_MaterialName";
	public static final String ID_Date_CreateBegin = "Date_CreateBegin";
	public static final String ID_Date_CreateEnd = "Date_CreateEnd";
	public static final String ID_Date_DeliverBegin = "Date_DeliverBegin";
	public static final String ID_Date_DeliverEnd = "Date_DeliverEnd";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation context) {
		final Text supplierNoText = createTextControl(ID_Text_SupplierNo);
		final Text supplierNameText = createTextControl(ID_Text_SupplierName);
		final Text billsNoText = createTextControl(ID_Text_BillsNo);
		final Text materialNoText = createTextControl(ID_Text_MaterialNo);
		final Text materialNameText = createTextControl(ID_Text_MaterialName);
		final SDatePicker createBeginDate = createControl(ID_Date_CreateBegin, SDatePicker.class);
		final SDatePicker createEndDate = createControl(ID_Date_CreateEnd, SDatePicker.class);
		final SDatePicker deliverBeginDate = createControl(ID_Date_DeliverBegin, SDatePicker.class);
		final SDatePicker deliverEndDate = createControl(ID_Date_DeliverEnd, SDatePicker.class);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				PurchaseSearchCondition condition = new PurchaseSearchCondition();
				condition.setSupplierCode(supplierNoText.getText());
				condition.setSupplierName(supplierNameText.getText());
				condition.setBillsNo(billsNoText.getText());
				condition.setMaterialCode(materialNoText.getText());
				condition.setMaterialName(materialNameText.getText());
				condition.setCreateDateBegin(createBeginDate.getDate().getTime());
				condition.setCreateDateEnd(createEndDate.getDate().getTime());
				condition.setDeliverDateBegin(deliverBeginDate.getDate().getTime());
				condition.setDeliverDateEnd(deliverEndDate.getDate().getTime());
				context.bubbleMessage(new MsgResponse(true, condition));
			}
			
			private boolean validateInput() {
				if (createBeginDate.getDate().getTime() > createEndDate.getDate().getTime()) {
					alert("创建日期：开始日期不能晚于结束日期。");
					return false;
				}
				if (deliverBeginDate.getDate().getTime() > deliverEndDate.getDate().getTime()) {
					alert("到货日期：开始日期不能晚于结束日期。");
					return false;
				}
				return true;
			}
		});
		
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

}
