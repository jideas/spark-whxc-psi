package com.spark.psi.inventory.browser.allocate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionChangingEvent;
import com.jiuqi.dna.ui.wt.events.SelectionChangingListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.Login;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo.AllocateGoodsItem;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;
import com.spark.psi.publish.inventory.task.UpdateInventoryAllocateSheetTask;

/**
 * <p>
 * 新增或编辑调拔单处理器
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 20012 - 20018<br>
 * 
 * 
 * @author 刘青
 * @version 2012-5-23
 */

public class EditAllocateSheetProcesser extends SimpleSheetPageProcessor<AllocateGoodsItem> implements IDataProcessPrompt {

	/**
	 * 组件ID
	 */
	public final static String ID_Button_AddGoods = "Button_AddGoods";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_ComboList_Out = "ComboList_Out";
	public final static String ID_ComboList_In = "ComboList_In";

	/**
	 * 类型
	 */
	public final static String Type_Submit = "submit";

	/**
	 * 列名
	 */
	public static enum Columns {
		code, number, name, spec, unit, availableCount, allocateCount
	}

	public static enum TableExtraValueName {
		name, scale, availableCount
	}

	/**
	 * 组件
	 */
	LWComboList allocateOutStoreList;
	LWComboList allocateInStoreList;
	Button addGoodsButton;
	Button submitButton;
	Button saveButton;

	//
	private String sheetId = null;
	private InventoryAllocateSheetInfo info = null;

