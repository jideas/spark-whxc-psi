package com.spark.psi.order.browser.purchase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
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
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageProcessor;
import com.spark.psi.base.browser.material.MaterialCategorySelectionMsg;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectRender.SelectedPurchaseGoodsItemListRender;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectRender.StoreAndGoodsItemListRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.key.GetInventoryInfoListKey;

/**
 * ��ȫ��������ѡ��ɹ����ϵĽ��洦����
 * 
 */
public class PurchasingGoodsSelectProcessor extends MaterialCategoryFramePageProcessor {
	public static enum Columns {
		GoodsName(100, JWT.CENTER, "��������", true), GoodsProperties(70, JWT.CENTER, "���Ϲ��"), GoodsUnit(60, JWT.CENTER, "��λ"), StoreName(
				120, JWT.LEFT, "���ֿ�"), InventoryCount(80, JWT.RIGHT, "�������"), PurchasingCount(70, JWT.RIGHT, "�ɹ�������"), DeliveryingCount(
				70, JWT.RIGHT, "��������"), InventoryLimt(100, JWT.CENTER, "���������"), PurchaseCount(80, JWT.RIGHT, "���βɹ�");
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
	 * ѡ�����
	 */
	private Map<String, SelectedPurchaseItem> selectedItemList = new LinkedHashMap<String, SelectedPurchaseItem>();

