package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ȡ������Ȩ��Ʒ�Ĺ�����ϵ
 */
public class RemoveRelationshipTask extends SimpleTask {

	/**
	 * ��ӦӦ�̵�ID
	 */
	private GUID authorizingSupplierId;

	/**
	 * ��Ȩ��Ʒ��ID
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
