package com.spark.psi.inventory.browser.loss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItem;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItemDet;
import com.spark.psi.publish.inventory.task.ChangReportLossInfoStautsTask;
import com.spark.psi.publish.inventory.task.ReportLossInfoTask;
import com.spark.psi.publish.inventory.task.ChangReportLossInfoStautsTask.Operation;

public class NewReportLossSheetProcessor<TItem> extends ReportLossSheetProcessor<TItem>{
	
	public static final String ID_List_Store = "List_Store";
	public static final String ID_Button_AddMaterial = "Button_AddMaterial";
	public static final String ID_Button_Submit = "Button_Submit";
	public static final String ID_Button_Save = "Button_Save";
	
	private Button      addMaterialBtn    = null;
	private Button      submitBtn         = null;
	private Button      saveBtn           = null;
	private Text        memoText          = null;
	
	private GUID        storeId           = null;
	private Map<String, Object> idItemMap = new HashMap<String, Object>();
	private final Map<ReportLossInfoTask.Item, DistributeInventoryItem> dbItemsStore = new HashMap<ReportLossInfoTask.Item, DistributeInventoryItem>();
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		final LWComboList storeList = createControl(ID_List_Store, LWComboList.class);
		addMaterialBtn = createControl(ID_Button_AddMaterial, Button.class);
		submitBtn = createControl(ID_Button_Submit, Button.class);
		saveBtn = createControl(ID_Button_Save, Button.class);
		memoText = createMemoText();
		
		if (null != storeList) {
			storeList.getList().setSource(new ListSourceAdapter() {
				
				Map<String, StoreItem> stores = new HashMap<String, StoreItem>();
				
				public String getElementId(Object element) {
					StoreItem storeInfo = (StoreItem)element;
					return storeInfo.getId().toString();
				}
				
				public Object getElementById(String id) {
					return stores.get(id);
				}
				
				public String getText(Object element) {
					StoreItem storeInfo = (StoreItem)element;
					return storeInfo.getName();
				}
				
				@SuppressWarnings("unchecked")
				public Object[] getElements(Object inputElement) {
					GetStoreListKey key = new GetStoreListKey(StoreStatus.ALL);
					ListEntity<StoreItem> result = situation.find(ListEntity.class, key);
					if (null != result) {
						List<StoreItem> storeList = result.getItemList();
						for (StoreItem storeInfo : storeList) {
							stores.put(storeInfo.getId().toString(), storeInfo);
						}
						return storeList.toArray(new StoreItem[0]);
					}
					return null;
				}
			});
			storeList.getList().setInput(null);
			storeList.addSelectionListener(new SelectionListener() {
				
				public void widgetSelected(SelectionEvent e) {
					// 仓库选择事件(清空商品列表)
					storeId = GUID.tryValueOf(storeList.getList().getSeleted());
					table.render();
				}
			});
		}
		
		addMaterialBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (null == storeId) {
					alert("请选择仓库。");
					return;
				}
				MsgRequest request = CommonSelectRequest.createSelectMaterialRequest(new MaterialsSelectParameters(storeId, false, false,false,null));
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (returnValue == null) return;
						MaterialsItemInfo[] materials = (MaterialsItemInfo[]) returnValue;
						for (MaterialsItemInfo item : materials) {
							if (idItemMap.containsKey(item.getItemData().getId().toString())) {
								continue;
							}
							table.addRow(item);
							idItemMap.put(item.getItemData().getId().toString(), item);
						}
						table.renderUpate();
					}
				});
				situation.bubbleMessage(request);
			}
		});
		
		submitBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 提交
				if (!validateInput()) {
					return ;
				}
				final ReportLossInfoTask saveTask = buildTask();
				PageController pc = new PageController(InventoryShelfInfoPageProcessor.class, InventoryShelfInfoPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, storeId, getDbtItems(saveTask.getItems()), "报损数量");
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(640, 480);
				MsgRequest request = new MsgRequest(pci, "货位分配");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						for (ReportLossInfoTask.Item iItem : saveTask.getItems()) {
							TaskItem iItemEntity = (TaskItem)iItem;
							DistributeInventoryItem dbtItem = getDbtItemByInitItem(iItemEntity);
							ReportLossInfoItemDetImpl[] iItemDets = new ReportLossInfoItemDetImpl[dbtItem.getInventoryDetItems().length];
							int itemIndex = 0;
							for (DistributeInventoryItemDet dbtItemDet : dbtItem.getInventoryDetItems()) {
								iItemDets[itemIndex] = new ReportLossInfoItemDetImpl();
								iItemDets[itemIndex].setCount(dbtItemDet.getDistributeCount());
								iItemDets[itemIndex].setProduceDate(dbtItemDet.getProduceDate());
								iItemDets[itemIndex].setShelfId(dbtItemDet.getShelfId());
								iItemDets[itemIndex].setShelfNo(dbtItemDet.getShelfNo());
								iItemDets[itemIndex].setStockId(dbtItemDet.getStockId());
								iItemDets[itemIndex].setTiersNo(dbtItemDet.getTiersNo());
								iItemDets[itemIndex].setId(GUID.randomID());
								
								itemIndex++;
							}
							iItemEntity.setItemDets(iItemDets);
						}
						doSave(saveTask);
						ChangReportLossInfoStautsTask submitTask = new ChangReportLossInfoStautsTask(saveTask.getId(), 
								reportLossInfo == null ? ReportLossStatus.Submitting : reportLossInfo.getStatus());
						submitTask.setChangePersonId(loginInfo.getEmployeeInfo().getId());
						submitTask.setChangePersonName(loginInfo.getEmployeeInfo().getName());
						submitTask.setChangeTime(new Date().getTime());
						submitTask.setItems(saveTask.getItems());
						submitTask.setOperation(Operation.submit);
						getContext().handle(submitTask);
						
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 保存
				ReportLossInfoTask task = buildTask();
				if (null == task) return;
				if (doSave(task)) {
					hint("保存成功。", new Runnable() {
						
						public void run() {
							getContext().bubbleMessage(new MsgResponse(true));
						}
					});
				}
			}
		});
		
		initData();
		
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateCell(String rowId, String columnName) {
				if (ColumnName.count.name().equals(columnName)) {
					String countStr = table.getEditValue(rowId, ColumnName.count.name())[0];
					if (StringHelper.isEmpty(countStr)) {
						return "报损数量不能为空。";
					}
				} else if (ColumnName.reason.name().equals(columnName)) {
					String reason = table.getEditValue(rowId, ColumnName.reason.name())[0];
					if (StringHelper.isEmpty(reason)) {
						return "报损原因不能为空。";
					}
				}
				return null;
			}

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "请先添加材料。";
				}
				return null;
			}
			
		});
	}
	
	private void initData() {
		storeId = reportLossInfo == null ? null : reportLossInfo.getStoreId();
		memoText.setText(reportLossInfo == null ? null : reportLossInfo.getRemark());
	}
	
	private boolean doSave(ReportLossInfoTask task) {
		if (null == task) return false;
		if (null == reportLossInfo) {
			getContext().handle(task, ReportLossInfoTask.Method.Create);
		} else {
			getContext().handle(task, ReportLossInfoTask.Method.Modify);
		}
		return true;
	}
	
	private ReportLossInfoTask buildTask() {
		if (!validateInput()) {
			return null;
		}
		ReportLossInfoTask task = new ReportLossInfoTask();
		task.setCreateDate(reportLossInfo == null ? new Date().getTime() : reportLossInfo.getCreateDate());
		task.setCreator(reportLossInfo == null ? loginInfo.getEmployeeInfo().getName() : reportLossInfo.getCreateor());
		task.setCreatorId(reportLossInfo == null ? loginInfo.getEmployeeInfo().getId() : reportLossInfo.getCreatorId());
		task.setId(reportLossInfo == null ? GUID.randomID() : reportLossInfo.getId());
		task.setStatus(reportLossInfo == null ? ReportLossStatus.Submitting : reportLossInfo.getStatus());
		task.setSheetNo(reportLossInfo == null ? null : reportLossInfo.getSheetNo());
		
		task.setRemark(memoText.getText());
		task.setStoreId(storeId);
		
		String[] rowIds = table.getAllRowId();
		if (rowIds == null || rowIds.length < 1) {
			alert("请先添加材料。");
			return null;
		}
		ReportLossInfoTask.Item[] items = new ReportLossInfoTask.Item[rowIds.length];
		TaskItem item = null;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String rowId = rowIds[rowIndex];
			item = new TaskItem();
			Object itemData = idItemMap.get(rowId);
			if (null == itemData) continue;
			if (itemData instanceof MaterialsItemInfo) {
				MaterialsItemInfo itemInfo = (MaterialsItemInfo)itemData;
				item.setGoodsId(itemInfo.getItemData().getId());
				item.setGoodsCode(itemInfo.getBaseInfo().getCode());
				item.setGoodsName(itemInfo.getBaseInfo().getName());
				item.setGoodsNumber(itemInfo.getItemData().getMaterialNo());
				item.setGoodsSpec(itemInfo.getItemData().getMaterialSpec());
				item.setGoodsUnit(itemInfo.getItemData().getUnit());
				item.setScale(itemInfo.getBaseInfo().getCategory().getScale());
			} else if (itemData instanceof ReportLossInfoItem) {
				ReportLossInfoItem itemInfo = (ReportLossInfoItem)itemData;
				item.setGoodsId(itemInfo.getGoodsId());
				item.setGoodsCode(itemInfo.getGoodsCode());
				item.setGoodsName(itemInfo.getGoodsName());
				item.setGoodsNumber(itemInfo.getGoodsNo());
				item.setGoodsSpec(itemInfo.getGoodsSpec());
				item.setGoodsUnit(itemInfo.getGoodsUnit());
				item.setScale(itemInfo.getScale());
			}
			String countStr = table.getEditValue(rowId, ColumnName.count.name())[0];
			item.setReportCount(DoubleUtil.strToDouble(countStr, item.getScale()));
			item.setReportReason(table.getEditValue(rowId, ColumnName.reason.name())[0]);
			if(item.getReportReason().length()>100){
				alert(item.getGoodsName()+" 报损原因超过额定长度，请修改！");
			}
			item.setId(GUID.randomID());
			items[rowIndex] = item;
		}
		task.setItems(items);
		if (task.getItems() == null || task.getItems().length < 1) {
			alert("保存材料出错！");
			return null;
		}
		return task;
	}
	
	private DistributeInventoryItem[] getDbtItems(ReportLossInfoTask.Item[] items) {
		DistributeInventoryItem[] dbtItems = new DistributeInventoryItem[items.length];
		int itemIndex = 0;
		for (ReportLossInfoTask.Item iItem : items) {
			dbtItems[itemIndex] = new DistributeInventoryItem();
			dbtItems[itemIndex].setStockId(iItem.getGoodsId());
			dbtItems[itemIndex].setName(iItem.getGoodsName());
			dbtItems[itemIndex].setCount(iItem.getReportCount());
			dbtItems[itemIndex].setScale(iItem.getScale());
			storeDbtItem(iItem, dbtItems[itemIndex]);
			itemIndex++;
		}
		return dbtItems;
	}
	
	
	private void storeDbtItem(ReportLossInfoTask.Item iItem, DistributeInventoryItem dItem) {
		dbItemsStore.put(iItem, dItem);
	}
	
	private DistributeInventoryItem getDbtItemByInitItem(ReportLossInfoTask.Item iItem) {
		return dbItemsStore.get(iItem);
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			// 删除材料条目
			table.removeRow(rowId);
			idItemMap.remove(rowId);
			table.renderUpate();
		}
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Delete.name()};
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return null;
	}

	@Override
	protected String getSheetTitle() {
		return reportLossInfo == null ? "新增报损单" : "报损单";
	}

	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStopCause() {
		if (null == reportLossInfo) return null;
		if (ReportLossStatus.Deied.equals(reportLossInfo.getStatus())
				&& !StringUtils.isEmpty(reportLossInfo.getRejectReason())) {
			return "退回原因：" + reportLossInfo.getRejectReason();
		}
		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof MaterialsItemInfo) {
			MaterialsItemInfo item = (MaterialsItemInfo)element;
			return item.getItemData().getId().toString();
		} else if (element instanceof ReportLossInfoItem) {
			ReportLossInfoItem item = (ReportLossInfoItem)element;
			return item.getGoodsId().toString();
		}
		return null;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (null == reportLossInfo) {
			return null;
		} else {
			for (ReportLossInfoItem item : reportLossInfo.getItems()) {
				idItemMap.put(item.getGoodsId().toString(), item);
			}
			return reportLossInfo.getItems();
		}
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (element instanceof ReportLossInfoItem) {
			ReportLossInfoItem item = (ReportLossInfoItem)element;
			if (ColumnName.count.name().equals(columnName)) {
				return DoubleUtil.getRoundStr(item.getReportCount(), item.getScale());
			} else if (ColumnName.reason.name().equals(columnName)) {
				return item.getReportReason() == null ? "" : item.getReportReason();
			}
		} else if (ColumnName.count.name().equals(columnName) || ColumnName.reason.name().equals(columnName)) {
			return "";
		}
		return null;
	}
	
	

}

class TaskItem implements ReportLossInfoTask.Item {
	private GUID id;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNumber;
	private String goodsName;
	private String goodsUnit;
	private String goodsSpec;
	private int scale;
	private double reportCount;
	private String reportReason;
	private ReportLossInfoItemDet[] itemDets;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public double getReportCount() {
		return reportCount;
	}
	public void setReportCount(double reportCount) {
		this.reportCount = reportCount;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public ReportLossInfoItemDet[] getItemDets() {
		return itemDets;
	}
	public void setItemDets(ReportLossInfoItemDet[] itemDets) {
		this.itemDets = itemDets;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	
}
