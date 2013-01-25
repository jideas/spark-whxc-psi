package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ͻ���Ȩ����Ʒ�б�<br>
 * ��ѯ�������޵�����ѯ
 */
public interface AuthorizingGoodsItem {

	/**
	 * ��ȡ��Ʒ��ĿID
	 */
	public GUID getGoodsItemId();

	/**
	 * ��ȡ��Ȩ�۸�
	 * 
	 * @return
	 */
	public double getAuthorizingPrice();

	/**
	 * ��ȡ˵��
	 */
	public String getRemark();

	/**
	 * ��ȡ״̬
	 * 
	 * @return
	 */
	public boolean isEnabled();
}
