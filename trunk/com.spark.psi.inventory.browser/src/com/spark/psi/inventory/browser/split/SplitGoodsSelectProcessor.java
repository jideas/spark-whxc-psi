package com.spark.psi.inventory.browser.split;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseListPageProcessor;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.inventory.browser.split.GoodsSelectRender.SelectedGoodsItemListRender;
import com.spark.psi.inventory.browser.split.SplitGoodsSelectRender.StoreAndGoodsItemListRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.key.GetInventoryInfoListKey;

/**
 * 从全部材料中选择采购材料的界面处理器
 * 
 */
public class SplitGoodsSelectProcessor extends GoodsCategoryFramePageProcessor {
	public static enum Columns {
		GoodsName(100, JWT.CENTER, "商品名称", true), GoodsProperties(70, JWT.CENTER, "商品规格"), GoodsUnit(60, JWT.CENTER, "单位"),InventoryCount(80, JWT.RIGHT, "库存数量"),Count(80, JWT.RIGHT, "数量");
		private int width;
		private int align;
		private String title;
		private boolean grab;

		/**
		 * @param width
		 * @param align
		 * @param title
		 */
		private Columns(int width, int align, String title) {
			this(width, align, title, false);
		}

		/**
		 * @param width
		 * @param align
		 * @param title
		 */
		private Columns(int width, int align, String title, boolean grab) {
			this.width = width;
			this.align = align;
			this.title = title;
			this.grab = grab;
		}

		/**
		 * @return the width
		 */
		public int getWidth() {
			return width;
		}

		/**
		 * @return the align
		 */
		public int getAlign() {
			return align;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		public boolean isGrab() {
			return this.grab;
		}
	}

	/**
	 * 选择材料
	 */
	private Map<String, SelectedItem> selectedItemList = new LinkedHashMap<String, SelectedItem>();

