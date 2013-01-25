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
import com.spark.psi.publish.CheckingInType;

public class MaterialCheckinAdvanceSearchProcessor extends
		BaseFormPageProcessor {

	public static final String ID_Text_PartnerNo = "Text_PartnerNo";
	public static final String ID_Text_PartnerName = "Text_PartnerName";
	public static final String ID_Text_PurchaseSheetNo = "Text_PurchaseSheetNo";
	public static final String ID_Text_SheetNo = "Text_SheetNo";
	public static final String ID_Date_CheckinDateBegin = "Date_CheckinDateBegin";
	public static final String ID_Date_CheckinDateEnd = "Date_CheckinDateEnd";
	public static final String ID_List_CheckingInType = "List_CheckingInType";
	public static final String ID_Text_MaterialNo= "Text_MaterialNo";
	public static final String ID_Text_MaterialName = "Text_MaterialName";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation context) {
		final Text partnerNoText = createTextControl(ID_Text_PartnerNo);
		final Text partnerNameText = createTextControl(ID_Text_PartnerName);
		final Text purchaseSheetNoText = createTextControl(ID_Text_PurchaseSheetNo);
		final Text sheetNoText = createTextControl(ID_Text_SheetNo);
		final SDatePicker checkinBeginDate = createControl(ID_Date_CheckinDateBegin, SDatePicker.class);
		final SDatePicker checkinEndDate = createControl(ID_Date_CheckinDateEnd, SDatePicker.class);
		final LWComboList typeList = createControl(ID_List_CheckingInType, LWComboList.class);
		final Text materialNotText = createTextControl(ID_Text_MaterialNo);
		final Text materialNameText = createTextControl(ID_Text_MaterialName);
		
		typeList.getList().setSource(new ListSourceAdapter() {
			
			@Override
			public String getElementId(Object element) {
				CheckingInType type = (CheckingInType)element;
				return type.getCode();
			}
			
			@Override
			public Object getElementById(String id) {
				return CheckingInType.getCheckingInType(id);
			}
			
			@Override
			public String getText(Object element) {
				CheckingInType type = (CheckingInType)element;
				return type.getName();
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return new CheckingInType[]{CheckingInType.Adjustment, CheckingInType.Irregular, 
						CheckingInType.Kit, CheckingInType.Purchase, 
						CheckingInType.RealGoods, CheckingInType.Return };
			}
		});
		typeList.getList().setInput(null);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				MaterialCheckInSearchCondition searchCondition = new MaterialCheckInSearchCondition();
				searchCondition.setPartnerNo(partnerNoText.getText());
				searchCondition.setPartnerName(partnerNameText.getText());
				searchCondition.setPurchaseSheetNo(purchaseSheetNoText.getText());
				searchCondition.setSheetNo(sheetNoText.getText());
				searchCondition.setCheckinDateBegin(checkinBeginDate.getDate().getTime());
				searchCondition.setCheckinDateEnd(checkinEndDate.getDate().getTime());
				searchCondition.setCheckingInType(typeList.getList().getSeleted() == null ? null : typeList.getList().getSeleted());
				searchCondition.setMaterialNo(materialNotText.getText());
				searchCondition.setMaterialName(materialNameText.getText());
				context.bubbleMessage(new MsgResponse(true, searchCondition));
			}
			
			private boolean validateInput() {
				if (checkinBeginDate.getDate().getTime() > checkinEndDate.getDate().getTime()) {
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
