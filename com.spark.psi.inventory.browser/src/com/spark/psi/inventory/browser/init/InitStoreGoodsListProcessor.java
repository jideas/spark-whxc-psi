package com.spark.psi.inventory.browser.init;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
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
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.inventory.browser.internal.DistributeShelfProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.InitInventoryDetItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.task.SaveInitInventoryTask;

/**
 * 初始化仓库材料库存界面处理器
 * 
 */
public class InitStoreGoodsListProcessor extends PSIListPageProcessor<InitInventoryItem> implements IDataProcessPrompt
{

	public final static String ID_Button_AddGoodsItem = "AddGoodsItem";
	public final static String ID_Button_Save = "Save";
	public final static String ID_Button_Active = "Active";
	public final static String ID_Button_Import = "Button_Import";
	public final static String ID_Label_StoreName = "StoreName";
	public final static String ID_Label_GoodsItemCount = "GoodsItemCount";

	public static enum Columns{
		GoodsItemCode,
		GoodsItemNumber,
		GoodsItemName,
		GoodsItemSpec,
		GoodsItemUnit,
		shelfLife,
		Count,
		Cost,
		Amount
	}

	private StoreInfo storeInfo;

	private final Map<InitInventoryItem, DistributeInventoryItem> dbItemsStore = new HashMap<InitInventoryItem, DistributeInventoryItem>();
	
	// XXX：先简化处理
	private Set<String> selectedGoodsIds = new HashSet<String>();

	private Label goodsItemCountLabel;

	public void init(Situation context){
		try{
			String storeId = (String)this.getArgument();
			storeInfo = context.find(StoreInfo.class, GUID.tryValueOf(storeId));
		}
		catch(Throwable t){
			throw new IllegalArgumentException();
		}
	}

