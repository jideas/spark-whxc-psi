package com.spark.psi.publish.base.goods.entity;

/**
 * ��Ʒ��ϸ��Ŀ���� <BR>
 * ��ѯ������<br>
 * (1)������ĿID��ѯ������GoodsItem����
 */
public class GoodsItemInfo {

	/**
	 * ��Ʒ������Ϣ
	 */
	protected GoodsInfo baseInfo;

	/**
	 * ��Ŀ����
	 */
	protected GoodsItemData itemData;

	/**
	 * ��ȡ��Ʒ������Ϣ
	 * 
	 * @return
	 */
	public GoodsInfo getBaseInfo() {
		return baseInfo;
	}

	/**
	 * ��ȡ��Ʒ��Ŀ���ݶ���
	 * 
	 * @return
	 */
	public GoodsItemData getItemData() {
		return itemData;
	}

	
}
