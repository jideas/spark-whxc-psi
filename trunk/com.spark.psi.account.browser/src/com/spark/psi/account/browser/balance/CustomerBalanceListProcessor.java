package com.spark.psi.account.browser.balance;

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
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.account.browser.CustomerScopeSource;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.entity.BalanceItem;
import com.spark.psi.publish.account.entity.BalanceListEntity;
import com.spark.psi.publish.account.key.GetCustomerBalanceListKey;

/**
 * 客户往来列表处理器
 */
public class CustomerBalanceListProcessor extends
		PSIListPageProcessor<BalanceItem> {

	public final static String ID_COMBO_CUSTOMER_SCOPE = "Combo_CustomerScope";
	public final static String ID_LABEL_CUSTOMER_COUNT = "Label_CustomerCount";
	public final static String ID_LABEL_DUEAMOUNT = "Label_TotalDueAmount";
	public final static String ID_TEXT_SEARCH = "Text_SearchText";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	// 客户数理
	private int customerCount = 0;
	// 应收总额
	private double receivable = 0.00;
	
	private Label cutomerCountLabel;
	private Label dueAmountlabel;
	private LoginInfo loginInfo;
	List<BalanceItem> resultList = new ArrayList<BalanceItem>();
	GetCustomerBalanceListKey key = new GetCustomerBalanceListKey();
	
	private void initData(Context context) {
		// resultList = context.getList(BalanceItem.class, key);
		ListEntity<BalanceItem> listEntity = context.find(BalanceListEntity.class, key);
		if (null != listEntity) {
			resultList = listEntity.getItemList();
		}
		customerCount = resultList.size();
		receivable = 0;
		for (BalanceItem item : resultList) {
			receivable += item.getAmount();
		} 
		 
		cutomerCountLabel.setText(String.valueOf(customerCount));
		dueAmountlabel.setText(DoubleUtil.getRoundStr(receivable));
		
	}
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		
		loginInfo = situation.find(LoginInfo.class);
		
		cutomerCountLabel = this.createControl(ID_LABEL_CUSTOMER_COUNT,
				Label.class, JWT.NONE);
		dueAmountlabel = this.createControl(ID_LABEL_DUEAMOUNT,
				Label.class, JWT.NONE);
		final LWComboList queryScopeList = this.createControl(
				ID_COMBO_CUSTOMER_SCOPE, LWComboList.class, JWT.NONE);
		final CustomerScopeSource scopeSource = new CustomerScopeSource(situation);
		queryScopeList.getList().setSource(scopeSource);
		queryScopeList.getList().setInput(null);
		queryScopeList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				key.setQueryScope(scopeSource.getQueryScopeById(queryScopeList.getList().getSeleted()));
				table.render();
			}
		});
		queryScopeList.setSelection(scopeSource.getDefaultId());
		
		if(loginInfo.hasAuth(Auth.Sales) && !loginInfo.hasAuth(Auth.SalesManager)) {
			queryScopeList.dispose();
		}
		
		cutomerCountLabel.setText(String.valueOf(customerCount));
		dueAmountlabel.setText(DoubleUtil.getRoundStr(receivable));
		
		final SSearchText2 search = createControl(ID_TEXT_SEARCH, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				key.setSearchText(search.getText());
				table.render();
			}
		});
		
		this.table.getSelection();

	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		if(tablestatus.getSortColumn() != null) {
			key.setSortField(GetCustomerBalanceListKey.SortField.valueOf(tablestatus.getSortColumn()));
		}
		if(tablestatus.getSortDirection() != null) {
			if(tablestatus.getSortDirection()==SSortDirection.ASC){
				key.setSortType(SortType.Asc);
			}else{
				key.setSortType(SortType.Desc);
			}
		}
		initData(context);
		return resultList.toArray();
	}

	// 对象id标识
	public String getElementId(Object element) {
		return ((BalanceItem) element).getPartnerId().toString();
	}

	// 行操作按钮
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Detail.name() };
	}

	// 根据条件或情况排除不执行按钮的行
	protected String[] getElementActionIds(Object element) {
		BalanceItem item = (BalanceItem) element;
		if (null != item.getPartnerId()) {
			return new String[] { Action.Detail.name() };
		}
		return null;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Detail.name())) {
			PageController pc = new PageController(
					CustomerDealingsListProcessor.class,
					CustomerDealingsListRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.valueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "客户往来明细");
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
		return "客户往来";
	}
}