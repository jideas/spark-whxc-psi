package com.spark.psi.base.browser.approval;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.approval.ApprovalRecordItem;
import com.spark.psi.publish.base.approval.GetApprovalRecordKey;

/**
 * 审批记录列表处理器
 */
public class ApprovalLogListProcessor extends PSIListPageProcessor<ApprovalRecordItem> {
	
	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	
	private int approvalCount = 0;//记录数量
	
	@Override
	public void process(Situation situation) {
//		super.init(situation);
//		Label countLabel = this.createControl(ID_LABEL_COUNT, Label.class, JWT.NONE);
//		countLabel.setText("12");
//		
//		Button allcoateButton = this.createControl(ID_BUTTON_ALLOCATE, Button.class, JWT.NONE);
//		allcoateButton.setText("分配客户");
//		
//		Button shareButton = this.createControl(ID_BUTTON_SHARE, Button.class, JWT.NONE);
//		shareButton.setText("设为共享");
//		
//		Button creditButton = this.createControl(ID_BUTTON_CHANGECREDIT, Button.class, JWT.NONE);
//		creditButton.setText("调整信用");
//		
//		this.table.getSelection();
		
		super.process(situation);
		
		ComboList cblstTime = this.createControl(ID_COMBO_TIME, ComboList.class, JWT.NONE);
		cblstTime.addItem("本月");
		cblstTime.setSelection(0);
		
		Label countLabel = this.createControl(ID_LABEL_COUNT, Label.class, JWT.NONE);
		countLabel.setText(String.valueOf(approvalCount));
		
		this.table.getSelection();
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		ListEntity<ApprovalRecordItem> listEntity = context.find(ListEntity.class,new GetApprovalRecordKey());
		return listEntity.getItemList().toArray(new ApprovalRecordItem[0]);
	}

	public String getElementId(Object element) {
		return ((ApprovalRecordItem)element).getId().toString();
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
