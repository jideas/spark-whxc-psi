package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������ָ����Ȩ��Ʒ�Ĺ�����ϵ
 */
public class BuildRelationshipTask extends SimpleTask {

	/**
	 * ��ӦӦ�̵�ID
	 */
	private GUID authorizingSupplierId;

	/**
	 * ��Ȩ��Ʒ��ID
	 */
	private GUID authorizingGoodsItemId;

	/**
	 * ��������Ʒ��ĿID
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
