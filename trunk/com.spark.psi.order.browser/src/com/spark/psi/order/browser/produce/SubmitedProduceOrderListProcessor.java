package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderListEntity;
import com.spark.psi.publish.produceorder.key.GetProduceOrderListKey;

public class SubmitedProduceOrderListProcessor<Item> extends
	ProduceOrderListProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	
	public static enum ColumnName {
		code, count, createInfo, planFinishDate
	}
	@Override
	public String getElementId(Object element) {
		ProduceOrderItem item = (ProduceOrderItem)element;
		return item.getId().toString();
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] {Action.Approval.name()};
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Approval.name()};
	}
	
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetProduceOrderListKey key = new GetProduceOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		key.setSearchText(search.getText());
		key.setStatus(ProduceOrderStatus.Submited);
		ListEntity<ProduceOrderItem> listEntity = context.find(ProduceOrderListEntity.class, key);
		if (null == listEntity) return null;
		updateCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new ProduceOrderItem[0]);
	}

	private void updateCount(boolean isFirstPage, int count) {
		final Label countLabel = createControl(ID_Label_Count, Label.class);
		int size = count;
		if (!isFirstPage) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName) || Action.Approval.name().equals(actionName)) {
			PageController pc = new PageController(ProduceDetailProcessor.class, ProduceDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "∂©µ•œÍ«È");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}
}
