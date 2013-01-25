/**
 * 
 */
package com.spark.psi.order.browser.distribute;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.exception.AbortException;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.order.entity.SalesDistributeOrderItem;
import com.spark.psi.publish.order.key.GetSalesDistributeOrderListKey;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask;

/**
 * 销售配货列表处理器
 * 
 */
public class DistributingSalesOrderListProcessor extends
		PSIListPageProcessor<SalesDistributeOrderItem> {

	public static final String ID_LABEL_COUNT = "Label_Count";
	public static final String ID_TEXT_SEARCHTEXT = "Text_SearchText";
	// public static final String ID_BUTTON_SEARCHBUTTON =
	// "Button_SearchButton";
	private Text searchText;
	private Label lblOrderCount;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		lblOrderCount = this.createControl(ID_LABEL_COUNT, Label.class,
				JWT.NONE);
		searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		// ListEntity<SalesDistributeOrderItem>, GetSalesDistributeOrderListKey
		GetSalesDistributeOrderListKey key = new GetSalesDistributeOrderListKey();
		key.setSearchText(searchText.getText());
		if (tablestatus.getSortColumn() != null) {
			key.setSortField(GetSalesDistributeOrderListKey.SortField
					.valueOf(tablestatus.getSortColumn()));
			key.setSortType(tablestatus.getSortDirection() == SSortDirection.ASC ? SortType.Asc
					: SortType.Desc);
		}
		@SuppressWarnings("unchecked")
		ListEntity<SalesDistributeOrderItem> entity = getContext().find(
				ListEntity.class, key);
		lblOrderCount.setText(String.valueOf(entity.getItemList().size()));
		return entity.getItemList().toArray();
	}

	public String getElementId(Object element) {
		SalesDistributeOrderItem item = (SalesDistributeOrderItem) element;
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Allocate.name() };
	}

	@Override
	public String[] getElementActionIds(Object element) {
		SalesDistributeOrderItem item = (SalesDistributeOrderItem) element;
		return item.getActions().length == 0 ? null : getTableActionIds();
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Allocate.name())) {
			try {
				getContext().handle(
						new SalesOrderDistributeTask(GUID.valueOf(rowId)),
						SalesOrderDistributeTask.Method.Start);
				PageControllerInstance pci = new PageControllerInstance(
						"DistributeSalesOrderPage", rowId);
				MsgRequest request = new MsgRequest(pci, "销售配货明细");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			} catch (AbortException e) {
				table.render();
			}

		}
	}

	@Override
	protected String getExportFileTitle() {
		return "销售配货单";
	}
}
