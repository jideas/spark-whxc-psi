package com.spark.psi.publish.base.b2b.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * ����Ȩ����Ʒ��ѯ�б��ؽ��<br>
 * ��ѯ������ʹ��GetAuthorizedGoodsItemListKey��ѯAuthorizedGoodsItemList
 */
public class AuthorizedGoodsItemList extends
		ListEntity<AuthorizedGoodsItemItem> {

	/**
	 * �ѽ�������������
	 */
	private int relatedCount;

	/**
	 * 
	 * @param dataList
	 * @param totalCount
	 * @param relatedCount
	 */
	public AuthorizedGoodsItemList(List<AuthorizedGoodsItemItem> dataList,
			int totalCount, int relatedCount) {
		super(dataList, totalCount);
		this.relatedCount = relatedCount;
	}

	/**
	 * @return the relatedCount
	 */
	public int getRelatedCount() {
		return relatedCount;
	}

}
