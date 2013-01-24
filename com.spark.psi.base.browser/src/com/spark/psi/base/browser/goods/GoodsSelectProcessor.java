package com.spark.psi.base.browser.goods;

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
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsSelectRender.GoodsItemListRender;
import com.spark.psi.base.browser.goods.GoodsSelectRender.SelectedGoodsItemListRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.task.MarkGoodsItemUsedTask;
import com.spark.psi.publish.inventory.entity.InventoryItem;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey;

/**
 * 商品选择界面处理器
 * 
 */
public class GoodsSelectProcessor extends GoodsCategoryFramePageProcessor {

	private Map<String, GoodsItemInfo> selectedItemList = new LinkedHashMap<String, GoodsItemInfo>();

	private GUID storeId;

	private boolean onsaleOnly;

	private boolean enableAdd;
	
	private boolean isSingleLimit = false;
	@Override
	public void init(final Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			this.storeId = (GUID) this.getArgument();
		}
		this.onsaleOnly = (Boolean) this.getArgument2();
		
		this.enableAdd = (Boolean) this.getArgument3();
		
		this.isSingleLimit = this.getArgument4() == null ? false : (Boolean) this.getArgument4();
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						
						OpenGoodsItemListParameter parameter = new OpenGoodsItemListParameter();
						parameter.setSelectedItemList(selectedItemList);
						parameter.setStoreId(storeId);
						parameter.setOnsaleOnly(onsaleOnly);
						parameter.setEnableAdd(enableAdd);
						parameter.setSingleLimit(isSingleLimit);
						rightArea.showPage(  
								ControllerPage.NAME,
								new PageControllerInstance(new PageController(
										GoodsItemListProcessor.class,
										GoodsItemListRender.class), message
										.getCategoryId(),parameter));
						
//						message
//						.getCategoryId(), selectedItemList,
//						storeId, onsaleOnly
					} 
				});
		super.process(context);
	}

	/**
	 * 
	 * 商品条目列表处理器
	 */
	public final static class GoodsItemListProcessor extends
			PSIGoodsListPageProcessor {

		public final static String ID_Area_Selected = "Area_Selected";
		public final static String ID_Label_SelectedCount = "Label_SelectedCount";
		public final static String ID_Button_NewGoods = "Button_NewGoods";
		public final static String ID_Button_ConfirmSelect = "Button_ConfirmSelect";
		public final static String ID_Button_CancelSelect = "Button_CancelSelect";

		private Composite selectedArea;
		private Composite selectedGoodsPage;
		private Label selectedCountLabel;

		private Map<String, GoodsItemInfo> selectedItemList = new LinkedHashMap<String, GoodsItemInfo>();

		private GUID storeId;
		private boolean onsaleOnly;
		private boolean enableAdd;
		private boolean isSingleLimit;
		
		@Override
		public void init(Situation situation) {
			super.init(situation);
			OpenGoodsItemListParameter parameter = (OpenGoodsItemListParameter) getArgument2();
			if (null != parameter) {
				this.selectedItemList = parameter.getSelectedItemList();
				if (parameter.getStoreId() != null) {
					this.storeId = parameter.getStoreId();
				}
				this.onsaleOnly = parameter.isOnsaleOnly();
				this.enableAdd = parameter.isEnableAdd();
				this.isSingleLimit = parameter.isSingleLimit();
			}
		}

		@Override
		public void process(Situation situation) {
			super.process(situation);
			selectedArea = this
					.createControl(ID_Area_Selected, Composite.class);
			selectedCountLabel = this.createControl(ID_Label_SelectedCount,
					Label.class);

			updateCountLabel();

			//
			// table.addMouseDoubleClickListener(new MouseDoubleClickListener()
			// {
			// public void doubleClick(MouseEvent e) {
			// System.out.println();
			// }
			// });
			//
			Button button = this
				.createControl(ID_Button_NewGoods, Button.class);
			if (enableAdd) {
				button.setVisible(false);
//				button.addActionListener(new ActionListener() {
//					//
//					public void actionPerformed(ActionEvent e) {
//						final PageController pc = new PageController(
//								GoodsQuickAddProccessor.class, GoodsQuickAddRender.class);
////											PageControllerInstance newPci = new PageControllerInstance(pc, goodsId,
////													uiDatas, getCategoryId());
//						PageControllerInstance newPci = new PageControllerInstance(pc, null, null, getCategoryId());
//						WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
//						windowStyle.setSize(620, 350);
//						MsgRequest newGoodsWinRequest = new MsgRequest(newPci, "快速新增商品",
//								windowStyle);
//						newGoodsWinRequest.setResponseHandler(new ResponseHandler() {
//							
//							public void handle(Object returnValue, Object returnValue2,
//									Object returnValue3, Object returnValue4) {
//								table.render();
//							}
//						});
//						getContext().bubbleMessage(newGoodsWinRequest);
//					}
//				});
			} else {
				button.setVisible(false);
			}
			
			this.createControl(ID_Button_ConfirmSelect, Button.class)
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (selectedItemList.size() == 0) {
								alert("请选择商品！");
								return;
							}
							GoodsItemInfo[] result = selectedItemList.values()
									.toArray(new GoodsItemInfo[0]);
							for (GoodsItemInfo goodsItemInfo : result) { // 设置商品条目已使用
								getContext().handle(
										new MarkGoodsItemUsedTask(goodsItemInfo
												.getItemData().getId()));
							}
							getContext().bubbleMessage(
									new MsgResponse(true, result));
						}
					});

			this.createControl(ID_Button_CancelSelect, Button.class)
					.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							getContext().bubbleMessage(new MsgCancel());
						}
					});

			// XXX：暂时放在这里实现，应该抽象到MsgRequest体系中
			SMenuWindow menuWindow = new SMenuWindow(null, selectedArea,
					Direction.Down);
			menuWindow.bindTargetControl(selectedArea);
			menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW,
					"PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
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
					selectedGoodsPage = area.showPage(ControllerPage.NAME,
							new PageControllerInstance(new PageController(
									SelectedGoodsItemListProcessor.class,
									SelectedGoodsItemListRender.class),
									selectedItemList));
					selectedGoodsPage.getContext().regMessageListener(
							GoodsItemInfo.class,
							new MessageListener<GoodsItemInfo>() {
								public void onMessage(
										Situation context,
										GoodsItemInfo message,
										MessageTransmitter<GoodsItemInfo> transmitter) {
									updateCountLabel();
								}
							});
				}
			});
		}

		private void updateCountLabel() {
			selectedCountLabel.setText(String.valueOf(selectedItemList.size()));
		}

		@Override
		public Object[] getElements(Context context, String searchText,
				GUID categoryId, STableStatus tablestatus) {
			if (storeId == null) {
				GetGoodsInfoListKey key = new GetGoodsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
				key.setSearchText(searchText);
				if (onsaleOnly) {
					key.setStatus(GoodsStatus.ON_SALE);
					key.setSetPriceOnley(true);
				}
				key.setCateoryId(categoryId);
				@SuppressWarnings("unchecked")
				ListEntity<GoodsInfo> listEntity = (ListEntity<GoodsInfo>) context
						.find(ListEntity.class, key);
				List<GoodsInfo> goodsList = listEntity.getItemList();
				List<Item> itemList = new ArrayList<Item>();
				for (GoodsInfo goodsInfo : goodsList) {
					for (GoodsItemData itemData : goodsInfo.getItems()) {
						Item item = new Item();
						item.goodsInfo = goodsInfo;
						item.itemData = itemData;
						itemList.add(item);
					}
				}
				return itemList.toArray();
			} else {
				//
				GetInventoryItemKey key = new GetInventoryItemKey(InventoryType.Goods, 0,
						Integer.MAX_VALUE, true);
				key.setGoodsCategoryId(categoryId);
				key.setSearchText(searchText);
				key.setStoreId(storeId);
				List<InventoryItem> list = getContext().getList(
						InventoryItem.class, key);
				if (CheckIsNull.isNotEmpty(list)) {
					return list.toArray();
				}
				return null;
			}
		}

		@Override
		public String getElementId(Object element) {
			if (element instanceof Item) {
				return ((Item) element).itemData.getId().toString();
			} else {
				return ((InventoryItem) element).getStockId()
						.toString();
			}
		}

		@Override
		public String[] getTableActionIds() {
			return new String[] { Action.Add.name() };
		}

		@Override
		public void actionPerformed(String rowId, String actionName,
				String actionValue) {
			if (Action.Add.name().equals(actionName)) {
				if (isSingleLimit) { //如果只能选择一个
					selectedItemList.clear();
					GoodsItemInfo itemInfo = getContext().find(GoodsItemInfo.class,
							GUID.tryValueOf(rowId));
					selectedItemList.put(rowId, itemInfo);
				} else {
					if (selectedItemList.containsKey(rowId)) {
						// SecondsWindow.showMessage("商品已加入购物车", 1);
						return;
					}
					GoodsItemInfo itemInfo = getContext().find(GoodsItemInfo.class,
							GUID.tryValueOf(rowId));
					selectedItemList.put(rowId, itemInfo);
				}
				updateCountLabel();
			}
		}

		@Override
		protected String getExportFileTitle() {
			return null;
		}

	}

	/**
	 * 
	 * 已选商品条目列表处理器
	 */
	public final static class SelectedGoodsItemListProcessor extends
			BaseListPageProcessor<Item> {

		public final static String ID_Label_Clear = "Label_Clear";

		private Map<String, GoodsItemInfo> selectedItemList = new LinkedHashMap<String, GoodsItemInfo>();

		@SuppressWarnings("unchecked")
		@Override
		public void init(final Situation situation) {
			super.init(situation);
			// situation.regMessageListener(GoodsItemInfo.class,
			// new MessageListener<GoodsItemInfo>() {
			// public void onMessage(Situation context,
			// GoodsItemInfo item,
			// MessageTransmitter<GoodsItemInfo> transmitter) {
			// table.addRow(item);
			// table.renderUpate();
			// }
			// }).setListenSubMessage(true);
			selectedItemList = (Map<String, GoodsItemInfo>) this.getArgument();
		}

		public void process(final Situation situation) {
			super.process(situation);
			this.createControl(ID_Label_Clear, Label.class)
					.addMouseClickListener(new MouseClickListener() {
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
			return ((GoodsItemInfo) element).getItemData().getId().toString();
		}

		@Override
		public String[] getTableActionIds() {
			return new String[] { Action.Delete.name() };
		}

		@Override
		public void actionPerformed(String rowId, String actionName,
				String actionValue) {
			if (Action.Delete.name().equals(actionName)) {
				selectedItemList.remove(rowId);
				table.removeRow(rowId);
				table.renderUpate();
				notifyListPage();
			}
		}

		private void notifyListPage() {
			getContext().bubbleMessage(new GoodsItemInfo()); // 用GoodsItemInfo作为消息来表示选择更新（被删除或者清空）
		}

	}

	final static class Item {
		GoodsInfo goodsInfo;
		GoodsItemData itemData;
	}
	
	protected final class OpenGoodsItemListParameter {
		private Map<String, GoodsItemInfo> selectedItemList;
		
		private GUID storeId;

		private boolean onsaleOnly;

		private boolean enableAdd;
		
		private boolean isSingleLimit;

		public Map<String, GoodsItemInfo> getSelectedItemList() {
			return selectedItemList;
		}

		public void setSelectedItemList(Map<String, GoodsItemInfo> selectedItemList) {
			this.selectedItemList = selectedItemList;
		}

		public GUID getStoreId() {
			return storeId;
		}

		public void setStoreId(GUID storeId) {
			this.storeId = storeId;
		}

		public boolean isOnsaleOnly() {
			return onsaleOnly;
		}

		public void setOnsaleOnly(boolean onsaleOnly) {
			this.onsaleOnly = onsaleOnly;
		}

		public boolean isEnableAdd() {
			return enableAdd;
		}

		public void setEnableAdd(boolean enableAdd) {
			this.enableAdd = enableAdd;
		}

		public boolean isSingleLimit() {
			return isSingleLimit;
		}

		public void setSingleLimit(boolean isSingleLimit) {
			this.isSingleLimit = isSingleLimit;
		}
		
		
	}

}
