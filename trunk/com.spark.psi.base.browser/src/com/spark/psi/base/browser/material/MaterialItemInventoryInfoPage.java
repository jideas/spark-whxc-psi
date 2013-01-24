package com.spark.psi.base.browser.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.SMessageAlertWindow;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.GoodsCommon.GoodsInventoryScope;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.ThresholdControlType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.materials.task.UpdateMaterialsItemThresholdTask;
import com.spark.psi.publish.base.materials.task.UpdateMaterialsItemThresholdTask.MaterialsItemThresholdItem;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.key.GetInventoryInfoListKey;

public class MaterialItemInventoryInfoPage extends Composite {
	private ThresholdControlType viewType;
	private GoodsInventoryScope viewScope;
	private MaterialsItemData goodsItem;
	private Composite headerArea;
	private Composite contentArea;
	private SEditTable itemTable;

	private final static String ALLSTORE = "allStore";
	
	private Map<StoreItem, InventoryInfo> storeInventoryMap = new HashMap<StoreItem, InventoryInfo>();
	private List<StoreItem> storeList;
	private boolean isShowOnly = true;
	
	public static enum ColumnName {
		StoreName,
		StoreUpper, 
		StoreAmountUpper,
		StoreLower,
		StoreCount,
		PurchasingCount,
		DeliveringCount
	}
		
	

