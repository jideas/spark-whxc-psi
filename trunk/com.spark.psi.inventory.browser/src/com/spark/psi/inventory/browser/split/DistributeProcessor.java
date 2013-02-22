package com.spark.psi.inventory.browser.split;

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
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;
import com.spark.psi.publish.split.task.GoodsSplitDistributTask;
import com.spark.psi.publish.split.task.GoodsSplitDistributeEntity;
import com.spark.psi.publish.split.task.GoodsSplitTaskDet;

public class DistributeProcessor extends BaseFormPageProcessor implements
		IDataProcessPrompt, SelectionListener {

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
	// private DistributeInfoWindow distributeInfoWindow = null;

	// arguments
	private GUID[] onlineOrderIds = null;
	// private List<TotalMaterialsItem> materialList = new
	// ArrayList<TotalMaterialsItem>();
	// private GoodsSplitDistributeEntity distributeEntity = null;
	private GoodsSplitInfo orderInfo = null;

	private String currentStoreId = null;
	private StoreItem[] stores = null;
	private Map<String, GoodsSplitTaskDet> materialListStore = new HashMap<String, GoodsSplitTaskDet>();
	// key:materialId value:(key:storeId, distributeCount)
	private Map<String, Map<String, Double>> materialDistributeResult = new HashMap<String, Map<String, Double>>();
	// key: storeId, value:(key:materialId, distributeCount)
	private Map<String, Map<String, Double>> storeDistributeResult = new HashMap<String, Map<String, Double>>();
	// key: storeId-materialId, value:inventoryCount
	private Map<String, Double> goodsInventorys = new HashMap<String, Double>();
	private List<GoodsSplitTaskDet> dets = null;

	@SuppressWarnings("unchecked")
	@Override
	public void init(Situation context) {
		// materialList = (List<TotalMaterialsItem>) getArgument();
		orderInfo = (GoodsSplitInfo) getArgument();
		// onlineOrderIds = (GUID[]) getArgument2();
		// distributeItems = (GoodsSplitDistributeEntity[]) getArgument3();
		dets = getGoodsSplitTaskDets();

		for (GoodsSplitTaskDet item : dets) {
			materialListStore.put(item.getId().toString(), item);
		}

		GetStoreListKey storeKey = new GetStoreListKey(true,
				StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		List<StoreItem> storeList = context.find(ListEntity.class, storeKey)
				.getItemList();
		stores = storeList.toArray(new StoreItem[0]);

		// for (StoreItem storeItem : stores) {
		// for (TotalMaterialsItem.MaterialsItem materialItem :
		// distributeEntity) {
		// GetAvailableCountKey key = new
		// GetAvailableCountKey(storeItem.getId(),
		// materialItem.getMaterialId());
		// Double v = context.find(Double.class, key);
		// if (v != null) {
		// if (v < 0) {
		// v = new Double(0);
		// }
		// goodsInventorys.put(storeItem.getId() + "-" +
		// materialItem.getMaterialId(), v);
		// }
		// }
		// }

	}

	private List<GoodsSplitTaskDet> getGoodsSplitTaskDets() {
		List<GoodsSplitTaskDet> dets = new ArrayList<GoodsSplitTaskDet>();
		for (GoodsSplitDet_Material m : orderInfo.getMaterialDets()) {
			GoodsSplitTaskDet det = new GoodsSplitTaskDet(m.getMaterialId(), m
					.getMcount());
			dets.add(det);
		}
		return dets;
	}

	@Override
	public void process(Situation context) {
		storeTable = this.createControl(ID_Table_Store, STable.class);
		materialTable = this.createControl(ID_Table_Material, SEditTable.class);
		storeNameLabel = createControl(ID_Label_StoreName, Label.class);
		resolveAllButton = createControl(ID_Button_ResolveAll, Label.class);
		final Button resetButton = createControl(ID_Button_Reset, Button.class);
		final Button confirmButton = this.createControl(ID_Button_Confirm,
				Button.class);

		storeTable.setContentProvider(new StoreTableContentProvider());
		storeTable.setLabelProvider(new StoreTableLabelProvider());
		materialTable.setContentProvider(new MaterialTableContentProvider(dets
				.toArray(new GoodsSplitTaskDet[0])));
		materialTable.setLabelProvider(new MaterialTableLabelProvider());

		storeTable.addClientNotifyListener(new ClientNotifyListener() {
			@SuppressWarnings("deprecation")
			public void notified(ClientNotifyEvent e) {
				JSONObject customSelectionInfo = storeTable
						.getClientObject("customSelectionInfo");
				String selectingId = null;
				try {
					selectingId = customSelectionInfo.getString("selecting");
				} catch (JSONException ex) {
				}
				materialTable.removeSelectionListener(DistributeProcessor.this); // 移除用于提示没有选择仓库的监听器
				if (currentStoreId != null
						&& currentStoreId.equals(selectingId)) {
					return;
				}
				if (currentStoreId == null) {
					currentStoreId = selectingId;
					storeTable.updateRow(getStoreItem(currentStoreId));
					storeTable.renderUpate();
					materialTable.render();
					storeNameLabel.setText("选择仓库："
							+ getStoreItem(selectingId).getName());
					return;
				} else {
					//
					if (!saveCurrentStoreStatus(selectingId)) {
						return;
					}
				}
			}
		});
		storeTable.addClientEventHandler(STable.CLIENT_EVENT_ROWCLICK,
				"DistributeSalesOrder.handleStoreSelection");

		materialTable.addSelectionListener(this);
		materialTable.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				if (actionName.equals("View")) {
					if (currentStoreId == null) {
						alert("请选择仓库");
						return;
					}
				}
			}
		});
		// 处理查询分配情况动作的客户端事件
		materialTable.addClientEventHandler(STable.CLIENT_EVENT_ACTION,
				"DistributeSalesOrder.handleActionPerformed");

		resolveAllButton.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				if (currentStoreId != null) {
					String[] salesGoodsItemIds = materialTable.getAllRowId();
					for (int i = 0; i < salesGoodsItemIds.length; i++) {
						double notAllocateCount = getItemAllocatedCountNotInStore(
								salesGoodsItemIds[i], currentStoreId);
						// int decimal = Integer
						// .parseInt(materialTable.getExtraData(
						// salesGoodsItemIds[i], "Decimal")[0]);
						materialTable.updateCell(salesGoodsItemIds[i],
								"Allocate", String.valueOf(notAllocateCount),
								String.valueOf(notAllocateCount), 2);
						materialTable.updateCell(salesGoodsItemIds[i],
								"UnAllocate", null, "0", 2);
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
		// distributeInfoWindow = new DistributeInfoWindow(materialTable);
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
			double allocating = DoubleUtil.strToDouble(materialTable
					.getExtraData(materialItemIds[i], "Allocating")[0]);
			double allocate = 0;
			try {
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(
						materialItemIds[i], "Allocate")[0]);
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
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(
						materialItemIds[i], "Allocate")[0], 2);
			} catch (Throwable t) {
				continue;
			}
			//
			distributeMaterilsInfo.put(materialItemIds[i], allocate);

			//
			Map<String, Double> allStoreResult = materialDistributeResult
					.get(materialItemIds[i]);
			if (allStoreResult == null) {
				allStoreResult = new HashMap<String, Double>();
				materialDistributeResult
						.put(materialItemIds[i], allStoreResult);
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
			storeNameLabel.setText("选择仓库："
					+ getStoreItem(selectingId).getName());
		}

		// 更新仓库标签列表标签
		storeTable.updateRow(getStoreItem(lastStoreId));
		// storeTable.updateRow(getStoreItem(currentStoreId));
		storeTable.renderUpate();
		//
		return true;
	}

	private double getItemAllocatedCountNotInStore(String itemId, String storeId) {
		GoodsSplitTaskDet item = null;
		for (GoodsSplitTaskDet tItem : dets) {
			if (tItem.getId().toString().equals(itemId)) {
				item = tItem;
				break;
			}
		}
		if (item == null) {
			throw new IllegalStateException();
		}
		double allocatedCount = 0;
		Map<String, Double> allStoreResult = materialDistributeResult
				.get(itemId);
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

	private double getItemAllocatedCountInStore(GoodsSplitTaskDet item,
			String storeId) {
		return getItemAllocatedCountInStore(item.getId().toString(), storeId);
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

	private double getItemAllocatedCount(GoodsSplitTaskDet item) {
		double allAllocatedCount = 0;
		Map<String, Double> allStoreResult = materialDistributeResult.get(item
				.getId().toString());
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
			return new SNameValue[] { new SNameValue(StoreTableColumn.name
					.name(), store.getName()) };
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

		private GoodsSplitTaskDet[] materialItems = null;

		public MaterialTableContentProvider(GoodsSplitTaskDet[] materialItems) {
			this.materialItems = materialItems;
		}

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			GoodsSplitTaskDet item = (GoodsSplitTaskDet) element;
			if (currentStoreId != null) {
				return new SNameValue[] {
						new SNameValue("Allocating", DoubleUtil.getRoundStr(
								DoubleUtil.round(item.getCount(), 2)
										- getItemAllocatedCount(item)
										+ getItemAllocatedCountInStore(item,
												currentStoreId), 2)),
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
					double allocateCount = getItemAllocatedCountInStore(
							(GoodsSplitTaskDet) element, currentStoreId);
					if (allocateCount > 0) {
						return DoubleUtil.getRoundStr(allocateCount, 2);
					}
					return "";
				}
			}
			return null;
		}

		public String getElementId(Object element) {
			GoodsSplitTaskDet item = (GoodsSplitTaskDet) element;
			return item.getId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return dets.toArray();
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}

	}

	private class MaterialTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			GoodsSplitTaskDet item = (GoodsSplitTaskDet) element;
			MaterialsItemInfo m = getContext().find(MaterialsItemInfo.class,
					item.getId());
			switch (columnIndex) {
			case 0:
				return m.getBaseInfo().getName();
			case 1:
				return m.getItemData().getUnit();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount());

			case 3:
				double counted = getItemAllocatedCount(item);
				
				return (item.getCount()-counted)+"";
			case 4:
				double count = getItemAllocatedCountInStore(item,
						currentStoreId);
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

		for (GoodsSplitTaskDet item : dets) {
			if (DoubleUtil.round(item.getCount(), 2) != getItemAllocatedCount(item)) {
				alert("材料未全部分配！");
				return false;
			}
		}
		List<GoodsSplitDistributeEntity> list = new ArrayList<GoodsSplitDistributeEntity>();
		Iterator<String> storeDisRsIt = storeDistributeResult.keySet()
				.iterator();
		while (storeDisRsIt.hasNext()) {
			GoodsSplitDistributeEntity entity = new GoodsSplitDistributeEntity();
			String storeId = storeDisRsIt.next();
			entity.setStoreId(GUID.valueOf(storeId));

			Map<String, Double> materialCountMap = storeDistributeResult
					.get(storeId);
			Iterator<String> materialCountIt = materialCountMap.keySet()
					.iterator();
			List<GoodsSplitTaskDet> dets = new ArrayList<GoodsSplitTaskDet>();
			while (materialCountIt.hasNext()) {

				String materialId = materialCountIt.next();
				double count = materialCountMap.get(materialId);
				if(count>0)
				{
					GoodsSplitTaskDet det = new GoodsSplitTaskDet(GUID
							.valueOf(materialId), count);
					dets.add(det);
				}
			}
			entity.setDets(dets);
			list.add(entity);
		}
		GoodsSplitDistributTask task = new GoodsSplitDistributTask(orderInfo
				.getRECID(), orderInfo.getBillNo(), orderInfo.getRemark(), list);
		getContext().handle(task);

		getContext().bubbleMessage(new MsgResponse(true));
		return true;
	}

	public void widgetSelected(SelectionEvent e) {
		if (currentStoreId == null) {
			alert("请选择仓库");
		}
	}

}
