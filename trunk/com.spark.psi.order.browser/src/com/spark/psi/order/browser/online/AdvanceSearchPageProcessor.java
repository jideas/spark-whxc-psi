package com.spark.psi.order.browser.online;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class AdvanceSearchPageProcessor extends BaseFormPageProcessor {

	public static final String ID_Text_BillsNo = "Text_BillsNo";
	public static final String ID_Text_RealName = "Text_RealName";
	public static final String ID_Text_StationName = "Text_StationName";
	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
	public static final String ID_Date_DeliveryeDateBegin = "Date_DeliveryeDateBegin";
	public static final String ID_Date_DeliveryeDateEnd = "Date_DeliveryeDateEnd";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation context) {
		final Text sheetNoText = createTextControl(ID_Text_BillsNo);
		final Text realNameText = createTextControl(ID_Text_RealName);
		final Text stationNameText = createTextControl(ID_Text_StationName);
		final SDatePicker createDateBegin = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createDateEnd = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		final SDatePicker deliveryeDateBegin = createControl(ID_Date_DeliveryeDateBegin, SDatePicker.class);
		final SDatePicker deliveryeDateEnd = createControl(ID_Date_DeliveryeDateEnd, SDatePicker.class);
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!validateInput()) return;
				AdvanceSearchCondition condition = new AdvanceSearchCondition();
				condition.setBillsNo(sheetNoText.getText());
				condition.setCreateDateBegin(createDateBegin.getDate().getTime());
				condition.setCreateDateEnd(createDateEnd.getDate().getTime());
				condition.setDeliveryeDateBegin(deliveryeDateBegin.getDate().getTime());
				condition.setDeliveryeDateEnd(deliveryeDateEnd.getDate().getTime());
				condition.setRealName(realNameText.getText());
				condition.setStationName(stationNameText.getText());
				context.bubbleMessage(new MsgResponse(true, condition));
			}
			
			private boolean validateInput() {
				if (createDateBegin.getDate().getTime() > createDateEnd.getDate().getTime()) {
					alert("下单日期：开始日期不能晚于结束日期。");
					return false;
				}
				if (deliveryeDateBegin.getDate().getTime() > deliveryeDateEnd.getDate().getTime()) {
					alert("交货日期：开始日期不能晚于结束日期。");
					return false;
				}
				return true;
			}
		});
		
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

}

class AdvanceSearchCondition {
	private String billsNo;
	private String realName;
	private String stationName;
	private long createDateBegin;
	private long createDateEnd;
	private long deliveryeDateBegin;
	private long deliveryeDateEnd;
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public long getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(long createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public long getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(long createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public long getDeliveryeDateBegin() {
		return deliveryeDateBegin;
	}
	public void setDeliveryeDateBegin(long deliveryeDateBegin) {
		this.deliveryeDateBegin = deliveryeDateBegin;
	}
	public long getDeliveryeDateEnd() {
		return deliveryeDateEnd;
	}
	public void setDeliveryeDateEnd(long deliveryeDateEnd) {
		this.deliveryeDateEnd = deliveryeDateEnd;
	}
}
