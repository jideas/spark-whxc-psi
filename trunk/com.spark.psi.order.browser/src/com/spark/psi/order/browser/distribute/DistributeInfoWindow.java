package com.spark.psi.order.browser.distribute;

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
import com.spark.portal.browser.SMenuWindow;

public class DistributeInfoWindow extends SMenuWindow {

	public DistributeInfoWindow(Control owner) {
		super(null, owner, Style.InfoWindow, Direction.Down, ActiveMode.Program);
		Composite contentArea = this.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginTop = gl.marginBottom = 5;
		gl.marginLeft = gl.marginRight = 5;
		contentArea.setLayout(gl);
	}

	public void refresh(String goodsItemInfo, String unAllocateCount,
			Item[] items, Point location) {
		//
		contentArea.clear();
		contentArea.layout();
		//
		Label label = new Label(contentArea);
		label.setText(goodsItemInfo);
		label = new Label(contentArea);
		label.setText("未分配数量："+unAllocateCount);

		//
		TableProvider tableProvider = new TableProvider(items);
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn("Name", 240, JWT.LEFT, "仓库名称");
		columns[1] = new STableColumn("AvailableCount", 120, JWT.RIGHT, "可用数量");
		columns[2] = new STableColumn("DistributeCount", 120, JWT.RIGHT,
				"已分配数量");
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		//
		int n = items.length > 5 ? 5 : items.length;
		int tableHeight = tableStyle.getHeaderHeight()
				+ tableStyle.getRowHeight() * n;
		//
		GridData gd = new GridData();
		gd.widthHint = 480;
		gd.heightHint = tableHeight - 1;
		//
		if (items.length <= 5) {
			tableStyle.setNoScroll(true);
			gd.heightHint = tableHeight - 1;
		} else {
			gd.widthHint = 500;
		}
		//
		STable table = new STable(contentArea, columns, tableStyle);
		table.setContentProvider(tableProvider);
		table.setLabelProvider(tableProvider);
		table.render();
		//
		table.setLayoutData(gd);
		//
		showMenu(location.x, location.y, 10, 25);
	}

	private static class TableProvider implements SContentProvider,
			SLabelProvider, SNumberLabelProvider {

		private Item[] items;

		public TableProvider(Item[] items) {
			this.items = items;
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return items;
		}

		public String getElementId(Object element) {
			Item item = (Item) element;
			return item.storeId.toString();
		}

		public String getText(Object element, int columnIndex) {
			Item item = (Item) element;
			switch (columnIndex) {
			case 0:
				return item.storeName;
			case 1:

				return item.availableCount;
			case 2:
				return item.distributeCount;
			}
			return "";
		}

		public int getDecimal(Object element, int columnIndex) {
			Item item = (Item) element;
			if (columnIndex > 0) {
				return item.decimal;
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

	public static class Item {
		private GUID storeId;
		private String storeName;
		private String availableCount;
		private String distributeCount;
		private int decimal;

		public Item(GUID storeId, String storeName, String availableCount,
				String distributeCount, int decimal) {
			super();
			this.storeId = storeId;
			this.storeName = storeName;
			this.availableCount = availableCount;
			this.distributeCount = distributeCount;
			this.decimal = decimal;
		}
	}
}
