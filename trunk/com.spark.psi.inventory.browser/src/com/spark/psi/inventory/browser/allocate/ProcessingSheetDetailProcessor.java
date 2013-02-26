package com.spark.psi.inventory.browser.allocate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;

/**
 * <p>进行中详细调拔单处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-24
 */

public class ProcessingSheetDetailProcessor extends AllocateSheetDetailBaseProcessor{

	/**
	 * 组件ID
	 */
	public final static String ID_Button_InOrOut = "Button_InOrOut";

	/**
	 * 组件
	 */
	private Button inOrOutButton;

	/**
	 * 页面初始化
	 * 
	 * @param context 上下文
	 */
	public void process(Situation context){
		super.process(context);
		//页面底部按钮区
		inOrOutButton = this.createControl(ID_Button_InOrOut, Button.class, JWT.NONE);
		//
		if(showAllocateOutButton()){ //有出库权限
			inOrOutButton.setText("确定调出");
			inOrOutButton.addActionListener(new AllocateOutButtonListener());
		}
		else if(showAllocateInButton()){ //有入库权限
			inOrOutButton.setText("确定调入");
			inOrOutButton.addActionListener(new AllocateInButtonListener());
		}
		else{ //无权限则注销
			inOrOutButton.dispose();
		}

	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context){
		super.postProcess(context);

	}

	/**
	 * 是否显示确定调出按钮
	 */
	private boolean showAllocateOutButton(){
		return InventoryAllocateStatus.Allocating.equals(info.getStatus()) && hasAuthForStore(info.getSourceStoreId());
	}

	/**
	 * 是否显示确定调入按钮
	 */
	private boolean showAllocateInButton(){
		return InventoryAllocateStatus.AllocateOut.equals(info.getStatus())
		        && hasAuthForStore(info.getDestinationStoreId());
	}

	/**
	 *　是否具有操作指定仓库的权限 
	 *
	 *@return true: 有权限 ， false:无权限 
	 */
	@SuppressWarnings("unchecked")
	private boolean hasAuthForStore(GUID storeGuid){
		if(getContext().find(Boolean.class, Auth.Boss)){
			return true;
		}
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, key);
		if(null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())){
			return false;
		}
		for(StoreItem item : listEntity.getItemList()){
			if(item.getId().equals(storeGuid)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 确定调出监听器
	 */
	private class AllocateOutButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			
			final Map<DistributeInventoryItem, GUID> dItemIdMap = new HashMap<DistributeInventoryItem, GUID>();
			final DistributeInventoryItem[] dItems = new DistributeInventoryItem[info.getItems().length];
			DistributeInventoryItem dItem = null;
			int index = 0;
			for (InventoryAllocateSheetInfo.AllocateGoodsItem item : info.getItems()) {
				dItem = new DistributeInventoryItem();
				dItem.setCount(item.getAllocateCount());
				dItem.setName(item.getGoodsItemName());
				dItem.setStockId(item.getGoodsItemId());
				dItems[index] = dItem;
				
				dItemIdMap.put(dItem, item.getId());
				index++;
			}
			PageController pc = new PageController(InventoryShelfInfoPageProcessor.class, InventoryShelfInfoPageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, info.getSourceStoreId(), dItems);
			MsgRequest request = new MsgRequest(pci, "货位分配");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					// 
					InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(sheetId));
					List<InventoryAllocateTask.Item> itemList = new ArrayList<InventoryAllocateTask.Item>();
					
					for (DistributeInventoryItem dItem : dItems) {
						InventoryAllocateTask.Item item = null;
						for (DistributeInventoryItemDet iDet : dItem.getInventoryDetItems()) {
							item = task.new Item();
							item.setAllocateItemId(dItemIdMap.get(dItem));
							item.setCount(iDet.getDistributeCount());
							item.setId(iDet.getId());
							item.setProduceDate(iDet.getProduceDate());
							item.setSheetId(GUID.valueOf(sheetId));
							item.setShelfId(iDet.getShelfId());
							item.setShelfNo(iDet.getShelfNo());
							item.setStockId(iDet.getStockId());
							item.setTiersNo(iDet.getTiersNo());
							item.setStoreId(info.getSourceStoreId());
							itemList.add(item);
						}
						
					}
					task.setItems(itemList.toArray(new InventoryAllocateTask.Item[0]));
					try	{
						getContext().handle(task, InventoryAllocateTask.Method.AllocateOut);
					} catch (Throwable t) {
						t.printStackTrace();
						alert(t.getMessage());
						return;
					}
					if(!task.isSuccess()){
						if(CheckIsNull.isNotEmpty(task.getErrors())){
							StringBuilder message = new StringBuilder();
							for(InventoryAllocateTask.Error error : task.getErrors()){
								if(message.length() > 0){
									message.append(";");
								}
								message.append(error.getMessage());
							}
							alert(message.toString());
							//是否出现并行操作错误
							if(task.isCurrentError()){
								getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
							}
						}
						return;
					} else {
						getContext().bubbleMessage(new MsgResponse(true));
					}
				}
			});
			getContext().bubbleMessage(request);
			
