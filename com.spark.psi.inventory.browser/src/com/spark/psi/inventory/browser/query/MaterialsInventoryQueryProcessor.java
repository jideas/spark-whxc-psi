package com.spark.psi.inventory.browser.query;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.InventoryViewWindow;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageProcessor;
import com.spark.psi.base.browser.material.MaterialCategorySelectionMsg;
import com.spark.psi.inventory.browser.query.MaterialsInventoryQueryRender.MaterialInventoryQueryListRender;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.inventory.entity.InventoryItem;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey.SortField;

public class MaterialsInventoryQueryProcessor extends MaterialCategoryFramePageProcessor {
	private MaterialInventoryQueryListProcessor queryListProcessor;

	public static enum Cloumns {
		materialCode, materialNumber, materialName, unit, shelfLife, spec, count;
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(MaterialCategorySelectionMsg.class,
				new MessageListener<MaterialCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							MaterialCategorySelectionMsg message,
							MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
						String storeId = null;
						if (queryListProcessor != null) {
							storeId = queryListProcessor.storeList.getText();
						}
						ControllerPage page = (ControllerPage) rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														MaterialInventoryQueryListProcessor.class,
														MaterialInventoryQueryListRender.class),
												message.getCategoryId(),
												storeId));
						if (page != null) {
							queryListProcessor = (MaterialInventoryQueryListProcessor) page
									.getProcessor();
						}
					}
				});
		super.process(context);
	}

	/**
	 * 商品库存查询处理器
	 */
	public final static class MaterialInventoryQueryListProcessor extends
			PSIGoodsListPageProcessor {

		// 过滤仓库下拉列表
		public final static String ID_COMBOLIST_STORE = "ComboList_Store";
		// 商品数量
		public final static String ID_LABEL_GOODSINVENTORY_COUNT = "Label_GoodsInventory_Count";
		
		public final static String ID_viewInventory = "viewInventory";

		private Label countLabel;
		private LWComboList storeList;
		//查库窗口
		protected InventoryViewWindow inventoryInfoWindow;
		
		@Override
		public void process(Situation situation) {
			super.process(situation);
			storeList = this.createControl(ID_COMBOLIST_STORE,
					LWComboList.class, JWT.NO);
			StoreSource source = new StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
			source.setShowAllStoreItem(true);
			storeList.getList().setSource(source);
			storeList.getList().setInput(null);
			if (this.getArgument2() != null) {
				storeList.setSelection((String) this.getArgument2());
			} else {
				if (CheckIsNull.isNotEmpty(source.getFirstStoreId())) {
					storeList.setSelection(source.getFirstStoreId().toString());
				}
			}
			storeList.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					table.render();

				}
			});
			countLabel = this.createControl(ID_LABEL_GOODSINVENTORY_COUNT,
					Label.class, JWT.NONE);
			//处理查库动作的客户端事件
			table.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "InventoryClient.handleActionPerformed");
			
			//初始化查库窗口
//			inventoryInfoWindow = new InventoryViewWindow(this.table);
		}

		@Override
		protected Object[] getElements(Context context, String searchText,
				GUID categoryId, STableStatus tablestatus) {
			GetInventoryItemKey key = new GetInventoryItemKey(InventoryType.Materials, tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
			key.setGoodsCategoryId(categoryId);
			key.setSearchText(searchText);
			GUID storeId = null;
			if (CheckIsNull.isNotEmpty(storeList.getText())) {
				storeId = GUID.valueOf(storeList.getText());
			}
			if(storeId.equals(GUID.emptyID)) {
				storeId = null;
			}
			key.setStoreId(storeId);
			if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
				key.setSortField(getSortField(tablestatus.getSortColumn()));
				key.setSortType(getSortType(tablestatus.getSortDirection()));
			}
			List<InventoryItem> list = getContext().getList(
					InventoryItem.class, key);
			int size = list.size();
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
			}
			countLabel.setText(String.valueOf(size));
			return list.toArray(new InventoryItem[0]);
//			if (CheckIsNull.isNotEmpty(list)) {
//				InventoryItem[] items = new InventoryItem[list.size()];
//				int index = 0;
//				for (InventoryItem item : list) {
//					items[index++] = item;
//				}
//				countLabel.setText(String.valueOf(list.size()));
//				return items;
//			}
//			countLabel.setText("0");
//			return null;
		}

		public SortField getSortField(String sortColumn) {
			if (Cloumns.materialCode.name().equals(sortColumn)) {
				return SortField.code;
			} else if (Cloumns.materialNumber.name().equals(sortColumn)) {
				return SortField.number;
			} else if (Cloumns.materialName.name().equals(sortColumn)) {
				return SortField.name;
			} else if (Cloumns.unit.name().equals(sortColumn)) {
				return SortField.unit;
			} else if (Cloumns.spec.name().equals(sortColumn)) {
				return SortField.spec;
			} else if (Cloumns.shelfLife.name().equals(sortColumn)) {
				return SortField.shelfLife;
			} else if (Cloumns.count.name().equals(sortColumn)) {
				return SortField.count;
			}
			return null;
		}

		public SortType getSortType(SSortDirection sortDirection) {
			if (SSortDirection.ASC.equals(sortDirection)) {
				return SortType.Asc;
			} else {
				return SortType.Desc;
			}

		}

		@Override
		public void actionPerformed(String rowId, String actionName,
				String actionValue) {
			if(ID_viewInventory.equals(actionName))
			{
				String[] locationInfo = actionValue.split(":");
				lookInventory(rowId,new Point(Integer.parseInt(locationInfo[0]), Integer.parseInt(locationInfo[1])));
			}
		}

		private void lookInventory(String rowId, Point point) {
			if (StringHelper.isEmpty(storeList.getList().getSeleted()) || 
					GUID.tryValueOf(storeList.getList().getSeleted()).equals(GUID.emptyID)) {
				inventoryInfoWindow = new InventoryViewWindow(this.table);
				inventoryInfoWindow.refresh(GUID.valueOf(rowId), point);
			} else {
				GUID storeId = GUID.tryValueOf(storeList.getList().getSeleted());
				inventoryInfoWindow = new InventoryViewWindow(this.table);
				inventoryInfoWindow.refresh(GUID.valueOf(rowId), storeId, point);
			}
		}

		/*
		 * 获取指定对象ID
		 */
		public String getElementId(Object element) {
			InventoryItem item = (InventoryItem) element;
			return item.getStockId().toString();
		}

		@Override
		protected String getExportFileTitle() {
			return "材料库存";
		}

	}
}
