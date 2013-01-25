package com.spark.psi.publish;

/**
 * 
 * <p>
 * ��ҳ��ѯ��ΧKey������������Ҫ��ҳ��ѯ��Key�ĸ���
 * 
 * @version 2012-2-21
 */
public abstract class LimitKey {

	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	protected int offset;

	/**
	 * ��ѯ����
	 */
	protected int count;

	/**
	 * �Ƿ��ѯ����
	 */
	protected boolean queryTotal;

	/**
	 * ����ʽ
	 */
	protected SortType sortType;

	/**
	 * �����ı�
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
