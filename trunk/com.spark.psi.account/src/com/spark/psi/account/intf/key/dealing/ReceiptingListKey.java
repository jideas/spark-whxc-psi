/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.intf.key.dealing
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.dealing;

import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.key.GetReceiptListKey.SortField;

/**
 * <p>查询应收/付列表</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-30
 */

public class ReceiptingListKey {

	private String searchText;
	private int count;
	private int offset;
	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	/**
	 * 排序方式
	 */
	private SortType sortType;
	
	private SortField sortField;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
}
