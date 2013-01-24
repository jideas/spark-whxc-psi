package com.spark.psi.account.browser.payment;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.entity.PaymentListEntity;
import com.spark.psi.publish.account.key.GetPaymentListKey;

public class SubmittedPaymentListProcessor<Item> extends
		PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_New = "Button_New";
	
	public static enum ColumnName {
		payDate, sheetNo, partnerName, payType, payAmount, applyAmount, applier
	}
	
	private Label        countLabel    = null;
	private SSearchText2 search        = null;
	
	private LoginInfo loginInfo   = null;
	
	
	@Override
	public void init(Situation context) {
		loginInfo = context.find(LoginInfo.class);
	}
	
	@Override
	public void process(Situation context) {
		super.process(context);
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		PaymentItem item = (PaymentItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetPaymentListKey key = new GetPaymentListKey(0, Integer.MAX_VALUE, true);
		key.setSearchText(search.getText());
		key.setStatus(PaymentStatus.Submitted);
		ListEntity<PaymentItem> listEntity = context.find(PaymentListEntity.class, key);
		if (null == listEntity) return null;
		List<PaymentItem> resultList = listEntity.getItemList();
		countLabel.setText("" + resultList.size());
		return resultList.toArray(new PaymentItem[0]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName) || Action.Approval.name().equals(actionName)
				|| Action.Deny.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(PaymentDetailProcessor.class, PaymentDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "付款详情");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	@Override
	public String[] getTableActionIds() {
		if (loginInfo.hasAuth(Auth.SubFunction_PaymentManage_Approval)) {
			return new String[] {Action.Approval.name(), Action.Deny.name(), };
		}
		return null;
	}

	@Override
	protected String getExportFileTitle() {
		return "付款申请-待审批";
	}

}