	public void process(Situation context){
		super.process(context);
		this.createControl(ID_Label_StoreName, Label.class).setText(storeInfo.getName());
		goodsItemCountLabel = this.createControl(ID_Label_GoodsItemCount, Label.class);

		createControl(ID_Button_AddGoodsItem, Button.class).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(true);
				MsgRequest request = CommonSelectRequest.createSelectMaterialRequest(new MaterialsSelectParameters(null, false, false,false,false));
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						MaterialsItemInfo[] itemList = (MaterialsItemInfo[])returnValue;
						for(MaterialsItemInfo item : itemList){
							if(selectedGoodsIds.contains(item.getItemData().getId().toString())){
								continue;
							}
							table.addRow(item); // XXX：表格控件提供插入多行数据接口后修改，目前效率
							selectedGoodsIds.add(item.getItemData().getId().toString());
						}
						goodsItemCountLabel.setText(String.valueOf(selectedGoodsIds.size()));
						table.renderUpate();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		createControl(ID_Button_Save, Button.class).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save(false);
					
			}
		});
		createControl(ID_Button_Active, Button.class).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save(true);
			}
		});
		
		createButtonControl(ID_Button_Import).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 导入
				doImport();
			}
		});
	}

	
	private void doImport() {
		PageController pc = new PageController(InitImportFileSelectProccessor.class, InitImportFileSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc,storeInfo);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(300, 100);
		MsgRequest request = new MsgRequest(pci, "导入", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if(null!=returnValue)
				{
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});
		getContext().bubbleMessage(request);
		
	}
	
	/**
	 * 验证材料编辑数据
	 *　
	 *@param count　初始数量 
	 *@param average　平均库存成本
	 *@param amount　库存金额
	 *@return　是否允许保存
	 */
	private boolean validateGoodsEditData(String count, String average, String amount){
		//初始数量为空
		if(StringHelper.isEmpty(count)){
			alert("存在初始数量为空的材料！");
			return false;
		}
		//初始数量大于0，平均库存成本或库存金额
		if(Double.parseDouble(count) > 0 && (StringHelper.isEmpty(average) || StringHelper.isEmpty(amount))){
			alert("存在初始数量大于0，而平均库存成本或库存金额为空的材料，不能执行保存！");
			return false;
		}
		return true;
	}

	/**
	 * 获得编辑单元格数据
	 * 0、初始数量
	 * 1、平均库存成本
	 * 2、库存金额
	 *
	 *@param rowId
	 *@return
	 */
	private String[] getEditData(String rowId){
		return table.getEditValue(rowId, Columns.Count.name(), Columns.Cost.name(), Columns.Amount.name());
	}

	/**
	 * 验证材料信息
	 *
	 *@return 是否允许保存
	 */
	private boolean validateGoodsInfo(){
		for(int i = 0; i < table.getAllRowId().length; i++){
			String[] values = getEditData(table.getAllRowId()[i]);
			if(!validateGoodsEditData(values[0], values[1], values[2])){
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证材料初始数量是否为0
	 *
	 *@return　ture:为0　false:不为0
	 */
//	private boolean validateCountIsZero(){
//		for(int i = 0; i < table.getAllRowId().length; i++){
//			String[] values = getEditData(table.getAllRowId()[i]);
//			if(Double.parseDouble(values[0]) == 0){
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * 保存材料数据
	 *
	 *@param enabled 是否启用仓库
	 */
	private boolean save(final boolean enabled){
		//初始数量为空或大于0时
		if(!validateGoodsInfo()){
			return false;
		}
		//获得材料task
		final SaveInitInventoryTask task = getGoodsItemTask();
		if (validateCountIsZero()){
			confirm("存在初始数量为0的材料，是否继续保存？", new Runnable(){

				public void run(){
					saveGoodsData(enabled, task, getContext());
					resetDataChangedstatus();
				}
			});
			return false;
		} else {
			saveGoodsData(enabled, task, getContext());
			resetDataChangedstatus();
			return true;
		}
	}

	/**
	 * 验证材料初始数量是否为0
	 *
	 *@return　ture:为0　false:不为0
	 */
	private boolean validateCountIsZero(){
		for(int i = 0; i < table.getAllRowId().length; i++){
			String[] values = getEditData(table.getAllRowId()[i]);
			if(Double.parseDouble(values[0]) == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获得材料task
	 *
	 *@return
	 */
	private SaveInitInventoryTask getGoodsItemTask(){
		String[] rowIds = table.getAllRowId();
		InitInventoryItem[] items = new InitInventoryItem[rowIds != null ? rowIds.length : 0];
		if(rowIds != null){
			for(int i = 0; i < rowIds.length; i++){
				String[] values = getEditData(rowIds[i]);
				String[] datas =
				        table.getExtraData(rowIds[i], "GoodsCode", "GoodsName", "GoodsProperties", "GoodsUnit", "scale");
				items[i] = new InitInventoryItem();
				items[i].setStockId(GUID.valueOf(rowIds[i]));
				items[i].setCount(DoubleUtil.strToDouble(values[0], 2));
				items[i].setScale(Integer.parseInt(datas[4]));
				items[i].setAverageCost(items[i].getCount() == 0 ? -1 : Double.parseDouble(values[1]));
				items[i].setAmount(items[i].getCount() == 0 ? -1 : Double.parseDouble(values[2]));
				items[i].setCode(datas[0]);
				items[i].setName(datas[1]);
				items[i].setSpec(datas[2]);
				items[i].setUnit(datas[3]);
			}
		}
		return new SaveInitInventoryTask(storeInfo.getId(), items);
	}

	/**
	 * 保存材料数据
	 *
	 *@param enabled 是否启用仓库
	 *@param task 材料task
	 */
	private void saveGoodsData(boolean enabled, final SaveInitInventoryTask task, final Context context){
		task.setEnable(enabled);
		if(enabled){ //启用仓库
			if (task.getItems() == null || task.getItems().length == 0) {
				context.handle(task);
				enableStore();
			} else {
				//初始数量等于0
				PageController pc = new PageController(DistributeShelfProcessor.class, DistributeShelfRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, storeInfo.getId(), getDbtItems(task.getItems()));
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(640, 480);
				MsgRequest request = new MsgRequest(pci, "货位分配");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						for (InitInventoryItem iItem : task.getItems()) {
							DistributeInventoryItem dbtItem = getDbtItemByInitItem(iItem);
							InitInventoryDetItem[] iItemDets = new InitInventoryDetItem[dbtItem.getInventoryDetItems().length];
							int itemIndex = 0;
							for (DistributeInventoryItemDet dbtItemDet : dbtItem.getInventoryDetItems()) {
								iItemDets[itemIndex] = new InitInventoryDetItem();
								iItemDets[itemIndex].setCount(dbtItemDet.getCount());
								iItemDets[itemIndex].setProduceDate(dbtItemDet.getProduceDate());
								iItemDets[itemIndex].setShelfId(dbtItemDet.getShelfId());
								iItemDets[itemIndex].setShelfNo(dbtItemDet.getShelfNo());
								iItemDets[itemIndex].setStockId(dbtItemDet.getStockId());
								iItemDets[itemIndex].setTiersNo(dbtItemDet.getTiersNo());
								iItemDets[itemIndex].setId(GUID.randomID());
								
								itemIndex++;
							}
							iItem.setInventoryDetItems(iItemDets);
						}
						context.handle(task);
						enableStore();
					}
				});
				getContext().bubbleMessage(request);
			}
		}
		else{
			context.handle(task);
			hint("保存成功！");
			if(table != null){
				table.render();
			}
		}
	}
	
	private DistributeInventoryItem[] getDbtItems(InitInventoryItem[] items) {
		DistributeInventoryItem[] dbtItems = new DistributeInventoryItem[items.length];
		int itemIndex = 0;
		for (InitInventoryItem iItem : items) {
			dbtItems[itemIndex] = new DistributeInventoryItem();
			dbtItems[itemIndex].setStockId(iItem.getStockId());
			dbtItems[itemIndex].setName(iItem.getName());
			dbtItems[itemIndex].setCount(iItem.getCount());
			dbtItems[itemIndex].setScale(iItem.getScale());
			storeDbtItem(iItem, dbtItems[itemIndex]);
			itemIndex++;
		}
		return dbtItems;
	}
	
	
	private void storeDbtItem(InitInventoryItem iItem, DistributeInventoryItem dItem) {
		dbItemsStore.put(iItem, dItem);
	}
	
	private DistributeInventoryItem getDbtItemByInitItem(InitInventoryItem iItem) {
		return dbItemsStore.get(iItem);
	}
	
	/**
	 * 启用仓库
	 */
	private void enableStore(){
		ChangeStoreStatusTask task = new ChangeStoreStatusTask(storeInfo.getId(), StoreStatus.ENABLE);
		getContext().handle(task);
		hint("启用成功", new Runnable(){
			public void run(){
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		List<InitInventoryItem> itemList = context.getList(InitInventoryItem.class, storeInfo.getId());
		for(InitInventoryItem item : itemList){
			selectedGoodsIds.add(item.getStockId().toString());
		}
		goodsItemCountLabel.setText(String.valueOf(selectedGoodsIds.size()));
		return itemList.toArray();
	}

	@Override
	public String getElementId(Object element){
		if(element instanceof InitInventoryItem){
			return ((InitInventoryItem)element).getStockId().toString();
		}
		else if(element instanceof MaterialsItemInfo){
			return ((MaterialsItemInfo)element).getItemData().getId().toString();
		}
		throw new IllegalStateException();
	}

	public String getValue(Object element, String columnName){
		if(element instanceof InitInventoryItem){
			InitInventoryItem storeInitGoodsItem = (InitInventoryItem)element;
			if(columnName.equals(Columns.Count.name())){
				return DoubleUtil.getRoundStr(storeInitGoodsItem.getCount(), storeInitGoodsItem.getScale());
			}
			if(columnName.equals(Columns.Cost.name())){
				return String.valueOf(storeInitGoodsItem.getAverageCost());
			}
			if(columnName.equals(Columns.Amount.name())){
				return String.valueOf(storeInitGoodsItem.getAmount());
			}
		}
		else if(element instanceof MaterialsItemInfo){
			if(columnName.equals(Columns.Count.name())){
				return "0";
			}
			if(columnName.equals(Columns.Cost.name())){
				return "0.00";
			}
			if(columnName.equals(Columns.Amount.name())){
				return "0.00";
			}
		}
		return null;
	}

	public SNameValue[] getExtraData(Object element){
		if(element instanceof InitInventoryItem){
			InitInventoryItem storeInitGoodsItem = (InitInventoryItem)element;
			return new SNameValue[] {new SNameValue("GoodsCode", storeInitGoodsItem.getsCode()),
			        new SNameValue("GoodsName", storeInitGoodsItem.getName()),
			        new SNameValue("GoodsProperties", storeInitGoodsItem.getSpec()),
			        new SNameValue("scale", storeInitGoodsItem.getScale() + ""),
			        new SNameValue("GoodsUnit", storeInitGoodsItem.getUnit())};
		}
		else if(element instanceof MaterialsItemInfo){
			MaterialsItemInfo item = (MaterialsItemInfo)element;
			return new SNameValue[] {
			        new SNameValue("GoodsCode", item.getBaseInfo().getCode()),
			        new SNameValue("GoodsName", item.getBaseInfo().getName()),
			        new SNameValue("scale", item.getBaseInfo().getCategory().getScale() + ""),
			        new SNameValue("GoodsProperties", item.getItemData().getPropertiesWithoutUnit()),
			        new SNameValue("GoodsUnit", item.getItemData().getPropertyValues().length > 0 ? item.getItemData()
			                .getPropertyValues()[0] : "")};
		}
		return null;
	}

	/**
	 * 获取可以对表格数据进行的所有操作
	 * 
	 * @return
	 */
	public String[] getTableActionIds(){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(Action.Delete.name().equals(actionName)){
			confirm("确认删除当前材料？", new Runnable(){
				public void run(){
					table.removeRow(rowId);
					selectedGoodsIds.remove(rowId);
					goodsItemCountLabel.setText(String.valueOf(selectedGoodsIds.size()));
					table.renderUpate();
				}
			});

		}
	}

	/**
	 * 提示信息
	 */
	public String getPromptMessage(){
		return null;
	}

	/**
	 * 处理数据
	 */
	public boolean processData(){
		return save(false);
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
