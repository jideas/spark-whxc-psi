package com.spark.psi.inventory.browser.count;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.common.utils.excel.ExcelReadHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfRender;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.inventory.intf.publish.entity.InventoryCountSheetInfoImpl.GoodsCountItemImpl;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsItemByNoKey;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo.GoodsCountItem;
import com.spark.psi.publish.inventory.key.GetInventoryInfoListKey;
import com.spark.psi.publish.inventory.task.InventoryCountTask;
import com.spark.psi.publish.inventory.task.InventoryCountTask.TaskGoodsCountItem;

/**
 * 材料库存盘点单明细界面处理器
 * 
 */
public class GoodsCountSheetDetailProcessor extends SimpleSheetPageProcessor<InventoryInfo> implements
		IDataProcessPrompt {

	public final static String ID_Text_Search = "Text_Search";
	public final static String ID_Button_AddGoods = "Button_AddGoods";
	public final static String ID_Button_Finish = "Button_Finish";
	public final static String ID_Button_Save = "Button_Save";

	public final static String ID_Label_Store = "Label_Store";

	public static enum Columns {
		GoodsCode, GoodsNo, GoodsName, GoodsProperties, GoodsUnit, Count, AcutalCount, BalanceCount, Memo
	}

	private InventoryCountSheetInfo countSheet;
	private Item[] items = null;

	/**
	 * 创建选择材料按钮
	 */
	private void createSelectGoodsButton() {
		Button selectGoodsButton = this.createControl(ID_Button_AddGoods, Button.class);
		if (selectGoodsButton != null) {
			selectGoodsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(null);
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3,
								Object returnValue4) {
							MaterialsItemInfo[] items = (MaterialsItemInfo[]) returnValue;
							Set<String> rowIds = new HashSet<String>();
							for (String rowId : table.getAllRowId()) {
								rowIds.add(rowId);
							}
							for (MaterialsItemInfo goodsItem : items) {
								Item item = new Item();
								item.setGoodsCode(goodsItem.getBaseInfo().getCode());
								item.setGoodsNo(goodsItem.getItemData().getMaterialNo());
								item.setGoodsItemId(goodsItem.getItemData().getId());
								item.setGoodsItemName(goodsItem.getBaseInfo().getName());
								item.setGoodsItemProperties(goodsItem.getItemData().getPropertiesWithoutUnit());
								item.setGoodsItemUnit(goodsItem.getItemData().getPropertyValues()[0]);
								GetInventoryInfoListKey key = new GetInventoryInfoListKey(new GUID[] { goodsItem
										.getItemData().getId() }, new GUID[] { countSheet.getStoreId() });
								List<InventoryInfo> detailList = getContext().getList(InventoryInfo.class, key);
								if (CheckIsNull.isNotEmpty(detailList)) {
									item.setCount(detailList.get(0).getCount());
									item.setActualCount(detailList.get(0).getCount()); // 默认为账面数
								}
								String itemId = goodsItem.getItemData().getId().toString();
								if (!rowIds.contains(itemId)) {
									rowIds.add(itemId);
									table.addRow(item);
								}
							}
							table.renderUpate();
						}
					});
					getContext().bubbleMessage(request);
				}
			});
		}
	}

	public void process(Situation context) {
		super.process(context);

		registerValidator();
		// 盘点中才允许添加材料和导出
		if (countSheet == null || countSheet.getSheetstatus() == InventoryCountStatus.Counting) {
			// 创建选择材料按钮
			createSelectGoodsButton();
		}

		Button finishButton = this.createControl(ID_Button_Finish, Button.class);
		if (finishButton != null) {
			finishButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (!validateInput()) {
						return;
					}
					String[] rowIds = table.getAllRowId();

					List<TaskGoodsCountItem> list = new ArrayList<TaskGoodsCountItem>();
					for (int i = 0; i < rowIds.length; i++) {
						String value = table.getEditValue(rowIds[i], Columns.AcutalCount.name())[0];
						String memo = table.getEditValue(rowIds[i], Columns.Memo.name())[0];
						if (CheckIsNull.isNotEmpty(value) && (Double.valueOf(value) > 0 || Double.valueOf(value) == 0)) {
							list.add(new TaskGoodsCountItem(GUID.valueOf(rowIds[i]), Double.valueOf(value), memo));
						}
					}
					TaskGoodsCountItem[] items = new TaskGoodsCountItem[list.size()];
					for (int i = 0; i < list.size(); i++) {
						items[i] = list.get(i);
					}

					InventoryCountTask task = new InventoryCountTask(countSheet.getSheetId(), items);
					task.setMemo(GoodsCountSheetDetailProcessor.super.createMemoText().getText());
					task.setType(InventoryCountType.Materials);
					task.setStoreId(countSheet.getStoreId());
					getContext().handle(task, InventoryCountTask.Method.Modify);
					doRealSave(task);
				}
			});
		}
		Button saveButton = this.createControl(ID_Button_Save, Button.class);
		if (saveButton != null) {
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					processData();
				}
			});
		}

		initControls();
	}

	private void doRealSave(final InventoryCountTask task) {
		Map<GUID, Item> map = new HashMap<GUID, Item>();
		for (Item item : items) {
			map.put(item.getGoodsItemId(), item);
		}
		List<DistributeInventoryItem> inventorysAdd = new ArrayList<DistributeInventoryItem>();
		final List<DistributeInventoryItem> inventorysSub = new ArrayList<DistributeInventoryItem>();

		for (TaskGoodsCountItem item : task.getTaskGoodsCountItems()) {
			DistributeInventoryItem dii = new DistributeInventoryItem();
			dii.setStockId(item.getGoodsItemId());
			dii.setName(map.get(item.getGoodsItemId()).getGoodsItemName());
			double oldCount = map.get(item.getGoodsItemId()).getCount();
			if (DoubleUtil.round(oldCount) > DoubleUtil.round(item.getActualCount())) {
				dii.setCount(DoubleUtil.round(oldCount) - DoubleUtil.round(item.getActualCount()));
				inventorysSub.add(dii);
			} else if (DoubleUtil.round(oldCount) < DoubleUtil.round(item.getActualCount())) {
				dii.setCount(DoubleUtil.round(item.getActualCount()) - DoubleUtil.round(oldCount));
				inventorysAdd.add(dii);
			}
		}
		if (inventorysAdd.size() <= 0 && inventorysSub.size() <= 0) {
			doRealFinish(task);
			return;
		} else if (inventorysAdd.size() <= 0 && inventorysSub.size() > 0) {
			PageController pc = new PageController(InventoryShelfInfoPageProcessor.class,
					InventoryShelfInfoPageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, task.getStoreId(), inventorysSub
					.toArray(new DistributeInventoryItem[inventorysSub.size()]));
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			windowStyle.setSize(820, 480);
			MsgRequest request = new MsgRequest(pci, "盘亏选择货位", windowStyle);

			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					if (null != returnValue) {
						task.setInventorysSub((DistributeInventoryItem[]) returnValue);
						doRealFinish(task);
					}
				}
			});
			context.bubbleMessage(request);
			return;
		}
		PageController pc = new PageController(DistributeShelfProcessor.class, DistributeShelfRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, task.getStoreId(), inventorysAdd
				.toArray(new DistributeInventoryItem[inventorysAdd.size()]));
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(720, 480);
		MsgRequest request = new MsgRequest(pci, "盘盈选择货位", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null != returnValue) {
					task.setInventorysAdd((DistributeInventoryItem[]) returnValue);
					if (inventorysSub.size() <= 0) {
						doRealFinish(task);
						return;
					}
					PageController pc = new PageController(InventoryShelfInfoPageProcessor.class,
							InventoryShelfInfoPageRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, task.getStoreId(), inventorysSub
							.toArray(new DistributeInventoryItem[inventorysSub.size()]));
					WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
					windowStyle.setSize(820, 480);
					MsgRequest request = new MsgRequest(pci, "盘亏选择货位", windowStyle);

					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3,
								Object returnValue4) {
							if (null != returnValue) {
								task.setInventorysSub((DistributeInventoryItem[]) returnValue);
								doRealFinish(task);
							}
						}
					});
					context.bubbleMessage(request);
				}
			}
		});
		context.bubbleMessage(request);
	}

	private void doRealFinish(InventoryCountTask task) {
		context.handle(task, InventoryCountTask.Method.Finish);
		if (!task.isSuccess()) {
			if (null != task.getErrors() && task.getErrors().length > 0) {
				StringBuffer message = new StringBuffer();
				for (InventoryCountTask.Error error : task.getErrors()) {
					message.append(error.getMessage());
				}
				alert(message.toString());
				return;
			}
		}
		getContext().bubbleMessage(new MsgResponse(true));
	}

	/**
	 * 注册表格验证器 void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "盘点材料不能为空";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				if (Columns.Memo.name().equals(columnName)) {
					String[] values = table.getEditValue(rowId, Columns.AcutalCount.name());
					String memo = table.getEditValue(rowId, Columns.Memo.name())[0];
					String countString = table.getExtraData(rowId, Columns.Count.name())[0];
					String goodsName = table.getExtraData(rowId, Columns.GoodsName.name())[0];
					if (CheckIsNull.isNotEmpty(values[0])
							&& CheckIsNull.isNotEmpty(countString)
							&& DoubleUtil.strToDouble(countString).doubleValue() != DoubleUtil.strToDouble(values[0])
									.doubleValue()) {
						if (CheckIsNull.isEmpty(memo)) {
							return "材料：" + goodsName + "，说明不能为空！";
						}
					}
				}
				return null;
			}
		});
	}

	/**
	 * 保存
	 */
	private boolean save() {
		if (!validateInput()) {
			return false;
		}
		String[] rowIds = table.getAllRowId();

		List<TaskGoodsCountItem> list = new ArrayList<TaskGoodsCountItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String value = table.getEditValue(rowIds[i], Columns.AcutalCount.name())[0];
			String memo = table.getEditValue(rowIds[i], Columns.Memo.name())[0];
			if (CheckIsNull.isNotEmpty(value) && Double.valueOf(value) > 0) {
				list.add(new TaskGoodsCountItem(GUID.valueOf(rowIds[i]), Double.valueOf(value), memo));
			}
		}
		TaskGoodsCountItem[] items = new TaskGoodsCountItem[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items[i] = list.get(i);
		}
		InventoryCountTask task = new InventoryCountTask(countSheet.getSheetId(), items);
		task.setMemo(GoodsCountSheetDetailProcessor.super.createMemoText().getText());
		task.setType(InventoryCountType.Materials);
		task.setStoreId(countSheet.getStoreId());
		getContext().handle(task, InventoryCountTask.Method.Modify);
		if (!task.isSuccess()) {
			if (null != task.getErrors() && task.getErrors().length > 0) {
				StringBuffer message = new StringBuffer();
				for (InventoryCountTask.Error error : task.getErrors()) {
					message.append(error.getMessage());
				}
				alert(message.toString());
				return false;
			}
		}
		getContext().bubbleMessage(new MsgResponse(false));
		hint("保存成功");
		return true;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return items;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof Item) {
			Item item = (Item) element;
			return item.getGoodsItemId().toString();
		} else {
			GoodsItemInfo item = (GoodsItemInfo) element;
			return item.getItemData().getId().toString();
		}
	}

	public String getValue(Object element, String columnName) {
		if (countSheet != null && countSheet.getSheetstatus() == InventoryCountStatus.Counted) {
			return null;
		}
		if (element instanceof Item) {
			Item item = (Item) element;
			if (GoodsCountSheetDetailProcessor.Columns.AcutalCount.name().equals(columnName)) {
				return String.valueOf(item.actualCount);
			}
			if (GoodsCountSheetDetailProcessor.Columns.Memo.name().equals(columnName)) {
				return null == item.getRemark() ? "" : item.getRemark();
			}
		}
		return "";
	}

	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			table.removeRow(rowId);
			table.renderUpate();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	protected String[] getElementActionIds(Object element) {

		return null;
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		return null;
	}

	@Override
	protected String getRemark() {
		return countSheet.getRemark();
	}

	@Override
	protected String getSheetApprovalInfo() {
		if (countSheet != null) {
			String str = "盘点开始时间：" + DateUtil.dateFromat(countSheet.getStartDate(), "yyyy-MM-dd HH:mm:ss");
			if (countSheet.getSheetstatus().getCode().equals("02"))
				str += "　盘点结束时间：" + DateUtil.dateFromat(countSheet.getEndDate(), "yyyy-MM-dd HH:mm:ss");
			return str;
		}
		return "";
	}

	@Override
	protected String getSheetCreateInfo() {
		if (countSheet != null)
			return "制单人：" + countSheet.getCreatorName() + "　盘点人：" + countSheet.getName();
		return "";
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return countSheet.getSheetNumber();
	}

	@Override
	protected String getSheetTitle() {
		return "材料库存盘点单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	protected void initSheetData() {

		initData();

	}

	private void initControls() {
		if (null == countSheet) {
			return;
		}
		this.createControl(ID_Label_Store, Label.class).setText("盘点仓库：" + countSheet.getStoreName());
		this.createMemoText().setText(countSheet.getRemark());
		if (InventoryCountStatus.Counted.equals(countSheet.getSheetstatus())) {
			this.createMemoText().setEnabled(false);
		}
	}

	private void initData() {
		if (null != this.getArgument()) {
			List<Item> sortlist = new ArrayList<Item>();
			countSheet = (InventoryCountSheetInfo) this.getArgument();
			if (null != this.getArgument2()) {
				ExcelReadHelper excel = (ExcelReadHelper) this.getArgument2();
				Map<String, GoodsCountItem> map = new HashMap<String, GoodsCountItem>();
				if (null != countSheet.getGoodsCountItems()) {
					for (GoodsCountItem item : countSheet.getGoodsCountItems()) {
						map.put(item.getGoodsNo(), item);
					}
				}
				List<GoodsCountItem> list = new ArrayList<GoodsCountItem>();
				for (String[] values : excel.getData()) {
					if (CheckIsNull.isEmpty(values[0]) || CheckIsNull.isEmpty(values[1])) {
						continue;
					}
					String no = DoubleUtil.getRoundStrWithOutQfw(DoubleUtil.strToDouble(values[0]), 0);// values[0];
					double count = DoubleUtil.strToDouble(values[1]);
					GoodsCountItemImpl item = (GoodsCountItemImpl) map.get(no);
					if (null == item) {
						MaterialsItem goods = getContext().find(MaterialsItem.class, new GetMaterialsItemByNoKey(no));
						if (goods == null) {
							alert("系统中不存在编号为：" + no + "的材料，请检查");
							return;
						}
						item = new GoodsCountItemImpl();
						item.setActualCount(count);
						item.setGoodsCode(goods.getMaterialCode());
						item.setGoodsNo(goods.getMaterialNo());
						item.setGoodsItemId(goods.getId());
						item.setGoodsItemName(goods.getMaterialName());
						item.setGoodsItemProperties(goods.getSpec());
						item.setGoodsItemUnit(goods.getMaterialUnit());
					}
					list.add(item);
				}
				for (int i = 0; i < list.size(); i++) {
					GoodsCountItem goodsCountItem = list.get(i);
					Item item = new Item();
					item.setGoodsCode(goodsCountItem.getGoodsCode());
					item.setGoodsNo(goodsCountItem.getGoodsNo());
					item.setGoodsItemId(goodsCountItem.getGoodsItemId());
					item.setGoodsItemName(goodsCountItem.getGoodsItemName());
					item.setGoodsItemProperties(goodsCountItem.getGoodsItemProperties());
					item.setGoodsItemUnit(goodsCountItem.getGoodsItemUnit());
					item.setCount(DoubleUtil.round(goodsCountItem.getCount(), 2));
					item.setActualCount(goodsCountItem.getActualCount());
					item.setMemo(goodsCountItem.getRemark());
					item.setExistInventory(goodsCountItem.isExistInventory());
					item.setCountDecimal(goodsCountItem.getScale());
					sortlist.add(item);
				}
				return;
			}
			if (null != countSheet.getGoodsCountItems() && countSheet.getGoodsCountItems().length > 0) {
				for (int i = 0; i < countSheet.getGoodsCountItems().length; i++) {
					InventoryCountSheetInfo.GoodsCountItem goodsCountItem = countSheet.getGoodsCountItems()[i];
					Item item = new Item();
					item.setGoodsCode(goodsCountItem.getGoodsCode());
					item.setGoodsNo(goodsCountItem.getGoodsNo());
					item.setGoodsItemId(goodsCountItem.getGoodsItemId());
					item.setGoodsItemName(goodsCountItem.getGoodsItemName());
					item.setGoodsItemProperties(goodsCountItem.getGoodsItemProperties());
					item.setGoodsItemUnit(goodsCountItem.getGoodsItemUnit());
					item.setCount(DoubleUtil.round(goodsCountItem.getCount()));
					item.setActualCount(goodsCountItem.getActualCount());
					item.setMemo(goodsCountItem.getRemark());
					item.setExistInventory(goodsCountItem.isExistInventory());
					item.setCountDecimal(goodsCountItem.getScale());
					sortlist.add(item);
				}
			}
			ComparatorUtils.sort(sortlist, "goodsCode", false);
			this.items = sortlist.toArray(new Item[sortlist.size()]);
		}

	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof Item) {
			Item item = (Item) element;
			return new SNameValue[] {
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.GoodsName.name(), item.getGoodsItemName()
							+ ""),
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.GoodsProperties.name(), item
							.getGoodsItemProperties()
							+ ""),
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.GoodsUnit.name(), item.getGoodsItemUnit()
							+ ""),
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.Count.name(), item.getCount() + ""),
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.AcutalCount.name(), item.getActualCount()
							+ ""),
					new SNameValue(GoodsCountSheetDetailProcessor.Columns.Memo.name(), item.getRemark() + "") };
		}
		return null;

	}

	/**
	 * 提示信息
	 */
	public String getPromptMessage() {
		return null;
	}

	/**
	 * 处理数据
	 */
	public boolean processData() {
		if (save()) {
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

	public class Item {
		/**
		 * 材料条目ID
		 * 
		 * @return
		 */
		private GUID goodsItemId;

		/**
		 * 材料条目编码
		 * 
		 * @return
		 */
		private String goodsCode;
		private String goodsNo;

		/**
		 * 材料条目名称
		 * 
		 * @return
		 */
		private String goodsItemName;

		/**
		 * 材料条目属性
		 * 
		 * @return
		 */
		private String goodsItemProperties;

		/**
		 * 材料条目单位
		 * 
		 * @return
		 */
		private String goodsItemUnit;

		/**
		 * 材料数量小数位
		 * 
		 * @return
		 */
		private int countDecimal;

		/**
		 * 材料账面数量
		 * 
		 * @return
		 */
		private double count;

		/**
		 * 材料实际数量
		 * 
		 * @return
		 */
		private double actualCount;

		/**
		 * 获取说明
		 * 
		 * @return
		 */
		private String memo;

		/**
		 * 是否库存物品
		 */
		private boolean existInventory;

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public String getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsNo() {
			return goodsNo;
		}

		public void setGoodsNo(String goodsNo) {
			this.goodsNo = goodsNo;
		}

		public String getGoodsItemName() {
			return goodsItemName;
		}

		public void setGoodsItemName(String goodsItemName) {
			this.goodsItemName = goodsItemName;
		}

		public String getGoodsItemProperties() {
			return goodsItemProperties;
		}

		public void setGoodsItemProperties(String goodsItemProperties) {
			this.goodsItemProperties = goodsItemProperties;
		}

		public String getGoodsItemUnit() {
			return goodsItemUnit;
		}

		public void setGoodsItemUnit(String goodsItemUnit) {
			this.goodsItemUnit = goodsItemUnit;
		}

		public int getScale() {
			return countDecimal;
		}

		public void setCountDecimal(int countDecimal) {
			this.countDecimal = countDecimal;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getActualCount() {
			return actualCount;
		}

		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		public String getRemark() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}

		public boolean isExistInventory() {
			return existInventory;
		}
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + countSheet.getSheetNumber() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("盘点单号", countSheet.getSheetNumber());
						bw.addLabel("入库仓库", countSheet.getStoreName());
						bw.addLabel("开始日期", DateUtil.dateFromat(countSheet.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
						bw.addLabel("盘点人", countSheet.getName());
						bw.addLabel("备注", countSheet.getRemark());
						if (countSheet.getEndDate() > 0) {
							bw.addLabel("结束日期", DateUtil.dateFromat(countSheet.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
						}

						String[] head = new String[] { "材料编号", "材料条码", "材料名称", "材料规格", "材料单位", "账面数量", "实盘数量", "差额",
								"原因", };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							Item item = (Item) obj;
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsItemName(),
									item.getGoodsItemProperties(), item.getGoodsItemUnit(),
									DoubleUtil.getRoundStr(item.getCount()),
									DoubleUtil.getRoundStr(item.getActualCount()),
									DoubleUtil.getRoundStr(DoubleUtil.sub(item.getCount(), item.getActualCount())),
									item.getRemark() });
						}
						bw.setTable(head, list);
						try {
							bw.write(countSheet.getSheetNumber());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	@Override
	protected boolean isNeedExport() {
		return true;
	}

}
