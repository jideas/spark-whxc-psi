package com.spark.psi.account.browser.payment;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.entity.PaymentListEntity;
import com.spark.psi.publish.account.key.GetPaymentListKey;

/**
 * 付款记录列表处理器
 */
public class PaymentListProcessor extends PSIListPageProcessor<PaymentItem> {
	
	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_LABEL_AMOUNT = "Label_Amount";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	
	
	public static enum ColumnName {
		payDate, sheetNo, partnerName, payType, payAmount, applyAmount, applier
	}
	private LWComboList queryTermList;
	private SSearchText2 search        = null;
	private Label        countLabel    = null;
	private Label        amountLabel   = null;
	
	@Override
	public void process(final Situation situation) {
		
		super.process(situation);
		
		queryTermList = createControl(ID_COMBO_TIME, LWComboList.class);
		search = createControl(ID_TEXT_SEARCHTEXT, SSearchText2.class);
		countLabel = createLabelControl(ID_LABEL_COUNT);
		amountLabel = createLabelControl(ID_LABEL_AMOUNT);
		
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
//				key.setSearchText(search.getText() == null ? "" : search.getText().trim());
//				key.setAdvanceCondition(null);
				table.render();
			}
		});
		
//		final Button advanceSearch = createControl(ID_Button_AdvanceSearch, Button.class);
//		advanceSearch.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				PageController pc = new PageController(AdvanceSearchConditionProcessor.class, AdvanceSearchConditionRender.class);
//				PageControllerInstance pci = new PageControllerInstance(pc);
//				WindowStyle style = new WindowStyle(JWT.MODAL | JWT.CLOSE);
//				style.setSize(440, 250);
//				MsgRequest request = new MsgRequest(pci, "高级搜索", style);
//				request.setResponseHandler(new ResponseHandler() {
//					
//					public void handle(Object returnValue, Object returnValue2,
//							Object returnValue3, Object returnValue4) {
//						if(returnValue == null) return;
//						// GetPaymentListKey.AdvanceCondition condition = (GetPaymentListKey.AdvanceCondition)returnValue;
////						key.setAdvanceCondition(condition);
////						key.setSearchText(null);
//						// table.render();
//					}
//				});
//				situation.bubbleMessage(request);
//			}
//		});
		
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetPaymentListKey key = new GetPaymentListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setSearchText(search.getText());
		key.setStatus(PaymentStatus.Paid);
		if(queryTermList != null) {
			key.setQueryTerm(getContext().find(QueryTerm.class,queryTermList.getText()));
		}
		if(!StringUtils.isEmpty(tablestatus.getSortColumn())){
			key.setSortField(GetPaymentListKey.SortField.valueOf(tablestatus.getSortColumn()));
			if(tablestatus.getSortDirection()==SSortDirection.ASC){
				key.setSortType(SortType.Asc);
			}else{
				key.setSortType(SortType.Desc);
			}
		}	
		ListEntity<PaymentItem> listEntity = context.find(PaymentListEntity.class, key);
		if (null == listEntity) return null;
		List<PaymentItem> resultList = listEntity.getItemList();
		double payAmount = 0.0;
		for(PaymentItem item : resultList) {
			payAmount += item.getAmount();
		}
		
		int size = resultList.size();
		double amount = payAmount;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
			String preAmount = amountLabel.getText();
			if (StringHelper.isNotEmpty(preAmount)) {
				amount = DoubleUtil.sub(amount, DoubleUtil.strToDouble(preAmount));
			}
		}
		countLabel.setText(String.valueOf(size));
		amountLabel.setText(DoubleUtil.getRoundStr(amount));
		return resultList.toArray(new PaymentItem[0]);
	}

	public String getElementId(Object element) {
		return ((PaymentItem)element).getId().toString();
	}
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Detail.name())) {
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
		return "付款记录";
	}
}