/**
 * 
 */
package com.spark.psi.order.browser.distribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.StableUtil;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.order.browser.internal.OrderImages;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask.DistributionGoodsItem;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask.DistributionItem;

/**
 * 销售配货单明细处理器
 * 
 */
public class DistributeSalesOrderProcessor extends BaseFormPageProcessor implements IDataProcessPrompt,
		SelectionListener {

	public final static String ID_Label_StoreInfo = "StoreInfo";
	public final static String ID_Text_Date = "Text_Date";
	public final static String ID_Button_ResolveAll = "Button_ResolveAll";
	public final static String ID_Button_Reset = "Button_Reset";
	public final static String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Table_StoreList = "Table_StoreList";
	public final static String ID_Table_GoodsList = "Table_GoodsList";

	private Label storeInfoLabel;
	private DatePicker datePicker;
	private Label resolveAllButton;
	private Button resetButton;
	private Button confirmButton;
	private STable storeTable;
	private SEditTable goodsTable;

	SalesOrderInfo orderInfo;
	private StoreItem[] stores;

	private DistributeInfoWindow distributeInfoWindow;

	//
	private String currentStoreId;
	private Map<String, SalesOrderGoodsItem> salesOrderGoodsItems = new HashMap<String, SalesOrderGoodsItem>();
	private Map<String, DistributionItem> storeDistributeResult = new HashMap<String, DistributionItem>();
	private Map<String, Map<String, Double>> goodsDistributeResult = new HashMap<String, Map<String, Double>>();
	private Map<String, Double> goodsInventorys = new HashMap<String, Double>();

	@SuppressWarnings("unchecked")
	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() instanceof String) {
			this.orderInfo = getContext().get(SalesOrderInfo.class, GUID.valueOf((String) this.getArgument()));
		} else {
			this.orderInfo = (SalesOrderInfo) this.getArgument();
		}
		for (SalesOrderGoodsItem item : orderInfo.getSalesOrderGoodsItems()) {
			salesOrderGoodsItems.put(item.getId().toString(), item);
		}
		//
		GetStoreListKey storeKey = new GetStoreListKey(true, StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		List<StoreItem> storeList = context.find(ListEntity.class, storeKey).getItemList();
		stores = storeList.toArray(new StoreItem[0]);

		for (StoreItem storeItem : stores) {
			for (SalesOrderGoodsItem goodsItem : orderInfo.getSalesOrderGoodsItems()) {
				GetAvailableCountKey key = new GetAvailableCountKey(storeItem.getId(), goodsItem.getGoodsItemId());
				Double v = context.find(Double.class, key);
				if (v != null) {
					if (v < 0) {
						v = new Double(0);
					}
					goodsInventorys.put(storeItem.getId() + "-" + goodsItem.getGoodsItemId(), v);
				}
			}
		}
	}

	@Override
	public void process(Situation context) {
		storeInfoLabel = this.createControl(ID_Label_StoreInfo, Label.class);
		// 此处实现用户对当前配货单的独占状态，用户获得一个配货单的占用时间只有20秒，页面打开后会启动一个js的定时循环，每10秒像服务器端发送一次消息，用于继续占用此配货单。
		// 此处增加客户端定时扫描任务，以保持当前用户对当前配货单处于独占状态 周利均
		storeInfoLabel.addClientEventHandler(JWT.CLIENT_EVENT_INIT, "DistributeSalesOrder.ResetDistributeSalesOrder");
		storeInfoLabel.addClientNotifyListener(new ClientNotifyListener() {
			// 捕获界面发送过来的连续独占此配货单的消息 平均10秒触发一次
			public void notified(ClientNotifyEvent e) {
				// 调用服务 维持对当前配货单的占用状态 如果20秒后不调用此服务，当前配货单将释放。
				getContext().handle(new SalesOrderDistributeTask(orderInfo.getId()),
						SalesOrderDistributeTask.Method.Reset);
			}
		});
		datePicker = this.createControl(ID_Text_Date, DatePicker.class);
		resolveAllButton = this.createControl(ID_Button_ResolveAll, Label.class);
		resolveAllButton.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				if (currentStoreId != null) {
					String[] salesGoodsItemIds = goodsTable.getAllRowId();
					for (int i = 0; i < salesGoodsItemIds.length; i++) {
						double notAllocateCount = getItemAllocatedCountNotInStore(salesGoodsItemIds[i], currentStoreId);
						int decimal = Integer.parseInt(goodsTable.getExtraData(salesGoodsItemIds[i], "Decimal")[0]);
						goodsTable.updateCell(salesGoodsItemIds[i], "Allocate", String.valueOf(notAllocateCount),
								String.valueOf(notAllocateCount), decimal);
						goodsTable.updateCell(salesGoodsItemIds[i], "UnAllocate", null, "0", decimal);
					}
					markDataChanged();
					goodsTable.renderUpate();
				} else {
					alert("请选择仓库！");
				}
			}
		});
		resetButton = this.createControl(ID_Button_Reset, Button.class);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodsDistributeResult.clear();
				storeDistributeResult.clear();
				currentStoreId = null;
				storeTable.render();
				goodsTable.render();
				datePicker.setEnabled(false);
				datePicker.setDate(null);
				updateStoreInfoLabel();
			}
		});
		confirmButton = this.createControl(ID_Button_Confirm, Button.class);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
		storeTable = this.createControl(ID_Table_StoreList, STable.class);
		goodsTable = this.createControl(ID_Table_GoodsList, SEditTable.class);

		storeTable.setContentProvider(new SContentProvider() {
			public boolean isSelected(Object element) {
				return getElementId(element).equals(currentStoreId);
			}

			public boolean isSelectable(Object element) {
				return true;
			}

			public Object[] getElements(Context context, STableStatus tablestatus) {
				return stores;
			}

			public String getElementId(Object element) {
				StoreItem item = (StoreItem) element;
				return item.getId().toString();
			}
		});
		storeTable.setLabelProvider(new SLabelProvider() {
			public String getToolTipText(Object element, int columnIndex) {
				StoreItem item = (StoreItem) element;
				return item.getAddress();
			}

			public String getText(Object element, int columnIndex) {
				StoreItem item = (StoreItem) element;
				ImageDescriptor image = OrderImages.getImage("images/saas_products_cart.png");
				String[] salesGoodsItemIds = goodsTable.getAllRowId();
				if (salesGoodsItemIds != null) {
					for (int i = 0; i < salesGoodsItemIds.length; i++) {
						if (getItemAllocatedCountInStore(salesGoodsItemIds[i], item.getId().toString()) > 0) {
							return StableUtil.toImg(image.getDNAURI(), "已分配材料", 15) + item.getName();
						}
					}
				}
				return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + item.getName();
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				StoreItem item = (StoreItem) element;
				if (item.getId().toString().equals(currentStoreId)) {
					return new Color(0xC3DFE9);
				}
				return null;
			}
		});
		storeTable.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				JSONObject customSelectionInfo = storeTable.getClientObject("customSelectionInfo");
				String selectingId = null;
				try {
					selectingId = customSelectionInfo.getString("selecting");
				} catch (JSONException ex) {
				}
				goodsTable.removeSelectionListener(DistributeSalesOrderProcessor.this); // 移除用于提示没有选择仓库的监听器
				if (currentStoreId != null && currentStoreId.equals(selectingId)) {
					return;
				}
				if (currentStoreId == null) {
					currentStoreId = selectingId;
					if (!currentStoreId.equals(orderInfo.getPartnerInfo().getId().toString())) {
						datePicker.setEnabled(true);
						datePicker.setDate(new Date(orderInfo.getDeliveryDate()));
					}
					goodsTable.render();
					storeTable.updateRow(getStoreItem(currentStoreId));
					storeTable.renderUpate();
					updateStoreInfoLabel();
					return;
				} else {
					//
					if (!saveCurrentStoreStatus(selectingId)) {
						return;
					}
				}
			}
		});
		// storeTable.addSelectionListener(new SelectionListener() {
		//
		// });
		storeTable.addClientEventHandler(STable.CLIENT_EVENT_ROWCLICK, "DistributeSalesOrder.handleStoreSelection");
		storeTable.render();

		goodsTable.setContentProvider(new SEditContentProvider() {
			public boolean isSelected(Object element) {
				return false;
			}

			public boolean isSelectable(Object element) {
				return true;
			}

			public Object[] getElements(Context context, STableStatus tablestatus) {
				return orderInfo.getSalesOrderGoodsItems();
			}

			public String getElementId(Object element) {
				return ((SalesOrderGoodsItem) element).getId().toString();
			}

			public String getValue(Object element, String columnName) {
				if (columnName.equals("Allocate")) {
					if (currentStoreId != null) {
						double allocateCount = getItemAllocatedCountInStore((SalesOrderGoodsItem) element,
								currentStoreId);
						if (allocateCount > 0) {
							return String.valueOf(allocateCount);
						}
						return "";
					}
				}
				return null;
			}

			public String[] getActionIds(Object element) {
				return null;
			}

			public Object getNewElement() {
				return null;
			}

			public SNameValue[] getExtraData(Object element) {
				SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
				if (currentStoreId != null) {
					return new SNameValue[] {
							new SNameValue("Allocating", DoubleUtil.getRoundStrWithOutQfw(item.getCount()
									- getItemAllocatedCount(item) + getItemAllocatedCountInStore(item, currentStoreId),
									item.getScale())), new SNameValue("Decimal", String.valueOf(item.getScale())) };
				}
				return null;
			}
		});
		goodsTable.setLabelProvider(new SNumberLabelProvider() {
			public String getToolTipText(Object element, int columnIndex) {
				if (columnIndex == 3) {
					return "点击查看该材料配货情况";
				}
				return null;
			}

			public String getText(Object element, int columnIndex) {
				SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
				switch (columnIndex) {
				case 0:
					return item.getName();
				case 1:
					return item.getSpec();
				case 2:
					return item.getUnit();
				case 3:
					return StableUtil.toLink(new SNameValue("View", null), null, null, DoubleUtil.getRoundStr(item
							.getCount()));
				case 4:
					return String.valueOf(item.getCount() - getItemAllocatedCount(item));
				case 5:
					if (currentStoreId != null) {
						Double v = goodsInventorys.get(currentStoreId + "-" + item.getGoodsItemId());
						if (v != null) {
							return DoubleUtil.getRoundStr(v);
						}
					}
					return "--";
				case 6:
					double count = getItemAllocatedCountInStore(item, currentStoreId);
					if (count > 0) {
						return DoubleUtil.getRoundStr(count);
					}
					return "";
				default:
					return "";
				}
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				return null;
			}

			public int getDecimal(Object element, int columnIndex) {
				switch (columnIndex) {
				case 4:
				case 5:
				case 6:
					SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
					return item.getScale();
				default:
					return -1;
				}
			}
		});
		goodsTable.addSelectionListener(this);
		goodsTable.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (actionName.equals("View")) {
					if (currentStoreId == null) {
						alert("请选择仓库");
						return;
					}
					SalesOrderGoodsItem salesOrderGoodsItem = salesOrderGoodsItems.get(rowId);
					DistributeInfoWindow.Item[] items = new DistributeInfoWindow.Item[stores.length];
					Map<String, Double> allStoreResult = goodsDistributeResult.get(salesOrderGoodsItem.getId()
							.toString());
					double unAllocateCount = salesOrderGoodsItem.getCount();
					for (int i = 0; i < items.length; i++) {
						Double v = goodsInventorys.get(stores[i].getId() + "-" + salesOrderGoodsItem.getGoodsItemId());
						String availableCount = "--";
						if (v != null) {
							availableCount = String.valueOf(v);
						}
						double distributeCount = 0;
						if (allStoreResult != null) {
							v = allStoreResult.get(stores[i].getId().toString());
							if (v != null) {
								distributeCount = v.doubleValue();
							}
						}
						//
						if (currentStoreId.equals(stores[i].getId().toString())) {
							String allocate = goodsTable.getEditValue(rowId, "Allocate")[0];
							if (!StringUtils.isEmpty(allocate)) {
								distributeCount += Double.parseDouble(allocate);
							}
						}
						//
						items[i] = new DistributeInfoWindow.Item(stores[i].getId(), stores[i].getName(),
								availableCount, String.valueOf(distributeCount), salesOrderGoodsItem.getScale());
						unAllocateCount -= distributeCount;
					}
					//
					String[] locationInfo = actionValue.split(":");
					distributeInfoWindow.refresh(salesOrderGoodsItem.getName() + "：" + salesOrderGoodsItem.getSpec()
							+ salesOrderGoodsItem.getUnit(), DoubleUtil.getRoundStr(unAllocateCount,
							salesOrderGoodsItem.getScale()), items, new Point(Integer.parseInt(locationInfo[0]),
							Integer.parseInt(locationInfo[1])));
				}
			}
		});
		// 处理查询分配情况动作的客户端事件
		goodsTable.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "DistributeSalesOrder.handleActionPerformed");

		//
		goodsTable.render();
		//
		distributeInfoWindow = new DistributeInfoWindow(goodsTable);
	}

	public void widgetSelected(SelectionEvent e) {
		if (currentStoreId == null) {
			alert("请选择仓库");
		}
	}

	private boolean saveCurrentStoreStatus(String selectingId) {
		// 先检查之前选择仓库的分配情况并记录
		boolean hasAllocateInfo = false;
		String[] salesGoodsItemIds = goodsTable.getAllRowId();
		// 校验分配数量是否超出剩余分配数量和库存数量
		for (int i = 0; i < salesGoodsItemIds.length; i++) {
			double allocating = Double.parseDouble(goodsTable.getExtraData(salesGoodsItemIds[i], "Allocating")[0]);
			double allocate = 0;
			try {
				allocate = Double.parseDouble(goodsTable.getEditValue(salesGoodsItemIds[i], "Allocate")[0]);
			} catch (Throwable t) {
				continue;
			}
			if (allocate < 0) {
				alert("分配数量必须大于0！");
				return false;
			}
			if (allocate > allocating) {
				alert("分配数量超过需要分配的剩余数量！");
				return false;
			}
			// TODO：校验库存

			// 标识有分配信息
			if (allocate > 0) {
				hasAllocateInfo = true;
			}
		}
		// 如果有分配信息，则校验日期
		if (hasAllocateInfo) {
			if (!currentStoreId.equals(orderInfo.getPartnerInfo().getId().toString())) { // 直供仓库不检查日期
				if (datePicker.getDate() == null) {
					alert("请填写当前仓库发货日期！");
					return false;
				}
				long checkoutDate = datePicker.getDate().getTime();
				if (DateUtil.getDayStartTime(checkoutDate) > DateUtil.getDayStartTime(orderInfo.getDeliveryDate())) {
					alert("本库发货日期不能大于交货日期！");
					return false;
				}
			}
		}

		// 分配数据正确，记录到结果中
		List<DistributionGoodsItem> distributionGoodsItemList = new ArrayList<DistributionGoodsItem>();
		for (int i = 0; i < salesGoodsItemIds.length; i++) {
			double allocate = 0;
			try {
				allocate = Double.parseDouble(goodsTable.getEditValue(salesGoodsItemIds[i], "Allocate")[0]);
			} catch (Throwable t) {
				continue;
			}
			//
			distributionGoodsItemList.add(new DistributionGoodsItem(GUID.valueOf(salesGoodsItemIds[i]), allocate));
			//
			Map<String, Double> allStoreResult = goodsDistributeResult.get(salesGoodsItemIds[i]);
			if (allStoreResult == null) {
				allStoreResult = new HashMap<String, Double>();
				goodsDistributeResult.put(salesGoodsItemIds[i], allStoreResult);
			}
			allStoreResult.put(currentStoreId, allocate);
		}

		//
		storeDistributeResult.put(currentStoreId, new DistributionItem(datePicker.getDate() == null ? new Date()
				.getTime() : datePicker.getDate().getTime(), GUID.valueOf(currentStoreId), distributionGoodsItemList
				.toArray(new DistributionGoodsItem[0])));

		//
		String lastStoreId = currentStoreId;

		// 切换到当前选择仓库，更改日期，刷新表格
		if (!currentStoreId.equals(selectingId)) {
			currentStoreId = selectingId;
			if (currentStoreId.equals(orderInfo.getPartnerInfo().getId().toString())) {
				datePicker.setDate(null);
				datePicker.setEnabled(false);
			} else {
				DistributionItem distributionItem = storeDistributeResult.get(currentStoreId);
				datePicker.setEnabled(true);
				if (distributionItem != null) {
					datePicker.setDate(new Date(distributionItem.getDeliverDate()));
				} else {
					datePicker.setDate(new Date(orderInfo.getDeliveryDate()));
				}
			}
			goodsTable.render();
			updateStoreInfoLabel();
		}

		// 更新仓库标签列表标签
		storeTable.updateRow(getStoreItem(lastStoreId));
		storeTable.updateRow(getStoreItem(currentStoreId));
		storeTable.renderUpate();

		//
		return true;
	}

	private double getItemAllocatedCount(SalesOrderGoodsItem item) {
		double allAllocatedCount = 0;
		Map<String, Double> allStoreResult = goodsDistributeResult.get(item.getId().toString());
		if (allStoreResult != null) {
			Iterator<Double> valueIt = allStoreResult.values().iterator();
			while (valueIt.hasNext()) {
				allAllocatedCount += valueIt.next();
			}
		}
		return allAllocatedCount;
	}

	private double getItemAllocatedCountInStore(SalesOrderGoodsItem item, String storeId) {
		return getItemAllocatedCountInStore(item.getId().toString(), storeId);
	}

	private double getItemAllocatedCountInStore(String itemId, String storeId) {
		if (storeId != null) {
			Map<String, Double> result = goodsDistributeResult.get(itemId);
			if (result != null) {
				Double allocateCount = result.get(storeId);
				if (allocateCount != null) {
					return allocateCount;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}

	private double getItemAllocatedCountNotInStore(String itemId, String storeId) {
		SalesOrderGoodsItem[] items = orderInfo.getSalesOrderGoodsItems();
		SalesOrderGoodsItem item = null;
		for (SalesOrderGoodsItem i : items) {
			if (i.getId().toString().equals(itemId)) {
				item = i;
				break;
			}
		}
		if (item == null) {
			throw new IllegalStateException();
		}
		double allocatedCount = 0;
		Map<String, Double> allStoreResult = goodsDistributeResult.get(itemId);
		if (allStoreResult != null) {
			Iterator<String> storeIt = allStoreResult.keySet().iterator();
			while (storeIt.hasNext()) {
				String key = storeIt.next();
				if (key.equals(storeId)) {
					continue;
				}
				allocatedCount += allStoreResult.get(key);
			}
		}
		return item.getCount() - allocatedCount;
	}

	private void updateStoreInfoLabel() {
		String storeName = "";
		StoreItem item = getStoreItem(currentStoreId);
		if (item != null) {
			storeName = item.getName();
		}
		storeInfoLabel.setText("选择仓库：" + storeName);
	}

	private StoreItem getStoreItem(String id) {
		for (StoreItem item : stores) {
			if (item.getId().toString().equals(id)) {
				return item;
			}
		}
		return null;
	}

	public void postDisposed(Situation context) {
		super.postDisposed(context);
		getContext().handle(new SalesOrderDistributeTask(orderInfo.getId()), SalesOrderDistributeTask.Method.Cancel);
	}

	public boolean processData() {
		//
		if (currentStoreId != null && !saveCurrentStoreStatus(currentStoreId)) {
			return false;
		}

		for (SalesOrderGoodsItem item : orderInfo.getSalesOrderGoodsItems()) {
			if (item.getCount() != getItemAllocatedCount(item)) {
				alert("该订单材料未全部分配！");
				return false;
			}
		}

		//
		List<DistributionItem> distributionItemList = new ArrayList<DistributionItem>();
		Iterator<String> it = storeDistributeResult.keySet().iterator();
		while (it.hasNext()) {
			String storeId = it.next();
			DistributionItem item = storeDistributeResult.get(storeId);
			List<DistributionGoodsItem> distributionGoodsItemList = new ArrayList<DistributionGoodsItem>();
			if (item.getItems() != null) {
				for (DistributionGoodsItem distributionGoodsItem : item.getItems()) {
					if (distributionGoodsItem.getCount() > 0) {
						distributionGoodsItemList.add(distributionGoodsItem);
					}
				}
			}
			if (distributionGoodsItemList.size() > 0) {
				if (item.getStoreId().equals(orderInfo.getPartnerInfo().getId())) { // 直供
					distributionItemList.add(new DistributionItem(distributionGoodsItemList
							.toArray(new DistributionGoodsItem[0])));
				} else {
					distributionItemList.add(new DistributionItem(item.getDeliverDate(), item.getStoreId(),
							distributionGoodsItemList.toArray(new DistributionGoodsItem[0])));
				}
			}
		}
		SalesOrderDistributeTask task = new SalesOrderDistributeTask(orderInfo.getId(), distributionItemList
				.toArray(new DistributionItem[0]));
		getContext().handle(task, SalesOrderDistributeTask.Method.Confirm);

		//
		getContext().bubbleMessage(new MsgResponse(true));
		return true;
	}

	public String getPromptMessage() {
		return null;
	}

	/**
	 * 直供仓库
	 */
	public final static class DirectSupplyStore implements StoreItem {

		private GUID customerId;

		public DirectSupplyStore(GUID customerId) {
			this.customerId = customerId;
		}

		public StoreStatus getStatus() {
			return StoreStatus.ENABLE;
		}

		public String getName() {
			return "供应商直供";
		}

		public String getKepperInfo() {
			return null;
		}

		public GUID[] getKeeperIds() {
			return null;
		}

		public GUID getId() {
			return this.customerId;
		}

		public String getAddress() {
			return "供应商直供";
		}

		public Action[] getAction() {
			return new Action[0];
		}
	}

}
