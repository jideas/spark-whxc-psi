package com.spark.psi.inventory.browser.checkin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInSheetShowItem;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey.SortField;

/**
 * 已处理完成的入库单列表处理器
 */
public class ProcessedCheckingInListProcessor extends PSIListPageProcessor<CheckInSheetShowItem> {

	// 选择下拉列表框，用于日期过滤项目条件设置 本周、上周、上月、本月
	public final static String ID_COMBOLIST_DATEITEM = "ComboList_DateItem";
	// 入库单数量
	public final static String ID_LABEL_CheckingIn_COUNT = "Label_CheckingIn_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";
	// 搜索按钮
	public final static String ID_BUTTON_SEARCH = "Button_Search";

	private Map<String, CheckInSheetShowItem> itemMap = new HashMap<String, CheckInSheetShowItem>();

	// 编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns {
		LastCheckinDate, SheetNumber, CheckInType, RelatedNumber, StoreName, status, CheckinEmployees
	}

	private LWComboList queryTermList;
	private Text searchText;
	private Label countLabel;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		queryTermList = this.createControl(ID_COMBOLIST_DATEITEM, LWComboList.class, JWT.NO);
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});

		countLabel = this.createControl(ID_LABEL_CheckingIn_COUNT, Label.class, JWT.NONE);

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
		GetCheckingInListKey key = new GetCheckingInListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		key.setSearchText(searchText.getText());
		key.setQueryTerm(context.find(QueryTerm.class, queryTermList.getText()));
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		List<CheckInSheetShowItem> itemList = context.getList(CheckInSheetShowItem.class, key);
//		if (CheckIsNull.isEmpty(itemList)) {
//			countLabel.setText("0");
//			return null;
//		}
		CheckInSheetShowItem[] items = new CheckInSheetShowItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
			itemMap.put(itemList.get(i).getId().toString(), items[i]);
		}
		int size = items.length;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
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

	/**
	 * 取得排序字段
	 * 
	 * @param sortColumn
	 * @return SortField
	 */
	private SortField getSortField(String sortColumn) {
		if (Columns.LastCheckinDate.name().equals(sortColumn)) {
			return SortField.LastCheckinDate;
		} else if (Columns.RelatedNumber.name().equals(sortColumn)) {
			return SortField.RelatedNumber;
		} else if (Columns.CheckInType.name().equals(sortColumn)) {
			return SortField.Type;
		} else if (Columns.SheetNumber.name().equals(sortColumn)) {
			return SortField.SheetNumber;
		} else if (Columns.status.name().equals(sortColumn)) {
			return SortField.status;
		} else if (Columns.StoreName.name().equals(sortColumn)) {
			return SortField.StoreName;
		} else if (Columns.CheckinEmployees.name().equals(sortColumn)) {
			return SortField.CheckinEmployees;
		}
		return null;
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((CheckInSheetShowItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (ID_ACTION_EDIT.equals(actionName)) {
			if (CheckingInType.Kit.equals(itemMap.get(rowId).getType())) {
				PageController pc = new PageController(KitCheckInSheetDetailProcessor.class, KitCheckInSheetDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, rowId);
				MsgRequest request = new MsgRequest(pci, "入库单详情");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			} else if (CheckingInType.Irregular.equals(itemMap.get(rowId).getType())) { 
				PageController pc = new PageController(IrregularCheckingInDetailProcessor.class, IrregularCheckingInDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
				MsgRequest request = new MsgRequest(pci, "零采入库明细");
				getContext().bubbleMessage(request);
			} else if (CheckingInType.RealGoods.equals(itemMap.get(rowId).getType())) { 
				PageController pc = new PageController(RealGoodsCheckedInDetailProcessor.class, RealGoodsCheckedInDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
				MsgRequest request = new MsgRequest(pci, "成品入库明细");
				getContext().bubbleMessage(request);
			} else if (CheckingInType.Joint.equals(itemMap.get(rowId).getType())) {  
				PageController pc = new PageController(JointCheckedInDetailProcessor.class, JointCheckedInDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
				MsgRequest request = new MsgRequest(pci, "联营入库明细");
				getContext().bubbleMessage(request);
			} else {
				CheckInBaseInfo info = getContext().find(CheckInBaseInfo.class, GUID.valueOf(rowId));
				PageController pc = new PageController(ShowCheckedInDetailProcessor.class, ShowCheckedInDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, info, rowId);
				MsgRequest request = new MsgRequest(pci, "入库单详情");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}

		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "入库单";
	}
}