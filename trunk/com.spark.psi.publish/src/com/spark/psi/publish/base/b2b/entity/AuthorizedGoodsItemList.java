package com.spark.psi.publish.base.b2b.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * 被授权的商品查询列表返回结果<br>
 * 查询方法：使用GetAuthorizedGoodsItemListKey查询AuthorizedGoodsItemList
 */
public class AuthorizedGoodsItemList extends
		ListEntity<AuthorizedGoodsItemItem> {

	/**
	 * 已建立关联的数量
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
