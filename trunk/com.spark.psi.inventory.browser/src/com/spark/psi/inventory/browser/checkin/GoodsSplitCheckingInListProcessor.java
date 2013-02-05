package com.spark.psi.inventory.browser.checkin;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;
import com.spark.psi.publish.inventory.entity.CheckingInItem;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey.SortField;

/**
 * 销售退货入库单列表处理器
 */
public class GoodsSplitCheckingInListProcessor extends PSIListPageProcessor<CheckingInItem> {

	// 入库单数量
	public final static String ID_LABEL_CheckingIn_COUNT = "Label_CheckingIn_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";
	// 搜索按钮
	public final static String ID_BUTTON_SEARCH = "Button_Search";

	// 编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns {
		CreateDate, RelatedNumber, StoreName, status
	}

	private Label countLabel;
	private Text searchText;

	@Override
	public void process(Situation situation) {

		super.process(situation);

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
		key.setType(CheckingInType.GoodsSplit);
		key.setSearchText(searchText.getText());
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));

		}
		List<CheckingInItem> itemList = context.getList(CheckingInItem.class, key);
//		if (CheckIsNull.isEmpty(itemList)) {
//			countLabel.setText("0");
//			return null;
//		}
		this.createControl(ID_LABEL_CheckingIn_COUNT, Label.class).setText(String.valueOf(itemList.size()));
		CheckingInItem[] items = new CheckingInItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
		}
		int size = items.length;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
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
		if (Columns.CreateDate.name().equals(sortColumn)) {
			return SortField.CreateDate;
		} else if (Columns.RelatedNumber.name().equals(sortColumn)) {
			return SortField.RelatedNumber;
		}  else if (Columns.status.name().equals(sortColumn)) {
			return SortField.status;
		} else if (Columns.StoreName.name().equals(sortColumn)) {
			return SortField.StoreName;
		}
		return null;
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((CheckingInItem) element).getSheetId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.CheckIn.name() };
	}

	private boolean hasInspectCount(CheckingInInfo info) {
		boolean b = false;
		for (CheckingGoodsItem item : info.getCheckingGoodsItems()) {
			if (item.getInspectCount() > 0) {
				b = true;
				break;
			}
		}
		return b;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		CheckingInInfo info = getContext().find(CheckingInInfo.class, GUID.valueOf(rowId));
		boolean b = hasInspectCount(info);
		if (!actionName.equals(Action.CheckIn.name()) && !ID_ACTION_EDIT.equals(actionName)) {
			return;
		}
		if (b) {
			openDetailPages(info, rowId);
		} else {
			openDetailPage(info, rowId);
		}
	}

	private void openDetailPages(CheckingInInfo info, String rowId) {
		BaseFunction[] functions = new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(new PageController(CheckingInDetailProcessor.class,
						CheckingInDetailRender.class), info, rowId), "单据详情"),
				new BaseFunction(new PageControllerInstance(new PageController(InspectCheckingInDetailProcessor.class,
						InspectCheckingInDetailRender.class), info, rowId), "待检材料") };
		MsgRequest request = new MsgRequest(functions, "供应商详情");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private void openDetailPage(CheckingInInfo info, String rowId) {
		PageController pc = new PageController(CheckingInDetailProcessor.class, CheckingInDetailRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, info, rowId);
		MsgRequest request = new MsgRequest(pci, "入库单详情");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}