package com.spark.psi.inventory.browser.split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitItem;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey.SortField;
import com.spark.psi.publish.split.task.DeleteGoodsSplitBillTask;
import com.spark.psi.publish.split.task.UpdateGoodsSplitStatusTask;

/**
 * 已处理完成的出库单列表处理器
 */
public class NewGoodsSplitListProcessor extends
		PSIListPageProcessor<GoodsSplitItem> {

	// 选择ComboList框，用于日期过滤项目条件设置 本周、上周、上月、本月
//	public final static String ID_COMBOLIST_DATEITEM = "ComboList_DateItem";
//	// 出库单数量
	public final static String ID_LABEL_CHECKOUTGINSHEET_COUNT = "Label_CheckingOut_Count";
//	// 搜索文本
//	public final static String ID_TEXT_SEARCH = "Text_Search";
//	// 搜索按钮
//	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public static final String ID_Button_Add = "Button_Add"; 

	// 编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns {
		CreateDate, SheetNumber, Creator, Status
	}

	private Map<String, GoodsSplitItem> itemMap = new HashMap<String, GoodsSplitItem>();

//	private LWComboList queryTermList;
//	private Text searchText;
	private Label countLabel;

	@Override
	public void process(final Situation context) {
		super.process(context);
		countLabel = this.createControl(ID_LABEL_CHECKOUTGINSHEET_COUNT,
				Label.class);
		final Button addButton    = createControl(ID_Button_Add, Button.class); 
		if(null!=addButton)
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				PageController pc = new PageController(NewGoodsSplitDetailProcessor.class, NewGoodsSplitDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "新增拆分");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				context.bubbleMessage(request);
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
		key.setStatus(new GoodsSplitStatus[]{GoodsSplitStatus.Submiting,GoodsSplitStatus.Rejected});
		ListEntity<GoodsSplitItem> entity = context.find(ListEntity.class, key);
		List<GoodsSplitItem> itemList = entity.getItemList();
//		key.setSearchText(searchText.getText());
//		key
//				.setQueryTerm(context.find(QueryTerm.class, queryTermList
//						.getText()));
		
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
		if (Columns.Creator.name().equals(sortColumn)) {
			return SortField.Creator;
		} else if (Columns.CreateDate.name().equals(sortColumn)) {
			return SortField.CreateDate;
		} else if (Columns.Status.name().equals(sortColumn)) {
			return SortField.Status;
		} else if (Columns.SheetNumber.name().equals(sortColumn)) {
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

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (ID_ACTION_EDIT.equals(actionName)) {
			PageController pc = new PageController(NewGoodsSplitDetailProcessor.class, NewGoodsSplitDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc,GUID.valueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "编辑拆分单");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
		else if (Action.Submit.name().equals(actionName)) {
			UpdateGoodsSplitStatusTask task = new UpdateGoodsSplitStatusTask(GUID.valueOf(rowId), GoodsSplitStatus.Approvaling);
			getContext().handle(task);
			table.render();
		}
		else if (Action.Delete.name().equals(actionName)) {
			confirm("确定要删除吗？", new Runnable() {
				
				public void run() {
					DeleteGoodsSplitBillTask task = new DeleteGoodsSplitBillTask(GUID.valueOf(rowId));
					getContext().handle(task);
					table.render();
				}
			});
			
		}
	}
	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name(), Action.Delete.name()};
	}
	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "商品拆分单";
	}
}