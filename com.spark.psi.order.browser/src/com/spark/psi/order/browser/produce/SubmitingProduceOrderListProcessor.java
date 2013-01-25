package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderListEntity;
import com.spark.psi.publish.produceorder.key.GetProduceOrderListKey;
import com.spark.psi.publish.produceorder.task.UpdateProduceOrderStatusTask;

public class SubmitingProduceOrderListProcessor<Item> extends
	ProduceOrderListProcessor<Item> {
	
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Button_Add = "Button_Add"; 

	public static enum ColumnName {
		code, count, planFinishDate, status
	}
	@Override
	public void process(final Situation context) {
		super.process(context);
		final Button addButton    = createControl(ID_Button_Add, Button.class); 
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewProduceOrderProcessor.class, NewProduceOrderRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "添加商品");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				context.bubbleMessage(request);
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		ProduceOrderItem item = (ProduceOrderItem)element;
		return item.getId().toString();
	}
	
	

	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] {Action.Submit.name()};
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name()};
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetProduceOrderListKey key = new GetProduceOrderListKey(0, Integer.MAX_VALUE, true);
		key.setSearchText(search.getText());
		key.setStatus(ProduceOrderStatus.Submiting, ProduceOrderStatus.Reject);
		ListEntity<ProduceOrderItem> listEntity = context.find(ProduceOrderListEntity.class, key);
		if (null == listEntity) return null;
		updateCount(listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new ProduceOrderItem[0]);
	}
	
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Submit.name().equals(actionName)) {
			UpdateProduceOrderStatusTask task = new UpdateProduceOrderStatusTask(GUID.tryValueOf(rowId));
			getContext().handle(task, UpdateProduceOrderStatusTask.Method.Submit);
			table.render();
		} else if (Action.Detail.name().equals(actionName)) {
			PageController pc = new PageController(ProduceDetailProcessor.class, ProduceDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "订单详情");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	private void updateCount(int count) {
		final Label countLabel = createControl(ID_Label_Count, Label.class);
		countLabel.setText("" + count);
	}

}
