package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

/**
 * 对客户的授权信息 <br>
 * 查询方法：根据客户ID查询AuthorizingCustomerInfo对象
 */
public interface CustomerAuthorizingInfo {

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
	public ContactBookInfo getContactInfo();

	/**
	 * 获取销售姓名
	 * 
	 * @return
	 */
	public String getSalesmanName();

	/**
	 * 获取授权商品列表
	 * 
	 * @return
	 */
	public Item[] getItems();

	/**
	 * 授权商品条目
	 */
	public static interface Item {

		/**
		 * 获取商品条目ID
		 */
		public GUID getGoodsItemId();

		/**
		 * 获取商品条目Code
		 */
		public String getGoodsItemCode();

		/**
		 * 获取商品条目名称
		 */
		public String getGoodsItemName();

		/**
		 * 获取商品条目属性
		 */
		public String getGoodsItemProperties();

		/**
		 * 获取商品条目单位
		 */
		public String getGoodsItemUnit();

		/**
		 * 获取授权价格
		 * 
		 * @return
		 */
		public double getAuthorizingPrice();

		/**
		 * 获取说明
		 */
		public String getRemark();

		/**
		 * 获取状态
		 * 
		 * @return
		 */
		public boolean isEnabled();

	}

}
