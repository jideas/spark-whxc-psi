package com.spark.psi.query.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;

public class GoodsCheckinAdvaceSearchProcessor extends BaseFormPageProcessor {
	public static final String ID_Text_Department = "Text_Department";
	public static final String ID_Text_ProduceSheetNo = "Text_ProduceSheetNo";
	public static final String ID_Text_GoodsCode = "Text_GoodsCode";
	public static final String ID_Text_GoodsName = "Text_GoodsName";
	public static final String ID_Check_NeedProduce = "Check_NeedProduce";
	public static final String ID_Date_CreateDateBegin = "Text_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Text_CreateDateEnd";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation context) {
		final Text departmentText = createTextControl(ID_Text_Department);
		final Text produceSheetNoText = createTextControl(ID_Text_ProduceSheetNo);
		final Text goodsCodeText = createTextControl(ID_Text_GoodsCode);
		final Text goodsNameText = createTextControl(ID_Text_GoodsName);
		final Button needProduceCheck = createButtonControl(ID_Check_NeedProduce);
		final SDatePicker createDateBegin = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createDateEnd = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				GoodsCheckinAdvanceSearch searchCondition = new GoodsCheckinAdvanceSearch();
				searchCondition.setCreateDateBegin(createDateBegin.getDate().getTime());
				searchCondition.setCreateDateEnd(createDateEnd.getDate().getTime());
				searchCondition.setDepartment(departmentText.getText());
				searchCondition.setGoodsCode(goodsCodeText.getText());
				searchCondition.setGoodsName(goodsNameText.getText());
				searchCondition.setNeedProduce(needProduceCheck.getSelection());
				searchCondition.setProduceSheetNo(produceSheetNoText.getText());
				context.bubbleMessage(new MsgResponse(true, searchCondition));
			}
			
			private boolean validateInput() {
				if (createDateBegin.getDate().getTime() > createDateEnd.getDate().getTime()) {
					alert("入库日期：开始日期不能晚于结束日期。");
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
