package com.spark.psi.query.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class SaleOrderAdvaceSearchProcessor extends BaseFormPageProcessor {
	public static final String ID_Text_CustomerName = "Text_CustomerName";
	public static final String ID_Text_SheetNo = "Text_SheetNo";
	public static final String ID_Text_GoodsCode = "Text_GoodsCode";
	public static final String ID_Text_GoodsNo = "Text_GoodsNo";
	public static final String ID_Text_GoodsName = "Text_GoodsName";
	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
	public static final String ID_Date_DeliveryDateBegin = "Date_DeliveryDateBegin";
	public static final String ID_Date_DeliveryDateEnd = "Date_DeliveryDateEnd";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	@Override
	public void process(final Situation context) {
		final Text customerNameText = createTextControl(ID_Text_CustomerName);
		final Text sheetNoText = createTextControl(ID_Text_SheetNo);
		final Text goodsCodeText = createTextControl(ID_Text_GoodsCode);
		final Text goodsNoText = createTextControl(ID_Text_GoodsNo);
		final Text goodsNameText = createTextControl(ID_Text_GoodsName);
		final SDatePicker createDateBegin = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createDateEnd = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		final SDatePicker deliveryDateBegin = createControl(ID_Date_DeliveryDateBegin, SDatePicker.class);
		final SDatePicker deliveryDateEnd = createControl(ID_Date_DeliveryDateEnd, SDatePicker.class);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				SaleOrderSearchCondition condition = new SaleOrderSearchCondition();
				condition.setCreateDateBegin(createDateBegin.getDate().getTime());
				condition.setCreateDateEnd(createDateEnd.getDate().getTime());
				condition.setCustomerName(customerNameText.getText());
				condition.setDeliveryDateBegin(deliveryDateBegin.getDate().getTime());
				condition.setDeliveryDateEnd(deliveryDateEnd.getDate().getTime());
				condition.setGoodsCode(goodsCodeText.getText());
				condition.setGoodsName(goodsNameText.getText());
				condition.setGoodsNo(goodsNoText.getText());
				condition.setSheetNo(sheetNoText.getText());
				context.bubbleMessage(new MsgResponse(true, condition));
			}
			
			private boolean validateInput() {
				if (createDateBegin.getDate().getTime() > createDateEnd.getDate().getTime()) {
					alert("下单日期：开始日期不能晚于结束日期。");
					return false;
				}
				if (deliveryDateBegin.getDate().getTime() > deliveryDateEnd.getDate().getTime()) {
					alert("交货日期：开始日期不能晚于结束日期。");
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
