package com.spark.psi.order.browser.online;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
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
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.SContentProvider;
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
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;

public abstract class AbstractDistributePageProcessor extends BaseFormPageProcessor implements SelectionListener, IDataProcessPrompt {
	public static final String ID_Label_ResolveAll = "Button_ResolveAll";
	public static final String ID_Label_StoreName = "Label_StoreName";
	public static final String ID_Button_Reset = "Button_Reset";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Table_Material = "Table_Material";
	public static final String ID_Table_Store = "Table_Store";
	
	public static enum MaterialTableColumn {
		name, unit, orderCount, UnAllocate, Available, Allocate
	}

	public static enum MaterialTableExtraName {
		Allocating
	}
	
	public static enum StoreTableColumn {
		name
	}
	
	private STable storeTable = null;
	private SEditTable materialTable = null;
	private Label storeNameLabel = null;
	private Label resolveAllLabel = null;	

	private TotalMaterialsItem.MaterialsItem[] materialItems = null;
	private StoreItem[] stores = null;
	private Map<String, TotalMaterialsItem.MaterialsItem> materialListStore = new HashMap<String, TotalMaterialsItem.MaterialsItem>();
	private Map<String, Double> materialInventorys = new HashMap<String, Double>();
	
	
	private String currentStoreId = null;
	// key: materialId value:(key:storeId, distributeCount)
	protected Map<String, Map<String, Double>> materialDistributeResult = new HashMap<String, Map<String, Double>>();
	// key: storeId,   value:(key:materialId, distributeCount)
//	private Map<String, Map<String, Double>> storeDistributeResult = new HashMap<String, Map<String, Double>>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(Situation context) {
		materialItems = (TotalMaterialsItem.MaterialsItem[]) getArgument();

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
					materialInventorys.put(storeItem.getId() + "-" + materialItem.getMaterialId(), v);
				}
			}
		}

	}
	
	protected abstract boolean doConfirmAction();
	
	@Override
	public void process(Situation context) {
		storeTable = this.createControl(ID_Table_Store, STable.class);
		materialTable = this.createControl(ID_Table_Material, SEditTable.class);
		storeNameLabel = createControl(ID_Label_StoreName, Label.class);
		resolveAllLabel = createControl(ID_Label_ResolveAll, Label.class);
		
		storeTable.setContentProvider(new StoreContentProvide());
		storeTable.setLabelProvider(new StoreLabelProvider());
		materialTable.setContentProvider(new MaterialContentProvider());
		materialTable.setLabelProvider(new MaterialLabelProvider());
		
		addStoreTableSelectionListener(storeTable);
		materialTable.addSelectionListener(this);
		addResolveAllAction(resolveAllLabel);
		
		storeTable.render();
		materialTable.render();
		
		final Button resetButton = createControl(ID_Button_Reset, Button.class);
		final Button confirmButton = this.createControl(ID_Button_Confirm, Button.class);
		addResetAction(resetButton);
		addConfirmAction(confirmButton);
	}
	
	private void addResetAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 重置
				materialDistributeResult.clear();
				currentStoreId = null;
				storeTable.render();
				materialTable.render();
				storeNameLabel.setText("选择仓库：");
			}
		});
	}
	
	private void addConfirmAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定操作
				processData();
			}
		});
	}
	/**
	 * 仓库选择事件
	 * @param storeTable
	 */
	private void addStoreTableSelectionListener(final STable storeTable) {
		storeTable.addClientEventHandler(STable.CLIENT_EVENT_ROWCLICK, "DistributeSalesOrder.handleStoreSelection");
		storeTable.addClientNotifyListener(new ClientNotifyListener() {

			@SuppressWarnings("deprecation")
			public void notified(ClientNotifyEvent e) {
				JSONObject customSelectionInfo = storeTable.getClientObject("customSelectionInfo");
				String selectingRowId = null;
				try {
					selectingRowId = customSelectionInfo.getString("selecting");
				} catch (JSONException ex) {
				}
				if (currentStoreId != null && selectingRowId.equals(currentStoreId)) {
					return;
				}
				materialTable.removeSelectionListener(AbstractDistributePageProcessor.this); // 移除用于提示没有选择仓库的监听器
				if (currentStoreId == null) {
					updateInfosOnStoreSelection(selectingRowId);
					return;
				} else {
					//
					boolean saveCurrentSuccess = true;
					saveCurrentSuccess = saveCurrentStoreStatus();
					if (saveCurrentSuccess) {
						updateInfosOnStoreSelection(selectingRowId);
					}
				}
			}
			
		});
	}
	
	/**
	 * 将剩余数量分配到当前库
	 * @param label
	 */
	private void addResolveAllAction(Label label) {
		label.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				if (currentStoreId == null) {
					alert("请选择仓库");
					return ;
				}
				String[] rowIds = materialTable.getAllRowId();
				for (int index = 0; index < rowIds.length; index++) {
					String materialId = rowIds[index];
					double notAllocateCount = getAllocatingCountNotInStore(materialId, currentStoreId);
					materialTable.updateCell(materialId, MaterialTableColumn.Allocate.name(), String.valueOf(notAllocateCount),
							String.valueOf(notAllocateCount), 2);
					materialTable.updateCell(materialId, MaterialTableColumn.UnAllocate.name(), null, "0.0", 2);
				}
				markDataChanged();
				materialTable.renderUpate();
			}
		});
	}
	
	/**
	 * 仓库选择时更新其他信息
	 * @param selectingRowId
	 */
	private void updateInfosOnStoreSelection(String selectingRowId) {
		currentStoreId = selectingRowId;
		StoreItem store = getStoreItem(currentStoreId);
		storeTable.updateRow(store);
		storeTable.renderUpate();
		materialTable.render();
		storeNameLabel.setText("选择仓库：" + store.getName());
	}
	
	/**
	 * 取得材料指定材料还未分配的数量
	 * @param materialId
	 * @param exceptStoreId
	 * 		不计算的仓库id
	 * @return
	 */
	private double getAllocatingCountNotInStore(String materialId, String exceptStoreId) {
		double totalCount = getTotalMaterialAllocatedCount(materialId);
		double exceptCount = getStoreMaterialAllocatedCount(materialId, exceptStoreId);
		TotalMaterialsItem.MaterialsItem materialItem = getMaterialItemById(materialId);
		double allocatedCount = DoubleUtil.sub(totalCount, exceptCount);
		return DoubleUtil.sub(materialItem.getCount(), allocatedCount);
	}
	/**
	 * 保存当前仓库下填写的材料信息
	 * @return
	 */
	private boolean saveCurrentStoreStatus() {
		if (!validateMaterialInput()) return false;
		String[] materialRowIds = materialTable.getAllRowId();
		for (int index = 0; index < materialRowIds.length; index++) {
			String rowId = materialRowIds[index];
			double allocate = 0;
			try {
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(rowId, MaterialTableColumn.Allocate.name())[0], 2);
			} catch (Throwable t) {
				continue;
			}
			addStorematerialAllocatedCount(rowId, currentStoreId, allocate);
		}
		return true;
	}
	
	/**
	 * 验证材料表输入
	 * @return
	 */
	private boolean validateMaterialInput() {
		String[] materialItemIds = materialTable.getAllRowId();
		// 校验分配数量是否超出剩余分配数量和库存数量
		for (int i = 0; i < materialItemIds.length; i++) {
			double allocating = DoubleUtil.strToDouble(materialTable.getExtraData(materialItemIds[i], MaterialTableExtraName.Allocating.name())[0]);
			double allocate = 0;
			try {
				allocate = DoubleUtil.strToDouble(materialTable.getEditValue(materialItemIds[i], MaterialTableColumn.Allocate.name())[0]);
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
		return true;
	}
	
	/**
	 * 根据仓库id取得storeItem
	 * @param id
	 * @return
	 */
	protected StoreItem getStoreItem(String id) {
		for (StoreItem item : stores) {
			if (item.getId().toString().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 根据材料id取得TotalMaterialsItem.MaterialsItem
	 * @param materialId
	 * @return
	 */
	protected TotalMaterialsItem.MaterialsItem getMaterialItemById(String materialId) {
		TotalMaterialsItem.MaterialsItem item = null;
		for (TotalMaterialsItem.MaterialsItem tItem : materialItems) {
			if (tItem.getMaterialId().toString().equals(materialId)) {
				item = tItem;
				break;
			}
		}
		if (item == null) {
			throw new IllegalStateException();
		}
		return item;
	}
	/**
	 * 增加指定材料在指定仓库的分配数量
	 * @param materialId
	 * @param storeId
	 * @param count
	 */
	private void addStorematerialAllocatedCount(String materialId, String storeId, double count) {
		Map<String, Double> storeMaterialMap = materialDistributeResult.get(materialId);
		if (null == storeMaterialMap) {
			storeMaterialMap = new HashMap<String, Double>();
			storeMaterialMap.put(storeId, count);
			materialDistributeResult.put(materialId, storeMaterialMap);
		} else {
//			Double preAllocatedCount = storeMaterialMap.get(storeId);
//			double storeCount = preAllocatedCount == null ? 0.0 : preAllocatedCount.doubleValue() + count;
			storeMaterialMap.put(storeId, count);
		}
	}
	
	/**
	 * 获得指定材料在指定仓库已分配的数量
	 * @param materialId
	 * @param storeId
	 * @return
	 */
	private double getStoreMaterialAllocatedCount(String materialId, String storeId) {
		Map<String, Double> storeMaterialMap = materialDistributeResult.get(materialId);
		if (null == storeMaterialMap) return 0.0;
		Double storeMaterialCount = storeMaterialMap.get(storeId);
		return storeMaterialCount == null ? 0.0 : storeMaterialCount.doubleValue();
	}
	/**
	 * 获得指定材料已分配的总数量
	 * @param materialId
	 * @return
	 */
	private double getTotalMaterialAllocatedCount(String materialId) {
		double totalCount = 0.0;
		for (StoreItem store : stores) {
			totalCount += getStoreMaterialAllocatedCount(materialId, store.getId().toString());
		}
		return totalCount;
	}
	
	/**
	 * 获得指定材料在指定仓库的现有库存数量
	 * @param materialId
	 * @param storeId
	 * @return
	 */
	private double getStoreMaterialInventoryCount(String materialId, String storeId) {
		Double inventoryCount = materialInventorys.get(storeId + "-" + materialId);
		return inventoryCount == null ? 0.0 : inventoryCount.doubleValue();
	}
	
	private class StoreContentProvide implements SContentProvider {

		public String getElementId(Object element) {
			StoreItem item = (StoreItem)element;
			return item.getId().toString();
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
		
	}
	
	private class StoreLabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			StoreItem item = (StoreItem)element;
			return item.getName();
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}

	private class MaterialContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			if (currentStoreId == null) return null;
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			return new SNameValue[] {
					new SNameValue(MaterialTableExtraName.Allocating.name(), DoubleUtil.getRoundStr(DoubleUtil.round(item.getCount(), 2)
							- getTotalMaterialAllocatedCount(item.getMaterialId().toString()) 
							+ getStoreMaterialAllocatedCount(item.getMaterialId().toString(), currentStoreId), 2)),
					// new SNameValue("AvaliableCount", String.valueOf(item
					// .getMaterialScale())),
					new SNameValue("Decimal", String.valueOf(2)) };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			if (null == currentStoreId) return "";
			double allocatedCount = getStoreMaterialAllocatedCount(item.getMaterialId().toString(), currentStoreId);
			return DoubleUtil.getRoundStr(allocatedCount);
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
	
	private class MaterialLabelProvider implements SLabelProvider, SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			TotalMaterialsItem.MaterialsItem item = (TotalMaterialsItem.MaterialsItem) element;
			switch(columnIndex) {
			case 0:
				return item.getMaterialName();
			case 1:
				return item.getUnit();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount());
			case 3:
				double avaliableCount = item.getCount() - getTotalMaterialAllocatedCount(item.getMaterialId().toString());
				return DoubleUtil.getRoundStr(avaliableCount, 2);
			case 4:
				if (null == currentStoreId) return "0.0";
				return DoubleUtil.getRoundStr(getStoreMaterialInventoryCount(item.getMaterialId().toString(), currentStoreId));
			case 5:
				if (null == currentStoreId) return "";
				double allocatedCount = getStoreMaterialAllocatedCount(item.getMaterialId().toString(), currentStoreId);
				return DoubleUtil.getRoundStr(allocatedCount);
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}
		
	}

	
	public void widgetSelected(SelectionEvent e) {
		if (currentStoreId == null) {
			alert("请选择仓库");
		}
	}
	public String getPromptMessage() {
		return null;
	}
	final public boolean processData() {
		if (currentStoreId != null && !saveCurrentStoreStatus()) {
			return false;
		}

		for (TotalMaterialsItem.MaterialsItem item : materialItems) {
			if (DoubleUtil.round(item.getCount(), 2) != getTotalMaterialAllocatedCount(item.getMaterialId().toString())) {
				alert("该订单商品未全部分配！");
				return false;
			}
		}
		if (doConfirmAction()) {
			getContext().bubbleMessage(new MsgResponse(true));
		}
		return true;
	}
}