	public MaterialItemInventoryInfoPage(Composite parent,
			MaterialsItemData goodsItem, GoodsInventoryScope viewScope,
			ThresholdControlType viewType, boolean isShowOnly) {
		super(parent);
		this.viewScope = viewScope;
		this.viewType = viewType;
		this.goodsItem = goodsItem;
		this.isShowOnly = isShowOnly;
		this.setLayout(new GridLayout());

		headerArea = new Composite(this);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
//		gdHeader.heightHint = 24;
		headerArea.setLayoutData(gdHeader);
		GridLayout glHeader = new GridLayout();
		glHeader.numColumns = 3;
//		glHeader.marginTop = 10;
		headerArea.setLayout(glHeader);

		contentArea = new Composite(this);
		GridData gdContent = new GridData(GridData.FILL_BOTH);
		contentArea.setLayoutData(gdContent);
		contentArea.setLayout(new GridLayout());

		fillContent();

		this.layout();
	}

	
	private void fillContent() {
		// header
		new Label(headerArea).setText("材料规格："
				+ goodsItem.getMaterialSpec());
		new Label(headerArea).setText("    单位："
				+ goodsItem.getPropertyValues()[0]);
		new Label(headerArea).setText("    状态："
				+ goodsItem.getStatus().getName());

		// table
		@SuppressWarnings("unchecked")
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, new GetStoreListKey(false, StoreStatus.ENABLE, StoreStatus.ONCOUNTING));
		storeList = listEntity == null ? new ArrayList<StoreItem>() : listEntity.getItemList();
		STableColumn[] columns = null;
		if (null == viewType) {
			columns = new STableColumn[4];
			columns[0] = new STableColumn(ColumnName.StoreName.name(), 100, JWT.LEFT, "仓库名称");
			columns[1] = new STableColumn(ColumnName.StoreCount.name(), 100, JWT.RIGHT,
					"采购库存数量");
			columns[2] = new STableColumn(ColumnName.PurchasingCount.name(), 100, JWT.RIGHT,
					"采购中数量");
			columns[3] = new STableColumn(ColumnName.DeliveringCount.name(), 100, JWT.RIGHT,
					"交货需求");
		} else if (ThresholdControlType.COUNT.equals(viewType)) {
			columns = new STableColumn[6];
			columns[0] = new STableColumn(ColumnName.StoreName.name(), 100, JWT.LEFT, "仓库名称");
			columns[1] = new SNumberEditColumn(ColumnName.StoreUpper.name(), 100, JWT.RIGHT,
					"库存上限数量");
			columns[2] = new SNumberEditColumn(ColumnName.StoreLower.name(), 100, JWT.RIGHT,
					"库存下限数量");
			columns[3] = new STableColumn(ColumnName.StoreCount.name(), 100, JWT.RIGHT,
					"采购库存数量");
			columns[4] = new STableColumn(ColumnName.PurchasingCount.name(), 100, JWT.RIGHT,
					"采购中数量");
			columns[5] = new STableColumn(ColumnName.DeliveringCount.name(), 100, JWT.RIGHT,
					"交货需求");
		} else {
			columns = new STableColumn[5];
			columns[0] = new STableColumn(ColumnName.StoreName.name(), 100, JWT.LEFT, "仓库名称");
			columns[1] = new SNumberEditColumn(ColumnName.StoreAmountUpper.name(), 120, JWT.RIGHT,
					"库存金额上限");
			columns[2] = new STableColumn(ColumnName.StoreCount.name(), 100, JWT.RIGHT,
					"采购库存数量");
			columns[3] = new STableColumn(ColumnName.PurchasingCount.name(), 100, JWT.RIGHT,
					"采购中数量");
			columns[4] = new STableColumn(ColumnName.DeliveringCount.name(), 100, JWT.RIGHT,
					"交货需求");
		}
		columns[0].setGrab(true);
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setNoScroll(true);
//		tableStyle.setRowHeight(24);
		int tableHeight = tableStyle.getHeaderHeight() + (storeList.size() + 1) * tableStyle.getRowHeight() - 1;
		itemTable = new SEditTable(contentArea, columns, tableStyle, null);
		GridData gdTable = new GridData(GridData.FILL_BOTH);
		gdTable.heightHint = tableHeight;
		itemTable.setLayoutData(gdTable);
		itemTable.setContentProvider(new SEditContentProvider() {

			public boolean isSelected(Object element) {
				return false;
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public Object[] getElements(Context context, STableStatus tablestatus) {
				MaterialsItemInfo goodsItemInfo = context.find(MaterialsItemInfo.class, goodsItem.getId());
				if(goodsItemInfo != null && goodsItemInfo.getItemData() != null) {
					goodsItem = goodsItemInfo.getItemData();
				}
				for(int storeIndex = 0; storeIndex < storeList.size(); storeIndex++) {
					List<InventoryInfo> inventoryList = getContext()
					.getList(
							InventoryInfo.class,
							new GetInventoryInfoListKey(
									new GUID[] { goodsItem.getId() }, new GUID[] {storeList.get(storeIndex).getId()}));
					if(inventoryList.size() > 0) {
						storeInventoryMap.put(storeList.get(storeIndex), inventoryList.get(0));
					} else {
						storeInventoryMap.put(storeList.get(storeIndex), null);
					}
				}
				List<Object> list = new ArrayList<Object>();
				list.addAll(storeList);
				list.add(ALLSTORE);
				return list.toArray();
			}

			public String getElementId(Object element) {
				if(element instanceof String) return ALLSTORE;
				StoreItem store = (StoreItem)element;
				return store.getId().toString();
			}

			public String getValue(Object element, String columnName) {
				if(isShowOnly) return null;
				if(element instanceof String) { //所有仓库
					if(columnName.equals(ColumnName.StoreName.name())) {
						return "所有仓库";
					} else if(columnName.equals(ColumnName.StoreUpper.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return null;
						} else {
//							return "";
							return getLimtShowStr(goodsItem.getTotalStoreUpperLimit(), false);
						}
					} else if(columnName.equals(ColumnName.StoreAmountUpper.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return null;
						} else {
//							return DoubleUtil.getRoundStr(goodsItem.getInventoryUpperLimit());
							if (goodsItem.getInventoryUpperLimit() > 0) {
								return getLimtShowStr(goodsItem.getInventoryUpperLimit(), false);
							} else {
								return "";
							}
						}
					} else if(columnName.equals(ColumnName.StoreLower.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return null;
						} else {
//							return "";
							return getLimtShowStr(goodsItem.getTotalStoreLowerLimit(), false);
						}
					} else if(columnName.equals(ColumnName.StoreCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.StoreCount.name()));
					} else if(columnName.equals(ColumnName.PurchasingCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.PurchasingCount.name()));
					} else if(columnName.equals(ColumnName.DeliveringCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.DeliveringCount.name()));
					}
				}
				
				StoreItem si = (StoreItem)element;
				InventoryInfo detail = storeInventoryMap.get(si);
				if(columnName.equals(ColumnName.StoreName.name())) {
					return si.getName();
				} else if(columnName.equals(ColumnName.StoreUpper.name())) {
					if(GoodsInventoryScope.Total.equals(viewScope)) {
						return null;
					}
					if (detail != null && detail.getUpperLimitCount() > 0) {
						return getLimtShowStr(detail.getUpperLimitCount(), false);
					} else {
						return "";
					}
				} else if(columnName.equals(ColumnName.StoreAmountUpper.name())) {
					if(GoodsInventoryScope.Total.equals(viewScope)) {
						return null;
					}
					if (detail != null && detail.getUpperLimitAmount() > 0) {
						return getLimtShowStr(detail.getUpperLimitAmount(), false);
					} else {
						return "";
					}
				} else if(columnName.equals(ColumnName.StoreLower.name())) {
					if(GoodsInventoryScope.Total.equals(viewScope)) {
						return null;
					}
					if (detail != null && detail.getLowerLimitCount() > 0) {
						return getLimtShowStr(detail.getLowerLimitCount(), false);
					} else {
						return "";
					}
				} else if(columnName.equals(ColumnName.StoreCount.name())) {
					return getLimtShowStr(detail == null ? 0.0 : detail.getCount());
				} else if(columnName.equals(ColumnName.PurchasingCount.name())) {
					return getLimtShowStr(detail == null ? 0.0 : detail.getPurchasingCount());
				} else if(columnName.equals(ColumnName.DeliveringCount.name())) {
					return getLimtShowStr(detail == null ? 0.0 : detail.getDeliveryingCount());
				}
				return null;
			}

