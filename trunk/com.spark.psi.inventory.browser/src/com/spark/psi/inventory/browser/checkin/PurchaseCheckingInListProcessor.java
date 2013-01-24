package com.spark.psi.inventory.browser.checkin;

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
 * 采购入库单列表处理器
 */
public class PurchaseCheckingInListProcessor extends PSIListPageProcessor<CheckingInItem> {

	// 新增入库单
	public final static String ID_BUTTON_NEW_LXCG = "Button_New_CheckingIn_Lxcg";
	public final static String ID_BUTTON_NEW_TZRK = "Button_New_CheckingIn_Tzrk";
	public final static String ID_BUTTON_NEW_LYRK = "Button_New_CheckingIn_Lyrk";
	public final static String ID_BUTTON_NEW_GIFT = "Button_New_CheckingIn_Gift";
	// 入库单数量
	public final static String ID_LABEL_CheckingIn_COUNT = "Label_CheckingIn_Count";
	// 搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";

	// 编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns {
		PlanCheckinDate, RelatedNumber, StoreName, status, CreateDate
	}

	private Text searchText;
	private Label countLabel;

	@Override
	public void process(final Situation situation) {

		super.process(situation);

		countLabel = this.createControl(ID_LABEL_CheckingIn_COUNT, Label.class, JWT.NONE);

		Button btnAdd = this.createControl(ID_BUTTON_NEW_LXCG, Button.class, JWT.NONE);
		btnAdd.setText(" 新增零采入库 ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewIrregularCheckingInProcessor.class,
						NewIrregularCheckingInRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				situation.bubbleMessage(new MsgRequest(pci, "零星采购"));
				// alert("暂未实现");
			}
		});
		Button btnAdd2 = this.createControl(ID_BUTTON_NEW_TZRK, Button.class, JWT.NONE);
		btnAdd2.setText(" 新增冲抵入库 ");
		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewAdjustCheckingInProcessor.class,
						NewAdjustCheckingInRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				situation.bubbleMessage(new MsgRequest(pci, "调整入库"));
			}
		});

		Button btnAdd3 = this.createControl(ID_BUTTON_NEW_LYRK, Button.class, JWT.NONE);
		btnAdd3.setText(" 新增联营入库 ");
		btnAdd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewJointCheckingInProcessor.class,
						NewJointCheckingInRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				situation.bubbleMessage(new MsgRequest(pci, "联营入库"));
			}
		});
		
		Button btnAdd4 = this.createControl(ID_BUTTON_NEW_GIFT, Button.class, JWT.NONE);
		btnAdd4.setText(" 新增赠品入库 ");
		btnAdd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewGiftCheckingInProcessor.class,
						NewGiftCheckingInRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				situation.bubbleMessage(new MsgRequest(pci, "赠品入库 "));
			}
		});
		
		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	/**
	 * 获取对象列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetCheckingInListKey key = new GetCheckingInListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setType(CheckingInType.Purchase);
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
		if (Columns.PlanCheckinDate.name().equals(sortColumn)) {
			return SortField.PlanCheckinDate;
		} else if (Columns.RelatedNumber.name().equals(sortColumn)) {
			return SortField.RelatedNumber;
		} else if (Columns.status.name().equals(sortColumn)) {
			return SortField.status;
		} else if (Columns.StoreName.name().equals(sortColumn)) {
			return SortField.StoreName;
		} else if (Columns.CreateDate.name().equals(sortColumn)) {
			return SortField.CreateDate;
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
				new BaseFunction(new PageControllerInstance(new PageController(
						CheckingInDetailProcessor.class, CheckingInDetailRender.class), info, rowId), "单据详情"),
				new BaseFunction(new PageControllerInstance(new PageController(
						InspectCheckingInDetailProcessor.class, InspectCheckingInDetailRender.class), info,
						rowId), "待检材料") };
		MsgRequest request = new MsgRequest(functions, "供应商详情");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3,
					Object returnValue4) {
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
			public void handle(Object returnValue, Object returnValue2, Object returnValue3,
					Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
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

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}