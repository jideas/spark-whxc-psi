package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 取消和授权商品的关联关系
 */
public class RemoveRelationshipTask extends SimpleTask {

	/**
	 * 对应应商的ID
	 */
	private GUID authorizingSupplierId;

	/**
	 * 授权商品的ID
	 */
	private GUID authorizingGoodsItemId;

	/**
	 * 
	 * @param authorizingSupplierId
	 * @param authorizingGoodsItemId
	 */
	public RemoveRelationshipTask(GUID authorizingSupplierId,
			GUID authorizingGoodsItemId) {
		super();
		this.authorizingSupplierId = authorizingSupplierId;
		this.authorizingGoodsItemId = authorizingGoodsItemId;
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

}
