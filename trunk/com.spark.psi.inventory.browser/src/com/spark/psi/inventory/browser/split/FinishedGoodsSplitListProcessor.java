package com.spark.psi.inventory.browser.split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckoutSheetItem;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey.SortField;

/**
 * 已处理完成的出库单列表处理器
 */
public class FinishedGoodsSplitListProcessor extends
		PSIListPageProcessor<CheckoutSheetItem> {

	// 选择ComboList框，用于日期过滤项目条件设置 本周、上周、上月、本月
	public final static String ID_COMBOLIST_DATEITEM = "ComboList_DateItem";
	// 出库单数量
	public final static String ID_LABEL_CHECKOUTGINSHEET_COUNT = "Label_CheckingOut_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";
	// 搜索按钮
	public final static String ID_BUTTON_SEARCH = "Button_Search";

	// 编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns {
		LastCheckoutDate, SheetNumber, Type, RelatedNumber, StoreName, CheckoutEmployees, status
	}

	private Map<String, CheckoutSheetItem> itemMap = new HashMap<String, CheckoutSheetItem>();

	private LWComboList queryTermList;
	private Text searchText;
	private Label countLabel;

	@Override
	public void process(Situation situation) {
		super.process(situation);

		queryTermList = this.createControl(ID_COMBOLIST_DATEITEM,
				LWComboList.class);
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});

		countLabel = this.createControl(ID_LABEL_CHECKOUTGINSHEET_COUNT,
				Label.class);

		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	/*
	 * 获取对象列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetCheckingOutListKey key = new GetCheckingOutListKey(tablestatus
				.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setRealGoods(true);
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		key.setSearchText(searchText.getText());
		key
				.setQueryTerm(context.find(QueryTerm.class, queryTermList
						.getText()));
		List<CheckoutSheetItem> itemList = context.getList(
				CheckoutSheetItem.class, key);
		// if (CheckIsNull.isEmpty(itemList)) {
		// countLabel.setText("0");
		// return null;
		// }
		CheckoutSheetItem[] items = new CheckoutSheetItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
			itemMap.put(items[i].getId().toString(), items[i]);
		}
		int size = items.length;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
		// countLabel.setText("" + items.length);
		countLabel.getParent().getParent().layout();
		return items;
	}

	private SortType getSortType(SSortDirection sortDirection) {
		if (SSortDirection.ASC.equals(sortDirection)) {
			return SortType.Asc;
		} else {
			return SortType.Desc;
		}
	}

	private SortField getSortField(String sortColumn) {
		if (Columns.CheckoutEmployees.name().equals(sortColumn)) {
			return SortField.CheckoutEmployees;
		} else if (Columns.LastCheckoutDate.name().equals(sortColumn)) {
			return SortField.LastCheckoutDate;
		} else if (Columns.Type.name().equals(sortColumn)) {
			return SortField.Type;
		} else if (Columns.RelatedNumber.name().equals(sortColumn)) {
			return SortField.RelatedNumber;
		} else if (Columns.StoreName.name().equals(sortColumn)) {
			return SortField.StoreName;
		} else if (Columns.SheetNumber.name().equals(sortColumn)) {
			return SortField.SheetNumber;
		} else if (Columns.status.name().equals(sortColumn)) {
			return SortField.status;
		}
		return null;
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((CheckoutSheetItem) element).getId().toString();
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (ID_ACTION_EDIT.equals(actionName)) {
			// CheckOutBaseInfo info = getContext().find(CheckOutBaseInfo.class,
			// GUID.valueOf(rowId));
			// PageController pc = new
			// PageController(CheckedOutDetailProcessor.class,
			// CheckedOutDetailRender.class);
			// PageControllerInstance pci = new PageControllerInstance(pc, info,
			// rowId);
			// MsgRequest request = new MsgRequest(pci, "出库单详情");
			// request.setResponseHandler(new ResponseHandler() {
			// public void handle(Object returnValue, Object returnValue2,
			// Object returnValue3, Object returnValue4) {
			// table.render();
			// }
			// });
			// getContext().bubbleMessage(request);
		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "成品出库单";
	}
}