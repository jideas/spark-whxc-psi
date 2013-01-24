package com.spark.psi.inventory.browser.allocate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SListEdit2Column;
import com.spark.common.components.table.edit.SListEditColumn;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.entity.InventoryAllocateItemDet;
import com.spark.psi.publish.inventory.key.GetAllocateItemDetBySheetIdKey;

public class AllocateInShelfInfoProcessor extends BaseFormPageProcessor {

	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Area_Content = "Area_Content";
	
	private static final int MAX_TIERS_COUNT = 10;
	
	public static enum ColumnName {
		produceDate, shelf, tiersNo, count, editCount
	}
	
//	public static enum TableExtraValueName {
//		produceDate, shelf, shelfId, tiersNo, count
//	}
	
	private StoreInfo storeInfo = null;
	private List<AllocateInDistributeItem> itemList = null;
	
	
	private final Map<GUID, ShelfItem> shelfIdMap = new HashMap<GUID, ShelfItem>();
	private final Map<AllocateInDistributeItem, SEditTable> itemTableMap = new HashMap<AllocateInDistributeItem, SEditTable>();
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID sheetId = (GUID)getArgument();
		List<InventoryAllocateItemDet> list = context.getList(InventoryAllocateItemDet.class, new GetAllocateItemDetBySheetIdKey(sheetId));
		itemList = getDetListGroupByGoodsAndProduceDate(list);
		GUID storeId = (GUID)getArgument2();
		storeInfo = context.find(StoreInfo.class, storeId);
		storeShelfItems();
	}
	
	@Override
	public void process(final Situation context) {
		final Composite contentArea = createControl(ID_Area_Content, Composite.class);
		final Button confirmBtn = createControl(ID_Button_Confirm, Button.class);
		confirmBtn.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = true;
				for (AllocateInDistributeItem disItem : itemList) {
					SEditTable itemTable = itemTableMap.get(disItem);
					if (validateTableInput(itemTable)) {
						// 临时存储货位条目信息
						List<DistributeInventoryItemDet> detItemList = getDetItemList(itemTable, disItem.getStockId(), disItem);
						if (!validateCount(disItem, detItemList)) {
							return ;
						} else {
							disItem.setInventoryDetItems(detItemList.toArray(new DistributeInventoryItemDet[0]));
						}
					} else {
						isSuccess = false;
						break;
					}
				}
				if (isSuccess) {
					context.bubbleMessage(new MsgResponse(true, itemList));
				}
				
			}
		});
		final STableColumn[] columns = getColumns();
		for (AllocateInDistributeItem disItem : itemList) {
			SEditTable distributeTable = showContent(contentArea, disItem, columns);
			itemTableMap.put(disItem, distributeTable);
		}
		contentArea.layout();

	}
	
	private void storeShelfItems() {
		for (ShelfItem shelfItem : storeInfo.getShelfItems()) {
			shelfIdMap.put(shelfItem.getId(), shelfItem);
		}
	}
	
	private boolean validateCount(AllocateInDistributeItem disItem, List<DistributeInventoryItemDet> detItemList) {
		double count = 0.0;
		for (DistributeInventoryItemDet detItem : detItemList) {
			count += detItem.getCount();
		}
		
		double diffCount = count - disItem.getCount();
		if (diffCount > 0) {
			alert("材料：" + disItem.getName() + "[" + DateUtil.dateFromat(disItem.getProduceDate()) + "]\n货位入库总数为" + count + "，比总入库数多了" + diffCount);
			return false;
		} else if (diffCount < 0) {
			alert("材料：" + disItem.getName() + "[" + DateUtil.dateFromat(disItem.getProduceDate()) + "]\n货位入库总数为" + count + "，比总入库数少了" + -diffCount);
			return false;
		}
		return true;
	}
	
	private boolean validateTableInput(SEditTable table) {
		String[] rowIds = table.getAllRowId();
		for (String rowId : rowIds) {
			String[] values = table.getEditValue(rowId, ColumnName.shelf.name(),
					ColumnName.tiersNo.name(), ColumnName.count.name());
			if (isEmptyRow(table, values)) {
				continue;
			}
			if (StringHelper.isEmpty(values[0])) {
				alert("货位不能为空。");
				return false;
			}
			if (StringHelper.isEmpty(values[1])) {
				alert("层号不能为空。");
				return false;
			}
			if (StringHelper.isEmpty(values[2])) {
				alert("数量不能为空。");
				return false;
			}
			
		}
		return true;
	}
	
	private List<DistributeInventoryItemDet> getDetItemList(SEditTable table, GUID stockId, AllocateInDistributeItem disItem) {
		List<DistributeInventoryItemDet> detItemList = new ArrayList<DistributeInventoryItemDet>();
		String[] rowIds = table.getAllRowId();
		DistributeInventoryItemDet detItem = null;
		for (String rowId : rowIds) {
			String[] values = table.getEditValue(rowId, ColumnName.produceDate.name(), ColumnName.shelf.name(),
					ColumnName.tiersNo.name(), ColumnName.count.name());
			if (isEmptyRow(table, values)) {
				continue;
			}
			detItem = new DistributeInventoryItemDet();
			detItem.setProduceDate(disItem.getProduceDate());
			
			detItem.setId(GUID.tryValueOf(rowId));
			detItem.setCount(DoubleUtil.strToDouble(values[3], 2));
			detItem.setShelfId(GUID.tryValueOf(values[1]));
			detItem.setTiersNo(Integer.parseInt(values[2]));
			detItem.setShelfNo(shelfIdMap.get(detItem.getShelfId()).getShelfNo());
			detItem.setStockId(stockId);
			
			detItemList.add(detItem);
		}
		return detItemList;
	}
	
	private boolean isEmptyRow(SEditTable table, String[] values) {
		for (String value : values) {
			if (StringHelper.isNotEmpty(value)) {
				return false;
			}
		}
		return true;
	}
	
	private SEditTable showContent(Composite contentArea, final AllocateInDistributeItem dbtItem, STableColumn[] columns) {
		Composite titleArea = new Composite(contentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 23;
		GridLayout glTitle = new GridLayout();
		glTitle.marginTop = 10;
		glTitle.numColumns = 7;
		titleArea.setLayout(glTitle);
		titleArea.setLayoutData(gdTitle);
		
		new Label(titleArea).setText("材料名称：");
		new Label(titleArea).setText(dbtItem.getName());
		new Label(titleArea).setText("    ");
		new Label(titleArea).setText("生产日期：" + DateUtil.dateFromat(dbtItem.getProduceDate()));
		new Label(titleArea).setText("    ");
		new Label(titleArea).setText("入库数量" + "：");
		new Label(titleArea).setText("" + DoubleUtil.getRoundStr(dbtItem.getCount()));
		
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(true);
		SActionInfo[] actions = new SActionInfo[] {
			PSIActionCommon.getActionInfo(Action.Delete.name())
		};
		final SEditTable distributeTable = new SEditTable(contentArea, columns, tableStyle, actions);
		GridData gdTable = new GridData(GridData.FILL_HORIZONTAL);
		gdTable.heightHint = 160;
		distributeTable.setLayoutData(gdTable);
		final String firstRowId = GUID.randomID().toString();
		distributeTable.setContentProvider(new SEditContentProvider() {
			
			public boolean isSelected(Object element) {
				return false;
			}
			
			public boolean isSelectable(Object element) {
				return false;
			}
			
			public Object[] getElements(Context context, STableStatus tablestatus) {
				return new String[] {firstRowId};
			}
			
			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String)element;
				}
				return null;
			}
			
			public String getValue(Object element, String columnName) {
				if (element instanceof String) {
					return "";
				}
				return null;
			}
			
			public Object getNewElement() {
				return GUID.randomID().toString();
			}
			
			public SNameValue[] getExtraData(Object element) {
				ShelfItem[] shelfItems = storeInfo.getShelfItems();
				SNameValue[] tiersInfo = new SNameValue[shelfItems.length];
				int itemIndex = 0;
				for (ShelfItem item : shelfItems) {
					tiersInfo[itemIndex] = new SNameValue(item.getId().toString(), "" + item.getTiersCount());
					itemIndex++;
				}
				return tiersInfo;
			}
			
			public String[] getActionIds(Object element) {
				return new String[] {Action.Delete.name()};
			}
		});
		distributeTable.setLabelProvider(new LabelProvider(dbtItem.getProduceDate()));
		distributeTable.render();
		distributeTable.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "InventoryClient.init.handleTableDataChanged");
		distributeTable.addActionListener(new SActionListener() {
			
			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				if (Action.Delete.name().equals(actionName)) {
					if (distributeTable.getAllRowId().length == 1) {
						distributeTable.addRow(GUID.randomID().toString());
					}
					distributeTable.removeRow(rowId);
					distributeTable.renderUpate();
				}
			}
		});
		return distributeTable;
	}
	
	protected STableColumn[] getColumns() {
		STableColumn produceColumn = new STableColumn(ColumnName.produceDate.name(), 150, JWT.CENTER, "生产日期");
		SListEditColumn shelfColumn   = new SListEditColumn(ColumnName.shelf.name(), 150, JWT.CENTER, "货位");
		SListEdit2Column tierColumn   = new SListEdit2Column(ColumnName.tiersNo.name(), 150, JWT.CENTER, MAX_TIERS_COUNT, "层号");
		SNumberEditColumn countColumn = new SNumberEditColumn(ColumnName.count.name(), 150, JWT.CENTER, "入库数量");
		countColumn.setDecimal(2);
		shelfColumn.setListSource(new ListSourceAdapter() {
			
			public String getElementId(Object element) {
				ShelfItem shelfItem = (ShelfItem)element;
				return shelfItem.getId().toString();
			}
			
			public Object getElementById(String id) {
				return shelfIdMap.get(GUID.tryValueOf(id));
			}
			
			public String getText(Object element) {
				ShelfItem shelfItem = (ShelfItem)element;
				return "货位" + shelfItem.getShelfNo();
			}
			
			public Object[] getElements(Object inputElement) {
				return storeInfo.getShelfItems();
			}
		});
		STableColumn[] columns = new STableColumn[] { produceColumn,	shelfColumn, tierColumn, countColumn };
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}
	
	private List<AllocateInDistributeItem> getDetListGroupByGoodsAndProduceDate(List<InventoryAllocateItemDet> list) {
		List<AllocateInDistributeItem> disList = new ArrayList<AllocateInDistributeItem>();
		Map<GUID, List<InventoryAllocateItemDet>> goodsDets = getDetListGroupByGoodsId(list);
		Iterator<GUID> gIt = goodsDets.keySet().iterator();
		while(gIt.hasNext()) {
			GUID goodsId = gIt.next();
			List<InventoryAllocateItemDet> goodsDetList = goodsDets.get(goodsId);
			Map<Long, List<InventoryAllocateItemDet>> produceDateDets = getDetListGroupByProduceDate(goodsDetList);
			Iterator<Long> pIt = produceDateDets.keySet().iterator();
			while(pIt.hasNext()) {
				long produceDate = pIt.next();
				List<InventoryAllocateItemDet> produceDateDetList =  produceDateDets.get(produceDate);
				disList.add(converAllocateDetToDisDet(produceDateDetList));
			}
		}
		return disList;
	}
	
	private Map<Long, List<InventoryAllocateItemDet>> getDetListGroupByProduceDate(List<InventoryAllocateItemDet> list) {
		Map<Long, List<InventoryAllocateItemDet>> produceDateDets = new HashMap<Long, List<InventoryAllocateItemDet>>();
		for (InventoryAllocateItemDet det : list) {
			long produceDate = det.getProduceDate();
			List<InventoryAllocateItemDet> produceDateDetList = produceDateDets.get(produceDate);
			if (null == produceDateDetList) {
				produceDateDetList = new ArrayList<InventoryAllocateItemDet>();
				produceDateDets.put(produceDate, produceDateDetList);
			} 
			produceDateDetList.add(det);
		}
		return produceDateDets;
	}
	
	private Map<GUID, List<InventoryAllocateItemDet>> getDetListGroupByGoodsId(List<InventoryAllocateItemDet> list) {
		Map<GUID, List<InventoryAllocateItemDet>> goodsDets = new HashMap<GUID, List<InventoryAllocateItemDet>>();
		for (InventoryAllocateItemDet det : list) {
			GUID goodsId = det.getStockId();
			List<InventoryAllocateItemDet> goodsDetList = goodsDets.get(goodsId);
			if (null == goodsDetList) {
				goodsDetList = new ArrayList<InventoryAllocateItemDet>();
				goodsDets.put(goodsId, goodsDetList);
			}
			goodsDetList.add(det);
		}
		return goodsDets;
	}
	
	private AllocateInDistributeItem converAllocateDetToDisDet(List<InventoryAllocateItemDet> produceDateDetList) {
		AllocateInDistributeItem item = new AllocateInDistributeItem();
		if (produceDateDetList.size() < 1) return null;
		double count = 0.0;
		for (InventoryAllocateItemDet det : produceDateDetList) {
			count += det.getCount();
		}
		item.setStockId(produceDateDetList.get(0).getStockId());
		item.setName(produceDateDetList.get(0).getStockName());
		item.setProduceDate(produceDateDetList.get(0).getProduceDate());
		item.setCount(count);
		return item;
	}
	
	private class LabelProvider implements SLabelProvider, SNumberLabelProvider {
		
		private long produceDate = 0;
		public LabelProvider(long produceDate) {
			this.produceDate = produceDate;
		}
		
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			if (0 == columnIndex) return DateUtil.dateFromat(produceDate);
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}
		
	}
}

class AllocateInDistributeItem {
	private GUID stockId;
	private String name;
	private long produceDate;
	private double count;
	
	private DistributeInventoryItemDet[] inventoryDetItems;
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
	}
	public DistributeInventoryItemDet[] getInventoryDetItems() {
		return inventoryDetItems;
	}
	public void setInventoryDetItems(DistributeInventoryItemDet[] inventoryDetItems) {
		this.inventoryDetItems = inventoryDetItems;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	
	
}