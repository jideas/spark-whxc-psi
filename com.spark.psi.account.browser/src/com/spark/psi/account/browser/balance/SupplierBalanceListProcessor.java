package com.spark.psi.account.browser.balance;

import java.util.ArrayList;
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
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.entity.BalanceItem;
import com.spark.psi.publish.account.entity.BalanceListEntity;
import com.spark.psi.publish.account.key.GetSupplierBalanceListKey;

/**
 * 供应商往来列表处理器
 */
public class SupplierBalanceListProcessor extends PSIListPageProcessor<BalanceItem> {

	public final static String ID_LABEL_SUPPLIER_COUNT = "Label_SupplierCount";
	public final static String ID_LABEL_DUEAMOUNT = "Label_TotalDueAmount";
	public final static String ID_TEXT_SEARCH = "Text_SearchText";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	
	private Label cutomerCountLabel;
	private Label dueAmountlabel;
	//供应商数理
	private int supplierCount = 0;
	//应付总额
	private double payableAmount = 0.00;
	
	List<BalanceItem> resultList = new ArrayList<BalanceItem>();
	final GetSupplierBalanceListKey key = new GetSupplierBalanceListKey();

	private void initData(Context context) {
		// resultList = context.getList(BalanceItem.class, key);
		ListEntity<BalanceItem> listEntity = context.find(BalanceListEntity.class, key);
		if (null != listEntity) {
			resultList = listEntity.getItemList();
		}
		supplierCount = resultList.size();
		
		payableAmount = 0;
		for(BalanceItem item : resultList) {
			payableAmount += item.getAmount();
		}
		
		cutomerCountLabel.setText(String.valueOf(supplierCount));
		dueAmountlabel.setText(DoubleUtil.getRoundStr(payableAmount));
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		
		cutomerCountLabel = this.createControl(ID_LABEL_SUPPLIER_COUNT,Label.class, JWT.NONE);
		
		dueAmountlabel = this.createControl(ID_LABEL_DUEAMOUNT, Label.class,JWT.NONE);
		
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
			key.setSortField(GetSupplierBalanceListKey.SortField.valueOf(tablestatus.getSortColumn()));
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
	
	public String getElementId(Object element) {
		return ((BalanceItem)element).getPartnerId().toString();
	}
	
	//行操作按钮
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Detail.name()};
	}
	//根据条件或情况排除不执行按钮的行
	protected String[] getElementActionIds(Object element) {
		BalanceItem item = (BalanceItem) element;
		if (null!=item.getPartnerId()) {
			return new String[] { Action.Detail.name() };
		}
		return null;
	}	
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,String actionValue) {
		if (actionName.equals(Action.Detail.name())) {
			PageController pc = new PageController(
					SupplierDealingsListProcessor.class,
					SupplierDealingsListRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.valueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "供应商往来明细");
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
		return "供应商往来";
	}

}
