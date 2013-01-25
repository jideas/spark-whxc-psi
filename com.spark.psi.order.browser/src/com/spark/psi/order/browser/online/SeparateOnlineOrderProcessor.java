package com.spark.psi.order.browser.online;

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
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.task.SplitOnlineOrderTask;

public class SeparateOnlineOrderProcessor<TItem> extends
		BaseListPageProcessor<TItem> {
	public static final String ID_Label_CustomerName = "Label_CustomerName";
	public static final String ID_Label_MobileNo = "Label_MobileNo";
	public static final String ID_Label_BookingDate = "Label_BookingDate";
	public static final String ID_Label_TotalAmount = "Label_TotalAmount";
	public static final String ID_Button_Save = "Button_Save";
	public static final String ID_Button_Cancel = "Button_Cancel";
	public static enum ColumnName {
		goodsName, goodsCode, goodsSpec, unit, preCount, price, separateCount, amount
	}
	
	public static enum TableValueExtraName {
		itemId, goodsName, goodsCode, goodsSpec, unit, preCount, preAmount, price
	}
	private OnlineOrderInfo orderInfo = null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		orderInfo = (OnlineOrderInfo)getArgument();
	}
	
	@Override
	public void process(Situation context) {
		super.process(context);
		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "OnlineOrder.handleTableDataChange");
		final Label nameLabel = createLabelControl(ID_Label_CustomerName);
		final Label mobileLable = createLabelControl(ID_Label_MobileNo);
		final Label dateLabel = createLabelControl(ID_Label_BookingDate);
		final Button saveButton = createButtonControl(ID_Button_Save);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		
		nameLabel.setText(orderInfo.getRealName());
		mobileLable.setText(orderInfo.getConsigneeTel());
		dateLabel.setText(DateUtil.dateFromat(orderInfo.getCreateDate()));
		
		searateAction(saveButton);
		cancelAction(cancelButton);
	}

	private void searateAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定拆分
				if (!validate_This()) return;
				SplitOnlineOrderTask task = new SplitOnlineOrderTask();
				List<SplitOnlineOrderTask.Item> itemList = new ArrayList<SplitOnlineOrderTask.Item>();
				String[] rowIds = table.getAllRowId();
				double totalSepAmount = 0.0;
				for (String rowId : rowIds) {
					String sepCountStr = table.getEditValue(rowId, ColumnName.separateCount.name())[0];
					if (StringUtils.isEmpty(sepCountStr)) continue;
					
					String[] extraValues = table.getExtraData(rowId, TableValueExtraName.itemId.name(),
							TableValueExtraName.preCount.name(), TableValueExtraName.preAmount.name(),
						    TableValueExtraName.price.name());
					SplitOnlineOrderTask.Item item = task.new Item();
					item.setId(GUID.tryValueOf(extraValues[0]));
					item.setCount(DoubleUtil.strToDouble(extraValues[1]));
					item.setAmount(DoubleUtil.strToDouble(extraValues[2]));
					double price = DoubleUtil.strToDouble(extraValues[3]);
					double sepCount = DoubleUtil.strToDouble(sepCountStr);
					double sepAmount = price * sepCount;
					item.setSplitingCount(sepCount);
					item.setSplitingAmount(sepAmount);
					totalSepAmount += sepAmount;
					itemList.add(item);
				}
				task.setItems(itemList.toArray(new SplitOnlineOrderTask.Item[0]));
				task.setTotalAmount(orderInfo.getTotalAmount());
				task.setSplitingAmount(totalSepAmount);
				task.setId(orderInfo.getId());
				try {
					getContext().handle(task);
					boolean isSuccess = true;
					getContext().bubbleMessage(new MsgResponse(true, isSuccess));
				} catch (Throwable th) {
					th.printStackTrace();
					alert(th.getMessage());
				}
			}
		});
	}
	
	private void cancelAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 取消
				boolean isSuccess = false;
				getContext().bubbleMessage(new MsgResponse(true, isSuccess));
			}
		});
	}
	
	@Override
	public String getElementId(Object element) {
		OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
		return item.getId().toString();
	}
	
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return orderInfo.getItems();
	}
	
	@Override
	public SNameValue[] getExtraData(Object element) {
		OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
		return new SNameValue[] {
				new SNameValue(TableValueExtraName.itemId.name(), item.getId().toString()),
				new SNameValue(TableValueExtraName.goodsName.name(), item.getGoodsName()),
				new SNameValue(TableValueExtraName.goodsCode.name(), item.getGoodsCode()),
				new SNameValue(TableValueExtraName.goodsSpec.name(), item.getGoodsSpec()),
				new SNameValue(TableValueExtraName.unit.name(), item.getUnit()),
				new SNameValue(TableValueExtraName.preCount.name(), "" + item.getCount()),
				new SNameValue(TableValueExtraName.preAmount.name(), "" + item.getAmount()),
				new SNameValue(TableValueExtraName.price.name(), "" + item.getPrice())
		};
	}
	
	@Override
	public String getValue(Object element, String columnName) {
		if (ColumnName.separateCount.name().equals(columnName)) {
			return "";
		}
		return null;
	}
	
	private boolean validate_This() {
		String[] rowIds = table.getAllRowId();
		int emptySepContRowCount = 0;
		for (String rowId : rowIds) {
			String preCountStr = table.getExtraData(rowId, ColumnName.preCount.name())[0];
			String sepCountstr = table.getEditValue(rowId, ColumnName.separateCount.name())[0];
			if (StringUtils.isEmpty(sepCountstr)) {
				emptySepContRowCount++;
				continue;
			}
			if (DoubleUtil.strToDouble(sepCountstr) > DoubleUtil.strToDouble(preCountStr)) {
				alert("拆分数量不能大于原订单数量。");
				return false;
			}
		}
		if (emptySepContRowCount == rowIds.length) {
			alert("请填写拆分数量。");
			return false;
		}
		return true;
	}
}
