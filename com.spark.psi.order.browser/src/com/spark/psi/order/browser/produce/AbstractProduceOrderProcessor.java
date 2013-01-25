package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.AbstractFormProcessor;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;

public abstract class AbstractProduceOrderProcessor extends AbstractFormProcessor {

	public static final String ID_Label_PlanDate = "Label_PlanDate";
	public static final String ID_Label_ReceiveReturn = "Label_ReceiveReturn";
	public static final String ID_Label_Status = "Label_Status";
	public static final String ID_Text_Remark = "Text_Remark";
	public static final String ID_Label_SheetInfo = "Label_SheetInfo";
	public static final String ID_Label_StopCause = "Label_StopCause";
	
	protected Text  remarkText         = null;
	protected Label receiveReturnLabel = null;
	protected Label planDateLabel      = null;
	protected Label sheetInfoLabel     = null;
	
	protected ProduceOrderInfo orderInfo  =  null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID orderId = (GUID)getArgument();
		orderInfo = context.find(ProduceOrderInfo.class, orderId);
	}
	
	@Override
	public void process(Situation context) {
		super.process(context);
		remarkText = createControl(ID_Text_Remark, Text.class);
		planDateLabel = createControl(ID_Label_PlanDate, Label.class);
		receiveReturnLabel = createControl(ID_Label_ReceiveReturn, Label.class);
		final Label statusLabel = createLabelControl(ID_Label_Status);
		statusLabel.setText(orderInfo.getStatus().getName());
		
		planDateLabel.setText(DateUtil.dateFromat(orderInfo.getPlanDate()));
		remarkText.setText(orderInfo.getRemark());
		
		if (orderInfo.getStatus() == ProduceOrderStatus.Submiting
				|| orderInfo.getStatus() == ProduceOrderStatus.Reject) {
			remarkText.setEnabled(true);
		} else {
			remarkText.setEnabled(false);
		}
		
		Label causeLabel = createControl(ID_Label_StopCause,
				Label.class);
		if (ProduceOrderStatus.Reject.equals(orderInfo.getStatus())) {
			causeLabel.setText("退回原因：" + orderInfo.getRejectReason());
		} else {
			causeLabel.dispose();
		}
	}

	@Override
	protected final String getSheetNumber() {
		return orderInfo.getBillsNo();
	}

	@Override
	protected final String getSheetTitle() {
		return "生产订单";
	}

//	private String getSheetInfo() {
//		String createInfo = "制单：" + orderInfo.getCreator() + "(" + DateUtil.dateFromat(orderInfo.getCreateDate()) + ")";
//		return createInfo;
//	}
}