	@Override
	public void process(final Situation context) {
		super.process(context);
		// �������Ϸ���仯��Ϣ��ˢ�½��棬��ѡ���ϲ��䲢����
		getContext().regMessageListener(MaterialCategorySelectionMsg.class, new MessageListener<MaterialCategorySelectionMsg>() {
			public void onMessage(Situation context, MaterialCategorySelectionMsg message,
					MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
				rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(StoreAndGoodsItemListProcessor.class,
						StoreAndGoodsItemListRender.class), message.getCategoryId(), selectedItemList));
			}
		});
		super.process(context);
	}

	/**
	 * 
	 * �ֿ�Ͳ�����Ŀ�б�����
	 */
	public final static class StoreAndGoodsItemListProcessor extends PSIGoodsListPageProcessor {

		public final static String ID_Area_Selected = "Area_Selected";
		public final static String ID_Label_SelectedCount = "Label_SelectedCount";
		public final static String ID_List_Store = "List_Store"; 
		public final static String ID_Button_ConfirmSelect = "Button_ConfirmSelect";
		public final static String ID_Button_CancelSelect = "Button_CancelSelect";

		private Composite selectedArea;
		private Composite selectedGoodsPage;
		private Label selectedCountLabel;
		private LWComboList storeList;

		/**
		 * �������ѡ�����б����
		 */
		private Map<String, SelectedPurchaseItem> selectedItemList = new LinkedHashMap<String, SelectedPurchaseItem>();

		@SuppressWarnings("unchecked")
		@Override
		public void init(Situation context) {
			super.init(context);
			this.selectedItemList = (Map<String, SelectedPurchaseItem>) this.getArgument2();
		}

		@Override
		public void process(Situation situation) {
			super.process(situation);
			//
			selectedArea = this.createControl(ID_Area_Selected, Composite.class);
			selectedCountLabel = this.createControl(ID_Label_SelectedCount, Label.class);
			storeList = this.createControl(ID_List_Store, LWComboList.class);

			// Ĭ��ѡ���һ���ֿ�
			StoreSource storeSource = new StoreSource(true);
			storeList.getList().setSource(storeSource);
			storeList.getList().setInput(null);
			if (storeSource.getFirstStoreId() != null) {
				storeList.setSelection(storeSource.getFirstStoreId().toString());
			} else {
				alert("ϵͳ��û�п��õĲֿ⣡", new Runnable() {
					public void run() {
						getContext().bubbleMessage(new MsgCancel());
					}
				});
			}
			storeList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					table.render();
				}
			});

			//
			updateCountLabel();

			// ȷ��ѡ��
			this.createControl(ID_Button_ConfirmSelect, Button.class).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selectedItemList.size() == 0) {
						alert("��ѡ��ɹ����ϣ�");
						return;
					}
					SelectedPurchaseItem[] returnValue = selectedItemList.values().toArray(new SelectedPurchaseItem[0]);
					getContext().bubbleMessage(new MsgResponse(true, returnValue));
				}
			});

			// ȡ��ѡ��
			this.createControl(ID_Button_CancelSelect, Button.class).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					getContext().bubbleMessage(new MsgCancel());
				}
			});

			// XXX����ʱ��������ʵ�֣�Ӧ�ó���MsgRequest��ϵ��
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
			// XXX�����ڱ��ؼ�ȱ�ݣ�Ŀǰ��Ҫÿ�ζ�ˢ���������
			menuWindow.addClientNotifyListener(new ClientNotifyListener() {
				public void notified(ClientNotifyEvent e) {
					selectedGoodsPage = area.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(
							SelectedPurchaseGoodsItemListProcessor.class, SelectedPurchaseGoodsItemListRender.class), selectedItemList));
					selectedGoodsPage.getContext().regMessageListener(SelectedPurchaseItem.class,
							new MessageListener<SelectedPurchaseItem>() {
								public void onMessage(Situation context, SelectedPurchaseItem message,
										MessageTransmitter<SelectedPurchaseItem> transmitter) {
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
		 * ��ȡָ���еı༭ֵ
		 */
		@Override
		public String getValue(Object element, String columnName) {
			return "";
		}

		@Override
		public Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus) {
			if (StringUtils.isEmpty(storeList.getText())) {
				return null;
			}
			GetMaterialsInfoListKey key = new GetMaterialsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
			key.setSearchText(searchText);
			key.setCateoryId(categoryId);
			key.setJointVenture(false);
			@SuppressWarnings("unchecked")
			ListEntity<MaterialsInfo> listEntity = context.find(ListEntity.class, key);
			List<MaterialsInfo> goodsList = listEntity.getItemList();
			List<Item> itemList = new ArrayList<Item>();
			for (MaterialsInfo goodsInfo : goodsList) {
				for (MaterialsItemData itemData : goodsInfo.getItems()) {
					Item item = new Item();
					item.goodsInfo = goodsInfo;
					item.itemData = itemData;
					GetInventoryInfoListKey getInventoryKey = new GetInventoryInfoListKey(new GUID[] { itemData.getId() },
							new GUID[] { GUID.valueOf(storeList.getText()) });
					List<InventoryInfo> inventoryDetailList = getContext().getList(InventoryInfo.class, getInventoryKey);
					if (inventoryDetailList != null && inventoryDetailList.size() > 0) {
						item.inventoryDetail = inventoryDetailList.get(0);
					}
					itemList.add(item);
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
				String storeId = storeList.getText();
				if (StringUtils.isEmpty(storeId)) {
					return;
				}
				double count = 0;
				try {
					count = Double.parseDouble(table.getEditValue(rowId, "PurchaseCount")[0]);
				} catch (Throwable t) {
				}
				if (count > 0) {
					String id = storeId + "-" + rowId;
					SelectedPurchaseItem item = null;
					if (selectedItemList.containsKey(id)) {
						item = selectedItemList.get(id);
						item.count = item.count + count;
					} else {
						MaterialsItemInfo itemInfo = getContext().find(MaterialsItemInfo.class, GUID.tryValueOf(rowId));
						item = new SelectedPurchaseItem();
						item.count = count;
						item.countDecimal = itemInfo.getBaseInfo().getCategory().getScale();
						item.id = id;
						item.storeId = GUID.valueOf(storeId);
						item.storeName = storeList.getDescription();
						item.goodsItem = itemInfo;
						selectedItemList.put(id, item);
					}
					table.updateCell(rowId, "PurchaseCount", "", " ", item.getScale());
					table.renderUpate();
					updateCountLabel();
				} else {
					alert("����д�ò��ϵı��βɹ�������");
				}
			}
		}

		public final static class Item {
			MaterialsInfo goodsInfo;
			MaterialsItemData itemData;
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
	 * ��ѡ�ɹ�������Ŀ�б�����
	 */
	public final static class SelectedPurchaseGoodsItemListProcessor extends BaseListPageProcessor<SelectedPurchaseItem> {

		public final static String ID_Label_Clear = "Label_Clear";

		private Map<String, SelectedPurchaseItem> selectedItemList = new LinkedHashMap<String, SelectedPurchaseItem>();

		@SuppressWarnings("unchecked")
		@Override
		public void init(final Situation situation) {
			super.init(situation);
			selectedItemList = (Map<String, SelectedPurchaseItem>) this.getArgument();
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
			return ((SelectedPurchaseItem) element).id;
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
			getContext().bubbleMessage(new SelectedPurchaseItem()); // ��SelectedPurchaseItem��Ϊ��Ϣ����ʾѡ����£���ɾ��������գ�
		}

	}

	/**
	 * ��ȫ��������ѡ��Ĳɹ���������
	 */
	public final static class SelectedPurchaseItem {
		String id;
		GUID storeId;
		String storeName;
		MaterialsItemInfo goodsItem;
		double count;
		int countDecimal;

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
