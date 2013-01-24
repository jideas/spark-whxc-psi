package com.spark.psi.publish.base.b2b.entity;

/**
 * 授权模板信息<br>
 * 查询方法：直接查询AuthorizingTemplateInfo对象
 */
public interface AuthorizingTemplateInfo {

	/**
	 * 获取授权商品列表信息
	 * 
	 * @return
	 */
	public AuthorizingGoodsItem[] getItems();

}
