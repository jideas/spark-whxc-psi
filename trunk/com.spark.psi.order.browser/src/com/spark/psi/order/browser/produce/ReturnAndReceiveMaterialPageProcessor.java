package com.spark.psi.order.browser.produce;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseListPageProcessor;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;
import com.spark.psi.publish.produceorder.task.ReceiveTask;
import com.spark.psi.publish.produceorder.task.ReturnTask;

public class ReturnAndReceiveMaterialPageProcessor<TItem> extends
		BaseListPageProcessor<TItem> {
	
	public static final String ID_Label_OrderNo = "Label_OrderNo";
	public static final String ID_Button_Confirm = "Button_Confirm";

	public static enum Type {
		RETURN,
		RECEIVE
	}
	
	public static enum ColumnName {
		materialName, count, receivedCount, returnedCount, currentReturnCount, currentReceivCount, storeName
	}
	
	public static enum TableExtraValueName {
		itemId, count, storeId, materialId
	}
	
	private ProduceOrderInfo orderInfo   = null;
	private Type type = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		orderInfo = (ProduceOrderInfo) getArgument();
		type = (Type)getArgument2();
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		final Label label = createControl(ID_Label_OrderNo, Label.class);
		final Button button = createControl(ID_Button_Confirm, Button.class);
		
		label.setText("生产订单编号：" + orderInfo.getBillsNo());
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确认
				if (!validateTableData())return;
				
				if (type == Type.RECEIVE) {
					doReceive();
				} else {
					doReturn();
				}
				
				
			}
		});
	}
	
	private void doReceive() {
		ReceiveTask task = new ReceiveTask(orderInfo.getId());
		String[] rowIds = table.getAllRowId();
		List<ReceiveTask.Item> itemList = new ArrayList<ReceiveTask.Item>();
		ReceiveTask.Item item = null;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String rowId = rowIds[rowIndex];
			String currentCountStr = table.getEditValue(rowId, ColumnName.currentReceivCount.name())[0];
			double currentCount = 0;
			if (!StringUtils.isEmpty(currentCountStr)) {
				currentCount = DoubleUtil.strToDouble(currentCountStr);
				if (currentCount == 0) {
					continue;
				}
			} else {
				continue;
			}
			
			String[] baseValues = table.getExtraData(rowId, TableExtraValueName.itemId.name(), 
					TableExtraValueName.count.name(), TableExtraValueName.storeId.name(),
					TableExtraValueName.materialId.name());
			item = task.new Item(GUID.tryValueOf(baseValues[0]), currentCount, 
					GUID.tryValueOf(baseValues[2]), GUID.tryValueOf(baseValues[3]));
			itemList.add(item);
		}
		task.setItems(itemList.toArray(new ReceiveTask.Item[0]));
		task.setSheetNo(orderInfo.getBillsNo());
		getContext().handle(task);
		hint("申请领料成功。", new Runnable() {
			
			public void run() {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void doReturn() {
		ReturnTask task = new ReturnTask(orderInfo.getId());
		String[] rowIds = table.getAllRowId();
		List<ReturnTask.Item> itemList = new ArrayList<ReturnTask.Item>();
		ReturnTask.Item item = null;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String rowId = rowIds[rowIndex];
			String currentCountStr = table.getEditValue(rowId, ColumnName.currentReturnCount.name())[0];
			double currentCount = 0;
			if (!StringUtils.isEmpty(currentCountStr)) {
				currentCount = DoubleUtil.strToDouble(currentCountStr);
				if (currentCount == 0) {
					continue;
				}
			} else {
				continue;
			}
			
			String[] baseValues = table.getExtraData(rowId, TableExtraValueName.itemId.name(), 
					TableExtraValueName.count.name(), TableExtraValueName.storeId.name(),
					TableExtraValueName.materialId.name());
			item = task.new Item(GUID.tryValueOf(baseValues[0]), currentCount, 
					GUID.tryValueOf(baseValues[2]), GUID.tryValueOf(baseValues[3]));
			itemList.add(item);
		}
		task.setItems(itemList.toArray(new ReturnTask.Item[0]));
		task.setSheetNo(orderInfo.getBillsNo());
		getContext().handle(task);
		hint("申请退料成功。", new Runnable() {
			
			public void run() {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	private boolean validateTableData() {
		String[] rowIds = table.getAllRowId();
		String columnName = type == Type.RECEIVE ? ColumnName.currentReceivCount.name() : ColumnName.currentReturnCount.name();
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String currentCountStr = table.getEditValue(rowIds[rowIndex], columnName)[0];
			if (StringHelper.isNotEmpty(currentCountStr)) {
				return true;
			}
		}
		if (type == Type.RECEIVE) {
			alert("请填写本次领料数量。");
		} else {
			alert("请填写本次退料数量。");
		}
		return false;
	}
	
	@Override
	public String getElementId(Object element) {
		ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return orderInfo.getMaterials();
	}
	
	@Override
	public SNameValue[] getExtraData(Object element) {
		ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem)element;
		return new SNameValue[] { new SNameValue(TableExtraValueName.itemId.name(), item.getId().toString()),
				new SNameValue(TableExtraValueName.count.name(), item.getCount() + ""),
				new SNameValue(TableExtraValueName.storeId.name(), item.getStoreId().toString()),
				new SNameValue(TableExtraValueName.materialId.name(), item.getMaterialId().toString())};
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (ColumnName.currentReturnCount.name().equals(columnName)) {
			return "";
		}
		if (ColumnName.currentReceivCount.name().equals(columnName)) {
			return "";
		}
		return null;
	}

}
