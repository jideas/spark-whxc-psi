package com.spark.psi.publish.base.store.entity;

/**
 * 系统仓库摘要数据<br>
 * 查询方法：直接查询StoreSummaryData对象
 * 
 */
public class StoreSummaryData {

	/**
	 * 仓库总数量
	 */
	private int totalCount;

	/**
	 * 已进行初始化商品和物品列表的仓库的数量
	 */
	private int initedCount;

	/**
	 * 已启用的仓库总数量
	 */
	private int usingCount;

	/**
	 * 构造函数
	 * 
	 * @param directSupply
	 * @param totalCount
	 * @param initedCount
	 * @param usingCount
	 */
	public StoreSummaryData( int totalCount,
			int initedCount, int usingCount) {
		super(); 
		this.totalCount = totalCount;
		this.initedCount = initedCount;
		this.usingCount = usingCount;
	} 

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the initedCount
	 */
	public int getInitedCount() {
		return initedCount;
	}

	/**
	 * @return the usingCount
	 */
	public int getUsingCount() {
		return usingCount;
	}

}
