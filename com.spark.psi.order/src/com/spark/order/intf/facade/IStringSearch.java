package com.spark.order.intf.facade;

/**
 * <p>字符串搜索工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-24
 */
public interface IStringSearch {
	/**
	 * 根据搜索文本返回Code集合
	 * @param searchText
	 * @return String[]
	 */
	String[] searchValue(String searchText);
	/**
	 * 根据搜索文本返回Code集合（支持Value拼音助记码搜索）
	 * @param searchText
	 * @return String[]
	 */
	String[] searchValue2(String searchText);
	/**
	 * 加入搜索队列
	 * @param code
	 * @param value
	 * @return IStringSearch
	 */
	IStringSearch put(String code, String value);
}
