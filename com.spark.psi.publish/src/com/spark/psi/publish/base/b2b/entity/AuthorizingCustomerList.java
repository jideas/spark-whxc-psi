package com.spark.psi.publish.base.b2b.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * ��Ȩ�ͻ���ѯ�б��ؽ��
 */
public class AuthorizingCustomerList extends
		ListEntity<AuthorizingCustomerItem> {

	/**
	 * �⻧����Ȩ�Ŀͻ�����
	 */
	private int availableCount;

	/**
	 * 
	 * @param dataList
	 * @param totalCount
	 * @param authorizableCount
	 */
	public AuthorizingCustomerList(List<AuthorizingCustomerItem> dataList,
			int totalCount, int availableCount) {
		super(dataList, totalCount);
		this.availableCount = availableCount;
	}

	/**
	 * @return the availableCount
	 */
	public int getAvailableCount() {
		return availableCount;
	}

}
