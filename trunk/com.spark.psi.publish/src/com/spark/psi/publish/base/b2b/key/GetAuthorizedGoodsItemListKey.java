package com.spark.psi.publish.base.b2b.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * 查询被授权的商品列表
 */
public class GetAuthorizedGoodsItemListKey extends LimitKey {

	/**
	 * 对应授权供应商的ID
	 */
	private GUID supplierId;

	/**
	 * 构造函数
	 */
	public GetAuthorizedGoodsItemListKey(GUID supplierId) {
		super(0, 0, false);
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierId
	 */
	public GUID getSupplierId() {
		return supplierId;
	}

}
