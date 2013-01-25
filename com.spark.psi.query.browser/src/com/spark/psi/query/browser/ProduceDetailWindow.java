package com.spark.psi.query.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.query.intf.publish.entity.ProduceItem;

public class ProduceDetailWindow extends SMenuWindow {
	
	private static enum ColumnName {
		goodsName("商品名称"), 
		count("完成数量"),
		creator("确认完成人"),
		createDate("确认完成时间");
		
		private String title = null;
		private ColumnName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}

	private STable table           = null;
	private ProduceItem.Log[] logs = null; 
	
	public ProduceDetailWindow(Control owner) {
		super(null, owner, Style.InfoWindow, Direction.Up, ActiveMode.Program);
		Composite superContentArea = this.getContentArea();
		superContentArea.setLayout(new GridLayout());
		final ScrolledPanel area = new ScrolledPanel(superContentArea);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 440;
		area.setLayoutData(gd);
		
		Composite contentArea = area.getComposite();
		GridLayout gl = new GridLayout();
		gl.marginTop = gl.marginBottom = 3;
		gl.marginLeft = gl.marginRight = 3;
		contentArea.setLayout(gl);
		
		showContent(contentArea);
	}
	
	public void refresh(ProduceItem.Log[] logs, Point location) {
		this.logs = logs;
		table.render();
		showMenu(location.x, location.y, 440, 320);
	}
	
	private void showContent(Composite contentArea) {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		table = new STable(contentArea, getColumns(), tableStyle);
		table.setContentProvider(new TableContentProvider());
		table.setLabelProvider(new TableLabelProvider());
		table.render();
		GridData gd = new GridData();
		gd.widthHint = 430;
		
		table.setLayoutData(gd);
	}
	
	
	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(ColumnName.goodsName.name(),170,JWT.CENTER, ColumnName.goodsName.getTitle());
		columns[1] = new STableColumn(ColumnName.count.name(), 80, JWT.RIGHT, ColumnName.count.getTitle());
		columns[2] = new STableColumn(ColumnName.creator.name(), 80, JWT.CENTER, ColumnName.creator.getTitle());
		columns[3] = new STableColumn(ColumnName.createDate.name(), 100, JWT.CENTER, ColumnName.createDate.getTitle());
		return columns;
	}
	
	private class TableContentProvider implements SContentProvider {

		@Override
		public String getElementId(Object element) {
			return GUID.randomID().toString();
		}

		@Override
		public Object[] getElements(Context context, STableStatus tablestatus) {
			return logs;
		}

		@Override
		public boolean isSelectable(Object element) {
			return false;
		}

		@Override
		public boolean isSelected(Object element) {
			return false;
		}
		
	}
	
	private class TableLabelProvider implements SLabelProvider {

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getText(Object element, int columnIndex) {
			ProduceItem.Log log = (ProduceItem.Log)element;
			switch(columnIndex) {
			case 0:
				return log.getGoodsName();
			case 1:
				return DoubleUtil.getRoundStr(log.getCount());
			case 2:
				return log.getCreator();
			case 3:
				return DateUtil.dateFromat(log.getCreateDate());
			}
			return null;
		}

		@Override
		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}
}