	@Override
	public void process(final Situation context) {
		super.process(context);
		getContext().regMessageListener(GoodsCategorySelectionMsg.class, new MessageListener<GoodsCategorySelectionMsg>() {
			public void onMessage(Situation context, GoodsCategorySelectionMsg message,
					MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
				rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(StoreAndGoodsItemListProcessor.class,
						StoreAndGoodsItemListRender.class), message.getCategoryId(), selectedItemList));
			}
		});
		super.process(context);
	}

	/**
	 * 
	 * 仓库和材料条目列表处理器
	 */
	public final static class StoreAndGoodsItemListProcessor extends PSIGoodsListPageProcessor {

		public final static String ID_Area_Selected = "Area_Selected";
		public final static String ID_Label_SelectedCount = "Label_SelectedCount";
//		public final static String ID_List_Store = "List_Store"; 
		public final static String ID_Button_ConfirmSelect = "Button_ConfirmSelect";
		public final static String ID_Button_CancelSelect = "Button_CancelSelect";

		private Composite selectedArea;
		private Composite selectedGoodsPage;
		private Label selectedCountLabel;
//		private LWComboList storeList;

		/**
		 * 传入的已选材料列表对象
		 */
		private Map<String, SelectedItem> selectedItemList = new LinkedHashMap<String, SelectedItem>();

		@SuppressWarnings("unchecked")
		@Override
		public void init(Situation context) {
			super.init(context);
			this.selectedItemList = (Map<String, SelectedItem>) this.getArgument2();
		}

		@Override
		public void process(Situation situation) {
			super.process(situation);
			//
			selectedArea = this.createControl(ID_Area_Selected, Composite.class);
			selectedCountLabel = this.createControl(ID_Label_SelectedCount, Label.class);
//			storeList = this.createControl(ID_List_Store, LWComboList.class);

			// 默认选择第一个仓库
//			StoreSource storeSource = new StoreSource(true);
//			storeList.getList().setSource(storeSource);
//			storeList.getList().setInput(null);
//			if (storeSource.getFirstStoreId() != null) {
//				storeList.setSelection(storeSource.getFirstStoreId().toString());
//			} else {
//				alert("系统中没有可用的仓库！", new Runnable() {
//					public void run() {
//						getContext().bubbleMessage(new MsgCancel());
//					}
//				});
//			}
//			storeList.addSelectionListener(new SelectionListener() {
//				public void widgetSelected(SelectionEvent e) {
//					table.render();
//				}
//			});

			//
			updateCountLabel();

			// 确认选择
			this.createControl(ID_Button_ConfirmSelect, Button.class).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selectedItemList.size() == 0) {
						alert("请选择商品！");
						return;
					}
					SelectedItem[] returnValue = selectedItemList.values().toArray(new SelectedItem[0]);
					getContext().bubbleMessage(new MsgResponse(true, returnValue));
				}
			});

			// 取消选择
			this.createControl(ID_Button_CancelSelect, Button.class).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					getContext().bubbleMessage(new MsgCancel());
				}
			});

			// XXX：暂时放在这里实现，应该抽象到MsgRequest体系中
			SMenuWindow menuWindow = new SMenuWindow(null, selectedArea, Direction.Down);
			menuWindow.bindTargetControl(selectedArea);
			menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
			Composite windowArea = menuWindow.getContentArea();
			windowArea.setLayout(new GridLayout());
			final Composite area = new Composite(windowArea);
			GridData gd = new GridData();
			gd.widthHint = 560;
			gd.heightHint = 360;
			area.setLayoutData(gd);
			// XXX：由于表格控件缺陷，目前需要每次都刷新整个表格
			menuWindow.addClientNotifyListener(new ClientNotifyListener() {
				public void notified(ClientNotifyEvent e) {
					selectedGoodsPage = area.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(
							SelectedGoodsItemListProcessor.class, SelectedGoodsItemListRender.class), selectedItemList));
					selectedGoodsPage.getContext().regMessageListener(SelectedItem.class,
							new MessageListener<SelectedItem>() {
								public void onMessage(Situation context, SelectedItem message,
										MessageTransmitter<SelectedItem> transmitter) {
									updateCountLabel();
								}
							});
				}
			});
		}

		private void updateCountLabel() {
			selectedCountLabel.setText(String.valueOf(selectedItemList.size()));
		}

		/**
		 * 获取指定列的编辑值
		 */
		@Override
		public String getValue(Object element, String columnName) {
			return "";
		}

		@Override
		public SNameValue[] getExtraData(Object element) {
			Item item = (Item) element;
			return new SNameValue[]{new SNameValue(Columns.InventoryCount.name(),item.inventoryDetail.getCount()+"")};
		}

		@Override
		public Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus) {
//			if (StringUtils.isEmpty(storeList.getText())) {
//				return null;
//			}
			GetGoodsInfoListKey key = new GetGoodsInfoListKey(0, JWT.MAXIMUM, false);
			key.setSearchText(searchText);
			key.setCateoryId(categoryId);
			key.setJointVenture(false);
			@SuppressWarnings("unchecked")
			ListEntity<GoodsInfo> listEntity = context.find(ListEntity.class, key);
			List<GoodsInfo> goodsList = listEntity.getItemList();
			List<Item> itemList = new ArrayList<Item>();
			for (GoodsInfo goodsInfo : goodsList) {
				for (GoodsItemData itemData : goodsInfo.getItems()) {
					Item item = new Item();
					item.goodsInfo = goodsInfo;
					item.itemData = itemData;
					GetInventoryInfoListKey getInventoryKey = new GetInventoryInfoListKey(new GUID[] { itemData.getId() },
							new GUID[] { Store.GoodsStoreId });
					List<InventoryInfo> inventoryDetailList = getContext().getList(InventoryInfo.class, getInventoryKey);
					if (inventoryDetailList != null && inventoryDetailList.size() > 0&&inventoryDetailList.get(0).getCount()>0) {
						item.inventoryDetail = inventoryDetailList.get(0);
						itemList.add(item);
					}
				}
			}
			return itemList.toArray();
		}

		@Override
		public String getElementId(Object element) {
			return ((Item) element).itemData.getId().toString();
		}

		@Override
		public String[] getTableActionIds() {
			return new String[] { Action.Add.name() };
		}

		@Override
		public void actionPerformed(String rowId, String actionName, String actionValue) {
			if (Action.Add.name().equals(actionName)) {
				String storeId = Store.GoodsStoreId.toString();
//				String storeId = storeList.getText();
//				if (StringUtils.isEmpty(storeId)) {
//					return;
//				}
				double count = 0;
				try {
					count = Double.parseDouble(table.getEditValue(rowId, "Count")[0]);
				} catch (Throwable t) {
				}
				if (count > 0) {
					if(Double.valueOf(table.getExtraData(rowId, Columns.InventoryCount.name())[0])<count)
					{
						alert("拆分商品数量不能大于库存数量！");
						return;
					}
					String id = storeId + "-" + rowId;
					SelectedItem item = null;
					if (selectedItemList.containsKey(id)) {
						item = selectedItemList.get(id);
						item.count = item.count+count;
					} else {
						GoodsItemInfo itemInfo = getContext().find(GoodsItemInfo.class, GUID.tryValueOf(rowId));
						item = new SelectedItem();
						item.count = count;
						item.countDecimal = itemInfo.getBaseInfo().getCategory().getScale();
						item.id = id;
						item.storeId = GUID.valueOf(storeId);
						item.storeName = Store.GoodsStoreName;
						item.goodsItem = itemInfo;
						selectedItemList.put(id, item);
					}
					table.updateCell(rowId, "Count", "", " ", item.getScale());
					table.renderUpate();
					updateCountLabel();
				} else {
					alert("请填写商品数量！");
				}
			}
		}

		public final static class Item {
			GoodsInfo goodsInfo;
			GoodsItemData itemData;
			InventoryInfo inventoryDetail;
		}

		@Override
		protected String getExportFileTitle() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * 
	 * 已选采购材料条目列表处理器
	 */
	public final static class SelectedGoodsItemListProcessor extends BaseListPageProcessor<SelectedItem> {

		public final static String ID_Label_Clear = "Label_Clear";

		private Map<String, SelectedItem> selectedItemList = new LinkedHashMap<String, SelectedItem>();

		@SuppressWarnings("unchecked")
		@Override
		public void init(final Situation situation) {
			super.init(situation);
			selectedItemList = (Map<String, SelectedItem>) this.getArgument();
		}

		@Override
		public void process(final Situation situation) {
			super.process(situation);
			this.createControl(ID_Label_Clear, Label.class).addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent e) {
					selectedItemList.clear();
					table.render();
					notifyListPage();
				}
			});
		}

		@Override
		public Object[] getElements(Context context, STableStatus tablestatus) {
			return selectedItemList.values().toArray();
		}

		@Override
		public String getElementId(Object element) {
			return ((SelectedItem) element).id;
		}

		@Override
		public String[] getTableActionIds() {
			return new String[] { Action.Delete.name() };
		}

		@Override
		public void actionPerformed(String rowId, String actionName, String actionValue) {
			if (Action.Delete.name().equals(actionName)) {
				selectedItemList.remove(rowId);
				table.removeRow(rowId);
				table.renderUpate();
				notifyListPage();
			}
		}

		private void notifyListPage() {
			getContext().bubbleMessage(new SelectedItem()); // 用SelectedPurchaseItem作为消息来表示选择更新（被删除或者清空）
		}

	}

	/**
	 * 从全部材料中选择的采购材料数据
	 */
	public final static class SelectedItem {
		String id;
		GUID storeId;
		String storeName;
		GoodsItemInfo goodsItem;
		double count;
		int countDecimal;
		String spec;
		String unit;
		String name;
		String code;
		public String getCode() {
			return goodsItem.getBaseInfo().getCode();
		}

		public String getName() {
			return goodsItem.getBaseInfo().getName();
		}
		
		public String getSpec() {
			return goodsItem.getItemData().getSpec();
		}

		public String getUnit() {
			return goodsItem.getItemData().getUnit();
		}

		/**
		 * @return the storeId
		 */
		public GUID getStoreId() {
			return storeId;
		}

		/**
		 * @return the item
		 */
		public GUID getGoodsItemId() {
			return goodsItem.getItemData().getId();
		}

		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}

		/**
		 * @return the countDecimal
		 */
		public int getScale() {
			return countDecimal;
		}
	}

}
