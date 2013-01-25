package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.b2b.entity.AuthorizingGoodsItem;

/**
 * ���¶Կͻ�����Ȩ��Ʒ��Ϣ
 * 
 */
public class UpdateCustomerAuthorizingInfoTask extends SimpleTask {

	/**
	 * �ͻ�ID
	 */
	private GUID customerId;

	/**
	 * ��ϵ��ID
	 */
	private GUID contactPersonId;

	/**
	 * ��Ȩ��Ʒ�б�
	 */
	private AuthorizingGoodsItem[] items;

	/**
	 * ���캯��
	 * 
	 * @param customerId
	 * @param contactPersonId
	 * @param items
	 */
	public UpdateCustomerAuthorizingInfoTask(GUID customerId,
			GUID contactPersonId, AuthorizingGoodsItem[] items) {
		super();
		this.customerId = customerId;
		this.contactPersonId = contactPersonId;
		this.items = items;
	}

	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}

	/**
	 * @return the contactPersonId
	 */
	public GUID getContactPersonId() {
		return contactPersonId;
	}

	/**
	 * @return the items
	 */
	public AuthorizingGoodsItem[] getItems() {
		return items;
	}
}
