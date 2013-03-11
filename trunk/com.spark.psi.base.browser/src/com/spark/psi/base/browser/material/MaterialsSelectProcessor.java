package com.spark.psi.base.browser.material;

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
import com.spark.psi.base.browser.material.MaterialsSelectRender.MaterialItemListRender;
import com.spark.psi.base.browser.material.MaterialsSelectRender.SelectedMaterialItemListRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey;
import com.spark.psi.publish.base.materials.task.MarkMaterialsItemUsedTask;
import com.spark.psi.publish.inventory.entity.InventoryItem;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey;

public class MaterialsSelectProcessor extends MaterialCategoryFramePageProcessor {
	private Map<String, MaterialsItemInfo> selectedItemList = new LinkedHashMap<String, MaterialsItemInfo>();

	private GUID storeId;

	private boolean onsaleOnly;

	private boolean isSingleLimit = false;
	private Boolean jointVenture;

	@Override
	public void init(final Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			MaterialsSelectParameters parameters = (MaterialsSelectParameters) this.getArgument();
			this.onsaleOnly = parameters.isOnsaleOnly();
			this.isSingleLimit = parameters.isSingleLimit();
			this.jointVenture = parameters.getJointVenture();
			this.storeId = parameters.getStoreId();
		}
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(MaterialCategorySelectionMsg.class, new MessageListener<MaterialCategorySelectionMsg>() {
			public void onMessage(Situation context, MaterialCategorySelectionMsg message,
					MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {

				OpenGoodsItemListParameter parameter = new OpenGoodsItemListParameter();
				parameter.setSelectedItemList(selectedItemList);
				parameter.setStoreId(storeId);
				parameter.setOnsaleOnly(onsaleOnly);
				parameter.setSingleLimit(isSingleLimit);
				parameter.setJointVenture(jointVenture);
				rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(MaterialItemListProcessor.class,
						MaterialItemListRender.class), message.getCategoryId(), parameter));
			}
		});
		super.process(context);
	}

	/**
	 * 
	 * 商品条目列表处理器
	 */
	public final static class MaterialItemListProcessor extends PSIGoodsListPageProcessor {

		public final static String ID_Area_Selected = "Area_Selected";
		public final static String ID_Label_SelectedCount = "Label_SelectedCount";
		public final static String ID_Button_NewGoods = "Button_NewGoods";
		public final static String ID_Button_ConfirmSelect = "Button_ConfirmSelect";
		public final static String ID_Button_CancelSelect = "Button_CancelSelect";

		private Composite selectedArea;
		private Composite selectedGoodsPage;
		private Label selectedCountLabel;

		private Map<String, MaterialsItemInfo> selectedItemList = new LinkedHashMap<String, MaterialsItemInfo>();

		private GUID storeId;
		private boolean onsaleOnly = true;
		private boolean isSingleLimit;
		private Boolean jointVenture;

		@Override
		public void init(Situation situation) {
			super.init(situation);
			OpenGoodsItemListParameter parameter = (OpenGoodsItemListParameter) getArgument2();
			if (null != parameter) {
				this.selectedItemList = parameter.getSelectedItemList();
				if (parameter.getStoreId() != null) {
					this.storeId = parameter.getStoreId();
				}
				//this.onsaleOnly = parameter.isOnsaleOnly();
				this.isSingleLimit = parameter.isSingleLimit();
				this.jointVenture = parameter.getJointVenture();
			}
		}

		@Override
		public void process(Situation situation) {
			super.process(situation);
			selectedArea = this.createControl(ID_Area_Selected, Composite.class);
			selectedCountLabel = this.createControl(ID_Label_SelectedCount, Label.class);

			updateCountLabel();

			this.createControl(ID_Button_ConfirmSelect, Button.class).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selectedItemList.size() == 0) {
						alert("请选择材料！");
						return;
					}
					MaterialsItemInfo[] result = selectedItemList.values().toArray(new MaterialsItemInfo[0]);
					for (MaterialsItemInfo goodsItemInfo : result) { // 设置商品条目已使用
						getContext().handle(new MarkMaterialsItemUsedTask(goodsItemInfo.getItemData().getId()));
					}
					getContext().bubbleMessage(new MsgResponse(true, result));
				}
			});

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
					try {
					selectedGoodsPage = area.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(
							SelectedMaterialItemListProcessor.class, SelectedMaterialItemListRender.class), selectedItemList));
					
					selectedGoodsPage.getContext().regMessageListener(MaterialsItemInfo.class, new MessageListener<MaterialsItemInfo>() {
						public void onMessage(Situation context, MaterialsItemInfo message,
								MessageTransmitter<MaterialsItemInfo> transmitter) {
							updateCountLabel();
						}
					});
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}

		private void updateCountLabel() {
			selectedCountLabel.setText(String.valueOf(selectedItemList.size()));
		}

		@Override
		public Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus) {
			if (storeId == null) {
				GetMaterialsInfoListKey key = new GetMaterialsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
				//key = new GetGoodsInfoListKey();
				key.setJointVenture(this.jointVenture);
				key.setSearchText(searchText);
				if (onsaleOnly) {
					key.setStatus(MaterialsStatus.ON_SALE);
					key.setSetPriceOnley(true);
				}
				key.setCateoryId(categoryId);
				@SuppressWarnings("unchecked")
				ListEntity<MaterialsInfo> listEntity = (ListEntity<MaterialsInfo>) context.find(ListEntity.class, key);
				List<MaterialsInfo> goodsList = listEntity.getItemList();
				List<Item> itemList = new ArrayList<Item>();
				for (MaterialsInfo goodsInfo : goodsList) {
					for (MaterialsItemData itemData : goodsInfo.getItems()) {
						Item item = new Item();
						item.goodsInfo = goodsInfo;
						item.itemData = itemData;
						itemList.add(item);
					}
				}
				return itemList.toArray();
			} else {
				// 库存信息
				GetInventoryItemKey key = new GetInventoryItemKey(InventoryType.Materials, 0, Integer.MAX_VALUE, true);
				key.setGoodsCategoryId(categoryId);
				key.setSearchText(searchText);
				key.setStoreId(storeId);
				List<InventoryItem> list = getContext().getList(InventoryItem.class, key);
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
				// 库存信息
				return ((InventoryItem) element).getStockId().toString();
			}
		}

		@Override
		public String[] getTableActionIds() {
			return new String[] { Action.Add.name() };
		}

		@Override
		public void actionPerformed(String rowId, String actionName, String actionValue) {
			if (Action.Add.name().equals(actionName)) {
				if (isSingleLimit) { // 如果只能选择一个
					selectedItemList.clear();
					MaterialsItemInfo itemInfo = getContext().find(MaterialsItemInfo.class, GUID.tryValueOf(rowId));
					selectedItemList.put(rowId, itemInfo);
				} else {
					if (selectedItemList.containsKey(rowId)) {
						// SecondsWindow.showMessage("商品已加入购物车", 1);
						return;
					}
					MaterialsItemInfo itemInfo = getContext().find(MaterialsItemInfo.class, GUID.tryValueOf(rowId));
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
	public final static class SelectedMaterialItemListProcessor extends BaseListPageProcessor<Item> {

		public final static String ID_Label_Clear = "Label_Clear";

		private Map<String, MaterialsItemInfo> selectedItemList = new LinkedHashMap<String, MaterialsItemInfo>();

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
			selectedItemList = (Map<String, MaterialsItemInfo>) this.getArgument();
		}

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
			return ((MaterialsItemInfo) element).getItemData().getId().toString();
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
			getContext().bubbleMessage(new MaterialsItemInfo()); // 用GoodsItemInfo作为消息来表示选择更新（被删除或者清空）
		}

	}

	final static class Item {
		MaterialsInfo goodsInfo;
		MaterialsItemData itemData;
	}

	protected final class OpenGoodsItemListParameter {
		private Map<String, MaterialsItemInfo> selectedItemList;

		private GUID storeId;

		private boolean onsaleOnly;

		private boolean isSingleLimit;
		private Boolean jointVenture;

		public Map<String, MaterialsItemInfo> getSelectedItemList() {
			return selectedItemList;
		}

		public void setSelectedItemList(Map<String, MaterialsItemInfo> selectedItemList) {
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

		public boolean isSingleLimit() {
			return isSingleLimit;
		}

		public void setSingleLimit(boolean isSingleLimit) {
			this.isSingleLimit = isSingleLimit;
		}

		public Boolean getJointVenture() {
			return jointVenture;
		}

		public void setJointVenture(Boolean jointVenture) {
			this.jointVenture = jointVenture;
		}

	}

}
