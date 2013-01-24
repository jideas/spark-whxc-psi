package com.spark.psi.inventory.browser.allocate;

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
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey.SortField;

/**
 * 所有已完成的库存调拨单列表处理器
 */
public class ProcessedAllocateSheetListProcessor extends
		PSIListPageProcessor<InventoryAllocateSheetItem> {

	// 选择下拉列表框，用于日期过滤项目条件设置 本周、上周、上月、本月
	public final static String ID_COMBOLIST_DATEITEM = "ComboList_DateItem";
	// 调拨单数量
	public final static String ID_LABEL_INVENTORYALLOCATESHEET_COUNT = "Label_InventoryAllocateSheet_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";

	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	/**列名常量*/
	public static enum Columns {
		SheetNumber, SourceStoreName, AllocateOutDate, DestinationStoreName, AllocateInDate, CreatorName;
	}
	
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

		countLabel = this.createControl(ID_LABEL_INVENTORYALLOCATESHEET_COUNT,
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
		GetInventoryAllocateSheetListKey key = new GetInventoryAllocateSheetListKey(
				tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setStatus(GetInventoryAllocateSheetListKey.Processedstatus);
		key.setQueryTerm(context.find(QueryTerm.class, queryTermList.getText()));
		key.setSearchText(searchText.getText());
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		List<InventoryAllocateSheetItem> itemsList = context.getList(
				InventoryAllocateSheetItem.class, key);
		int size = itemsList.size();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
		return itemsList.toArray();
	}
	
	private SortType getSortType(SSortDirection sortDirection) {
		if (SSortDirection.ASC.equals(sortDirection)) {
			return SortType.Asc;
		} else {
			return SortType.Desc;
		}

	}

	private SortField getSortField(String sortColumn) {
		if(Columns.AllocateInDate.name().equals(sortColumn))
		{
			return SortField.AllocateInDate;
		}
		else if(Columns.DestinationStoreName.name().equals(sortColumn))
		{
			return SortField.DestinationStoreName;
		}
		else if(Columns.SheetNumber.name().equals(sortColumn))
		{
			return SortField.SheetNumber;
		}
		else if(Columns.SourceStoreName.name().equals(sortColumn))
		{
			return SortField.SourceStoreName;
		}
		else if(Columns.AllocateOutDate.name().equals(sortColumn))
		{
			return SortField.AllocateOutDate;
		}
		else if(Columns.CreatorName.name().equals(sortColumn))
		{
			return SortField.CreatorName;
		}
		return null;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {

		if (ID_ACTION_VIEW.equals(actionName)
				|| Action.Approval.name().equals(actionName)) {
			InventoryAllocateSheetInfo info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(rowId));
			PageControllerInstance pci = new PageControllerInstance("AllocateSheetDetailBasePage", rowId, info);
			MsgRequest request = new MsgRequest(pci, "调拨单详情");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem) element;
		return item.getSheetId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	protected String getExportFileTitle() {
		return "调拔单";
	}
}