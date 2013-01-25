package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ȩ�ͻ��б�<br>
 * ��ѯ������ʹ��GetAuthorizingCustomerListKey��ѯAuthorizingCustomerList����
 */
public interface AuthorizingCustomerItem {

	/**
	 * ��ȡ�ͻ�ID
	 * 
	 * @return
	 */
	public GUID getCustomerId();

	/**
	 * ��ȡ�ͻ�����
	 * 
	 * @return
	 */
	public String getCustomerName();

	/**
	 * ��ȡ��ϵ������
	 * 
	 * @return
	 */
	public String getContactPersonName();

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public String getSalesmanName();

	/**
	 * ��ȡ��Ȩ��Ʒ����
	 * 
	 * @return
	 */
	public int getAuthorizingGoodsItemCount();

	/**
	 * ��ȡ�Ƿ����
	 * 
	 * @return
	 */
	public boolean isEnabled();

}
