package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.deliver.entity.DeliverItem;
import com.spark.psi.publish.deliver.entity.DeliverListEntity;
import com.spark.psi.publish.deliver.key.GetDeliverListKey;

public class ExceptionDeliverListPageProcessor<Item> extends
	DeliverListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	
	public static enum ColumnName {
		billsNo, orderCount, station, deliveryPerson, sendDate,confirmArrivePerson, arrivedDate
	}
	
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetDeliverListKey key = new GetDeliverListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		key.setSearchText(search.getText());
		key.setStatus(DeliverStatus.Exception, DeliverStatus.Handled);
		ListEntity<DeliverItem> listEntity = context.find(DeliverListEntity.class, key);
		if (null == listEntity) return null;
		setListCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new DeliverItem[0]);
	}

	@Override
	protected void advanceSearchAction() {
		// TODO Auto-generated method stub
		
	}

}
