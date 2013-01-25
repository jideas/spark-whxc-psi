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
import com.spark.psi.publish.CheckingOutType;

public class ReceiptMaterialAdvanceSearchProcessor extends
		BaseFormPageProcessor {

	public static final String ID_Text_Department = "Text_Department";
	public static final String ID_Text_ProduceSheetNo = "Text_ProduceSheetNo";
	public static final String ID_Text_SheetNo = "Text_SheetNo";
	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
	public static final String ID_List_CheckingOutType = "List_CheckingOutType";
	public static final String ID_Text_MaterialNo = "Text_materialNo";
	public static final String ID_Text_MaterialName = "Text_materialName";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	@Override
	public void process(final Situation context) {
		final Text departmentText = createTextControl(ID_Text_Department);
		final Text produceSheetNoText = createTextControl(ID_Text_ProduceSheetNo);
		final Text sheetNoText = createTextControl(ID_Text_SheetNo);
		final SDatePicker createBeginDate = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createEndDate = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		final LWComboList typeList = createControl(ID_List_CheckingOutType, LWComboList.class);
		final Text materialNoText = createTextControl(ID_Text_MaterialNo);
		final Text materialNameText = createTextControl(ID_Text_MaterialName);
		
		typeList.getList().setSource(new ListSourceAdapter() {
			
			@Override
			public String getElementId(Object element) {
				CheckingOutType type = (CheckingOutType)element;
				return type.getCode();
			}
			
			@Override
			public Object getElementById(String id) {
				return CheckingOutType.getCheckingOutType(id);
			}
			
			@Override
			public String getText(Object element) {
				CheckingOutType type = (CheckingOutType)element;
				return type.getName();
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return new CheckingOutType[]{ CheckingOutType.Mateiral_Return, CheckingOutType.Mateiral_Take, CheckingOutType.RealGoods, CheckingOutType.Return, CheckingOutType.Sales};
			}
		});
		typeList.getList().setInput(null);
		
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定搜索
				if (!validateInput()) return;
				ReceiptMaterialSearchCondition condition = new ReceiptMaterialSearchCondition();
				condition.setDepartment(departmentText.getText());
				condition.setProduceSheetNo(produceSheetNoText.getText());
				condition.setSheetNo(sheetNoText.getText());
				condition.setCreateDateBegin(createBeginDate.getDate().getTime());
				condition.setCreateDateEnd(createEndDate.getDate().getTime());
				condition.setMaterialNo(materialNoText.getText());
				condition.setMaterialName(materialNameText.getText());
				condition.setCheckingOutType(typeList.getList().getSeleted() == null ? null : typeList.getList().getSeleted());
				context.bubbleMessage(new MsgResponse(true, condition));
			}
			
			private boolean validateInput() {
				if (createBeginDate.getDate().getTime() > createEndDate.getDate().getTime()) {
					alert("出库日期：开始日期不能晚于结束日期。");
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
