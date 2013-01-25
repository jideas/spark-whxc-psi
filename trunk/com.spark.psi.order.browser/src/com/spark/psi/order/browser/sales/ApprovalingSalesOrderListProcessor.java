/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;

/**
 * 待审批的销售订单列表处理器
 *
 */
public class ApprovalingSalesOrderListProcessor extends SalesOrderListProcessor<OrderItem>{

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		GetSalesOrderListKey key = new GetSalesOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setOrderStatus(OrderStatus.Approval_No);
//		key.setQueryScope(queryScope)
//		key.setQueryTerm(queryTerm)
		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//排序
		setLimitKeySort(key, tablestatus);
		
		OrderListEntity entity = getContext().find(OrderListEntity.class, key);
		return entity.getItemList().toArray();
	}
	
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Approval.name()};
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}