package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;

/**
 * 查询商品信息列表
 * 
 */
public class GetGoodsInfoItemListKey {

	/**
	 * 分类ID，null标识所有分类
	 */
	private GUID categoryId;

	/**
	 * 搜索文本
	 */
	private String searchText;

	/**
	 * 是否只查询没有设置价格的商品
	 */
	private boolean nopriceOnly;

	/**
	 * 
	 */
	private GoodsStatus status;

	/**
	 * 构造函数
	 * 
	 * @param categoryId
	 *            分类ID，null标识所有分类
	 * @param searchText
	 *            搜索文本
	 */
	public GetGoodsInfoItemListKey(GUID categoryId, GoodsStatus status,
			String searchText, boolean nopriceOnly) {
		this.categoryId = categoryId;
		this.searchText = searchText;
		this.nopriceOnly = nopriceOnly;
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @return
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @return the nopriceOnly
	 */
	public boolean isNopriceOnly() {
		return nopriceOnly;
	}

	/**
	 * @return the status
	 */
	public GoodsStatus getStatus() {
		return status;
	}

}
