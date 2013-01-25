package com.spark.order.entityTo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;

/**
 * 内部数据转换成接口数据（接口层接口实现类父类）
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-14
 */
class EntityToFather<T extends EntityFather> {
	private final Context context;
	private final T t;
	public EntityToFather(Context context, T t) {
		super();
		this.context = context;
		this.t = t;
	}
	protected Context getContext() {
		return context;
	}
	protected T getEntity(){
		return t;
	}

	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");

	public String getCreatorLabel() {
		EntityFather e = (EntityFather) t;
		return e.getCreator() + "（" + sdf.format(new Date(e.getCreateDate()))
				+ "）";
	}

	public long getCreateDate() {
		return ((EntityFather) t).getCreateDate();
	}

	public GUID getId() {
		return ((EntityFather) t).getRecid();
	}

	//子类可能使用的到方法
	/**
	 * 根据订单数据库状态，获得前台页面状态（销售、采购订单专用）
	 * @param key
	 * @param key
	 * @return OrderStatus
	 */
	protected OrderStatus getOrderStatus(OrderEnum orderEnum, TypeEnum type, String key){
		OrderStatus orderstatus = null;
		StatusEnum status = StatusEnum.getstatus(key);
		switch (status) {
		case Submit:
			orderstatus = OrderStatus.Submit;
			break;
		case Approveing:
			orderstatus = OrderStatus.Approval_No;
			break;
		case Approve:
			orderstatus = OrderStatus.Approval_Yes;
			break;
		case Return:
			orderstatus = OrderStatus.Denied;
			break;
		case Store_N0:
			if(isOut(orderEnum, type, status)){
				orderstatus = OrderStatus.CheckingOut_No;
			}
			else{
				orderstatus = OrderStatus.CheckingIn_NO;
			}
			break;
		case Store_Part:
			if(isOut(orderEnum, type, status)){
				orderstatus = OrderStatus.CheckingOut_Part;
			}
			else{
				orderstatus = OrderStatus.CheckingIn_Part;
			}
			break;
		case Store_All:
			if(isOut(orderEnum, type, status)){
				orderstatus = OrderStatus.CheckedOut;
			}
			else{
				orderstatus = OrderStatus.CheckedIn;
			}
			break;
		case Finished:
			orderstatus = OrderStatus.finish;
			break;
		case Consignment_No:
			orderstatus = OrderStatus.CONSIGNMENT_NO;
			break;
		case Consignment_Yes:
			orderstatus = OrderStatus.CONSIGNMENT_YES;
			break;
		}
		return orderstatus;
	}
	
	/**
	 * 出库
	 * @param orderEnum
	 * @param type
	 * @param status
	 * @return boolean
	 */
	private static boolean isOut(OrderEnum orderEnum, TypeEnum type, StatusEnum status){
		if(OrderEnum.Purchase_Return == orderEnum){
			return true;
		}
		if(OrderEnum.Purchase == orderEnum && TypeEnum.CANCEL == type){
			return true;
		}
		if(OrderEnum.Sales == orderEnum && TypeEnum.CANCEL != type){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据订单数据库类型，获得前台页面类型（销售、采购订单专用）
	 * @param key
	 * @return OrderType
	 */
	protected OrderType getOrderType(OrderEnum orderEnum, String key){
		OrderType orderType = null;
		TypeEnum type = TypeEnum.getType(key);
		switch (orderEnum) {
		case Purchase:
			switch (type) {
			case BUY_SPORADIC:
				orderType = OrderType.Purchase_SPORADIC;
				break;
			case CANCEL:
				orderType = OrderType.Purchase_Return;
				break;
			case ON_LINE:
				orderType = OrderType.ON_LINE;
				break;
			case ON_LINE_DIRECT:
				orderType = OrderType.ON_LINE_DIRECT;
				break;
			case PLAIN:
				orderType = OrderType.PLAIN;
				break;
			}
			break;
		case Purchase_Return:
			switch (type) {
			case CANCEL:
				orderType = OrderType.Purchase_Return;
				break;
			}
			break;
		case Sales:
			switch (type) {
			case PLAIN:
				orderType = OrderType.PLAIN;
				break;
			case ON_LINE:
				orderType = OrderType.ON_LINE;
				break;
			case CANCEL:
				orderType = OrderType.SALES_RETURN;
				break;
			}
			break;
		case Sales_Return:
			switch (type) {
			case CANCEL:
				orderType = OrderType.SALES_RETURN;
				break;
			}
			break;
		}
		return orderType;
	}
}
