package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询条目的采购原因，返回String
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-9
 */
public class GetPurchasingGoodsCauseKey {
	private GUID id;

	/**
	 * @param id
	 */
	public GetPurchasingGoodsCauseKey(GUID id) {
		super();
		this.id = id;
	}
	public GUID getId() {
		return id;
	}
}
