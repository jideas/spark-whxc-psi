package com.spark.psi.inventory.browser.count;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;
import com.spark.psi.publish.inventory.key.GetInventoryCountSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryCountSheetListKey.SortField;
import com.spark.psi.publish.inventory.task.InventoryCountTask;
import com.spark.psi.publish.inventory.task.InventoryCountTask.TaskGoodsCountItem;

/**
 * 库存盘点单列表处理器
 * 
 */
public class InventoryCountSheetListProcessor extends PSIListPageProcessor<InventoryCountSheetItem> {

	// 新增盘点
	public final static String ID_BUTTON_INVENTORYCOUNTSHEET_NEW = "Button_New_InventoryCountSheet";
	// 盘点单数量
	public final static String ID_LABEL_INVENTORYCOUNTSHEET_COUNT = "Label_InventoryCountSheet_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";

	public final static String ID_ACTION_EDIT = "edit";

	//
	public static enum Columns {
		StartDate, EndDate, SheetNumber, Sheetstatus, StoreName, CountProfit, CountLoss
	}

	private Label countLable;
	private Text searchText;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		countLable = this.createControl(ID_LABEL_INVENTORYCOUNTSHEET_COUNT, Label.class, JWT.NONE);
		countLable.setText("3");

		//
		this.createControl(ID_BUTTON_INVENTORYCOUNTSHEET_NEW, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(InventoryCountTypeSelectProcessor.class, InventoryCountTypeSelectRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				windowStyle.setSize(360, 180);
				MsgRequest request = new MsgRequest(pci, "新增盘点", windowStyle);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						final GUID storeId = (GUID) returnValue;// 盘点仓库
						String counter = (String) returnValue2; // 盘点人
						final InventoryCountType countType = (InventoryCountType) returnValue3; // 盘点类型
						InventoryCountTask task = new InventoryCountTask(null, new TaskGoodsCountItem[0]);
						task.setName(counter);
						task.setStoreId(storeId);
						task.setType(countType);
						getContext().handle(task, InventoryCountTask.Method.Insert);
						table.render();
						openInventoryCountInfoWindow(task.getSheetId().toString(), countType, returnValue4);
					}
				});
				getContext().bubbleMessage(request);
			}
		});

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
		GetInventoryCountSheetListKey key = new GetInventoryCountSheetListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		key.setSearchText(searchText.getText());
		List<InventoryCountSheetItem> list = getContext().getList(InventoryCountSheetItem.class, key);
//		if (CheckIsNull.isNotEmpty(list)) {
//			InventoryCountSheetItem[] items = new InventoryCountSheetItem[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				items[i] = list.get(i);
//			}
//		}
		int size = list.size();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLable.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLable.setText(String.valueOf(size));
		return list.toArray(new InventoryCountSheetItem[0]);
	}

	private SortType getSortType(SSortDirection sortDirection) {
		if (SSortDirection.ASC.equals(sortDirection)) {
			return SortType.Asc;
		} else {
			return SortType.Desc;
		}
	}

	private SortField getSortField(String sortColumn) {
		if (Columns.CountLoss.name().equals(sortColumn)) {
			return SortField.CountLoss;
		} else if (Columns.CountProfit.name().equals(sortColumn)) {
			return SortField.CountProfit;
		} else if (Columns.EndDate.name().equals(sortColumn)) {
			return SortField.EndDate;
		} else if (Columns.SheetNumber.name().equals(sortColumn)) {
			return SortField.SheetNumber;
		} else if (Columns.Sheetstatus.name().equals(sortColumn)) {
			return SortField.Sheetstatus;
		} else if (Columns.StartDate.name().equals(sortColumn)) {
			return SortField.StartDate;
		} else if (Columns.StoreName.name().equals(sortColumn)) {
			return SortField.StoreName;
		}
		return null;
	}

	@Override
	public SNameValue[] getExtraData(Object element) {

		SNameValue[] data = new SNameValue[] { new SNameValue("Type", ((InventoryCountSheetItem) element).getType().getCode()) };
		return data;
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (ID_ACTION_EDIT.equals(actionName)) {
			InventoryCountType type = InventoryCountType.getTypeByCode(table.getExtraData(rowId, "Type")[0]);
			openInventoryCountInfoWindow(rowId, type, null);
		}
	}

	/**
	 * 打开库存盘点单明细
	 * 
	 * @param rowId
	 *            void
	 */
	private void openInventoryCountInfoWindow(String rowId, InventoryCountType type, Object returnValue4) {

		MsgRequest request = null;
		if (InventoryCountType.Materials.equals(type)) {
			InventoryCountSheetInfo info = getContext().find(InventoryCountSheetInfo.class, GUID.valueOf(rowId));
			PageController pc = new PageController(GoodsCountSheetDetailProcessor.class, GoodsCountSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, info, returnValue4);
			request = new MsgRequest(pci, "材料库存盘点单明细");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
		} else {
			InventoryCountSheetInfo info = getContext().get(InventoryCountSheetInfo.class, GUID.valueOf(rowId));
			if (null == info) {
				alert("单据不存在，请检查！");
			}
			PageController pc = new PageController(KitCountSheetDetailProcessor.class, KitCountSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, info, returnValue4);
			request = new MsgRequest(pci, "其他库存盘点单明细");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
		}
		getContext().bubbleMessage(request);
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((InventoryCountSheetItem) element).getSheetId().toString();
	}

	@Override
	protected String getExportFileTitle() {
		return "库存盘点";
	}

}