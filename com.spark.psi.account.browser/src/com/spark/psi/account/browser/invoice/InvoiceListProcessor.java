package com.spark.psi.account.browser.invoice;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
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
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.account.browser.AccountScopeSource;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.entity.InvoiceItem;
import com.spark.psi.publish.account.key.GetInvoiceListKey;

/**
 * 开票列表处理器
 */
public class InvoiceListProcessor extends PSIListPageProcessor<InvoiceItem> {
	public final static String ID_COMBO_DEPARTMENT = "Combo_Department";
	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_LABEL_AMOUNT = "Label_Amount";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	
//	private GetInvoiceListKey key = new GetInvoiceListKey(0, JWT.MAXIMUM, true);
	private List<InvoiceItem> resultList = new ArrayList<InvoiceItem>();
	
	private double totalAmount = 0;
	private LWComboList queryScopeList;
	private LWComboList queryTermList; 
	private SSearchText2 search ;
	private Label countLabel;
	private Label amountLabel;
	
	private GUID customerId;
	private void loadData(Context context, STableStatus tablestatus) {
		GetInvoiceListKey key = new GetInvoiceListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setCustomerId(customerId);
		key.setSortField(GetInvoiceListKey.SortField.InvoiceDate);
		key.setSortType(SortType.Desc);
		key.setSearchText(search.getText());
		if(queryScopeList != null) {
			key.setDepartmentId(GUID.tryValueOf(queryScopeList.getList().getSeleted()));
		}
		if(queryTermList != null) {
			key.setQueryTerm(getContext().find(QueryTerm.class,queryTermList.getText()));
		}
		resultList = context.getList(InvoiceItem.class, key);
		
		totalAmount = 0;
		for(InvoiceItem item : resultList) {
			totalAmount += item.getAmount();
		}
		
		int size = resultList.size();
		double amount = totalAmount;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
			String preAmount = amountLabel.getText();
			if (StringHelper.isNotEmpty(preAmount)) {
				amount = DoubleUtil.sum(amount, DoubleUtil.strToDouble(preAmount));
			}
		}
		countLabel.setText(String.valueOf(size));
		amountLabel.setText(DoubleUtil.getRoundStr(amount));
	}
	
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		
		if(getArgument() != null && getArgument() instanceof GUID) {
			customerId = (GUID)getArgument();
		}
		countLabel = createLabelControl(ID_LABEL_COUNT);
		amountLabel = createLabelControl(ID_LABEL_AMOUNT);
		queryScopeList = createControl(ID_COMBO_DEPARTMENT, LWComboList.class);
		
		AccountScopeSource source = new AccountScopeSource(situation);
		queryScopeList.getList().setSource(source);
		queryScopeList.getList().setInput(null);
		queryScopeList.setSelection(source.getDeptTree().getRoot().getId().toString());
		
		queryScopeList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
//				key.setDepartmentId(GUID.tryValueOf(queryScopeList.getList().getSeleted()));
//				loadData(situation);
				table.render();
			}
		});
		
		queryTermList = createControl(ID_COMBO_TIME, LWComboList.class);
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		search = createControl(ID_TEXT_SEARCHTEXT, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// key.setSearchText(search.getText());
				table.render();
			}
		});
		
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		loadData(context, tablestatus);
		return resultList.toArray();
	}

	public String getElementId(Object element) {
		return ((InvoiceItem)element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[]{Action.Invalidate.name()};
	}
	
	
	@Override
	protected String[] getElementActionIds(Object element) {
		InvoiceItem item = (InvoiceItem)element;
		if(item.isInvalided()) {
			return null;
		} else {
			return new String[]{Action.Invalidate.name()};
		}
	}
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if(actionName.equals(Action.Invalidate.name())) {
			PageController pc = new PageController(InvalidatePageProcessor.class, InvalidatePageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(480, 300);
			MsgRequest request = new MsgRequest(pci, "作废原因", style);
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
		return "开票记录";
	}
	
	
}
