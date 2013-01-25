package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>流程工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
public final class OrderFactory {
	private OrderFactory(){
	}
	
	/**
	 * 获得指定类型单据流程控制器，指定类型单据无审核流程控制器返回null
	 * @param billsEnum
	 * @return IProcessManage
	 */
	public static IProcessManage getProcessManage(Context context, BillsEnum billsEnum){
		switch (billsEnum) {
		case SALE:
			return new SalesOrderProcessManageImpl(context);
		case SALE_CANCEL:
			return new SalesReturnProcessManageImpl(context);
		case SALE_PROMOTION:
			return new SalesPromotionProcessManageImpl(context);
		case SALE_DEPLOYMENT:
			return null;
		case PURCHASE:
			return new PurchaseOrderProcessManageImpl(context);
		case PURCHASE_CANCEL:
			return new PurchaseReturnProcessManageImpl(context);
		default:
			return null;
		}
	}
	
	public static IOrderAction getOrderAction(Context context, BillsEnum billsEnum){
		switch (billsEnum) {
		case SALE:
			return new SalesOrderActionImpl(context);
		case SALE_CANCEL:
			return new SalesReturnActionImpl(context);
		case SALE_PROMOTION:
			return null;
		case SALE_DEPLOYMENT:
			return null;
		case PURCHASE:
			return new PurchaseOrderActionImpl(context);
		case PURCHASE_CANCEL:
			return new PurchaseReturnActionImpl(context);
		default:
			return null;
		}
	}
}
