package com.spark.order.intf.type;

import java.util.List;

import com.spark.order.intf.OrderEnum;

/**
 * <p>
 * 订单类型
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-10-26
 */
public enum TypeEnum {
	/**
	 * 普通订单
	 */
	PLAIN("01", "普通订单"),
	/**
	 * 网上订单
	 */
	@Deprecated
	ON_LINE("02", "网上订单"), 
	/**
	 * 网上订单(直供)
	 */
	@Deprecated
	ON_LINE_DIRECT("04", "网上订单(直供)"),
	/**
	 * "采购退货", "销售退货"
	 */
	CANCEL("05", "采购退货", "销售退货"),
	/**
	 * 零星采购
	 */
	BUY_SPORADIC("06", "零星采购"),
	/**
	 * 商品零售
	 */
	Retail("07", "商品零售"),
	/**
	 * 零售退货
	 */
	Retail_Return("08", "零售退货");
	private String key;
	private String[] name;

	private TypeEnum(String key, String... names) {
		this.key = key;
		this.name = names;
		EnumUtil.tyepEnummap.put(key, this);
		for(OrderEnum orderEnum : OrderEnum.values()){
			EnumUtil.addOrderTypeSearch(orderEnum, key, getName(orderEnum));
		}
	}
	/**
	 * 搜索用，返回code集合
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchType(OrderEnum orderEnum, String searchText){
		return EnumUtil.getOrderTypeSearch(orderEnum, searchText);
	}

	/**
	 * 获取标识名称，退货情况不可用
	 * 
	 * @return String[]
	 */
	public String getName() {
		return name[0];
	}

	/**
	 * 获取类型key
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 获取标识名称
	 * 
	 * @return String[]
	 */
	@Deprecated
	public String getName(BillsEnum ord) {
		if (BillsEnum.SALE.equals(ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}
	
	/**
	 * 获取标识名称
	 * 
	 * @return String[]
	 */
	public String getName(OrderEnum ord) {
		if ((OrderEnum.Sales == ord || OrderEnum.Sales_Return == ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}

	/**
	 * 根据key获得枚举
	 * 
	 * @param key
	 * @return TypeEnum
	 */
	public static TypeEnum getType(String key) {
		return EnumUtil.tyepEnummap.get(key);
	}

	/**
	 * 判断当前枚举是否是指定枚举中一个，是的话返回true
	 * 
	 * @param enums
	 * @return boolean
	 */
	public boolean isInType(TypeEnum... enums) {
		for (TypeEnum type : enums) {
			if (this == type) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断当前枚举是否是指定枚举中一个，是的话返回true
	 * 
	 * @param enums
	 * @return boolean
	 */
	public boolean isInType(String... keys) {
		for (String type : keys) {
			if (isThis(type)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断指定key是否当前枚举
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key){
		return this.key.equals(key);
	}
}
