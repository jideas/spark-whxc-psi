package com.spark.psi.query.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.DeliverTicketType;

public class GoodsDeliverAdvanceSearchProcessor extends BaseFormPageProcessor {
	
	public static final String ID_Text_MemberRealName = "Text_MemberRealName";
	public static final String ID_Text_SheetNo = "Text_SheetNo";
	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
	public static final String ID_List_DeliverTicketType = "List_DeliverTicketType";
	public static final String ID_Text_GoodsCode = "Text_GoodsCode";
	public static final String ID_Text_GoodsNo = "Text_GoodsNo";
	public static final String ID_Text_GoodsName = "Text_GoodsName";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	@Override
	public void process(final Situation context) {
		final Text memberRealNameText = createTextControl(ID_Text_MemberRealName);
		final Text sheetNoText = createTextControl(ID_Text_SheetNo);
		final SDatePicker createDateBegin = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createDateEnd = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		final LWComboList typeList = createControl(ID_List_DeliverTicketType, LWComboList.class);
		final Text goodsCodeText = createTextControl(ID_Text_GoodsCode);
		final Text goodsNoText = createTextControl(ID_Text_GoodsNo);
		final Text goodsNameText = createTextControl(ID_Text_GoodsName);
		
		typeList.getList().setSource(new ListSourceAdapter() {
			
			@Override
			public String getElementId(Object element) {
				DeliverTicketType type = (DeliverTicketType)element;
				return type.getCode();
			}
			
			@Override
			public Object getElementById(String id) {
				return DeliverTicketType.getDeliverTicketType(id);
			}
			
			@Override
			public String getText(Object element) {
				DeliverTicketType type = (DeliverTicketType)element;
				return type.getName();
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return new DeliverTicketType[] {DeliverTicketType.Common, DeliverTicketType.Reissue};
			}
		});
		typeList.getList().setInput(null);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				GoodsDeliverSearch searchCondition = new GoodsDeliverSearch();
				searchCondition.setCreateDateBegin(createDateBegin.getDate().getTime());
				searchCondition.setCreateDateEnd(createDateEnd.getDate().getTime());
				searchCondition.setDeliverTicketType(typeList.getList().getSeleted() == null ? null : typeList.getList().getSeleted());
				searchCondition.setGoodsCode(goodsCodeText.getText());
				searchCondition.setGoodsName(goodsNameText.getText());
				searchCondition.setGoodsNo(goodsNoText.getText());
				searchCondition.setMemberRealName(memberRealNameText.getText());
				searchCondition.setSheetNo(sheetNoText.getText());
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
