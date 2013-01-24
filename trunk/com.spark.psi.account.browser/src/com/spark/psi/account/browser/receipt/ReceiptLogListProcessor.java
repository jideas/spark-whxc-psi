package com.spark.psi.account.browser.receipt;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.key.GetReceiptListKey;

/**
 * 收款记录列表处理器
 */
public class ReceiptLogListProcessor extends PSIListPageProcessor<ReceiptItem> {

	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_LABEL_AMOUNT = "Label_Amount";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_Search = "ID_Search";		
	
	public static enum ColumnName {
		receiptDate, sheetNo, partnerName, receiptType, receiptWay, amount, receiptPerson
	}
	
	private LWComboList queryTermList;
	private SSearchText2 search = null;
	private Label countLabel    = null;
	private Label amountLabel   = null;
	
	@Override
	public void process(final Situation situation) {
		
		super.process(situation);
		countLabel = this.createControl(ID_LABEL_COUNT, Label.class);
		amountLabel = this.createControl(ID_LABEL_AMOUNT, Label.class);
		search = createControl(ID_Search, SSearchText2.class);
		queryTermList = createControl(ID_COMBO_TIME, LWComboList.class);
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReceiptListKey key = new GetReceiptListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setStatus(ReceiptStatus.Receipted);
		key.setSearchText(search.getText());
		if(queryTermList != null) {
			key.setQueryTerm(getContext().find(QueryTerm.class,queryTermList.getText()));
		}
		ListEntity<ReceiptItem> listEntity = context.find(ReceiptListEntity.class, key);
		if (null == listEntity) return null;
		
		List<ReceiptItem> resultList = listEntity.getItemList();
		double receiptAmount = 0.00;
		for(ReceiptItem item : resultList) {
			receiptAmount += item.getAmount();
		}
		
		int size = resultList.size();
		double amount = receiptAmount;
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
		return listEntity.getItemList().toArray(new ReceiptItem[0]);
	}

	public String getElementId(Object element) {
		ReceiptItem item = (ReceiptItem)element;
		return item.getId().toString();
	}
	
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(ReceiptDetailProcessor.class, ReceiptDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "收款详情");
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
		return "收款记录";
	}
	
}