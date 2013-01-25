package com.spark.order.intf.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.order.intf.OrderEnum;
import com.spark.order.util.StringSearchUtil;

/**
 * <p>订单枚举类型工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-21
 */
class EnumUtil {
	private EnumUtil(){
		
	}
	/**
	 * 状态枚举
	 */
	static final Map<String, StatusEnum> StatusEnumMap = new HashMap<String, StatusEnum>();
	/**
	 * 类型枚举
	 */
	final static Map<String, TypeEnum> tyepEnummap = new HashMap<String, TypeEnum>();
	private static Map<OrderEnum, StringSearchUtil> orderstatusSearch = new HashMap<OrderEnum, StringSearchUtil>();
	/**
	 * 订单状态搜索
	 */
	public static void addOrderStatusSearch(OrderEnum orderEnum, String key, String name){
		if(null == orderstatusSearch.get(orderEnum)){
			orderstatusSearch.put(orderEnum, new StringSearchUtil());
		}
		orderstatusSearch.get(orderEnum).put(key, name);
	}
	/**
	 * 订单状态搜索(包括助记码)
	 */
	public static List<String> getOrderStatusSearch(OrderEnum orderEnum, String search){
		return orderstatusSearch.get(orderEnum).getKeys2(search);
	}
	private static Map<OrderEnum, StringSearchUtil> orderTypeSearch = new HashMap<OrderEnum, StringSearchUtil>();
	/**
	 * 订单类型搜索
	 */
	public static void addOrderTypeSearch(OrderEnum orderEnum, String key, String name){
		if(null == orderTypeSearch.get(orderEnum)){
			orderTypeSearch.put(orderEnum, new StringSearchUtil());
		}
		orderTypeSearch.get(orderEnum).put(key, name);
	}
	/**
	 * 订单类型搜索(包括助记码)
	 */
	public static List<String> getOrderTypeSearch(OrderEnum orderEnum, String search){
		return orderTypeSearch.get(orderEnum).getKeys2(search);
	}
}
