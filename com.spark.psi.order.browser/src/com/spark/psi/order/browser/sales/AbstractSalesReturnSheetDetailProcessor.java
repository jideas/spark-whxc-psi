/**
 * 
 */
package com.spark.psi.order.browser.sales;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.order.browser.internal.SalesReturnGoodsItemImpl;
import com.spark.psi.order.browser.util.OrderDetailProcessor;
import com.spark.psi.order.browser.util.SalesUtil;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask;

/**
 * �����˻�����ϸ������
 *
 */
public abstract class AbstractSalesReturnSheetDetailProcessor extends OrderDetailProcessor<SalesReturnGoodsItem>{
	
	private StoreItem storeItem;
	
	public void init(Situation context) {
		super.init(context);
		StoreSource storeSource = new StoreSource(true);
		Object[] elements = storeSource.getElements(null);
		if(elements != null && elements.length == 1){
			storeItem = (StoreItem)elements[0];
		}
	}
	
	@Override
	public final String getValue(Object element, String columnName) {
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			SalesReturnGoodsItem item = (SalesReturnGoodsItem) element;
			if(Cloumns.ReturnStore.name().equals(columnName)){
				return String.valueOf(item.getStoreId() == null?"":item.getStoreId());
			}
			if(Cloumns.ReturnCount.name().equals(columnName)){
				return item.getCount()+"";
			}
			if(Cloumns.ReturnPrice.name().equals(columnName)){
				return item.getPrice()+"";
			}
			if(Cloumns.ReturnAmount.name().equals(columnName)){
				return item.getAmount()+"";
			}
		}
		return null;
	}
	
	protected final void addGoods() {
		this.createControl(ID_BUTTON_ADDGOODS, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MsgRequest request = CommonSelectRequest
								.createSelectMaterialsRequest(null);
						request.setResponseHandler(new ResponseHandler() {
							public void handle(Object returnValue,
									Object returnValue2, Object returnValue3,
									Object returnValue4) {
								MaterialsItemInfo[] itemList = (MaterialsItemInfo[]) returnValue;
								for (MaterialsItemInfo item : itemList) {
									SalesReturnGoodsItem gi = SalesUtil
											.getSalesReturnMaterialsItem(item);
									if(storeItem != null) { //Ĭ�ϲֿ⣨ֻ��һ���ֿ⣩
										((SalesReturnGoodsItemImpl)gi).setStoreId(storeItem.getId());
										((SalesReturnGoodsItemImpl)gi).setStoreName(storeItem.getName());
									}
									table.addRow(gi);
								}
								table.renderUpate();
							}
						});
						getContext().bubbleMessage(request);
					}
				});
	}

	protected final void save() {
		this.createButton(" ���� ").addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addSaveAction(true);
			}
		});

	}
	
	@Override
	protected final void lookInventory(String rowId, Point location) {
		inventoryInfoWindow.refresh(getGoodsItem(rowId).getGoodsItemId(), null,location,false);
	}

	@Override
	protected final String getTableNullTitle() {
		return "�������Ҫ�����˻��Ĳ��ϣ�";
	}

	@Override
	protected final String validateTitle(
			com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns columnsEnum,
			com.spark.psi.order.browser.util.OrderDetailProcessor.ValiDateErrorEnum error) {
		switch (columnsEnum) {
		case ReturnStore:
			switch (error) {
			case NULL:
				return "�˻��ֿⲻ��Ϊ�գ�";
			case FormatException:
				return "ϵͳ�����뼰ʱ�����Ա��ϵ";
			default:
				return null;
			}
		case ReturnCount:
			switch (error) {
			case NULL:
				return "�˻���������Ϊ��";
			case Less_than_zero:
				return "�˻������������0";
			case Zero:
				return "�˻������������0";
			case Greater_than_Max:
				return "�����˻���������";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.9��97";
			default:
				return null;
			}
		case ReturnPrice:
			switch (error) {
			case NULL:
				return "�˻����۲���Ϊ��";
			case Less_than_zero:
				return "�˻����۲���С��0";
			case Greater_than_Max:
				return "�����˻����۹���";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		case ReturnAmount:
			switch (error) {
			case NULL:
				return "�˻�����Ϊ��";
			case Less_than_zero:
				return "�˻�����С��0";
			case Greater_than_Max:
				return "�����˻�������";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		default:
			return null;
		}
	}
	
	protected final void fillTaskData(UpdateSalesReturnTask task) {
		task.setMemo(memoText.getText());
		List<com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem> list = new ArrayList<com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem>();
		com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem item;
		double totalAmount = 0;
		for (String rowId : table.getAllRowId()) {
			SalesReturnGoodsItem goods = getGoodsItem(rowId);
			item = new com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem();
			item.setCount(Double.valueOf(table.getEditValue(rowId,
					Cloumns.ReturnCount.name())[0]));
			item.setGoodsItemId(goods.getGoodsItemId());
			item.setPrice(Double.valueOf(table.getEditValue(rowId,
					Cloumns.ReturnPrice.name())[0]));
			item.setAmount(item.getPrice() * item.getCount());
			item.setStoreId(GUID.valueOf(table.getEditValue(rowId,
					Cloumns.ReturnStore.name())[0]));
			totalAmount += item.getCount() * item.getPrice();
			list.add(item);
		}
		task.setAmount(totalAmount);
		task.setSalesReturnGoodsItems(list
				.toArray(new com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem[list
						.size()]));
	}
}
