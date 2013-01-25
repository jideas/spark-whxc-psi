package com.spark.order.util.button;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.TypeEnum;

/**
 * <p>操作按钮操作图标对象实例工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public final class OrderButtonFactory {
	private OrderButtonFactory(){
		
	}
	
	/**
	 * 获得实例化对象
	 * @param context
	 * @param billsEnum
	 * @param typeEnum
	 * @return IOrderButton
	 * @throws Throwable 
	 */
	public static IOrderButton getOrderButton(Context context, BillsEnum billsEnum, TypeEnum typeEnum) throws Throwable{
		IOrderButton ob = null;
		switch (billsEnum) {
		case PURCHASE:
			if(TypeEnum.CANCEL == typeEnum){
				ob = new PurchaseReturnImpl(context, null);
			}
			else{
				ob = new PurchaseOrderImpl(context, null);
			}
			break;
		case PURCHASE_CANCEL:
			ob = new PurchaseReturnImpl(context, null);
			break;
		case SALE:
			if(TypeEnum.CANCEL == typeEnum){
				ob = new SalesReturnImpl(context, null);
			}
			else{
				ob = new SalesOrderImpl(context, null);
			}
			break;
		case SALE_CANCEL:
			ob = new SalesReturnImpl(context, null);
			break;
		default:
			throw new Throwable("无该对象图标操作实例，请扩展！");
		}
		return ob;
	}
	
	/**
	 * 获得实例化对象
	 * @param context
	 * @param billsEnum
	 * @param typeEnum
	 * @return IOrderButton
	 * @throws Throwable 
	 */
	public static IOrderButton getOrderButton(Context context, BillsEnum billsEnum, OrderInfo orderInfo) throws Throwable{
		if(null == orderInfo){
			throw new Throwable("当前判断对象传入为空，请检查！");
		}
		TypeEnum typeEnum = TypeEnum.getType(orderInfo.getBillType());
		IOrderButton ob = null;
		switch (billsEnum) {
		case PURCHASE:
			if(TypeEnum.CANCEL == typeEnum){
				ob = new PurchaseReturnImpl(context, orderInfo);
			}
			else{
				ob = new PurchaseOrderImpl(context, orderInfo);
			}
			break;
		case PURCHASE_CANCEL:
			ob = new PurchaseReturnImpl(context, orderInfo);
			break;
		case SALE:
			if(TypeEnum.CANCEL == typeEnum){
				ob = new SalesReturnImpl(context, orderInfo);
			}
			else{
				ob = new SalesOrderImpl(context, orderInfo);
			}
			break;
		case SALE_CANCEL:
			ob = new SalesReturnImpl(context, orderInfo);
			break;
		default:
			throw new Throwable("无该对象图标操作实例，请扩展！");
		}
		return ob;
	}
}
