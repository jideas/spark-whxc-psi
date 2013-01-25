package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;

/**
 * 材料明细条目数据对象<br>
 * 查询方法：无
 */
public interface MaterialsItemData {

	/**
	 * id
	 * @return
	 */
	public GUID getId();
	
	/**
	 * 
	 * @return 
	 */
	public String getMaterialNo();
	
	/**
	 * 规格
	 * @return
	 */
	public String getMaterialSpec();

	/**
	 * 单价
	 * @return
	 */

	public double getSalePrice();
	
	/**
	 * 
	 * @return 损耗率
	 */
	public double getLossRate();

	/**
	 * 最近采购单价
	 * @return
	 */
	public double getRecentPurchasePrice();

	/**
	 * 平均库存成本
	 * @return
	 */
	public double getAvgBuyPrice();
	
	/**
	 * 计划价格
	 * @return
	 */
	public double getPlanPrice();

	/**
	 * 标准价格
	 * @return
	 */
	public double getStandardPrice();
	/**
	 * 属性
	 * @return
	 */
	public String[] getPropertyValues();

	/**
	 * 不带单位的属性值
	 * @return
	 */
	public String getPropertiesWithoutUnit();

	/**
	 * 带单位的属性值
	 * @return
	 */
	public String getPropertiesWithUnit();

	/**
	 * 库存上限
	 * @return
	 */
	public double getInventoryUpperLimit();
	
	/**
	 * 总库存上限数量
	 * 
	 * @return double
	 */
	public double getTotalStoreUpperLimit();
	/**
	 * 总库存下限数量
	 * 
	 * @return double
	 */
	public double getTotalStoreLowerLimit();

	/**
	 * 状态
	 * @return
	 */
	public MaterialsStatus getStatus();
	/**
	 * 是否已关联
	 * @return
	 */
	public boolean isRefFlag();
	
	/**
	 * 商品数量小数位数
	 * 
	 * @return int
	 */
	public int getScale();
	
	/**
	 * 商品单位
	 * 
	 * @return String
	 */
	public String getUnit();
	
	/**
	 * 库存策略
	 */
	public String getInventoryStrategy(); 
}
