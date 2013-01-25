package com.spark.order.util2;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.OrderException;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.PromotionAction;

/**
 * <p>����Util2ʵ��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-3
 */
public final class OrderUtilFactory2 {
	private OrderUtilFactory2(){
	}
	@SuppressWarnings("serial")
	public static class OrderUtilFactoryException extends OrderException{
		public OrderUtilFactoryException(){
			super("����ҵ��Util2ʵ����ʱδ�ҵ����ʵ���������ʵ����ʱ���Ͳ�ƥ��");
		}
	}
	
	/**
	 * ��ȡ�ɲ�������ʵ����
	 * @param context
	 * @param orderEnum
	 * @return
	 * @throws OrderUtilFactoryException IOrderButton2<? extends Object>
	 */
	@Deprecated
	public static IOrderButton2<? extends Object> newOrderButton2(Context context, OrderEnum orderEnum) throws OrderUtilFactoryException{
		switch(orderEnum){
		case Purchase:
			return new OrderButton_PurchaseOrder2(context);
		case Purchase_Return:
			return new OrderButton_PurchaseReturn2(context);
		case Sales:
			return new OrderButton_SalesOrder2(context);
		case Sales_Return:
			return new OrderButton_SalesReturn2(context);
		case Sales_Promotion:
			return new OrderButton_Promotion2(context);
		case Retail_Return:
		case Retail:
			return new OrderButton_Retail2(context);
		default:
			throw new OrderUtilFactoryException();
		}
	}

	/**
	 * ��ȡ�ɲ�������ʵ����
	 * @param context
	 * @param orderEnum
	 * @return
	 * @throws OrderUtilFactoryException IOrderButton2<? extends Object>
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> IOrderButton2<T> newOrderButton2(Class<T> c, Context context, OrderEnum orderEnum) throws OrderUtilFactoryException{
		switch(orderEnum){
		case Purchase:
			if(c.getCanonicalName().equals(OrderAction.class.getCanonicalName())){
				return (IOrderButton2<T>) new OrderButton_PurchaseOrder2(context);
			}
		case Purchase_Return:
			if(c.getCanonicalName().equals(OrderAction.class.getCanonicalName())){
				return (IOrderButton2<T>) new OrderButton_PurchaseReturn2(context);
			}
		case Sales:
			if(c.getCanonicalName().equals(OrderAction.class.getCanonicalName())){
				return (IOrderButton2<T>) new OrderButton_SalesOrder2(context);
			}
		case Sales_Return:
			if(c.getCanonicalName().equals(OrderAction.class.getCanonicalName())){
				return (IOrderButton2<T>) new OrderButton_SalesReturn2(context);
			}
		case Sales_Promotion:
			if(c.getCanonicalName().equals(PromotionAction.class.getCanonicalName())){
				return (IOrderButton2<T>) new OrderButton_Promotion2(context);
			}
		case Retail_Return:
		case Retail:
			return (IOrderButton2<T>) new OrderButton_Retail2(context);
		default:
			throw new OrderUtilFactoryException();
		}
	}
	
	/**
	 * ���̶���ʵ����
	 * @param <T>
	 * @param c
	 * @param context
	 * @param orderEnum
	 * @return
	 * @throws OrderUtilFactoryException IProcessManage2<T>
	 */
	public static <T extends Enum<T>> IProcessManage2<T> newProcessManage2(Class<T> c, Context context, OrderEnum orderEnum) throws OrderUtilFactoryException{
		return null;
	}
	
	/**
	 * ��ȡִ�в�������ʵ����
	 * @param <T>
	 * @param c
	 * @param context
	 * @param orderEnum
	 * @return
	 * @throws OrderUtilFactoryException IOrderAction2<? extends Enum<?>,T>
	 */
	public static <T extends Enum<T>> IOrderAction2<? extends Enum<?>, T> newOrderAction2(Class<T> c, Context context, OrderEnum orderEnum) throws OrderUtilFactoryException{
		return null;
	}
	
	//====================================����ʹ�÷���==================================
}
