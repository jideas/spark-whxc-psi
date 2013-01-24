package com.spark.psi.inventory.browser.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.ExcelReadHelper;
import com.spark.common.utils.excel.ExcelReadHelper2007;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoByCodeKey;
import com.spark.psi.publish.base.store.entity.InitInventoryDetItem;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.inventory.task.SaveInitInventoryTask;

public class InitImportFileSelectProccessor extends BaseFormPageProcessor {

	public static final String ID_Text_FilePath = "Text_FilePath";
//	public static final String ID_Button_Select = "Button_Select";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	public static final String ID_FileChooser = "FileChooser";
	
	private StoreInfo storeInfo = null;
	
	
	@Override
	public void init(Situation context) {
		storeInfo = (StoreInfo)getArgument();
	}

	@Override
	public void process(Situation context) {
//		final Button selectButton = createButtonControl(ID_Button_Confirm);
		final Button confirmButton = createButtonControl(ID_Button_Confirm);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		final FileChooser fc = this.createControl(ID_FileChooser, FileChooser.class);
		
		
		fc.setRelativeTarget(confirmButton, ActionListener.class);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 材料编码  数量  单价 货位号 层号 生产日期
				ExcelReadHelper read = read(fc);
				if (null == read) return;
				List<String[]> initInfoList = read.getData();
				//SaveInitInventoryTask task = new SaveInitInventoryTask();
				doSaveAndEnable(initInfoList);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void doSaveAndEnable(List<String[]> initInfoList) {
		List<InitInventoryItem> itemList = new ArrayList<InitInventoryItem>();
		try {
			itemList = getItemList(initInfoList);
			if (null == itemList) return;
		} catch(Exception e) {
			e.printStackTrace();
			alert("导入失败，内容格式有误。");
			return;
		} 
		SaveInitInventoryTask task = new SaveInitInventoryTask(storeInfo.getId(), itemList.toArray(new InitInventoryItem[0]));
		task.setEnable(true);
		getContext().handle(task);
		
		ChangeStoreStatusTask enableTask = new ChangeStoreStatusTask(storeInfo.getId(), StoreStatus.ENABLE);
		getContext().handle(enableTask);
		hint("启用成功", new Runnable(){
			public void run(){
				getContext().bubbleMessage(new MsgResponse(true, true));
			}
		});
		
		//hint("导入成功！");
		//getContext().bubbleMessage(new MsgResponse(true,true));
	}
	
	
	private List<InitInventoryItem> getItemList(List<String[]> initInfoList) {
		List<InitInventoryItem> itemList = new ArrayList<InitInventoryItem>();
		Map<String,List<String[]>> materialItems = getInvetnoryItemGroupByMaterialCode(initInfoList);
		Iterator<String> itemIt = materialItems.keySet().iterator();
		while(itemIt.hasNext()) {
			//MaterialsInfo, GetMaterialsInfoByCodeKey
			//String[] values = materialItems.get(itemIt.next());
			String materialCode = itemIt.next();
			GetMaterialsInfoByCodeKey key = new GetMaterialsInfoByCodeKey(materialCode);
			MaterialsInfo info = getContext().find(MaterialsInfo.class, key);
			
			if (null == info) {
				alert("系统中不存在编号为" + materialCode + "的材料");
				return null;
			}
			
			InitInventoryItem inventoryItem = new InitInventoryItem();
			inventoryItem.setStockId(info.getItems()[0].getId());
			inventoryItem.setCode(info.getCode());
			inventoryItem.setName(info.getName());
			inventoryItem.setSpec(info.getItems()[0].getMaterialSpec());
			inventoryItem.setUnit(info.getItems()[0].getUnit());
			inventoryItem.setStockNo(info.getItems()[0].getMaterialNo());
			inventoryItem.setScale(2);
			List<String[]> detStrList = materialItems.get(materialCode);
			double price = DoubleUtil.strToDouble(detStrList.get(0)[2]);
			
			double count = 0.0;
			double amount = 0.0;
			
			List<InitInventoryDetItem> detList = new ArrayList<InitInventoryDetItem>();
			InitInventoryDetItem detItem = null;
			for (String[] values : detStrList) {
				double detCount = DoubleUtil.strToDouble(values[1]);
				int detShelfNo = (int)(DoubleUtil.strToDouble(values[3], 0).doubleValue());
				int detTiersNo = (int)(DoubleUtil.strToDouble(values[4], 0).doubleValue());
				
				long produceDate = DateUtil.convertStringToDate(values[5]);
				count += detCount;
				amount += DoubleUtil.mul(price, detCount);
				detItem = getExistDet(detList, detShelfNo, detTiersNo, produceDate);
				if (null == detItem) {
					detItem = new InitInventoryDetItem();
					detItem.setId(GUID.randomID());
					detItem.setCount(detCount);
					detItem.setProduceDate(produceDate);
					boolean valiShelf = false;
					boolean valiTier = false;
					for(ShelfItem si:storeInfo.getShelfItems())
					{
						if(si.getShelfNo()==detShelfNo)
						{
							detItem.setShelfId(si.getId());
							valiShelf = true;
							if (detTiersNo <= si.getTiersCount()) {
								valiTier = true;
							}
						}
					}
					if(!valiShelf)
					{
						alert("商品："+info.getCode()+"对应的货位号："+detShelfNo+"不存在，请检查！");
						return null;
					}
					if (!valiTier) {
						alert("商品："+info.getCode()+"对应的层号："+detTiersNo+"不存在，请检查！");
						return null;
					}
					
					detItem.setShelfNo(detShelfNo);
					detItem.setTiersNo(detTiersNo);
					detItem.setStockId(inventoryItem.getStockId());
				} else {
					detItem.setCount(detItem.getCount() + detCount);
				}
				detList.add(detItem);
			}
			inventoryItem.setCount(count);
			inventoryItem.setAmount(amount);
			inventoryItem.setAverageCost(price);
			inventoryItem.setInventoryDetItems(detList.toArray(new InitInventoryDetItem[0]));
			itemList.add(inventoryItem);
		}
		return itemList;
	}
	
	private InitInventoryDetItem getExistDet(List<InitInventoryDetItem> detList, int shelfNo, int tiersNo, long produceDate) {
		for (InitInventoryDetItem det : detList) {
			if (det.getShelfNo() == shelfNo
					&& det.getTiersNo() == tiersNo
					&& det.getProduceDate() == produceDate) {
				return det;
			}
		}
		return null;
	}
	
	private Map<String,List<String[]>> getInvetnoryItemGroupByMaterialCode(List<String[]> initInfoList) {
		// key: code; value: InitInventoryDetItemList
		Map<String, List<String[]>> materialItems = new HashMap<String, List<String[]>>();
		//List<InitInventoryItem> itemList = new ArrayList<InitInventoryItem>();
		for (String[] values : initInfoList) {
			//InitInventoryItem item = getInventoryItemByValue(values);
			//String code = DoubleUtil.getRoundStrWithOutQfw(DoubleUtil.strToDouble(values[0], 0), 0);
			String code = values[0];
			List<String[]> materialItemList = materialItems.get(code);
			if (null == materialItemList) {
				materialItemList = new ArrayList<String[]>();
				materialItemList.add(values);
				materialItems.put(code, materialItemList);
			} else {
				materialItemList.add(values);
			}
		}
		return materialItems;
	}
	
	private ExcelReadHelper read(FileChooser fc) {
		String text = fc.getFileName();
		if (CheckIsNull.isEmpty(text)) {
			return null;
		}
		if (!".xls".equals(text.substring(text.lastIndexOf(".")))&&!".xlsx".equals(text.substring(text.lastIndexOf(".")))) {
			alert("导入数据需要选择excel文件!");
			return null;
		}
		ExcelReadHelper read = null;
		if(".xls".equals(text.substring(text.lastIndexOf(".")))){
			read = new ExcelReadHelper();
		}else {
			read = new ExcelReadHelper2007();
		}
		if (CheckIsNull.isNotEmpty(text)) {
			InputStream input = null;
			try {
				input = fc.getInputStream(text);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				read.read(input, true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return read;
	}

}
