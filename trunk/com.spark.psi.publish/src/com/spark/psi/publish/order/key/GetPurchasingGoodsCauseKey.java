package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ��Ŀ�Ĳɹ�ԭ�򣬷���String
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
