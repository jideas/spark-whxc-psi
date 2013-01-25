/**
 * 
 */
package com.spark.psi.order.browser.sales;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;

/**
 * 
 * 预计出库日期 请通过com.spark.psi.order.browser.sales.SetPlanOutDate工具类使用，不要直接使用
 */
public class PlanOutDateProcessor extends BaseFormPageProcessor {
	public final static String ID_Plan_Date = "plan_date";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Cancel = "Button_Cancel";
	public final static String ID_Button_Return = "Button_Return";

	private DatePicker datePicker;

	@Override
	public void process(Situation context) {
		datePicker = createControl(ID_Plan_Date, DatePicker.class);

		if (null != getArgument()) {
			this.createControl(ID_Button_Return, Button.class)
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							getContext().bubbleMessage(new MsgResponse(true));
						}
					});
		}

		this.createControl(ID_Button_Save, Button.class).addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						if (StringUtils.isEmpty(datePicker.getText())) {
							alert("请设置预计出库日期！");
							return;
						}
						getContext().bubbleMessage(
								new MsgResponse(true, datePicker.getDate()));
					}
				});

		this.createControl(ID_Button_Cancel, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						getContext().bubbleMessage(new MsgCancel());
					}
				});
	}
}
