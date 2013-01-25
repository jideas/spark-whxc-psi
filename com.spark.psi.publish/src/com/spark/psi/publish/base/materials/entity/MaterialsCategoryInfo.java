package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 材料分类详细数据，包括属性信息 <br>
 * 查询说明：<br>
 * (1)根据分类ID查询MaterialsCategoryInfo对象
 */
public interface MaterialsCategoryInfo {


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
	 * 获取分类编号
	 * @return
	 */
	public String getCategoryNo();

	/**
	 * 获取数量小数位
	 * 
	 * @return
	 */
	public int getScale();

	/**
	 * 获取库存金额上限
	 * 
	 * @return
	 */
//	public double getInventoryUpperLimit();

	/**
	 * 获取属性定义列表
	 * 
	 * @return
	 */
	public MaterialsPropertyDefine[] getPropertyDefines();

}
