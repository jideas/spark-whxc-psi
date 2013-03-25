package com.spark.psi.base.browser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;
import com.spark.psi.publish.inventory.entity.InventoryDetItem;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary.SummaryItem;
import com.spark.psi.publish.inventory.key.GetInventoryDetByStockIdKey;
import com.spark.psi.publish.inventory.key.GetInventoryDetByStoreAndStockIdKey;
import com.spark.psi.publish.inventory.key.GetInventorySummaryKey;

public class InventoryViewWindow extends SMenuWindow {

	private static enum ColumName {
		productDate, shelf, tierNo, count
	}

	private Context context;
	private Composite contentArea;

	public InventoryViewWindow(Control owner) {
		super(null, owner, Style.InfoWindow, Direction.Up, ActiveMode.Program);
		Composite superContentArea = this.getContentArea();
		superContentArea.setLayout(new GridLayout());
		final ScrolledPanel area = new ScrolledPanel(superContentArea);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 380;
		area.setLayoutData(gd);

		contentArea = area.getComposite();
		GridLayout gl = new GridLayout();
		gl.marginTop = gl.marginBottom = 3;
		gl.marginLeft = gl.marginRight = 3;
		contentArea.setLayout(gl);

		context = contentArea.getContext();

	}

	public void refresh(GUID stockId, GUID storeId, Point location) {
		GetInventoryDetByStoreAndStockIdKey key = new GetInventoryDetByStoreAndStockIdKey(storeId, stockId,
				InventoryType.Materials);
		List<InventoryDetItem> detItemList = context.getList(InventoryDetItem.class, key);
		doRefresh(detItemList, location);
	}

	public void refresh(GUID stockId, Point location) {
		GetInventoryDetByStockIdKey key = new GetInventoryDetByStockIdKey(stockId, InventoryType.Materials);
		List<InventoryDetItem> detItemList = context.getList(InventoryDetItem.class, key);
		doRefresh(detItemList, location);
	}

	private void doRefresh(List<InventoryDetItem> detItemList, Point location) {
		contentArea.clear();
		Map<GUID, StoreInventoryDet> storeIdDetMap = getStoreInventoryDets(detItemList);
		Iterator<GUID> it = storeIdDetMap.keySet().iterator();
		while (it.hasNext()) {
			StoreInventoryDet storeInvdet = storeIdDetMap.get(it.next());
			String title = "仓库名称：" + storeInvdet.getStoreName() + "     库存数量："
					+ DoubleUtil.getRoundStr(storeInvdet.getCount());
			showContent(title, storeInvdet);
		}
		contentArea.layout();
		showMenu(location.x, location.y, 400, 250);
	}

	private Map<GUID, StoreInventoryDet> getStoreInventoryDets(List<InventoryDetItem> detItemList) {
		Map<GUID, StoreInventoryDet> storeIdDetMap = new HashMap<GUID, StoreInventoryDet>();
		GUID storeId = null;
		for (InventoryDetItem detItem : detItemList) {
			storeId = detItem.getStoreId();
			if (storeIdDetMap.containsKey(storeId)) {
				storeIdDetMap.get(storeId).addDetItem(detItem);
			} else {
				StoreInventoryDet storeInvdet = new StoreInventoryDet(storeId, detItem.getStoreName());
				storeInvdet.addDetItem(detItem);
				storeIdDetMap.put(storeId, storeInvdet);
			}
		}
		return storeIdDetMap;
	}

	private STable showContent(String title, final StoreInventoryDet storeInvdet) {
		if (StringHelper.isNotEmpty(title)) {
			Composite titleArea = new Composite(contentArea);
			GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
			gdTitle.heightHint = 23;
			GridLayout glTitle = new GridLayout();
			glTitle.marginTop = 10;
			glTitle.numColumns = 5;
			titleArea.setLayout(glTitle);
			titleArea.setLayoutData(gdTitle);
			Label label = new Label(titleArea);
			label.setText(title);
		}
		STableStyle tableStyle = new STableStyle();
//		tableStyle.setAutoAddRow(true);
		tableStyle.setPageAble(false);
		STable distributeTable = new STable(contentArea, getColumns(), tableStyle);
		GridData gdTable = new GridData(GridData.FILL_HORIZONTAL);
		gdTable.heightHint = 160;

		distributeTable.setLayoutData(gdTable);
		distributeTable.setContentProvider(new SContentProvider() {

			public boolean isSelected(Object element) {
				return false;
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public Object[] getElements(Context context, STableStatus tablestatus) {
				return storeInvdet.getInventoryDetItems();
			}

			public String getElementId(Object element) {
				InventoryDetItem detItem = (InventoryDetItem) element;
				return detItem.getId().toString();
			}
		});
		distributeTable.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return getText(element, columnIndex);
			}

			public String getText(Object element, int columnIndex) {
				InventoryDetItem detItem = (InventoryDetItem) element;
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(detItem.getProduceDate());
				case 1:
					return "货位" + detItem.getShelfNo();
				case 2:
					return "" + detItem.getTiersNo();
				case 3:
					return DoubleUtil.getRoundStr(detItem.getCount());
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
		distributeTable.render();
		return distributeTable;
	}

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[] {
				new STableColumn(ColumName.productDate.name(), 80, JWT.CENTER, "生产日期"),
				new STableColumn(ColumName.shelf.name(), 60, JWT.CENTER, "货位"),
				new STableColumn(ColumName.tierNo.name(), 60, JWT.CENTER, "层号"),
				new STableColumn(ColumName.count.name(), 80, JWT.CENTER, "数量"), };

		return columns;
	}

