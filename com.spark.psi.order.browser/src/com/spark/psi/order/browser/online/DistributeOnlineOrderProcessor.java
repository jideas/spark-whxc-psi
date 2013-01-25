package com.spark.psi.order.browser.online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.order.browser.common.DistributeGoodsItem;
import com.spark.psi.order.browser.distribute.DistributeInfoWindow;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;

public class DistributeOnlineOrderProcessor extends BaseFormPageProcessor implements IDataProcessPrompt,
		SelectionListener {

	public static final String ID_Date_Date = "Date_Date";
	public static final String ID_Button_ResolveAll = "Button_ResolveAll";
	public static final String ID_Label_StoreName = "Label_StoreName";
	public static final String ID_Button_Reset = "Button_Reset";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Table_Material = "Table_Material";
	public static final String ID_Table_Store = "Table_Store";
	public static final String ID_Text_Remark = "Text_Remark";

	public static enum MaterialTableColumn {
		name, unit, orderCount, UnAllocate, Available, Allocate
	}

	public static enum StoreTableColumn {
		name
	}

	private STable storeTable = null;
	private SEditTable materialTable = null;
	private Label storeNameLabel = null;
	private Label resolveAllButton = null;
	private DistributeInfoWindow distributeInfoWindow = null;

	// arguments
	private GUID[] onlineOrderIds = null;
	// private List<TotalMaterialsItem> materialList = new
	// ArrayList<TotalMaterialsItem>();
	private TotalMaterialsItem.MaterialsItem[] materialItems = null;
	private DistributeGoodsItem[] distributeGoodsItems = null;

	private String currentStoreId = null;
	private StoreItem[] stores = null;
	private Map<String, TotalMaterialsItem.MaterialsItem> materialListStore = new HashMap<String, TotalMaterialsItem.MaterialsItem>();
	// key:materialId value:(key:storeId, distributeCount)
	private Map<String, Map<String, Double>> materialDistributeResult = new HashMap<String, Map<String, Double>>();
	// key: storeId, value:(key:materialId, distributeCount)
	private Map<String, Map<String, Double>> storeDistributeResult = new HashMap<String, Map<String, Double>>();
	// key: storeId-materialId, value:inventoryCount
	private Map<String, Double> goodsInventorys = new HashMap<String, Double>();

	@SuppressWarnings("unchecked")
	@Override
	public void init(Situation context) {
		// materialList = (List<TotalMaterialsItem>) getArgument();
		materialItems = (TotalMaterialsItem.MaterialsItem[]) getArgument();
		onlineOrderIds = (GUID[]) getArgument2();
		distributeGoodsItems = (DistributeGoodsItem[]) getArgument3();

		for (TotalMaterialsItem.MaterialsItem item : materialItems) {
			materialListStore.put(item.getMaterialId().toString(), item);
		}

		GetStoreListKey storeKey = new GetStoreListKey(true, StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		List<StoreItem> storeList = context.find(ListEntity.class, storeKey).getItemList();
		stores = storeList.toArray(new StoreItem[0]);

		for (StoreItem storeItem : stores) {
			for (TotalMaterialsItem.MaterialsItem materialItem : materialItems) {
				GetAvailableCountKey key = new GetAvailableCountKey(storeItem.getId(), materialItem.getMaterialId());
				Double v = context.find(Double.class, key);
				if (v != null) {
					if (v < 0) {
						v = new Double(0);
					}
					goodsInventorys.put(storeItem.getId() + "-" + materialItem.getMaterialId(), v);
				}
			}
		}

	}

	@Override
	public void process(Situation context) {
		storeTable = this.createControl(ID_Table_Store, STable.class);
		materialTable = this.createControl(ID_Table_Material, SEditTable.class);
		storeNameLabel = createControl(ID_Label_StoreName, Label.class);
		resolveAllButton = createControl(ID_Button_ResolveAll, Label.class);
		final Button resetButton = createControl(ID_Button_Reset, Button.class);
		final Button confirmButton = this.createControl(ID_Button_Confirm, Button.class);

		storeTable.setContentProvider(new StoreTableContentProvider());
		storeTable.setLabelProvider(new StoreTableLabelProvider());
		materialTable.setContentProvider(new MaterialTableContentProvider(materialItems));
		materialTable.setLabelProvider(new MaterialTableLabelProvider());

		storeTable.addClientNotifyListener(new ClientNotifyListener() {
			@SuppressWarnings("deprecation")
			public void notified(ClientNotifyEvent e) {
				JSONObject customSelectionInfo = storeTable.getClientObject("customSelectionInfo");
				String selectingId = null;
				try {
					selectingId = customSelectionInfo.getString("selecting");
				} catch (JSONException ex) {
				}
				materialTable.removeSelectionListener(DistributeOnlineOrderProcessor.this); // 移除用于提示没有选择仓库的监听器
				if (currentStoreId != null && currentStoreId.equals(selectingId)) {
					return;
				}
				if (currentStoreId == null) {
					currentStoreId = selectingId;
					storeTable.updateRow(getStoreItem(currentStoreId));
					storeTable.renderUpate();
					materialTable.render();
					storeNameLabel.setText("选择仓库：" + getStoreItem(selectingId).getName());
					return;
				} else {
					//
					if (!saveCurrentStoreStatus(selectingId)) {
						return;
					}
				}
			}
		});
		storeTable.addClientEventHandler(STable.CLIENT_EVENT_ROWCLICK, "DistributeSalesOrder.handleStoreSelection");

		materialTable.addSelectionListener(this);
		materialTable.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (actionName.equals("View")) {
					if (currentStoreId == null) {
						alert("请选择仓库");
						return;
					}
				}
			}
		});
		// 处理查询分配情况动作的客户端事件
		materialTable.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "DistributeSalesOrder.handleActionPerformed");

		resolveAllButton.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				if (currentStoreId != null) {
					String[] salesGoodsItemIds = materialTable.getAllRowId();
					for (int i = 0; i < salesGoodsItemIds.length; i++) {
						double notAllocateCount = getItemAllocatedCountNotInStore(salesGoodsItemIds[i], currentStoreId);
						// int decimal = Integer
						// .parseInt(materialTable.getExtraData(
						// salesGoodsItemIds[i], "Decimal")[0]);
						materialTable.updateCell(salesGoodsItemIds[i], "Allocate", String.valueOf(notAllocateCount),
								String.valueOf(notAllocateCount), 2);
						materialTable.updateCell(salesGoodsItemIds[i], "UnAllocate", null, "0", 2);
					}
					markDataChanged();
					materialTable.renderUpate();
				} else {
					alert("请选择仓库！");
				}
			}
		});

		storeTable.render();
		materialTable.render();

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materialDistributeResult.clear();
				storeDistributeResult.clear();
				currentStoreId = null;
				storeTable.render();
				materialTable.render();
				storeNameLabel.setText("选择仓库：");
			}
		});

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
		distributeInfoWindow = new DistributeInfoWindow(materialTable);
	}

	private StoreItem getStoreItem(String id) {
		for (StoreItem item : stores) {
			if (item.getId().toString().equals(id)) {
				return item;
			}
		}
		return null;
	}

	private boolean saveCurrentStoreStatus(String selectingId) {
		// 先检查之前选择仓库的分配情况并记录
		// boolean hasAllocateInfo = false;
		String[] materialItemIds = materialTable.getAllRowId();
		// 校验分配数量是否超出剩余分配数量和库存数量
		for (int i = 0; i < materialItemIds.length; i++) {
			double allocating = DoubleUtil.strToDouble(materialTable.getExtraData(materialItemIds[i], "Allocating")[0]);
			double allocate = 0;
			try {
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(materialItemIds[i], "Allocate")[0]);
			} catch (Throwable t) {
				continue;
			}
			if (allocate < 0) {
				alert("分配数量必须大于0！");
				return false;
			}
			if (allocate > allocating) {
				alert("分配数量超过需要分配的剩余数量！");
				return false;
			}
		}

		// 分配数据正确，记录到结果中
		Map<String, Double> distributeMaterilsInfo = new HashMap<String, Double>();
		for (int i = 0; i < materialItemIds.length; i++) {
			double allocate = 0;
			try {
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(materialItemIds[i], "Allocate")[0], 2);
			} catch (Throwable t) {
				continue;
			}
			//
			distributeMaterilsInfo.put(materialItemIds[i], allocate);

			//
			Map<String, Double> allStoreResult = materialDistributeResult.get(materialItemIds[i]);
			if (allStoreResult == null) {
				allStoreResult = new HashMap<String, Double>();
				materialDistributeResult.put(materialItemIds[i], allStoreResult);
			}
			allStoreResult.put(currentStoreId, allocate);
		}

		//
		storeDistributeResult.put(currentStoreId, distributeMaterilsInfo);

		String lastStoreId = currentStoreId;
		// 切换到当前选择仓库，更改日期，刷新表格
		if (!currentStoreId.equals(selectingId)) {
			currentStoreId = selectingId;
			materialTable.render();
			storeNameLabel.setText("选择仓库：" + getStoreItem(selectingId).getName());
		}

		// 更新仓库标签列表标签
		storeTable.updateRow(getStoreItem(lastStoreId));