	/**
	 * 添加材料侦听器
	 */
	private class AddGoodsButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(GUID.valueOf(allocateOutStoreList.getText()), null);
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					MaterialsItemInfo[] items = (MaterialsItemInfo[]) returnValue;
					for (MaterialsItemInfo material : items) {
						String itemId = material.getItemData().getId().toString();
						if (!getGoodsIdList().contains(itemId)) {
							AllocateShowItem item = new AllocateShowItem();
							item.setAvailableCount(getAvailableCount(GUID.valueOf(itemId)));
							item.setStockItemCode(material.getBaseInfo().getCode());
							item.setStockItemNumber(material.getItemData().getMaterialNo());
							item.setStockItemId(material.getItemData().getId());
							item.setStockItemName(material.getBaseInfo().getName());
							item.setStockSpec(material.getItemData().getMaterialSpec());
							item.setStockItemUnit(material.getItemData().getPropertyValues()[0]);
							item.setScale(material.getItemData().getScale());
							table.addRow(item);
						}
					}
					table.renderUpate();
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	/**
	 * 提交申请侦听器
	 */
	private class SubmitButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!validateDataBeforeSave()) {
				refreshGoodsAvailableCount();
				return;
			}
			if (!executeSaveData()) {
				return;
			}
			InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(sheetId));
			getContext().handle(task, InventoryAllocateTask.Method.Submit);
			if (!task.isSuccess()) {
				if (null != task.getErrors() && task.getErrors().length > 0) {
					StringBuilder message = new StringBuilder();
					for (InventoryAllocateTask.Error error : task.getErrors()) {
						if (message.length() > 0) {
							message.append(";");
						}
						message.append(error.getMessage());
					}
					alert(message.toString());
				}
				refreshGoodsAvailableCount();
				// 是否出现并行操作错误
				if (task.isCurrentError()) {
					getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
				}
				return;
			}
			//
			if (getContext().get(Login.class).hasAuth(Auth.Boss)) { // 总经理
				getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
			} else {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		}

	}

	/**
	 * 保存
	 */
	protected boolean saveData() {
		if (executeSaveData()) {
			hint("保存成功！");
			getContext().bubbleMessage(new MsgResponse(false));
			return true;
		}
		return false;
	}

	/**
	 * 执行保存数据
	 */
	private boolean executeSaveData() {
		if (CheckIsNull.isEmpty(sheetId)) { // 新增

			CreateInventoryAllocateSheetTask task = new CreateInventoryAllocateSheetTask(GUID.valueOf(allocateOutStoreList.getText()), GUID
					.valueOf(allocateInStoreList.getText()), createMemoText().getText(), getAllocateGoodsItems());
			getContext().handle(task);
			sheetId = String.valueOf(task.getSheetId());
		} else { // 编辑

			UpdateInventoryAllocateSheetTask task = new UpdateInventoryAllocateSheetTask(GUID.valueOf(sheetId), GUID
					.valueOf(allocateOutStoreList.getText()), GUID.valueOf(allocateInStoreList.getText()), createMemoText().getText(),
					getAllocateGoodsItems());
			getContext().handle(task);
			if (!task.isSuccess()) {
				if (null != task.getErrors() && task.getErrors().length > 0) {
					StringBuffer message = new StringBuffer();
					for (UpdateInventoryAllocateSheetTask.Error error : task.getErrors()) {
						message.append(error.getMessage());
					}
					alert(message.toString());
					// 是否出现并行操作错误
					if (task.isCurrentError()) {
						getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
					}
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 注册验证器
	 */
	private void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "调拨材料不能为空！";
				}

				List<com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem> itemList = new ArrayList<com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem>();
				String[] rowIds = table.getAllRowId();
				for (int i = 0; i < rowIds.length; i++) {
					String rowId = rowIds[i];
					String[] values = table.getEditValue(rowId, Columns.allocateCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem(GUID
								.valueOf(rowId), Double.valueOf(values[0])));
					}
				}
				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写材料调拨数量！";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId, Columns.allocateCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					String[] columnValue = table.getEditValue(rowId, Columns.allocateCount.name());
					String goodsName = table.getExtraData(rowId, Columns.name.name())[0];
					if (DoubleUtil.sub(Double.valueOf(columnValue[0]), getAvailableCount(GUID.valueOf(rowId))) > 0) {
						return "材料：" + goodsName + "，超出可用库存数量！";
					}
				}
				return null;
			}
		});
	}

	/**
	 * 验证仓库
	 * 
	 *@return
	 */
	@SuppressWarnings("unchecked")
	protected boolean validateStore() {
		if (getContext().find(Boolean.class, Auth.Boss)) {
			return true;
		}
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			alert("您没有操作权限，请重新选择仓库！");
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(GUID.valueOf(allocateOutStoreList.getText()))
					|| item.getId().equals(GUID.valueOf(allocateInStoreList.getText()))) {
				hasStore = true;
				break;
			}
		}
		if (!hasStore) {
			alert("您没有操作权限，请重新选择仓库！");
		}
		return hasStore;
	}

	/**
	 * 更新材料的可用库存
	 */
	private void refreshGoodsAvailableCount() {
		String[] rowIds = table.getAllRowId();
		if (rowIds != null && rowIds.length > 0) {
			for (String rowId : rowIds) {
				String countDecimal = table.getExtraData(rowId, TableExtraValueName.scale.name())[0];
				Double availableCount = getAvailableCount(GUID.valueOf(rowId));
				table.updateCell(rowId, 4, DoubleUtil.getRoundStr(availableCount, Integer.parseInt(countDecimal)));
			}
			table.renderUpate();
		}
	}

	/**
	 * 保存前验证数据
	 */
	private boolean validateDataBeforeSave() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStore()) {
			return false;
		}
		return true;
	}

	/**
	 * 获得材料数据
	 */
	private com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem[] getAllocateGoodsItems() {
		String[] rowIds = table.getAllRowId();
		com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem[] items = new com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem[rowIds.length];

		for (int i = 0; i < rowIds.length; i++) {
			String value = table.getEditValue(rowIds[i], Columns.allocateCount.name())[0];
			items[i] = new com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem(GUID.valueOf(rowIds[i]),
					Double.valueOf(value));
		}
		return items;
	}

	/**
	 * 页面初始化
	 * 
	 * @param context
	 *            上下文
	 */
	public void process(Situation context) {
		super.process(context);
		// 调出仓库
		allocateOutStoreList = this.createControl(ID_ComboList_Out, LWComboList.class, JWT.NONE);
		// 调入仓库
		allocateInStoreList = this.createControl(ID_ComboList_In, LWComboList.class, JWT.NONE);
		// 添加材料
		addGoodsButton = this.createControl(ID_Button_AddGoods, Button.class, JWT.NONE);
		addGoodsButton.addActionListener(new AddGoodsButtonListener());
		// 提交申请
		submitButton = this.createControl(ID_Button_Submit, Button.class, JWT.NONE);
		submitButton.addActionListener(new SubmitButtonListener());
		// 保存
		saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		// 重置按钮状态
		resetButtonStatus();
		//
		allocateOutStoreList.getList().addSelectionChangingListener(new SelectionChangingListener() {
			public void selectionChanging(final SelectionChangingEvent e) {
				e.doIt = false;
				if (table.getAllRowId().length != 0 && CheckIsNull.isNotEmpty(allocateOutStoreList.getText())
						&& !allocateOutStoreList.getText().equals((String) e.target)) {
					confirm("选择仓库后将清空材料列表，是否继续？", new Runnable() {
						public void run() {
							allocateOutStoreList.setSelection((String) e.target);
						}
					});
				} else {
					allocateOutStoreList.setSelection((String) e.target);
				}
				allocateOutStoreList.setPanelVisible(false);
			}
		});
		//
		allocateOutStoreList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				removeAllSelectedGoods();
				resetButtonStatus();
			}
		});
		//
		allocateInStoreList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				resetButtonStatus();
			}
		});
		//
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}

		});
		// 注册验证监听器
		registerValidator();
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 *            上下文
	 */
	public void postProcess(Situation context) {
		// 调出仓库设置源数据
		allocateOutStoreList.getList().setSource(getStoreSource());
		allocateOutStoreList.getList().setInput(null);
		// 调入仓库设置源数据
		allocateInStoreList.getList().setSource(getStoreSource());
		allocateInStoreList.getList().setInput(null);
		if (CheckIsNull.isNotEmpty(info)) {
			// 初始化选择的仓库
			allocateOutStoreList.setSelection(String.valueOf(info.getSourceStoreId()));
			allocateInStoreList.setSelection(String.valueOf(info.getDestinationStoreId()));
			// 初始化备注
			this.createMemoText().setText(info.getCause());
		}
		super.postProcess(context);
	}

	/**
	 * 获得仓库源数据
	 */
	private StoreSource getStoreSource() {
		if (getContext().get(Login.class).hasAuth(Auth.Boss)) { // 总经理
			return new StoreSource();
		} else { // 非总经理
			return new StoreSource(true, new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING });
		}
	}

	/**
	 * 获得单据基本信息
	 */
	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 单据数据（金额）信息
	 */
	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取备注内容
	 */
	@Override
	protected String getRemark() {
		if (null == info) {
			return null;
		}
		return info.getCause();
	}

	/**
	 * 获取审核信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String getSheetApprovalInfo() {
		if (info == null) {
			return "";
		}
		String approvalInfo = "";
		// 审批人
		if (StringHelper.isNotEmpty(info.getApprovePerson())) {
			approvalInfo += "调出审批人：" + info.getApprovePerson();
			approvalInfo += "(" + DateUtil.dateFromat(info.getApproveDate()) + ")";
		}
		// 调入审批人
		// if(StringHelper.isNotEmpty(info.getAllocateInExamName())){
		// approvalInfo += hasValue ? "  " : "";
		// approvalInfo += "调入审批人：" + info.getAllocateInExamName();
		// approvalInfo += "(" +
		// DateUtil.dateFromat(info.getAllocateInExamDate()) + ")";
		// }
		return approvalInfo;
	}

	/**
	 * 获取制单信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String getSheetCreateInfo() {
		if (info == null) {
			return "";
		}
		String createInfo = "";
		createInfo += "制单：" + info.getCreatorName();
		createInfo += "(" + DateUtil.dateFromat(info.getCreateDate()) + ")";

		return createInfo;
	}

	/**
	 * 其他信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String[] getSheetExtraInfo() {
		if (info == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		// 状态信息
		list.add("状态：" + info.getStatus().getName());
		if (InventoryAllocateStatus.Rejected.equals(info.getStatus())) {
			list.add("退回原因：" + info.getDanyCause());
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 获取单据编号(显示在右上角)
	 */
	@Override
	protected String getSheetNumber() {
		return info != null ? info.getSheetNumber() : "";
	}

	/**
	 * 获得单据标题(显示本单据标题)
	 */
	@Override
	protected String getSheetTitle() {
		return sheetId == null ? "新增调拔单" : "编辑调拔单";
	}

	/**
	 * 获取单据类型
	 */
	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取中止原因
	 */
	@Override
	protected String getStopCause() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 页面加载之前初始化本单据数据
	 */
	@Override
	protected void initSheetData() {
		if (CheckIsNull.isNotEmpty(this.getArgument())) {
			sheetId = (String) this.getArgument();
			info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		}
	}

	/**
	 * 获取指定对象的ID
	 */
	@Override
	public String getElementId(Object element) {
		return ((AllocateShowItem) element).getStockItemId().toString();
	}

	/**
	 * 获格的数据列表
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (CheckIsNull.isEmpty(sheetId)) {
			return null;
		}
		info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		if (CheckIsNull.isEmpty(info)) {
			return null;
		}
		List<AllocateShowItem> itemList = new ArrayList<AllocateShowItem>();
		AllocateGoodsItem[] goodsItem = info.getItems();
		for (int i = 0; i < goodsItem.length; i++) {
			itemList.add(changeAllocateGoodsItemToItem(goodsItem[i]));
		}

		return itemList.toArray();
	}

	/**
	 * 根据调拔材料获得材料
	 * 
	 *@param item
	 *@return
	 */
	private AllocateShowItem changeAllocateGoodsItemToItem(AllocateGoodsItem allocateItem) {
		AllocateShowItem item = new AllocateShowItem();
		if (CheckIsNull.isNotEmpty(allocateItem)) {
			item.setAllocateCount(allocateItem.getAllocateCount());
			item.setAvailableCount(getAvailableCount(allocateItem.getGoodsItemId()) == null ? 0.00 : getAvailableCount(allocateItem
					.getGoodsItemId()));
			item.setStockItemCode(allocateItem.getGoodsItemCode());
			item.setStockItemNumber(allocateItem.getStockNumber());
			item.setStockItemId(allocateItem.getGoodsItemId());
			item.setStockItemName(allocateItem.getGoodsItemName());
			item.setScale(allocateItem.getScale());
			item.setStockSpec(allocateItem.getGoodsItemProperties());
			item.setStockItemUnit(allocateItem.getGoodsItemUnit());
		}
		return item;
	}

	/**
	 * 获得某材料在仓库的可用数量
	 */
	private Double getAvailableCount(GUID guid) {
		// 获得仓库ID
		GUID allocateOutStoreGuid = GUID.valueOf(allocateOutStoreList.getText());
		GetAvailableCountKey key = new GetAvailableCountKey(allocateOutStoreGuid, guid);
		Double availableCountD = getContext().find(Double.class, key);
		double availableCount = availableCountD == null ? 0.0 : availableCountD.doubleValue();
		return availableCount > 0 ? availableCount : 0;
	}

	/**
	 * 重置按钮状态
	 */
	private void resetButtonStatus() {
		addGoodsButton.setEnabled(getButtonIsAvailable());
		submitButton.setEnabled(getButtonIsAvailable());
		saveButton.setEnabled(getButtonIsAvailable());
	}

	/**
	 * 获得按钮是否可用
	 */
	protected boolean getButtonIsAvailable() {
		return !StringUtils.isEmpty(allocateOutStoreList.getText()) && !StringUtils.isEmpty(allocateInStoreList.getText())
				&& !allocateOutStoreList.getText().equals(allocateInStoreList.getText());
	}

	/**
	 * 获得选择的材料ID的list
	 */
	protected List<String> getGoodsIdList() {
		List<String> goodsIdList = new ArrayList<String>();
		Collections.addAll(goodsIdList, table.getAllRowId());
		return goodsIdList;
	}

	/**
	 * 获得可编辑值
	 */
	public String getValue(Object element, String columnName) {
		if (element instanceof AllocateShowItem) {
			if (Columns.allocateCount.name().equals(columnName)) {
				return String.valueOf(((AllocateShowItem) element).getAllocateCount());
			}
		}

		return "0.00";
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		table.removeRow(rowId);
		table.renderUpate();
	}

	/**
	 * 未保存关闭页面时的提示信息
	 */
	public String getPromptMessage() {
		return null;
	}

	/**
	 * 保存关闭页面执行
	 */
	public boolean processData() {
		if (saveData()) {
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取可以对表格数据进行删除操作
	 */
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * 获取可以对指定行对象进行删除操作
	 */
	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * 获得不可编辑值
	 */
	@Override
	public SNameValue[] getExtraData(Object element) {
		AllocateShowItem item = (AllocateShowItem) element;
		return new SNameValue[] { new SNameValue(TableExtraValueName.availableCount.name(), item.getAvailableCount() + ""),
				new SNameValue(TableExtraValueName.name.name(), item.getStockItemName()),
				new SNameValue(TableExtraValueName.scale.name(), item.getScale() + "") };
	}

	/**
	 * 删除表格所有数据
	 */
	private void removeAllSelectedGoods() {
		if (table.getAllRowId() == null || table.getAllRowId().length == 0) {
			return;
		}
		for (String id : table.getAllRowId()) {
			table.removeRow(id);
		}
		table.renderUpate();
	}
}
