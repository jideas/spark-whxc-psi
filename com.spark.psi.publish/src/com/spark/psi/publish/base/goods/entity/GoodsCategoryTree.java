package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 商品分类树<br>
 * 查询说明：<br>
 * (1)直接查询，无键值
 */
public interface GoodsCategoryTree {
	/**
	 * 获取设置了属性的分类数量
	 * 
	 * @return
	 */
	public int getPropertiedCount();

	/**
	 * 获取所有分类数量
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public CategoryNode[] getRootNodes();

	/**
	 * 通过分类ID 获得节点
	 * 
	 * @param id
	 * @return CategoryNode
	 */
	public CategoryNode getNodeById(GUID id);

	/**
	 * 分类节点
	 */
	public interface CategoryNode {

		/**
		 * 获取分类ID
		 * 
		 * @return
		 */
		public GUID getId();

		/**
		 * 获取分类名称
		 * 
		 * @return
		 */
		public String getName();
		
		/**
		 * 
		 * @return
		 */
		public String getCategoryNo();

		/**
		 * 获取父节点
		 * 
		 * @return
		 */
		public CategoryNode getParent();

		/**
		 * 获取子分类节点
		 * 
		 * @return
		 */
		public CategoryNode[] getChildren();
		
		/**
		 * 是否已设置属性
		 * 已设置：true
		 * 
		 * @return boolean
		 */
		public boolean isSetPropertyFlag();
	}
}
