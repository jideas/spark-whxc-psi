package com.spark.psi.publish;

/**
 * 
 * <p>
 * 分页查询范围Key，用于所有需要分页查询的Key的父类
 * 
 * @version 2012-2-21
 */
public abstract class LimitKey {

	/**
	 * 查询偏移（从0开始）
	 */
	protected int offset;

	/**
	 * 查询数量
	 */
	protected int count;

	/**
	 * 是否查询总数
	 */
	protected boolean queryTotal;

	/**
	 * 排序方式
	 */
	protected SortType sortType;

	/**
	 * 搜索文本
	 */
	protected String searchText;

	/**
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public LimitKey(int offset, int count, boolean queryTotal) {
		this.offset = offset;
		this.count = count;
		this.queryTotal = queryTotal;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the queryTotal
	 */
	public boolean isQueryTotal() {
		return queryTotal;
	}

	/**
	 * @return the sortType
	 */
	public SortType getSortType() {
		return sortType;
	}

	/**
	 * @param sortType
	 *            the sortType to set
	 */
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText
	 *            the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
