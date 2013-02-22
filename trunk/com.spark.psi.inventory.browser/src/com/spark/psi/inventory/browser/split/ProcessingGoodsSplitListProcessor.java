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
import com.spark.psi.inventory.browser.split.NewGoodsSplitListProcessor.Columns;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitItem;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey.SortField;

/**
 * 已处理完成的出库单列表处理器
 */
public class ProcessingGoodsSplitListProcessor extends
		PSIListPageProcessor<GoodsSplitItem> {

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

	private Map<String, GoodsSplitItem> itemMap = new HashMap<String, GoodsSplitItem>();

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
		GetGoodsSplitBillListKey key = new GetGoodsSplitBillListKey(tablestatus
				.getBeginIndex(), tablestatus.getPageSize(), false);
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		key.setStatus(new GoodsSplitStatus[]{GoodsSplitStatus.Distributed,GoodsSplitStatus.Checkingin});
		key.setSearchText(searchText.getText());
		key.setBeginTime(context.find(QueryTerm.class, queryTermList
						.getText()).getStartTime());
		key.setEndTime(context.find(QueryTerm.class, queryTermList
				.getText()).getEndTime());
		ListEntity<GoodsSplitItem> entity = context.find(ListEntity.class, key);
		List<GoodsSplitItem> itemList = entity.getItemList();
		
		
		// if (CheckIsNull.isEmpty(itemList)) {
		// countLabel.setText("0");
		// return null;
		// }
		GoodsSplitItem[] items = new GoodsSplitItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
			itemMap.put(items[i].getRECID().toString(), items[i]);
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
		if (NewGoodsSplitListProcessor.Columns.Creator.name().equals(sortColumn)) {
			return SortField.Creator;
		} else if (NewGoodsSplitListProcessor.Columns.CreateDate.name().equals(sortColumn)) {
			return SortField.CreateDate;
		} else if (NewGoodsSplitListProcessor.Columns.Status.name().equals(sortColumn)) {
			return SortField.Status;
		} else if (NewGoodsSplitListProcessor.Columns.SheetNumber.name().equals(sortColumn)) {
			return SortField.BillNumber;
		} 
		return null;
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((GoodsSplitItem) element).getRECID().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[]{Action.Detail.name()};
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		PageController pc = new PageController(GoodsSplitDetailProcessor.class, GoodsSplitDetailRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc,GUID.valueOf(rowId));
		MsgRequest request = new MsgRequest(pci, "拆分单");
		request.setResponseHandler(new ResponseHandler() {
			
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getExportFileTitle() {
		return "商品拆分单";
	}
}