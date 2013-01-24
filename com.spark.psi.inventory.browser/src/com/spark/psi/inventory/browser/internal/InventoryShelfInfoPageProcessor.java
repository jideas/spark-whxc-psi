package com.spark.psi.inventory.browser.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.entity.InventoryDetItem;
import com.spark.psi.publish.inventory.key.GetInventoryDetByStoreAndStockIdKey;

public class InventoryShelfInfoPageProcessor extends PageProcessor {

	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Area_Content = "Area_Content";
	
	
	public static enum ColumnName {
		produceDate, shelf, tiersNo, count, editCount
	}
	
	public static enum TableExtraValueName {
		produceDate, shelf, shelfId, tiersNo, count, editCount
	}
	
	private StoreInfo           storeInfo       = null;
	private DistributeInventoryItem[] dbtItems  = null;
	private final Map<DistributeInventoryItem, SEditTable> itemTableMap = new HashMap<DistributeInventoryItem, SEditTable>();
	
	private String countTitle = "出库数量";
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID storeId = (GUID)getArgument();
		storeInfo = context.find(StoreInfo.class, storeId);
		dbtItems = (DistributeInventoryItem[])getArgument2();
		if (null != getArgument3()) {
			countTitle = (String) getArgument3();
		}
	}

	@Override
	public void process(final Situation context) {
		final Composite contentArea = createControl(ID_Area_Content, Composite.class);
		final Button confirmBtn = createControl(ID_Button_Confirm, Button.class);
		confirmBtn.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				for (DistributeInventoryItem dbtItem : dbtItems) {
					SEditTable itemTable = itemTableMap.get(dbtItem);
					if (validateTableInput(itemTable)) {
						// 临时存储货位条目信息
						List<DistributeInventoryItemDet> detItemList = getDetItemList(itemTable, dbtItem.getStockId());
						if (!validateCount(dbtItem, detItemList)) {
							alert("货位出库数量必须与总数量相等。");
							return ;
						} else {
							dbtItem.setInventoryDetItems(detItemList.toArray(new DistributeInventoryItemDet[0]));
						}
					} else {
						return;
					}
				}
				context.bubbleMessage(new MsgResponse(true, dbtItems));
			}
		});
		final STableColumn[] columns = getColumns();
		for (DistributeInventoryItem dbtItem : dbtItems) {
			SEditTable distributeTable = showContent(contentArea, dbtItem, columns);
			itemTableMap.put(dbtItem, distributeTable);
		}
		contentArea.layout();
	}
	
	private SEditTable showContent(Composite contentArea, final DistributeInventoryItem dbtItem, STableColumn[] columns) {
		Composite titleArea = new Composite(contentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 23;
		GridLayout glTitle = new GridLayout();
		glTitle.marginTop = 10;
		glTitle.numColumns = 5;
		titleArea.setLayout(glTitle);
		titleArea.setLayoutData(gdTitle);
		
		new Label(titleArea).setText("材料名称：");
		new Label(titleArea).setText(dbtItem.getName());
		new Label(titleArea).setText("    ");
		new Label(titleArea).setText(countTitle + "：");
		new Label(titleArea).setText("" + DoubleUtil.getRoundStr(dbtItem.getCount()));
		
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setPageAble(false);
		final SEditTable distributeTable = new SEditTable(contentArea, columns, tableStyle, null);
		GridData gdTable = new GridData(GridData.FILL_HORIZONTAL);
		gdTable.heightHint = 160;
		distributeTable.setLayoutData(gdTable);
		distributeTable.setContentProvider(new SEditContentProvider() {
			
			public boolean isSelected(Object element) {
				return false;
			}
			
			public boolean isSelectable(Object element) {
				return false;
			}
			
			public Object[] getElements(Context context, STableStatus tablestatus) {
				GetInventoryDetByStoreAndStockIdKey key = new GetInventoryDetByStoreAndStockIdKey(storeInfo.getId(), dbtItem.getStockId(), InventoryType.Materials);
				List<InventoryDetItem> resultList = context.getList(InventoryDetItem.class, key);
				return resultList.toArray(new InventoryDetItem[0]);
			}
			
			public String getElementId(Object element) {
				InventoryDetItem item = (InventoryDetItem)element;
				return item.getId().toString();
			}
			
			public String getValue(Object element, String columnName) {
				if (ColumnName.editCount.name().equals(columnName)) {
					return "";
				}
				return null;
			}
			
			public Object getNewElement() {
				return null;
			}
			
			public SNameValue[] getExtraData(Object element) {
				InventoryDetItem item = (InventoryDetItem)element;
				return new SNameValue[] {new SNameValue(TableExtraValueName.produceDate.name(), item.getProduceDate() + ""),
						new SNameValue(TableExtraValueName.shelf.name(), item.getShelfNo() + ""),
						new SNameValue(TableExtraValueName.shelfId.name(), item.getShelfId().toString() + ""),
						new SNameValue(TableExtraValueName.tiersNo.name(), item.getTiersNo() + ""),
						new SNameValue(TableExtraValueName.count.name(), item.getCount() + "")};
			}
			
			public String[] getActionIds(Object element) {
				return null;
			}
		});
		distributeTable.setLabelProvider(new SLabelProvider() {
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			public String getText(Object element, int columnIndex) {
				InventoryDetItem item = (InventoryDetItem)element;
				switch(columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getProduceDate());
				case 1:
					return "货位" + item.getShelfNo();
				case 2:
					return "" +item.getTiersNo();
				case 3:
					return DoubleUtil.getRoundStr(item.getCount()); 
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
	protected STableColumn[] getColumns() {
		STableColumn produceColumn = new STableColumn(ColumnName.produceDate.name(), 150, JWT.CENTER, "生产日期");
		STableColumn shelfColumn   = new STableColumn(ColumnName.shelf.name(), 150, JWT.CENTER, "货位");
		STableColumn tierColumn   = new STableColumn(ColumnName.tiersNo.name(), 150, JWT.CENTER, "层号");
		STableColumn countColumn   = new STableColumn(ColumnName.count.name(), 150, JWT.CENTER, "库存数量");
		SNumberEditColumn editCountColumn = new SNumberEditColumn(ColumnName.editCount.name(), 150, JWT.CENTER, countTitle);
		STableColumn[] columns = new STableColumn[] { produceColumn, shelfColumn, tierColumn, countColumn, editCountColumn };
		
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}
	private boolean validateCount(DistributeInventoryItem dbtItem, List<DistributeInventoryItemDet> detItemList) {
		double count = 0.0;
		for (DistributeInventoryItemDet detItem : detItemList) {
			count += detItem.getDistributeCount();
		}
		if (count == dbtItem.getCount()) {
			return true;
		} else {
			return false;
		}
	}
	
	private List<DistributeInventoryItemDet> getDetItemList(SEditTable table, GUID stockId) {
		List<DistributeInventoryItemDet> detItemList = new ArrayList<DistributeInventoryItemDet>();
		String[] rowIds = table.getAllRowId();
		DistributeInventoryItemDet detItem = null;
		for (String rowId : rowIds) {
			String[] values = table.getExtraData(rowId, TableExtraValueName.produceDate.name(), TableExtraValueName.shelf.name(),
					TableExtraValueName.shelfId.name(), TableExtraValueName.tiersNo.name(), TableExtraValueName.count.name());
			String currentCountStr = table.getEditValue(rowId, ColumnName.editCount.name())[0];
			if (StringUtils.isEmpty(currentCountStr)) {
				continue;
			}
			detItem = new DistributeInventoryItemDet();
			detItem.setId(GUID.tryValueOf(rowId));
			detItem.setProduceDate(Long.parseLong(values[0]));
			detItem.setShelfNo(Integer.parseInt(values[1]));
			detItem.setShelfId(GUID.tryValueOf(values[2]));
			detItem.setTiersNo(Integer.parseInt(values[3]));
			detItem.setCount(DoubleUtil.strToDouble(values[4]));
			detItem.setStockId(stockId);
			
			detItem.setDistributeCount(DoubleUtil.strToDouble(currentCountStr));
			
			detItemList.add(detItem);
		}
		return detItemList;
	}
	
	private boolean validateTableInput(SEditTable table) {
		String[] rowIds = table.getAllRowId();
		for (String rowId : rowIds) {
			String countStr = table.getExtraData(rowId, TableExtraValueName.count.name())[0];
			String currentCountStr = table.getEditValue(rowId, ColumnName.editCount.name())[0];
			if (StringUtils.isEmpty(currentCountStr)) {
				continue;
			}
			if (DoubleUtil.strToDouble(currentCountStr) > DoubleUtil.strToDouble(countStr)) {
				alert(countTitle + "不能大于库存数量。");
				return false;
			}
		}
		return true;
	}
}
