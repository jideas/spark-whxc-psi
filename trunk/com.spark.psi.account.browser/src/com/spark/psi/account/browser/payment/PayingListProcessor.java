package com.spark.psi.account.browser.payment;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
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

/**
 * 待付款记录列表处理器
 */
public class PayingListProcessor extends PSIListPageProcessor<PaymentItem> {

	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_LABEL_AMOUNT = "Label_Amount";
	public final static String ID_SSearch = "SSearch";
	public static enum Columns {
		PaymentTargetName, PaymentTypeName, PayingAmount, CreditAmount, OverCreditAmount,OverCreditPeriod,RemindDay,RemindingAmount;
	}
	public static enum ColumnName {
		payDate, sheetNo, partnerName, payType, payAmount, applyAmount, applier
	}
	private Label countLabel;
//	private Label amountLabel;
	private SSearchText2 search  =  null;
	
	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = this.createControl(ID_LABEL_COUNT, Label.class,
				JWT.NONE);
//		amountLabel = this.createControl(ID_LABEL_AMOUNT, Label.class,
//				JWT.NONE);
		
		search = createControl(ID_SSearch, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});

		this.table.getSelection();
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetPaymentListKey key = new GetPaymentListKey(0, Integer.MAX_VALUE, true);
		key.setSearchText(search.getText() == null ? null : search.getText().trim());
		key.setStatus(PaymentStatus.Paying);
		ListEntity<PaymentItem> listEntity = context.find(PaymentListEntity.class, key);
		if (null == listEntity) return null;
		List<PaymentItem> resultList = listEntity.getItemList();
		countLabel.setText("" + resultList.size());
//		double payAmount = 0.0;
//		for(PaymentItem item : resultList) {
//			payAmount += item.getAmount();
//		}
//		amountLabel.setText(DoubleUtil.getRoundStr(payAmount));
		return resultList.toArray(new PaymentItem[0]);
	}

	public String getElementId(Object element) {
		return ((PaymentItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		if (loginInfo.hasAuth(Auth.SubFunction_PaymentManage_Pay)) {
			return new String[] { Action.Pay.name() };
		} else {
			return null;
		}
	}

	// 根据条件或情况排除不执行按钮的行
	protected String[] getElementActionIds(Object element) {
		PaymentItem item = (PaymentItem) element;
		if (null != item.getId()) {
			return new String[] { Action.Pay.name() };
		}
		return null;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Pay.name()) || actionName.equals(Action.Detail.name())) {
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
	protected String getExportFileTitle() {
		return "付款申请-待付款";
	}
}