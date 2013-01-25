package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>���̹���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
public final class OrderFactory {
	private OrderFactory(){
	}
	
	/**
	 * ���ָ�����͵������̿�������ָ�����͵�����������̿���������null
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
