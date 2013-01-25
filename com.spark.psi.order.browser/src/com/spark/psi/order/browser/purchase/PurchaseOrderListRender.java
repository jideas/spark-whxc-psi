package com.spark.psi.order.browser.purchase;

import com.spark.common.components.table.STableColumn;
import com.spark.psi.order.browser.util.OrderListRender;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.order.entity.OrderItem;

/**
 * <p>
 * 销售订单列表父类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author modi
 * @version 2012-4-17
 */
abstract class PurchaseOrderListRender extends OrderListRender<OrderItem> {
	protected final static String supplierName = "供应商";
	@Override
	protected STableColumn getSTableColumn(Columns e) {
		if(Columns.PartnerName == e){
			STableColumn c = new STableColumn(e.name(), e.getWidth(), e.getAlign(),
					supplierName);
			c.setGrab(e.isGrab());
			c.setSortable(e.isSort());
			return c;
		}
		return super.getSTableColumn(e);
	}
	
	@Override
	protected boolean isEmployee() {
		LoginInfo login = getContext().find(LoginInfo.class);
		if(!login.hasAuth(Auth.Boss, Auth.PurchaseManager)){
			return true;
		}
		return false;
	}
}
