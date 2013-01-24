package com.spark.psi.account.browser.balance;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.date.TimeTest;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.account.browser.payment.PaymentDetailProcessor;
import com.spark.psi.account.browser.payment.PaymentDetailRender;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.account.entity.BalanceInfo;
import com.spark.psi.publish.account.entity.BalanceInfoItem;
import com.spark.psi.publish.account.key.GetDealingsListKey;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;

/**
 * 供应商往来明细列表处理器
 */
public class SupplierDealingsListProcessor extends PSIListPageProcessor<BalanceInfoItem> {

	public final static String ID_LABEL_SUPPLIERNAME = "Label_SupplierName";
	public final static String ID_LABEL_DUEAMOUNT = "Label_DueAmount";
	public final static String ID_DATE_BEGIN = "Date_Begin";
	public final static String ID_DATE_END = "Date_End";
	public final static String ID_BUTTON_ADJUST = "Button_Adjust";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	
//	private String supplierName = "供应商中文名称";//供应商中文名称
//	private double balanceAmount = 0.00;//应付余额
	
	private BalanceInfo balanceInfo;
//	private GetDealingsListKey key = new GetDealingsListKey(0, 2000, true);
	private GUID supplierId;
	private SDatePicker startDatePicker;
	private SDatePicker endDatePicker;
	private LoginInfo loginInfo;
	
	@Override
	public void init(Situation context) {
		loginInfo = context.find(LoginInfo.class);
		supplierId = (GUID) getArgument();
		balanceInfo = getContext().find(BalanceInfo.class, supplierId);
	}
	@Override
	public void process(final Situation situation) {
		
		super.process(situation);
		
		startDatePicker = createControl(ID_DATE_BEGIN, SDatePicker.class);
		endDatePicker = createControl(ID_DATE_END, SDatePicker.class);
		TimeTest tt = new TimeTest();
		startDatePicker.setDate(DateUtil.convertLongToDate(tt.getFirstDayOfMonth()));
		endDatePicker.setDate(new Date());
		
		final Label lblSupplierName = this.createControl(ID_LABEL_SUPPLIERNAME, Label.class, JWT.NONE);
		lblSupplierName.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));
		PartnerInfo partnerInfo = situation.find(PartnerInfo.class, supplierId);
		lblSupplierName.setText(partnerInfo == null ? "" : partnerInfo.getName());
		
		final Label lblBalanceAmount = this.createControl(ID_LABEL_DUEAMOUNT, Label.class, JWT.NONE);
		final Button adjustButton = this.createControl(ID_BUTTON_ADJUST, Button.class, JWT.NONE);
		lblBalanceAmount.setText(balanceInfo == null ? "0.00元" : DoubleUtil.getRoundStr((balanceInfo.getAmount())) + "元");
		if(balanceInfo != null && (loginInfo.hasAuth(Auth.Boss) | loginInfo.hasAuth(Auth.Account))) {//只有总经理和财务可以进行调整
			adjustButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					PageController pc = new PageController(SupplierBalanceAdjustProcessor.class, SupplierBalanceAdjustRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, balanceInfo);
					WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
					style.setSize(518, 200);
					MsgRequest request = new MsgRequest(pci, "应付款调整", style);
					request.setResponseHandler(new ResponseHandler() {
						
						public void handle(Object returnValue, Object returnValue2,
								Object returnValue3, Object returnValue4) {
							balanceInfo = getContext().find(BalanceInfo.class, supplierId);
							lblBalanceAmount.setText(DoubleUtil.getRoundStr(balanceInfo.getAmount()) + "元");
							lblBalanceAmount.getParent().layout();
							table.render();
							//往上一层发消息，数据发生修改
							situation.bubbleMessage(new MsgResponse(false));
						}
					});
					situation.bubbleMessage(request);
				}
			});
		} else {
			adjustButton.dispose();
		}
		Button btnSearch = this.createControl(ID_BUTTON_SEARCH, Button.class, JWT.NONE);
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetDealingsListKey key = new GetDealingsListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setPartnerId(supplierId);
		key.setStartDate(startDatePicker.getDate().getTime());
		key.setEndDate(endDatePicker.getDate().getTime());
		List<BalanceInfoItem> resultList = context.getList(BalanceInfoItem.class, key);
		return resultList.toArray();
	}

	public String getElementId(Object element) {
		if(((BalanceInfoItem)element).getId() == null) {
			return GUID.randomID().toString();
		}
		return ((BalanceInfoItem)element).getId().toString();
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if(Action.AccountDetail.name().equals(actionName))
		{
			PageController pc = new PageController(PaymentDetailProcessor.class, PaymentDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc,GUID.valueOf(table.getExtraData(rowId,
					"accountBillsId")[0]));
			// WindowStyle style = new WindowStyle(JWT.CLOSE |
			// JWT.MODAL);
			// style.setSize(518, 200);
			MsgRequest request = new MsgRequest(pci,"付款单");
			getContext().bubbleMessage(request);
		}
		else if(Action.Detail.name().equals(actionName))
		{
			PageController pc = new PageController(SupplierBalanceAdjustProcessor.class, SupplierBalanceAdjustRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, balanceInfo, false, GUID.valueOf(rowId));
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(518, 200);
			MsgRequest request = new MsgRequest(pci, "应付款调整", style);
			getContext().bubbleMessage(request);
		}
		
	}
	
	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof BalanceInfoItem) {
			BalanceInfoItem item = (BalanceInfoItem) element;
			return new SNameValue[] { new SNameValue("accountBillsId", item.getAccountBillsId().toString()) };
		}
		return super.getExtraData(element);
	}
	@Override
	protected String getExportFileTitle() {
		return "供应商往来明细";
	}
	
}