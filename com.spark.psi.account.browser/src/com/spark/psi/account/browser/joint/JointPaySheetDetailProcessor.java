package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfoItem;
import com.spark.b2c.publish.JointVenture.task.JointSettlementPayTask;
import com.spark.b2c.publish.JointVenture.task.UpdateJointSettlementStatusTask;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.JointSettlementStatus;

public class JointPaySheetDetailProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public static final String ID_Label_Supplier = "Label_Supplier";
	public static final String ID_Label_Begin = "Label_Begin";
	public static final String ID_Label_End = "Label_End";
	public static final String ID_Label_TotalPayingAmount = "Label_TotalPayingAmount";
	public static final String ID_Label_AdjustAmount = "Label_AdjustAmount";
	public static final String ID_Label_TotalSaleAmount = "Label_TotalSaleAmount";
	public static final String ID_Label_TotalPercentageAmount = "Label_TotalPercentageAmount";
	public static final String ID_Label_PaidAmount = "Label_PaidAmount";

	public static final String ID_Button_Approval = "Button_Approval";
	public static final String ID_Button_Reject = "Button_Reject";
	public static final String ID_Button_Pay = "Button_Pay";

	public static enum ColumnName {
		materialName("材料名称"), materialCode("材料编号"), materialNo("材料条码"), price("单价"), count("销售数量"), amount("销售金额"), percentage("提成比例"), percentageAmount(
				"提成金额");

		private String title;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private JointSettlementInfo info = null;

	@Override
	public void process(Situation situation) {
		super.process(situation);

		final Text remarkText = createMemoText();
		if (null != info && CheckIsNull.isNotEmpty(info.getRemark()))
			remarkText.setText(info.getRemark());
		// remarkText.setText(info.get)
		remarkText.setEnabled(false);

		initActions();
	}

	private void initActions() {
		Button button = null;
		if (JointSettlementStatus.Submitted == info.getStatus()) {
			button = createButtonControl(ID_Button_Approval);
			addApprovalAction(button);

			button = createButtonControl(ID_Button_Reject);
			addRejectAction(button);
		} else if (JointSettlementStatus.Paying == info.getStatus()) {
			button = createButtonControl(ID_Button_Pay);
			addPayAction(button);
		}
	}

	private void addApprovalAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 批准
				UpdateJointSettlementStatusTask task = new UpdateJointSettlementStatusTask(info.getId());
				getContext().handle(task, UpdateJointSettlementStatusTask.Method.Approve);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addRejectAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (returnValue2 != null) {
							UpdateJointSettlementStatusTask task = new UpdateJointSettlementStatusTask(info.getId());
							task.setDenyReason((String) returnValue2);
							getContext().handle(task, UpdateJointSettlementStatusTask.Method.Deny);
							getContext().bubbleMessage(new MsgResponse(true));
						}

					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addPayAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 付款
				PageController pc = new PageController(PayProcessor.class, PayRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, info);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				windowStyle.setSize(380, 200);
				MsgRequest request = new MsgRequest(pci, "付款", windowStyle);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							double amount = (Double) returnValue;
							double molingAmount = returnValue2 == null ? 0.0 : (Double) returnValue2;
							JointSettlementPayTask task = new JointSettlementPayTask(info.getId(), amount, molingAmount);
							getContext().handle(task);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return info.getSheetNo();
	}

	@Override
	protected String getSheetTitle() {
		return "联营结算单";
	}

	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStopCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initSheetData() {
		if (getArgument() != null) {
			GUID id = (GUID) getArgument();
			info = getContext().find(JointSettlementInfo.class, id);
		}
	}

	@Override
	public String getElementId(Object element) {
		JointSettlementInfoItem item = (JointSettlementInfoItem) element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return info.getItems();
	}

}
