/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.key.dealing
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-30       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.dealing;

import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.key.GetReceiptListKey.SortField;

/**
 * <p>��ѯӦ��/���б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-30
 */

public class ReceiptingListKey {

	private String searchText;
	private int count;
	private int offset;
	/**
	 * �Ƿ��ѯ����
	 */
	private boolean queryTotal;

	/**
	 * ����ʽ
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
