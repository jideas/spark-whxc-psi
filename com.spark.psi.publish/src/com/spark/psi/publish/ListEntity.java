package com.spark.psi.publish;

import java.util.List;

/**
 * List实体，用来存储返回List和总条数类型的数据
 * 
 * @version 2012-2-21
 */
public class ListEntity<Item> {

	/**
	 * 结果列表
	 */
	private List<Item> itemList;

	/**
	 * 总数（如果为不查询总数，则返回0）
	 */
	private long totalCount;

	/**
	 * 构造函数
	 * 
	 * @param dataList
	 * @param totalCount
	 */
	public ListEntity(List<Item> dataList, long totalCount) {
		this.itemList = dataList;
		this.totalCount = totalCount;
	}

	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

}
