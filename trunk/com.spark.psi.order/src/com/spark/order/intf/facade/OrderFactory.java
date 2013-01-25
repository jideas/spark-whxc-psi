package com.spark.order.intf.facade;

import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;


/**
 * <p>订单工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-24
 */
public final class OrderFactory {
	private OrderFactory(){
	}
	
	private final static IStringSearch salesType, purchaseType, salesstatus, purchasestatus;
	static{
		//销售类型
		salesType = new StringSearchImpl();
		for(TypeEnum type : TypeEnum.values()){
			salesType.put(type.getKey(), type.getName(OrderEnum.Sales));
		}
		//采购类型
		purchaseType = new StringSearchImpl();
		for(TypeEnum type : TypeEnum.values()){
			purchaseType.put(type.getKey(), type.getName(OrderEnum.Purchase));
		}
		//销售状态
		salesstatus = new StringSearchImpl();
		for(StatusEnum status : StatusEnum.values()){
			salesstatus.put(status.getKey(), status.getName(OrderEnum.Sales));
		}
		//采购状态
		purchasestatus = new StringSearchImpl();
		for(StatusEnum status : StatusEnum.values()){
			purchasestatus.put(status.getKey(), status.getName(OrderEnum.Purchase));
		}
	}
	/**
	 * 获得类型搜索工具类
	 * @param orderEnum
	 * @return IStringSearch
	 * @throws OrderException 
	 */
	public final static IStringSearch searchSalesType(OrderEnum orderEnum) throws OrderException {
		switch (orderEnum) {
		case Sales:
			return salesType;
		case Sales_Return:
			return salesType;
		case Purchase:
			return purchaseType;
		case Purchase_Return:
			return purchaseType;
		default:
			throw new OrderException("当前类型暂无搜索工具支持。");
		}
	}
	/**
	 * 获得状态搜索工具类
	 * @param orderEnum
	 * @return IStringSearch
	 * @throws OrderException 
	 */
	public final static IStringSearch searchSalesstatus(OrderEnum orderEnum) throws OrderException {
		switch (orderEnum) {
		case Sales:
			return salesstatus;
		case Sales_Return:
			return salesstatus;
		case Purchase:
			return purchasestatus;
		case Purchase_Return:
			return purchasestatus;
		default:
			throw new OrderException("当前类型暂无搜索工具支持。");
		}
	}
}
