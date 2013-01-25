package com.spark.psi.publish.base.b2b.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * ��ѯ����Ȩ����Ʒ�б�
 */
public class GetAuthorizedGoodsItemListKey extends LimitKey {

	/**
	 * ��Ӧ��Ȩ��Ӧ�̵�ID
	 */
	private GUID supplierId;

	/**
	 * ���캯��
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