//			if(!task.isSuccess()){
//				if(CheckIsNull.isNotEmpty(task.getErrors())){
//					StringBuilder message = new StringBuilder();
//					for(InventoryAllocateTask.Error error : task.getErrors()){
//						if(message.length() > 0){
//							message.append(";");
//						}
//						message.append(error.getMessage());
//					}
//					alert(message.toString());
//					//是否出现并行操作错误
//					if(task.isCurrentError()){
//						getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
//					}
//				}
//				return;
//			}
//			if(hasAuthForStore(info.getDestinationStoreId())){ //有入库权限
//				getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
//			}
//			else{ //无入库权限
//				getContext().bubbleMessage(new MsgResponse(true));
//			}
		}

	}

	/**
	 * 确定调入监听器
	 */
	private class AllocateInButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			// XXX 先货位
			// 1、获得所有调出商品的货位信息  	2、按照商品分组 	3、按照生产日期分组
//			InventoryAllocateItemDet, GetAllocateItemDetBySheetIdKey
			
			PageController pc = new PageController(AllocateInShelfInfoProcessor.class, AllocateInShelfInfoRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.valueOf(sheetId), info.getDestinationStoreId());
			MsgRequest request = new MsgRequest(pci, "货位分配");
			request.setResponseHandler(new ResponseHandler() {
				
				@SuppressWarnings("unchecked")
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					if (null == returnValue) return;
					List<AllocateInDistributeItem> dItemList = (List<AllocateInDistributeItem>)returnValue;
					InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(sheetId));
					
					List<InventoryAllocateTask.Item> itemList = new ArrayList<InventoryAllocateTask.Item>();
					for (AllocateInDistributeItem dItem : dItemList) {
						InventoryAllocateTask.Item item = null;
						for (DistributeInventoryItemDet iDet : dItem.getInventoryDetItems()) {
							item = task.new Item();
							item.setCount(iDet.getCount());
							item.setId(iDet.getId());
							item.setProduceDate(iDet.getProduceDate());
							item.setSheetId(GUID.valueOf(sheetId));
							item.setShelfId(iDet.getShelfId());
							item.setShelfNo(iDet.getShelfNo());
							item.setStockId(iDet.getStockId());
							item.setTiersNo(iDet.getTiersNo());
							item.setStoreId(info.getDestinationStoreId());
							itemList.add(item);
						}
					}
					task.setItems(itemList.toArray(new InventoryAllocateTask.Item[0]));
					try	{
						getContext().handle(task, InventoryAllocateTask.Method.AllocateIn);
					} catch (Throwable t) {
						t.printStackTrace();
						alert(t.getMessage());
						return;
					}
					if(!task.isSuccess()){
						if(CheckIsNull.isNotEmpty(task.getErrors())){
							StringBuilder message = new StringBuilder();
							for(InventoryAllocateTask.Error error : task.getErrors()){
								if(message.length() > 0){
									message.append(";");
								}
								message.append(error.getMessage());
							}
							alert(message.toString());
							//是否出现并行操作错误
							if(task.isCurrentError()){
								getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
							}
						}
						return;
					} else {
						getContext().bubbleMessage(new MsgResponse(true));
					}
				}
			});
			getContext().bubbleMessage(request);
		}

	}

}
