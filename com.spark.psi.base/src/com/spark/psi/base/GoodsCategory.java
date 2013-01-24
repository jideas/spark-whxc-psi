package com.spark.psi.base;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.GoodsCategoryImpl;
import com.spark.psi.base.internal.entity.ILevelTree;

/**
 * 商品分类<br>
 * 查询方法：根据商品分类ID直接查询GoodsCategory对象
 */
public interface GoodsCategory extends ILevelTree {
	
	/**
	 * 根节点
	 */
	public static final GoodsCategory ROOT = new GoodsCategoryImpl(GUID.valueOf("10000000000000000000000000000001"),"全部"); 
	

	/**
	 * @return the id
	 */
	public GUID getId();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * 小数点位数
	 * @return the amountDecimal
	 */
	public int getScale();

	/**
	 * 分类编号
	 * @return the inventoryUpperLimit
	 */
	public String getCategoryNo();
	
	/**
	 * 获得子类型列表
	 * 
	 * @return GoodsCategory[]
	 */
	public GoodsCategory[] getChildren(final Context context);
	
	/**
	 * 获得所有叶子节点类型列表
	 * 
	 * @return GoodsCategory[]
	 */
	public GoodsCategory[] getLeafNodes(final Context context);
	
	/**
	 * 获得父类型
	 * 
	 * @return GoodsCategory
	 */
	public GoodsCategory getParent(final Context context);
	
	/**
	 * 父类Id
	 * 
	 * @return GUID
	 */
	public GUID getParent();
	
	/**
	 * 获得拥有的商品条目列表
	 * 
	 * @return GoodsItem[]
	 */
	public GoodsItem[] getGoodsItems(final Context context);
	
	/**
	 * 是否是叶子节点
	 * 
	 * @return boolean
	 */
	public boolean isLeafNode();
	
	/**
	 * 是否已设置属性
	 * 已设置：true
	 * 
	 * @return boolean
	 */
	public boolean isPropertyFlag();
	
	/**
	 * 获得级次路径
	 * 
	 * @return String
	 */
	public String getPath();

//	/**
//	 * 
//	 * @return
//	 */
//	public PropertyDefine[] getPropertyDefines();
	/**
	 * 创建日期
	 */
	public long getCreateDate();
	
	public GUID getTenantId();
}
