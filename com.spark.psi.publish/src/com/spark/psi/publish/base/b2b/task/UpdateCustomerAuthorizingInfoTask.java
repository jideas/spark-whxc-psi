package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.b2b.entity.AuthorizingGoodsItem;

/**
 * 更新对客户的授权商品信息
 * 
 */
public class UpdateCustomerAuthorizingInfoTask extends SimpleTask {

	/**
	 * 客户ID
	 */
	private GUID customerId;

	/**
	 * 联系人ID
	 */
	private GUID contactPersonId;

	/**
	 * 授权商品列表
	 */
	private AuthorizingGoodsItem[] items;

	/**
	 * 构造函数
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