	public void refresh(GUID goodsItemId, GUID[] storeIds, Point location, boolean showTotalOnly) {
		//
		contentArea.clear();
		contentArea.layout();
		//
		MaterialsItemInfo goodsItem = contentArea.getContext().find(MaterialsItemInfo.class, goodsItemId);
		GoodsOrKitInventorySummary summary = contentArea.getContext().find(GoodsOrKitInventorySummary.class,
				new GetInventorySummaryKey(goodsItemId));
		List<SummaryItem> itemList = new ArrayList<SummaryItem>();
		if (storeIds != null && storeIds.length > 0) {
			for (GUID storeId : storeIds) {
				for (SummaryItem item : summary.getItems()) {
					if (storeId.equals(item.getStoreId())&&item.getCount()>0) {
						itemList.add(item);
						break;
					}
				}
			}
		} else {
			for (SummaryItem item : summary.getItems()) {
				if(item.getCount()>0)
				itemList.add(item);
			}
		}
		double v = 0;
		for (SummaryItem item : itemList) {
			v += item.getCount();
		}
		// final double allCount = v;
		// itemList.add(new SummaryItem() {
		// public String getStoreName() {
		// return "总计";
		// }
		//			
		// public GUID getStoreId() {
		// return GUID.randomID();
		// }
		//			
		// public double getCount() {
		// return allCount;
		// }
		// });
		SummaryItem[] items = itemList.toArray(new SummaryItem[0]);
		//
		if (showTotalOnly) {
			double count = 0;
			for (SummaryItem item : items) {
				count += item.getCount();
			}
			Label label = new Label(contentArea);
			label.setLayoutData(GridData.INS_FILL_BOTH);
			label.setText("总库存数量：" + DoubleUtil.getRoundStr(count, goodsItem.getItemData().getScale()));
		} else {
			TableProvider tableProvider = new TableProvider(goodsItem, items);
			STableColumn[] columns = new STableColumn[2];
			columns[0] = new STableColumn("name", 240, JWT.LEFT, "仓库名称");
			// columns[0].setGrab(true);
			columns[1] = new STableColumn("count", 120, JWT.RIGHT, "库存数量");
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			//
			int n = items.length > 5 ? 5 : items.length;
			int tableHeight = tableStyle.getHeaderHeight() + tableStyle.getRowHeight() * n;
			//
			GridData gd = new GridData();
			gd.widthHint = 360;
			gd.heightHint = tableHeight - 1;
			if (items.length <= 5) {
				tableStyle.setNoScroll(true);
				gd.heightHint = tableHeight - 1;
			} else {
				gd.widthHint = 380;
			}
			//
			STable table = new STable(contentArea, columns, tableStyle);
			table.setContentProvider(tableProvider);
			table.setLabelProvider(tableProvider);
			table.render();

			table.setLayoutData(gd);
		}
		showMenu(location.x, location.y, 10, 25);
	}

	private class TableProvider implements SContentProvider, SLabelProvider, SNumberLabelProvider {

		private SummaryItem[] items;

		private MaterialsItemInfo goodsItem;

		public TableProvider(MaterialsItemInfo goodsItem2, SummaryItem[] items) {
			this.goodsItem = goodsItem2;
			this.items = items;
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return items;
		}

		public String getElementId(Object element) {
			SummaryItem item = (SummaryItem) element;
			return item.getStoreId().toString();
		}

		public String getText(Object element, int columnIndex) {
			SummaryItem item = (SummaryItem) element;
			if (columnIndex == 0) {
				return item.getStoreName();
			} else {
				return String.valueOf(item.getCount());
			}
		}

		public int getDecimal(Object element, int columnIndex) {
			if (columnIndex == 1) {
				return goodsItem.getItemData().getScale();
			}
			return -1;
		}

		// ///////////
		public boolean isSelected(Object element) {
			return false;
		}

		public boolean isSelectable(Object element) {
			return true;
		}

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

	}
}

class StoreInventoryDet {
	private GUID storeId;
	private String storeName;
	private double count;
	private final List<InventoryDetItem> detItemList = new ArrayList<InventoryDetItem>();

	public StoreInventoryDet(GUID storeId, String storeName) {
		this.storeId = storeId;
		this.storeName = storeName;
	}

	public void addDetItem(InventoryDetItem detItem) {
		this.detItemList.add(detItem);
		this.count += detItem.getCount();
	}

	public InventoryDetItem[] getInventoryDetItems() {
		return detItemList.toArray(new InventoryDetItem[0]);
	}

	public GUID getStoreId() {
		return this.storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public double getCount() {
		return this.count;
	}
}
