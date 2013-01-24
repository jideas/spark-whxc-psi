package com.spark.psi.inventory.browser.query;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
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
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.InventoryViewWindow;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.inventory.browser.query.GoodsInventoryQueryRender.GoodsInventoryQueryListRender;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.InventoryItem;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey.SortField;

public class GoodsInventoryQueryProcessor extends
		GoodsCategoryFramePageProcessor {

	private GoodsInventoryQueryListProcessor queryListProcessor;

	public static enum Cloumns {
		goodsCode, GoodNumber, GoodsName, GoodsAttribute, Unit, Count;
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						String storeId = null;
						if (queryListProcessor != null) {
							storeId = Store.GoodsStoreId.toString();
//							storeId = queryListProcessor.storeList.getText();
						}
						ControllerPage page = (ControllerPage) rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														GoodsInventoryQueryListProcessor.class,
														GoodsInventoryQueryListRender.class),
												message.getCategoryId(),
												storeId));
						if (page != null) {
							queryListProcessor = (GoodsInventoryQueryListProcessor) page
									.getProcessor();
						}
					}
				});
		super.process(context);
	}

	/**
	 * 商品库存查询处理器
	 */
	public final static class GoodsInventoryQueryListProcessor extends
			PSIGoodsListPageProcessor {

		// 过滤仓库下拉列表
		public final static String ID_COMBOLIST_STORE = "ComboList_Store";
		// 商品数量
		public final static String ID_LABEL_GOODSINVENTORY_COUNT = "Label_GoodsInventory_Count";
		
		public final static String ID_viewInventory = "viewInventory";

		private Label countLabel;
//		private LWComboList storeList;
		//查库窗口
		protected InventoryViewWindow inventoryInfoWindow;
		
		@Override
		public void process(Situation situation) {
			super.process(situation);
//			storeList = this.createControl(ID_COMBOLIST_STORE,
//					LWComboList.class, JWT.NO);
//			StoreSource source = new StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
//			source.setShowAllStoreItem(true);
//			storeList.getList().setSource(source);
//			storeList.getList().setInput(null);
//			if (this.getArgument2() != null) {
//				storeList.setSelection((String) this.getArgument2());
//			} else {
//				if (CheckIsNull.isNotEmpty(source.getFirstStoreId())) {
//					storeList.setSelection(source.getFirstStoreId().toString());
//				}
//			}
//			storeList.addSelectionListener(new SelectionListener() {
//
//				public void widgetSelected(SelectionEvent e) {
//					table.render();
//
//				}
//			});
			countLabel = this.createControl(ID_LABEL_GOODSINVENTORY_COUNT,
					Label.class, JWT.NONE);
			//处理查库动作的客户端事件
			table.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "InventoryClient.handleActionPerformed");
			
			//初始化查库窗口
			inventoryInfoWindow = new InventoryViewWindow(this.table);
		}

		@Override
		protected Object[] getElements(Context context, String searchText,
				GUID categoryId, STableStatus tablestatus) {
			GetInventoryItemKey key = new GetInventoryItemKey(InventoryType.Goods, tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
			key.setGoodsCategoryId(categoryId);
			key.setSearchText(searchText);
			GUID storeId = Store.GoodsStoreId;
//			if (CheckIsNull.isNotEmpty(storeList.getText())) {
//				storeId = GUID.valueOf(storeList.getText());
//			}
//			if(storeId.equals(GUID.emptyID)) {
//				storeId = null;
//			}
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
//			
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
			if (Cloumns.Count.name().equals(sortColumn)) {
				return SortField.count;
			} else if (Cloumns.GoodNumber.name().equals(sortColumn)) {
				return SortField.number;
			} else if (Cloumns.GoodsAttribute.name().equals(sortColumn)) {
				return SortField.spec;
			} else if (Cloumns.GoodsName.name().equals(sortColumn)) {
				return SortField.name;
			} else if (Cloumns.Unit.name().equals(sortColumn)) {
				return SortField.unit;
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
			inventoryInfoWindow.refresh(GUID.valueOf(rowId), null,point,false);
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
			return "商品库存";
		}

	}

}
