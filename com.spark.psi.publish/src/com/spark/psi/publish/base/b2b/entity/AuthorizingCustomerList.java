package com.spark.psi.publish.base.b2b.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

/**
 * 授权客户查询列表返回结果
 */
public class AuthorizingCustomerList extends
		ListEntity<AuthorizingCustomerItem> {

	/**
	 * 租户可授权的客户数量
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