			public Object getNewElement() {
				// TODO Auto-generated method stub
				return null;
			}

			public SNameValue[] getExtraData(Object element) {
				// TODO Auto-generated method stub
				return null;
			}

			public String[] getActionIds(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		itemTable.setLabelProvider(new SLabelProvider() {
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			public String getText(Object element, int columnIndex) {
				String columnName = getColumnNameByIndex(columnIndex);
				if(columnName == null) return null;
				if(element instanceof String) { //所有仓库
					if(columnName.equals(ColumnName.StoreName.name())) {
						return "所有仓库";
					} else if(columnName.equals(ColumnName.StoreUpper.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return "-";
						} else {
							// TODO 显示总库存上限
							return null;
						}
					} else if(columnName.equals(ColumnName.StoreAmountUpper.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return "-";
						} else {
							if (goodsItem.getInventoryUpperLimit() > 0) {
								return getLimtShowStr(goodsItem.getInventoryUpperLimit());
							} else {
								return "";
							}
						}
					} else if(columnName.equals(ColumnName.StoreLower.name())) {
						if(GoodsInventoryScope.EachStore.equals(viewScope)) {
							return "-";
						} else {
							// TODO 显示总库存下限
							return null;
						}
					} else if(columnName.equals(ColumnName.StoreCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.StoreCount.name()));
					} else if(columnName.equals(ColumnName.PurchasingCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.PurchasingCount.name()));
					} else if(columnName.equals(ColumnName.DeliveringCount.name())) {
						return getLimtShowStr(getTotleByColumn(ColumnName.DeliveringCount.name()));
					} else {
						return null;
					}
				} else { //分库存
				
					StoreItem si = (StoreItem)element;
					InventoryInfo detail = storeInventoryMap.get(si);
					if(columnName.equals(ColumnName.StoreName.name())) {
						return si.getName();
					} else if(columnName.equals(ColumnName.StoreUpper.name())) {
						if(GoodsInventoryScope.Total.equals(viewScope)) {
							return "-";
						}
						if (detail != null && detail.getUpperLimitCount() > 0) {
							return getLimtShowStr(detail.getUpperLimitCount(), false);
						} else {
							return "";
						}
					} else if(columnName.equals(ColumnName.StoreAmountUpper.name())) {
						if(GoodsInventoryScope.Total.equals(viewScope)) {
							return "-";
						}
						if (detail != null && detail.getUpperLimitAmount() > 0) {
							return getLimtShowStr(detail.getUpperLimitAmount(), false);
						} else {
							return "";
						}
					} else if(columnName.equals(ColumnName.StoreLower.name())) {
						if(GoodsInventoryScope.Total.equals(viewScope)) {
							return "-";
						}
						if (detail != null && detail.getLowerLimitCount() > 0) {
							return getLimtShowStr(detail.getLowerLimitCount(), false);
						} else {
							return "";
						}
					} else if(columnName.equals(ColumnName.StoreCount.name())) {
						return getLimtShowStr(detail == null ? 0.0 : detail.getCount());
					} else if(columnName.equals(ColumnName.PurchasingCount.name())) {
						return getLimtShowStr(detail == null ? 0.0 : detail.getPurchasingCount());
					} else if(columnName.equals(ColumnName.DeliveringCount.name())) {
						return getLimtShowStr(detail == null ? 0.0 : detail.getDeliveryingCount());
					}
				}
				return null;
			}
			
			public Color getForeground(Object element, int columnIndex) {
				return null;
			}
			
			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		itemTable.render();
	}
	
