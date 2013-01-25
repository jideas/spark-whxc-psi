package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.OrderInfo;

/**
 * 
 * <p>∂©µ•œÍ«È</p>
 *


 *
 
 * @version 2012-3-1
 */
public abstract class OrderInfoImpl2<T extends OrderFather> extends EntityToFather<T> implements OrderInfo{
	public OrderInfoImpl2(Context context, T t) {
		super(context, t);
	}

	public abstract OrderAction[] getActions();

	public double getAmount() {
		return getEntity().getTotalAmount();
	}

	public String getApproverLabel() {
		return getEntity().getExamin();
	}

	public abstract ContactBookInfo getConsigneeInfo();

	public ContactBookInfo getContactBookInfo() {
		if(null == getEntity().getContactId()){
			return null;
		}
		return getContext().find(ContactBookInfo.class, getEntity().getContactId());
	}

	public long getDeliveryDate() {
		return getEntity().getDeliveryDate();
	}

	public String getDenyCause() {
		return getEntity().getReturnCause();
	}

	public GUID getDeptId() {
		return getEntity().getDeptId();
	}

	public String getRemark() {
		return getEntity().getRemark();
	}

	public String getOrderNumber() {
		return getEntity().getOrderNumber();
	}

	public abstract OrderStatus getOrderStatus();

	public abstract OrderType getOrderType();

	public PartnerInfo getPartnerInfo() {
		return getContext().find(PartnerInfo.class, getEntity().getPartnerId());
	}

	public String getStopCause() {
		return getEntity().getReturnCause();
	}

	public boolean isStoped() {
		return getEntity().isStoped();
	}

	public EmployeeInfo getCreator() {
		return getContext().find(EmployeeInfo.class,
				getEntity().getCreatorId());
	}	
}
