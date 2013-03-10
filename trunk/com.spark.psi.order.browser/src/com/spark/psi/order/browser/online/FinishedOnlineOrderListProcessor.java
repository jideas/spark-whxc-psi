package com.spark.psi.order.browser.online;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;

public class FinishedOnlineOrderListProcessor<Item> extends
	OnlinOrderListProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Search = "Search";
	public static final String ID_Search_Advanced = "Search_Advanced";
	
	public static enum ColumnName {
		code, customerName, amount, bookingTime, isToDoor,station, arriveTime, finishTime,isVantagesGoodsOrder
	}
	
	private Label countLabel       =  null;
	private Label amountLabel      =  null;
	
	private SSearchText2 search   = null;
	private AdvanceSearchCondition advanceCondition  = null;
	
	@Override
	public void process(Situation context) {
		super.process(context);
		countLabel = createControl(ID_Label_Count, Label.class);
		amountLabel = createControl(ID_Label_Amount, Label.class);
		
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				advanceCondition = null;
				table.render();
			}
		});
		final Button advanceButton = createControl(ID_Search_Advanced, Button.class);
		advanceButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = OnlineCommon.createAdvanceRequest();
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							advanceCondition = (AdvanceSearchCondition) returnValue;
							table.render();
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}
	
	@Override
	public String getElementId(Object element) {
		OnlineOrderItem item = (OnlineOrderItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		if (null != advanceCondition) {
			key.setSearchText(null);
			GetOnlineOrderListKey.AdvanceValues av = key.new AdvanceValues();
			av.setBillsNo(advanceCondition.getBillsNo());
			av.setCreateDateBegin(advanceCondition.getCreateDateBegin());
			av.setCreateDateEnd(advanceCondition.getCreateDateEnd());
			av.setDeliveryeDateBegin(advanceCondition.getDeliveryeDateBegin());
			av.setDeliveryeDateEnd(advanceCondition.getDeliveryeDateEnd());
			av.setRealName(advanceCondition.getRealName());
			av.setStationName(advanceCondition.getStationName());
			av.setDeliverTime(advanceCondition.getDeliverTime());
			key.setAdvanceValues(av);
		} else {
			key.setSearchText(search.getText());
		}
		key.setStatus(OnlineOrderStatus.Finished);
		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
		if (null != listEntity) {
			int size = listEntity.getItemList().size();
			double totalAmount = DoubleUtil.round(getTotalAmount(listEntity.getItemList()),2);
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
				String preAmount = amountLabel.getText();
				if (StringHelper.isNotEmpty(preAmount)) {
					totalAmount = DoubleUtil.sum(totalAmount, DoubleUtil.strToDouble(preAmount));
				}
			}
			countLabel.setText(String.valueOf(size));
			amountLabel.setText(DoubleUtil.getRoundStr(totalAmount));
			return listEntity.getItemList().toArray(new OnlineOrderItem[0]);
		} else {
			return null;
		}
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			// ���������
			PageController pc = new PageController(OnlineOrderDetailProcessor.class, OnlineOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "��������");
			getContext().bubbleMessage(request);
		}
	}
	
	private double getTotalAmount(List<OnlineOrderItem> itemList) {
		double totalAmount = 0;
		for (OnlineOrderItem item : itemList) {
			totalAmount += item.getTotalAmount();
		}
			
		return totalAmount;
	}

}