	private String getLimtShowStr(double limitValue, boolean isWithThousand) {
		if(limitValue < 0) {
			return "0.00";
		} else {
			return DoubleUtil.getRoundStr(limitValue, isWithThousand);
		}
	}
	
	private String getLimtShowStr(double limitValue) {
		return getLimtShowStr(limitValue, true);
	}
	
	private double getTotleByColumn(String column) {
		double sum = 0;
		for(StoreItem store : storeList) {
			InventoryInfo inventory = storeInventoryMap.get(store);
			if(inventory == null) continue;
			if(column.equals(ColumnName.StoreCount.name())) {
				sum += inventory.getCount();
			} else if(column.equals(ColumnName.PurchasingCount.name())) {
				sum += inventory.getPurchasingCount();
			} else if(column.equals(ColumnName.DeliveringCount.name())) {
				sum += inventory.getDeliveryingCount();
			}
		}
		return sum;
	}
	private String getColumnNameByIndex(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return ColumnName.StoreName.name();
		case 1:
			if (viewType == null) {
				return ColumnName.StoreCount.name();
			}
			if(ThresholdControlType.AMOUNT.equals(viewType)) {
				return ColumnName.StoreAmountUpper.name();
			} else {
				return ColumnName.StoreUpper.name();
			}
		case 2:
			if (viewType == null) {
				return ColumnName.PurchasingCount.name();
			}
			if(ThresholdControlType.AMOUNT.equals(viewType)) {
				return ColumnName.StoreCount.name();
			} else {
				return ColumnName.StoreLower.name();
			}
		case 3:
			if (viewType == null) {
				return ColumnName.DeliveringCount.name();
			}
			if(ThresholdControlType.AMOUNT.equals(viewType)) {
				return ColumnName.PurchasingCount.name();
			} else {
				return ColumnName.StoreCount.name();
			}
		case 4:
			if (viewType == null) {
				return null;
			}
			if(ThresholdControlType.AMOUNT.equals(viewType)) {
				return ColumnName.DeliveringCount.name();
			} else {
				return ColumnName.PurchasingCount.name();
			}
		case 5:
			if (viewType == null) {
				return null;
			}
			if(ThresholdControlType.AMOUNT.equals(viewType)) {
				return null;
			} else {
				return ColumnName.DeliveringCount.name();
			}
		default:
			return null;
		}
	}
	
	public boolean doSave() {
		String[] rowIds = itemTable.getAllRowId();
		if(ThresholdControlType.AMOUNT.equals(viewType)) { // 
			if(GoodsInventoryScope.Total.equals(viewScope)) { // 金额总库存
				String[] amountUpperLimit = itemTable.getEditValue(rowIds[rowIds.length - 1], ColumnName.StoreAmountUpper.name());
				double storeAmountUpper = -1;
				if(amountUpperLimit != null && StringHelper.isNotEmpty(amountUpperLimit[0])) {
					storeAmountUpper = DoubleUtil.strToDouble( amountUpperLimit[0]);
				}
				if (storeAmountUpper != -1) {
					MaterialsItemThresholdItem thresHoldItem = new MaterialsItemThresholdItem(goodsItem.getId(), storeAmountUpper);
					UpdateMaterialsItemThresholdTask totalTask = new UpdateMaterialsItemThresholdTask(thresHoldItem);
					getContext().handle(totalTask);
				}
			} else { // 金额分库存
				List<MaterialsItemThresholdItem> itemList = new ArrayList<MaterialsItemThresholdItem>();
				for(int rowIndex = 0; rowIndex < rowIds.length - 1; rowIndex++) {
					String[] amountUpperLimit = itemTable.getEditValue(rowIds[rowIndex], ColumnName.StoreAmountUpper.name());
					double storeAmountUpper = -1;
					if(amountUpperLimit != null && StringHelper.isNotEmpty(amountUpperLimit[0])) {
						storeAmountUpper = DoubleUtil.strToDouble(amountUpperLimit[0]);
					}
					if (storeAmountUpper == -1) {
						continue;
					}
					MaterialsItemThresholdItem item = new MaterialsItemThresholdItem(goodsItem.getId(), GUID.tryValueOf(rowIds[rowIndex]), storeAmountUpper);
					itemList.add(item);
				}
				if (itemList.size() > 0) {
					UpdateMaterialsItemThresholdTask itemTask = new UpdateMaterialsItemThresholdTask(itemList.toArray(new MaterialsItemThresholdItem[itemList.size()]));
					getContext().handle(itemTask);
				}
			}
		} else { //
			if(GoodsInventoryScope.Total.equals(viewScope)) { // 数量总库存 需求中不能设置总库存数量 
//				String[] limit = itemTable.getEditValue(rowIds[rowIds.length - 1], ColumnName.StoreUpper.name(), ColumnName.StoreLower.name());
//				double storeUpper = 0;
//				double storeLower = 0;
//				if(limit != null && StringHelper.isNotEmpty(limit[0])) {
//					storeUpper = DoubleUtil.strToDouble( limit[0]);
//				}
//				if(limit != null && StringHelper.isNotEmpty(limit[1])) {
//					storeLower = DoubleUtil.strToDouble( limit[1]);
//				}
//				GoodsItemThresholdItem item = new GoodsItemThresholdItem(goodsItem.getId(), storeUpper, storeLower);
//				UpdateGoodsItemThresholdTask totalTask = new UpdateGoodsItemThresholdTask(item);
//				getContext().handle(totalTask);
			} else { // 数量分库存
				List<MaterialsItemThresholdItem> itemList = new ArrayList<MaterialsItemThresholdItem>();
				for(int rowIndex = 0; rowIndex < rowIds.length - 1; rowIndex++) {
					String[] limit = itemTable.getEditValue(rowIds[rowIndex], ColumnName.StoreUpper.name(), ColumnName.StoreLower.name());
					double storeUpper = -1;
					double storeLower = -1;
					if(limit != null && StringHelper.isNotEmpty(limit[0])) {
						storeUpper = DoubleUtil.strToDouble( limit[0]);
					}
					if(limit != null && StringHelper.isNotEmpty(limit[1])) {
						storeLower = DoubleUtil.strToDouble( limit[1]);
					}
					if (storeUpper == -1 && storeLower == -1) {
						continue;
					}
					if(storeUpper != 0 && storeLower != 0 && storeUpper <= storeLower) {
						new SMessageAlertWindow(true, "库存上限数量必须大于库存下限数量！", null);
						return false;
					}
					MaterialsItemThresholdItem item = new MaterialsItemThresholdItem(goodsItem.getId(), GUID.tryValueOf(rowIds[rowIndex]), storeUpper, storeLower);
					itemList.add(item);
				}
				if (itemList.size() > 0) {
					UpdateMaterialsItemThresholdTask itemTask = new UpdateMaterialsItemThresholdTask(itemList.toArray(new MaterialsItemThresholdItem[itemList.size()]));
					getContext().handle(itemTask);
				}
			}
		}
		itemTable.render();
		return true;
	}
}
