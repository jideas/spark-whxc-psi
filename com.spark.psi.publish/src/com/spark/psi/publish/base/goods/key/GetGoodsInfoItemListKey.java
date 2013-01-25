package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;

/**
 * ��ѯ��Ʒ��Ϣ�б�
 * 
 */
public class GetGoodsInfoItemListKey {

	/**
	 * ����ID��null��ʶ���з���
	 */
	private GUID categoryId;

	/**
	 * �����ı�
	 */
	private String searchText;

	/**
	 * �Ƿ�ֻ��ѯû�����ü۸����Ʒ
	 */
	private boolean nopriceOnly;

	/**
	 * 
	 */
	private GoodsStatus status;

	/**
	 * ���캯��
	 * 
	 * @param categoryId
	 *            ����ID��null��ʶ���з���
	 * @param searchText
	 *            �����ı�
	 */
	public GetGoodsInfoItemListKey(GUID categoryId, GoodsStatus status,
			String searchText, boolean nopriceOnly) {
		this.categoryId = categoryId;
		this.searchText = searchText;
		this.nopriceOnly = nopriceOnly;
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @return
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @return the nopriceOnly
	 */
	public boolean isNopriceOnly() {
		return nopriceOnly;
	}

	/**
	 * @return the status
	 */
	public GoodsStatus getStatus() {
		return status;
	}

}
