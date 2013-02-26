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
 * <p>��������ϸ���ε�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-24
 */

public class ProcessingSheetDetailProcessor extends AllocateSheetDetailBaseProcessor{

	/**
	 * ���ID
	 */
	public final static String ID_Button_InOrOut = "Button_InOrOut";

	/**
	 * ���
	 */
	private Button inOrOutButton;

	/**
	 * ҳ���ʼ��
	 * 
	 * @param context ������
	 */
	public void process(Situation context){
		super.process(context);
		//ҳ��ײ���ť��
		inOrOutButton = this.createControl(ID_Button_InOrOut, Button.class, JWT.NONE);
		//
		if(showAllocateOutButton()){ //�г���Ȩ��
			inOrOutButton.setText("ȷ������");
			inOrOutButton.addActionListener(new AllocateOutButtonListener());
		}
		else if(showAllocateInButton()){ //�����Ȩ��
			inOrOutButton.setText("ȷ������");
			inOrOutButton.addActionListener(new AllocateInButtonListener());
		}
		else{ //��Ȩ����ע��
			inOrOutButton.dispose();
		}

	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context){
		super.postProcess(context);

	}

	/**
	 * �Ƿ���ʾȷ��������ť
	 */
	private boolean showAllocateOutButton(){
		return InventoryAllocateStatus.Allocating.equals(info.getStatus()) && hasAuthForStore(info.getSourceStoreId());
	}

	/**
	 * �Ƿ���ʾȷ�����밴ť
	 */
	private boolean showAllocateInButton(){
		return InventoryAllocateStatus.AllocateOut.equals(info.getStatus())
		        && hasAuthForStore(info.getDestinationStoreId());
	}

	/**
	 *���Ƿ���в���ָ���ֿ��Ȩ�� 
	 *
	 *@return true: ��Ȩ�� �� false:��Ȩ�� 
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
	 * ȷ������������
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
			MsgRequest request = new MsgRequest(pci, "��λ����");
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
							//�Ƿ���ֲ��в�������
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
//					//�Ƿ���ֲ��в�������
//					if(task.isCurrentError()){
//						getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
//					}
//				}
//				return;
//			}
//			if(hasAuthForStore(info.getDestinationStoreId())){ //�����Ȩ��
//				getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
//			}
//			else{ //�����Ȩ��
//				getContext().bubbleMessage(new MsgResponse(true));
//			}
		}

	}

	/**
	 * ȷ�����������
	 */
	private class AllocateInButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			// XXX �Ȼ�λ
			// 1��������е�����Ʒ�Ļ�λ��Ϣ  	2��������Ʒ���� 	3�������������ڷ���
//			InventoryAllocateItemDet, GetAllocateItemDetBySheetIdKey
			
			PageController pc = new PageController(AllocateInShelfInfoProcessor.class, AllocateInShelfInfoRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.valueOf(sheetId), info.getDestinationStoreId());
			MsgRequest request = new MsgRequest(pci, "��λ����");
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
							//�Ƿ���ֲ��в�������
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