//		storeTable.updateRow(getStoreItem(currentStoreId));
		storeTable.renderUpate();
		//
		return true;
	}

	private double getItemAllocatedCountNotInStore(String itemId, String storeId) {
		TotalMaterialsItem.MaterialsItem item = null;
		for (TotalMaterialsItem.MaterialsItem tItem : materialItems) {
			if (tItem.getMaterialId().toString().equals(itemId)) {
				item = tItem;
				break;
			}
		}
		if (item == null) {
			throw new IllegalStateException();
		}
		double allocatedCount = 0;
		Map<String, Double> allStoreResult = materialDistributeResult.get(itemId);
		if (allStoreResult != null) {
			Iterator<String> storeIt = allStoreResult.keySet().iterator();
			while (storeIt.hasNext()) {
				String key = storeIt.next();
				if (key.equals(storeId)) {
					continue;
				}
				allocatedCount += allStoreResult.get(key);
			}
		}
		return item.getCount() - allocatedCount;
	}

	private double getItemAllocatedCountInStore(TotalMaterialsItem.MaterialsItem item, String storeId) {
		return getItemAllocatedCountInStore(item.getMaterialId().toString(), storeId);
	}

	private double getItemAllocatedCountInStore(String itemId, String storeId) {
		if (storeId != null) {
			Map<String, Double> result = materialDistributeResult.get(itemId);
			if (result != null) {
				Double allocateCount = result.get(storeId);
				if (allocateCount != null) {
					return allocateCount;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}

	private double getItemAllocatedCount(TotalMaterialsItem.MaterialsItem item) {
		double allAllocatedCount = 0;
		Map<String, Double> allStoreResult = materialDistributeResult.get(item.getMaterialId().toString());
		if (allStoreResult != null) {
			Iterator<Double> valueIt = allStoreResult.values().iterator();
			while (valueIt.hasNext()) {
				allAllocatedCount += valueIt.next();
			}
		}
		return allAllocatedCount;
	}

	private class StoreTableContentProvider implements SEditContentProvider {

		public String getElementId(Object element) {
			StoreItem store = (StoreItem) element;
			return store.getId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return stores;
		}

		public boolean isSelectable(Object element) {
			return true;
		}

		public boolean isSelected(Object element) {
			return getElementId(element).equals(currentStoreId);
		}

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			StoreItem store = (StoreItem) element;
			return new SNameValue[] { new SNameValue(StoreTableColumn.name.name(), store.getName()) };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			return null;
		}

	}

	private class StoreTableLabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			StoreItem store = (StoreItem) element;
			return store.getName();
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

	}

	private class MaterialTableContentProvider implements SEditContentProvider {

		private TotalMaterialsItem.MaterialsItem[] materialItems = null;

		public MaterialTableContentProvider(TotalMaterialsItem.MaterialsItem[] materialItems) {
			this.materialItems = materialItems;
		}

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			if (currentStoreId != null) {
				return new SNameValue[] {
						new SNameValue("Allocating", DoubleUtil.getRoundStr(DoubleUtil.round(item.getCount(), 2)
								- getItemAllocatedCount(item) + getItemAllocatedCountInStore(item, currentStoreId), 2)),
						// new SNameValue("AvaliableCount", String.valueOf(item
						// .getMaterialScale())),
						new SNameValue("Decimal", String.valueOf(2)) };
			}
			return null;
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			if (columnName.equals("Allocate")) {
				if (currentStoreId != null) {
					double allocateCount = getItemAllocatedCountInStore((TotalMaterialsItem.MaterialsItem) element,
							currentStoreId);
					if (allocateCount > 0) {
						return DoubleUtil.getRoundStr(allocateCount, 2);
					}
					return "";
				}
			}
			return null;
		}

		public String getElementId(Object element) {
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			return item.getMaterialId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return materialItems;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}

	}

	private class MaterialTableLabelProvider implements SLabelProvider, SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			switch (columnIndex) {
			case 0:
				return item.getMaterialName();
			case 1:
				return item.getUnit();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount());
			case 3:
				double avaliableCount = item.getCount() - getItemAllocatedCount(item);
				if (currentStoreId != null) {
					avaliableCount += getItemAllocatedCountInStore(item, currentStoreId);
				}
				return DoubleUtil.getRoundStr(avaliableCount);

			case 4:
				if (currentStoreId != null) {
					Double v = goodsInventorys.get(currentStoreId + "-" + item.getMaterialId());
					if (v != null) {
						return String.valueOf(v);
					} else {
						return 0.00 + "";
					}
				}
				return "--";
			case 5:
				double count = getItemAllocatedCountInStore(item, currentStoreId);
				if (count > 0) {
					return String.valueOf(count);
				}
				return "";
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			switch (columnIndex) {
			case 4:
				return 2;
			case 3:
				return 2;
			case 5:
				return 2;
			}
			return -1;
		}

	}

	public String getPromptMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean processData() {
		//
		if (currentStoreId != null && !saveCurrentStoreStatus(currentStoreId)) {
			return false;
		}

		for (TotalMaterialsItem.MaterialsItem item : materialItems) {
			if (DoubleUtil.round(item.getCount(), 2) != getItemAllocatedCount(item)) {
				alert("该订单商品未全部分配！");
				return false;
			}
		}

		CreateProduceOrderTask task = new CreateProduceOrderTask();
		//
		//
		CreateProduceOrderTask.GoodsItem[] goodsItems = buildGoodsItems(task);

		task.setGoods(goodsItems);
		task.setOnlineOrderIds(onlineOrderIds);

		//
		List<CreateProduceOrderTask.MaterialsItem> materialItemList = new ArrayList<CreateProduceOrderTask.MaterialsItem>();
		Iterator<String> materialDisRsIt = materialDistributeResult.keySet().iterator();
		while (materialDisRsIt.hasNext()) {
			String materialId = materialDisRsIt.next();
			TotalMaterialsItem.MaterialsItem materialItem = materialListStore.get(materialId);
			Map<String, Double> storeCountMap = materialDistributeResult.get(materialId);
			Iterator<String> storeCountIt = storeCountMap.keySet().iterator();
			CreateProduceOrderTask.MaterialsItem mItem = null;
			while (storeCountIt.hasNext()) {
				String storeId = storeCountIt.next();
				double count = storeCountMap.get(storeId);
				if (count > 0) {
					mItem = buildMaterialItem(task, materialItem, getStoreItem(storeId), count);
					materialItemList.add(mItem);
				}
			}
		}

		final SDatePicker finishDatePicker = createControl(ID_Date_Date, SDatePicker.class);
		final Text remarkText = createControl(ID_Text_Remark, Text.class);
		task.setMaterials(materialItemList.toArray(new CreateProduceOrderTask.MaterialsItem[0]));
		task.setGoodsCount(task.getGoods().length);
		task.setPlanDate(finishDatePicker.getDate().getTime());
		task.setRemark(remarkText.getText());
		if (onlineOrderIds == null) {
			task.setStatus(ProduceOrderStatus.Submiting);
		} else {
			task.setStatus(ProduceOrderStatus.Producing);
		}
		getContext().handle(task);

		getContext().bubbleMessage(new MsgResponse(true));
		return true;
	}

	private CreateProduceOrderTask.GoodsItem[] buildGoodsItems(CreateProduceOrderTask task) {
		CreateProduceOrderTask.GoodsItem[] goodsItems = new CreateProduceOrderTask.GoodsItem[distributeGoodsItems.length];
		CreateProduceOrderTask.GoodsItem goodsItem = null;
		int goodsIndex = 0;
		for (DistributeGoodsItem pgItem : distributeGoodsItems) {
			goodsItem = task.new GoodsItem();
			goodsItem.setBomId(pgItem.getBomId());
			goodsItem.setCount(pgItem.getCount());
			goodsItem.setGoodsCode(pgItem.getGoodsCode());
			goodsItem.setGoodsId(pgItem.getGoodsId());
			goodsItem.setGoodsName(pgItem.getGoodsName());
			goodsItem.setGoodsNo(pgItem.getGoodsNo());
			goodsItem.setGoodsScale(pgItem.getGoodsScale());
			goodsItem.setGoodsSpec(pgItem.getGoodsSpec());
			goodsItem.setUnit(pgItem.getUnit());
			goodsItems[goodsIndex] = goodsItem;
			goodsIndex++;
		}
		return goodsItems;
	}

	private CreateProduceOrderTask.MaterialsItem buildMaterialItem(CreateProduceOrderTask task,
			TotalMaterialsItem.MaterialsItem tItem, StoreItem store, double count) {
		CreateProduceOrderTask.MaterialsItem item = task.new MaterialsItem();
		item.setCount(count);
		item.setMaterialCode(tItem.getMaterialCode());
		item.setMaterialId(tItem.getMaterialId());
		item.setMaterialName(tItem.getMaterialName());
		item.setMaterialNo(tItem.getMaterialNo());
		item.setMaterialScale(tItem.getMaterialScale());
		item.setStoreId(store.getId());
		item.setStoreName(store.getName());
		item.setUnit(tItem.getUnit());
		return item;
	}

	public void widgetSelected(SelectionEvent e) {
		if (currentStoreId == null) {
			alert("请选择仓库");
		}
	}

}
