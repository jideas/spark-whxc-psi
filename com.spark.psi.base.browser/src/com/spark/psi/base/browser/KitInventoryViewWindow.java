package com.spark.psi.base.browser;

import java.util.ArrayList;
import java.util.List;

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
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary.SummaryItem;
import com.spark.psi.publish.inventory.key.GetInventorySummaryKey;
import com.spark.psi.publish.inventory.key.GetKitInventorySummaryKey;

public class KitInventoryViewWindow extends SMenuWindow {

	public KitInventoryViewWindow(Control owner) {
		super(null, owner, Style.InfoWindow, Direction.Up, ActiveMode.Program);
		Composite contentArea = this.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginTop = gl.marginBottom = 3;
		gl.marginLeft = gl.marginRight = 3;
		contentArea.setLayout(gl);
	}

	public void refresh(String rowId, GUID[] storeIds, Point location,
			boolean showTotalOnly) {
		//
		contentArea.clear();
		contentArea.layout();
		String kitName = rowId.split("")[0];
		String kitDesc = rowId.split("")[1];
		String unit = rowId.split("")[2];
		GetKitInventorySummaryKey key = new GetKitInventorySummaryKey(kitName, kitDesc, unit);
		GoodsOrKitInventorySummary summary = contentArea.getContext().find(
				GoodsOrKitInventorySummary.class,key);
		List<SummaryItem> itemList = new ArrayList<SummaryItem>();
		if (storeIds != null && storeIds.length > 0) {
			for (GUID storeId : storeIds) {
				for (SummaryItem item : summary.getItems()) {
					if (storeId.equals(item.getStoreId())) {
						itemList.add(item);
						break;
					}
				}
			}
		} else {
			for (SummaryItem item : summary.getItems()) {
				itemList.add(item);
			}
		}
		SummaryItem[] items = itemList.toArray(new SummaryItem[0]);
		//
		if (showTotalOnly) {
			double count = 0;
			for (SummaryItem item : items) {
				count += item.getCount();
			}
			Label label = new Label(contentArea);
			label.setLayoutData(GridData.INS_FILL_BOTH);
			label.setText("总库存数量："
					+ DoubleUtil.getRoundStr(count));
		} else {
			TableProvider tableProvider = new TableProvider(items);
			STableColumn[] columns = new STableColumn[2];
			columns[0] = new STableColumn("name", 240, JWT.LEFT, "仓库名称");
			// columns[0].setGrab(true);
			columns[1] = new STableColumn("count", 120, JWT.RIGHT, "库存数量");
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			//
			int n = items.length > 5 ? 5 : items.length;
			int tableHeight = tableStyle.getHeaderHeight()
					+ tableStyle.getRowHeight() * n;
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

	private class TableProvider implements SContentProvider, SLabelProvider,
			SNumberLabelProvider {

		private SummaryItem[] items;

		public TableProvider(SummaryItem[] items) {
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

		public int getDecimal(Object element, int columnIndex) {
			if(0==columnIndex)
			{
				return -1;
			}
			return 2;
		}

	}
}
