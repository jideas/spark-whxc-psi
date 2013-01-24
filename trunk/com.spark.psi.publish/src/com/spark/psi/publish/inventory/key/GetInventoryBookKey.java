package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * 查询库存台账Key
 */
public class GetInventoryBookKey extends LimitKey {

	/**
	 * 商品分类ID（空标识所有分类）
	 */
	private GUID goodsCategoryId;

	/**
	 * 仓库ID（空标识所有仓库）
	 */
	private GUID storeId;

	/**
	 * 查询时期范围
	 */
	private QueryTerm queryTerm;

	/**
	 * 构造函数
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInventoryBookKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}



	/**
	 * @return the goodsCategoryId
	 */
	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

}
