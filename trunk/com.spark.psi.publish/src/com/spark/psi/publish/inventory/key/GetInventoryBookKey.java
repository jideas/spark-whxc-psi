package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * ��ѯ���̨��Key
 */
public class GetInventoryBookKey extends LimitKey {

	/**
	 * ��Ʒ����ID���ձ�ʶ���з��ࣩ
	 */
	private GUID goodsCategoryId;

	/**
	 * �ֿ�ID���ձ�ʶ���вֿ⣩
	 */
	private GUID storeId;

	/**
	 * ��ѯʱ�ڷ�Χ
	 */
	private QueryTerm queryTerm;

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInventoryBookKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public void setGoodsCategoryId(GUID goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}



	/**
	 * @return the goodsCategoryId
	 */
	public GUID getGoodsCategoryId() {
		return goodsCategoryId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

}
