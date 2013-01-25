package com.spark.psi.order.browser.sales;

import com.spark.psi.order.browser.util.OrderListRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.order.entity.OrderItem;

/**
 * <p>
 * ���۶����б���
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author modi
 * @version 2012-4-17
 */
abstract class SalesOrderListRender extends OrderListRender<OrderItem> {
	@Override
	protected boolean isEmployee() {
		LoginInfo login = getContext().find(LoginInfo.class);
		if(!login.hasAuth(Auth.Boss, Auth.SalesManager)){
			return true;
		}
		return false;
	}
}
