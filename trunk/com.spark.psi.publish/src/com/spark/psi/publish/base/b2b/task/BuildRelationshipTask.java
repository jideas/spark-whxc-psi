package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 建立和指定授权商品的关联关系
 */
public class BuildRelationshipTask extends SimpleTask {

	/**
	 * 对应应商的ID
	 */
	private GUID authorizingSupplierId;

	/**
	 * 授权商品的ID
	 */
	private GUID authorizingGoodsItemId;

	/**
	 * 关联的商品条目ID
	 */
	private GUID relatedGoodsItemId;

	/**
	 * 
	 * @param authorizingSupplierId
	 * @param authorizingGoodsItemId
	 * @param relatedGoodsItemId
	 */
	public BuildRelationshipTask(GUID authorizingSupplierId,
			GUID authorizingGoodsItemId, GUID relatedGoodsItemId) {
		super();
		this.authorizingSupplierId = authorizingSupplierId;
		this.authorizingGoodsItemId = authorizingGoodsItemId;
		this.relatedGoodsItemId = relatedGoodsItemId;
	}

	/**
	 * @return the authorizingSupplierId
	 */
	public GUID getAuthorizingSupplierId() {
		return authorizingSupplierId;
	}

	/**
	 * @return the authorizingGoodsItemId
	 */
	public GUID getAuthorizingGoodsItemId() {
		return authorizingGoodsItemId;
	}

	/**
	 * @return the relatedGoodsItemId
	 */
	public GUID getRelatedGoodsItemId() {
		return relatedGoodsItemId;
	}

}
