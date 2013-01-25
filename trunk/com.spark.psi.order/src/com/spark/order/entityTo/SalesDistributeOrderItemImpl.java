package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.sales2.SalesOrderInfo2;
import com.spark.order.util.SaleDistrbuteUtil;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.order.entity.SalesDistributeOrderItem;

/**
 * <p>���۴���������б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class SalesDistributeOrderItemImpl extends EntityToFather<SalesOrderInfo2> implements SalesDistributeOrderItem{

	public SalesDistributeOrderItemImpl(Context context, SalesOrderInfo2 t) {
		super(context, t);
	}

	public OrderAction[] getActions() {
		if(SaleDistrbuteUtil.isDistrbuting(getContext().getLogin().getID(), getEntity().getRecid())) {
			return null;
		} else {
			return new OrderAction[]{OrderAction.Allocate};
		}
	}

	public String getAddress() {
		return getEntity().getAddress();
	}

	public GUID getCustomerId() {
		return getEntity().getPartnerId();
	}

	public String getCustomerName() {
		return getEntity().getPartnerName();
	}

	public String getCustomerShortName() {
		return getEntity().getPartnerShortName();
	}

	public long getDeliveryDate() {
		return getEntity().getDeliveryDate();
	}

	public String getOrderNumber() {
		return getEntity().getOrderNumber();
	}
	
}
