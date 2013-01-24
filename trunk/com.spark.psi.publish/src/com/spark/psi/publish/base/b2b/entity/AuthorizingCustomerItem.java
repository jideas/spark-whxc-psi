package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 授权客户列表<br>
 * 查询方法：使用GetAuthorizingCustomerListKey查询AuthorizingCustomerList对象
 */
public interface AuthorizingCustomerItem {

	/**
	 * 获取客户ID
	 * 
	 * @return
	 */
	public GUID getCustomerId();

	/**
	 * 获取客户名称
	 * 
	 * @return
	 */
	public String getCustomerName();

	/**
	 * 获取联系人名称
	 * 
	 * @return
	 */
	public String getContactPersonName();

	/**
	 * 获取销售姓名
	 * 
	 * @return
	 */
	public String getSalesmanName();

	/**
	 * 获取授权商品数量
	 * 
	 * @return
	 */
	public int getAuthorizingGoodsItemCount();

	/**
	 * 获取是否可用
	 * 
	 * @return
	 */
	public boolean isEnabled();

}
